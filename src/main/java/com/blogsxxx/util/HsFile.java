package com.blogsxxx.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class HsFile {

	private String coding;
	private String path = "";
	private String fileSystem = "windows";

	// 默认参数
	public static final String WINDOWS_SYSTEM = "windows";
	public static final String LINUX_SYSTEM = "linux";

	public HsFile(String path) {
		this.path = path;
	}

	public HsFile(String path, String fileSystem) {
		if (fileSystem.equals("windows")) {
			path = path.replace("/", "\\");
		} else if (fileSystem.equals("linux")) {
			path = path.replace("\\", "/");
		}

		this.path = path;
		this.fileSystem = fileSystem;
	}

	public static void main(String[] args) {
		// File file=new File("src/main/java/com/blogsxxx/util/HsFile.java");
		// System.out.println(file.exists());
		// System.out.println(file.length());
		//
		// URL url=
		// HsFile.class.getClassLoader().getResource("src/main/java/com/blogsxxx/util/HsFile.java");
		//
		// System.out.println(HsFile.class.getClassLoader().getResourceAsStream("com/blogsxxx/util/HsFile.java"));
		// System.out.println(url+"**");
		// /Users/xiongxingxing/Documents/uploads/1428733967505075829.jpg
		// http://localhost:8080/xxxblogs/ueditor/jsp/upload/image/20150416/1429193129926088002.jpg

		String str = "/Users/xiongxingxing/Documents/uploads/upload/image/20150416/";
		File file = new File(str);
		if (!file.exists()) {
			file.mkdirs();
			System.out.println("***********");
		}

	}

	// 创建目录
	public void createlist() {
		File f;
		f = new File(path);
		if (!f.exists()) {
			f.mkdirs();
		}
	}

	// 创建文件
	public void createfile() {
		try {
			File f;
			int index = path.length();
			if (fileSystem.equals("windows")) {
				index = path.lastIndexOf("\\");
			} else if (fileSystem.equals("linux")) {
				index = path.lastIndexOf("/");
			}

			String list = path.substring(0, index);

			f = new File(list);

			// 如果目录不存在，先创建目录
			if (!f.exists()) {
				f.mkdirs();
			}
			// 然后创建文件
			f = new File(path);
			f.createNewFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 获取目录中的文件和目录名
	public String[] getListOfFileName() {
		File f = new File(path);
		return f.list();
	}

	// 删除文件
	public void deletefile() {
		File f = new File(path);
		f.delete();
	}

	// 删除多个文件
	public void deletefiles() {
		File f = new File(path);
		String[] files = f.list();
		for (int a = 0; a < files.length; a++) {
			File file = new File(path + "\\" + files[a]);
			file.delete();
		}
	}

	// 验证文件是否存在
	public boolean exist() {
		File f = new File(path);
		return f.exists();
	}

	// 修改文件名
	public boolean renameTo(String newFileName) {
		File oldFile = new File(path);
		File newFile = null;
		if (fileSystem.equals(HsFile.WINDOWS_SYSTEM)) {
			newFile = new File(path.substring(0, path.lastIndexOf("\\") + 1)
					+ newFileName);
		} else if (fileSystem.equals(HsFile.LINUX_SYSTEM)) {
			newFile = new File(path.substring(0, path.lastIndexOf("/") + 1)
					+ newFileName);
		}
		return oldFile.renameTo(newFile);
	}

	// 写入文件（缓冲区）
	public void write(String content, boolean append) {
		FileOutputStream fos = null;
		BufferedOutputStream out = null;

		try {
			File f = new File(path);

			fos = new FileOutputStream(f, append);
			out = new BufferedOutputStream(fos);

			if (this.getCoding() == null || this.getCoding().equals("")) {
				out.write(content.getBytes("utf-8"));
			} else {
				out.write(content.getBytes(this.getCoding()));
			}
			out.flush();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭io流
			try {
				if (fos != null) {
					fos.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 读取文件
	public String read() {
		return this.read("GBK");
	}

	// 读取文件（缓冲区）
	public String read(String decode) {
		String content = "";
		FileInputStream fis = null;
		BufferedInputStream in = null;

		try {
			File f = new File(path);

			fis = new FileInputStream(f);
			in = new BufferedInputStream(fis);

			byte[] b = new byte[in.available()];
			in.read(b);
			if (this.getCoding() == null || this.getCoding().equals("")) {
				if (decode == null || decode.equals("")) {
					content = new String(b, "utf-8");
				} else {
					content = new String(b, decode);
				}
			} else {
				content = new String(b, this.getCoding());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭io流
				if (fis != null) {
					fis.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return content;

	}

	/**
	 * @author xiongxingxing
	 * @desc  复制文件
	 * @param oldfile
	 * @param newfile
	 */
	public static void copyfile(String oldfile, String newfile) {
		FileInputStream fis = null;
		FileOutputStream fos = null;

		BufferedInputStream in = null;
		BufferedOutputStream out = null;

		try {
			// file f1 = new file(oldfile);
			// f1.createfile();
			File f11 = new File(oldfile);
			fis = new FileInputStream(f11);
			in = new BufferedInputStream(fis);

			// file f2 = new file(newfile);
			// f2.createfile();
			
			createlist(newfile);
			
			File f22 = new File(newfile);
			fos = new FileOutputStream(f22);
			out = new BufferedOutputStream(fos);

			byte[] data = new byte[1024];
			int datalength;
			while ((datalength = in.read(data)) != -1) {
				out.write(data, 0, datalength);
				out.flush();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭io流
				if (fis != null)
					fis.close();
				if (in != null)
					in.close();
				if (fos != null)
					fos.close();
				if (out != null)
					out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 复制目录
	public void copylist(String oldpath, String newpath) {
		File f1 = new File(oldpath);
		String[] listpath = f1.list();
		File f2 = new File(newpath);
		f2.mkdir();
		for (int a = 0; a < listpath.length; a++) {
			if (listpath[a].contains(".")) {
				this.copyfile(oldpath + listpath[a], newpath + listpath[a]);
			} else {
				File f22 = new File(newpath + listpath[a] + "\\");
				f22.mkdir();
				this.copylist(oldpath + listpath[a] + "\\", newpath
						+ listpath[a] + "\\");
			}
		}
	}

	// 打开文件
	public boolean open(String file) {
		boolean back = false;
		try {
			Runtime.getRuntime().exec("cmd /c start " + file);
			back = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return back;
	}

	// 打开exe文件
	public boolean openfile(String file) {
		boolean back = false;
		try {
			Runtime.getRuntime().exec(file);
			back = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return back;
	}

	public String getCoding() {
		return coding;
	}

	public void setCoding(String coding) {
		this.coding = coding;
	}

	public String getFileSystem() {
		return fileSystem;
	}

	public void setFileSystem(String fileSystem) {
		this.fileSystem = fileSystem;
	}
/**
 * @desc 创建目录
 * @author xiongxingxing
 */
	// 创建目录
	public static void createlist(String fileName) {
		String path=fileName.substring(0,fileName.lastIndexOf('/'));
		File f;
		f = new File(path);
		if (!f.exists()) {
			f.mkdirs();
		}
	}
}
