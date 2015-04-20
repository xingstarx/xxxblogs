var curPath = window.document.location.href;
var pathName = window.document.location.pathname;
var basePath;
var basepathWithOutSplit;//结尾没有／线
var projectName;
//如果项目名有/xxxblogs--，兼容调试
if( pathName.substring(0,pathName.substr(1).indexOf('/')+1) == '/xxxblogs'){
	projectName='/xxxblogs';
	basePath = curPath.substring(0,curPath.indexOf(pathName)) + pathName.substring(0,pathName.substr(1).indexOf('/')+1) + "/";
	basepathWithOutSplit=curPath.substring(0,curPath.indexOf(pathName)) + pathName.substring(0,pathName.substr(1).indexOf('/')+1) ;
}
//on server
else{
	projectName='';
	basePath = curPath.substring(0,curPath.indexOf(pathName)) + "/";
	basepathWithOutSplit = curPath.substring(0,curPath.indexOf(pathName)) ;
}
//alert(basePath);
