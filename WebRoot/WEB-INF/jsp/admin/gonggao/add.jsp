<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ck" uri="http://ckeditor.com"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>公告板</title>
<link href="${pageContext.request.contextPath}/css/slider.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/common.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/index.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/slider2.css" rel="stylesheet" type="text/css" media="all"/>
        <link href="${pageContext.request.contextPath}/css/productShow.css" rel="stylesheet" type="text/css" media="all"/>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js">
        </script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/move-top.js">
        </script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/easing.js">
        </script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/startstop-slider.js">
        </script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/ckeditor/ckeditor.js">
        </script>
</head>
<body>
 
	<div class="container cart">
			<table>
				 
			 
				<tr>
				<td colspan="7">
					<form action="${ pageContext.request.contextPath }/admin/saveGonggao.action" method="post">
					  标题：<input type="text" name="title"/>
				    	<textarea rows="10" cols="10" id="messageinfo" name="content"></textarea>  
				    	<ck:replace replace="content" basePath="ckeditor"></ck:replace>  
				    	<input type="submit" value="发表公告"></input>
				    </form>
				</td>
				</tr>
		</table>
	</div>
 
</body>
</html>