<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<div>欢迎，mvc ${name.name}</div>
<button id="firstOne"></button>
</body>
</html>
<script src="../../scriptmodule/jquery-1.7.1.min.js"></script>
<script type="application/javascript">
    $(function () {
    /*   var ajaxUtil =function(url,method){   //todo
           $.ajax({
               url : url,
               type : method,
               async : async,
               contentType : "application/json",
               cache : false,
               dataType : type,
               //jsonpCallback : "result",
               data : data,
               timeout:20000,
               beforeSend:function(XMLHttpRequest) {//发起请求前执行方法
                   if(typeof sfn == 'function') {
                       sfn(XMLHttpRequest);
                   }
               },
               success : function(retdata) {
                   fn(retdata);
               },
               error : function(XMLHttpRequestP, textStatusP, errorThrownP) {
                   var data = {
                       result : '1',
                       retCode : '0001',
                       retMsg : "系统开小差了哦~请您稍后再试吧！",
                       XMLHttpRequestP:XMLHttpRequestP
                   };
                   //add by wangtlc REST权限校验不通过
                   //1、考虑到可能有浏览器不兼容XMLHttpRequestP.responseText故暂时使用403判断
                   //2、这里没考虑多次弹框的情况，如果没有权限都需要配置上才能正常使用。
                   if(XMLHttpRequestP.status==403){
                       var msgtotal="您没有访问当前请求的权限请联系管理员！";
                       if(typeof XMLHttpRequestP.responseText != 'undefined'){
                           if(XMLHttpRequestP.responseText.indexOf("-1101")!=-1){
                               msgtotal='您没有访问当前请求的权限，请联系管理员配置操作员对应菜单权限！';
                               console.log('您没有访问当前请求的权限，请联系管理员配置操作员对应菜单权限！'+uri.toString());
                           }else if(XMLHttpRequestP.responseText.indexOf("-1102")!=-1){
                               msgtotal='您没有访问当前请求的权限，请联系管理员配置OPCODE对应REST权限！请求地址为：'+uri.toString();
                           }else if(XMLHttpRequestP.responseText.indexOf("-1100")!=-1){
                               msgtotal='会话已失效，请重新登录！';
                           }else if(XMLHttpRequestP.responseText.indexOf("-1201")!=-1){
                               msgtotal='您没有访问当前请求的权限，请先通过金库认证！请求地址为：'+uri.toString();
                           }
                       }
                       data.retMsg=msgtotal;
                   }
                   fn(data);
               },
               complete : function(xhr,status){ //请求完成后最终执行参数
                   if(status=='timeout'){
                       ajaxFunc.abort();
                       if (typeof cfn == 'function') {
                           cfn(status);
                       }
                   }
               }
           });
       }*/


        console.log("123");
       $("#firstOne").on("click",function(){
           var paramIn= {
                       "name":"123",
                       "age":"567"
                        };
           var dataIn =JSON.stringify(paramIn)

           $.ajax({
               url: 'http://localhost:8080/v1/mvc/ajaxhello/123?abc=123&bcd=2',
               type: 'post',
               async: true,
               contentType: "application/json;charset=utf-8",
               data : dataIn,
               cache: false,
               dataType: 'json',
               success: function (data) {
                   console.log(data.msg);
               }

           })
       });
    })
</script>