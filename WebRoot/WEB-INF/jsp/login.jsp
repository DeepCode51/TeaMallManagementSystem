<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>会员登录</title>
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
function checkUsername() {
	var username = document.getElementById("username").value;
	/* 针对中文编码 */
	/* ajax如果提交post请求的话可以直接进行设置编码  如果是get请求可以在浏览器端进行加密处理 然后在服务器端进行解密   */
	/* var username = encodeURIComponent(encodeURIComponent(name)); */
	var xmlHttp = creatXMLHttpreauest();
	/* xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded"); */
	xmlHttp.open("GET",
			"${pageContext.request.contextPath}/loginFindByid.action?username="
					+ username, true);
	xmlHttp.send(null); 
	xmlHttp.onreadystatechange = function() {
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			document.getElementById("span1").innerHTML = xmlHttp.responseText;
		}
	}
}
function creatXMLHttpreauest() {
	try {
		return new XMLHttpRequest();
	} catch (e) {
		try {
			return ActiveXObject("Msxml2.XMLHttp");
		} catch (e) {
			try {
				return ActiveXObject("micoresoftxml2.XMLHttp");
			} catch (e) {
				throw e;
			}
		}
	}
}
function change(){
		var img = document.getElementById("checkImg");
		img.src="${pageContext.request.contextPath}/checkImg.action?"+new Date();
	}
</script>
</head>
<body>
	<div class="container header">
		<div class="span5">
			<div class="logo">
				<a href="/ssm_chaye_shop/"> <img
					src="${pageContext.request.contextPath}/image/r___________renleipic_01/logo.gif" alt="">
				</a>
			</div>
		</div>
		<div class="span9">
			<div class="headerAd">
				<img src="${pageContext.request.contextPath}/image/header.jpg" width="320" height="50" alt="正品保障"
					title="正品保障">
			</div>
		</div>
		<%@ include file="menu.jsp"%>

	</div>
	<div class="container login">
		<div class="span12">
			<div class="ad">
				<img src="${pageContext.request.contextPath}/image/login.jpg" width="500" height="330" alt="会员登录"
					title="会员登录">
			</div>
		</div>
		<div class="span12 last">
			<div class="wrap">
				<div class="main">
					<div class="title">
						<strong>会员登录</strong>USER LOGIN
					</div>
					<form id="loginForm" method="post" 
					action="${pageContext.request.contextPath}/userLogin.action" novalidate="novalidate">
						<table>
							<tbody>
								<tr>
									<th>用户名/E-mail:</th>
									<td>
										<input type="text" id="username" name="username"
										class="text" maxlength="20"  onblur="checkUsername()">
										<span id="span1" style="color:green;"></span>
									</td>
								</tr>
								<tr>
									<th>密&nbsp;&nbsp;码:</th>
									<td><input type="password" id="password" name="password"
										class="text" maxlength="20" autocomplete="off"></td>
								</tr>
								<tr>
									<th>验证码:</th>
									<td>
										<span class="fieldSet">
										<input type="text" id="captcha" name="checkImg" class="text captcha" maxlength="4" autocomplete="off">
										<img id="checkImg" class="captchaImage" src="${pageContext.request.contextPath}/checkImg.action"
													title="点击更换验证码" onclick="change()"> 
										</span>
									</td>
								</tr>
								<tr>
									<th>&nbsp;</th>
									<td><label> <input type="checkbox"
											id="isRememberUsername" name="isRememberUsername"
											value="true">记住用户名
									</label> <label> &nbsp;&nbsp;<a>找回密码</a>
									</label></td>
								</tr>
								<tr>
									<th></th>
									<td style="color:#088A29;"><input type="submit" class="submit" value="登 录">
									&nbsp;${message}
									</td>
								</tr>
								<tr class="register">
									<th>&nbsp;</th>
									<td>
										<dl>
											<dt>还没有注册账号？</dt>
											<dd>
												立即注册即可体验在线购买茶叶！ <a href="${pageContext.request.contextPath}/regist.action">立即注册</a>
											</dd>
										</dl>
									</td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="container footer">
		<div class="span24">
			<div class="footerAd">
				<img src="${pageContext.request.contextPath}/image/footer.jpg" width="950" height="52" alt="我们的优势"
					title="我们的优势" />
			</div>
		</div>
		<div class="span24">
			<ul class="bottomNav">
				<li><hr></hr></li>
			</ul>
		</div>
		<div class="span24">
			<div class="copyright">Copyright ©  网上茶叶商城 版权所有</div>
		</div>
	</div>
</body>
</html>