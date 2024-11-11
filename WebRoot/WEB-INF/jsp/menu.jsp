<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="span10 last">
	<div class="topNav clearfix">
		<ul>
			<c:choose>
				<c:when test="${loginUser==null}">
					<li id="headerLogin" class="headerLogin" style="display: list-item;">
						<a href="${pageContext.request.contextPath}/login.action">登录</a>|
					</li>
					<li id="headerRegister" class="headerRegister" style="display: list-item;">
						<a href="${pageContext.request.contextPath}/regist.action">注册</a>|</li>
						<li id="headerLogin" class="headerLogin" style="display: list-item;">
					<a href="${pageContext.request.contextPath}/messageList.action?page=1" style="color: red;">留言板</a>
				   |</li></li>
						<li id="headerLogin" class="headerLogin" style="display: list-item;">
					<a href="${pageContext.request.contextPath}/gonggaoList.action?page=1" style="color: red;">商城公告</a>
				   |</li>
				</c:when>
				<c:otherwise>
					<li id="headerLogin" class="headerLogin" style="display: list-item;">
						${loginUser.username}
					</li>
					<li id="headerLogin" class="headerLogin" style="display: list-item;">
					<a href="${pageContext.request.contextPath}/myOrder.action?page=1" style="color: red;">我的订单</a>
				   |</li>
				   <li id="headerLogin" class="headerLogin" style="display: list-item;">
					<a href="${pageContext.request.contextPath}/messageList.action?page=1" style="color: red;">留言板</a>
				   |</li></li>
						<li id="headerLogin" class="headerLogin" style="display: list-item;">
					<a href="${pageContext.request.contextPath}/gonggaoList.action?page=1" style="color: red;">商城公告</a>
				   |</li><li id="headerLogin" class="headerLogin" style="display: list-item;">
					<a href="${pageContext.request.contextPath}/changepwd.action" style="color: red;">修改密码</a>
				   |</li>
				    <div class="cart">
						<a href="${pageContext.request.contextPath}/myCart.action">购买茶叶车</a>
					</div>
					<li id="headerLogout" class="headerLogout" style="display: list-item;">
					<a href="${pageContext.request.contextPath}/userLogout.action">退出</a>|</li>
				</c:otherwise>
			</c:choose>
			
			<li><a href="${pageContext.request.contextPath}/admin.action">后台登录</a></li>
		</ul>
	</div>
	
	<div class="phone">
		<form method="post" action="${pageContext.request.contextPath}/searchProduct.action" >
                <input id="condition" name="condition" type="text" value="请输入关键词" onfocus="this.value = '';"
                onblur="if (this.value == '') {this.value = '请输入关键词';}">
                <input id="searchSub" type="submit" value="搜">
        </form>
	</div>
</div>
<div class="span24">
	<ul class="mainNav">
		<li><a href="${pageContext.request.contextPath}/index.action">首页</a> |</li>
		<c:forEach var="c" items="${cList}">
		<li><a href="${pageContext.request.contextPath}/findCategorySecond.action?cid=${c.cid}&page=1">${c.cname}</a> |</li>
		</c:forEach>
	</ul>
</div>
