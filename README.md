##这是我的个人博客项目--xxxblogs
***
###技术上采用的是springmvc,spring,mybatis。项目托管在bitbucket上，通过maven构建处理项目之间的依赖。数据库是mysql，项目部署在阿里云上，阿里云系统为centos
***
###版本功能介绍：###
* v1.0实现了最基本的写博客功能，博客内容展示，写博客通过的是百度ueditor，详细内容如下，项目第一个版本1.0就是这些了，现在注释掉了同步任务，等会从本地启动，同步阿里云上面的数据库。
* v1.1修改log4j配置文件，其实就是日志记录的文件路径所在..本地开发路径是Mac的，线上的是centos的
* v1.2修改图片存放问题，修改博客内容部分显示处理逻辑
* v1.3打印上传图片的明细信息
* v1.4修改得到basePath路径，加上80端口的判断
* v1.5实现了博客更新功能，博客日志记录，后台管理功能，同步个人csdn博客内容，详细如下，添加编辑文章，存储过程的日志记录，html工具类的方法添加，部分bug修改。（项目二期结束）