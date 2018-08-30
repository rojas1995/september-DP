<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="user/display.do" modelAttribute="user">

	<security:authorize access="hasRole('USER')">
		<display:table name="followTable" class="displaytag" id="row">

			<jstl:if test="${row.id != currentUserId }">
				<display:column>
					<acme:links url="user/follow.do?userId=${row.id}"
						code="user.follow" />
				</display:column>
				<display:column>
					<acme:links url="user/unfollow.do?userId=${row.id}"
						code="user.unfollow" />
				</display:column>
			</jstl:if>

			<display:column>
				<acme:links url="user/listFollowers.do?userId=${row.id }"
					code="user.followers" />
			</display:column>
			<display:column>
				<acme:links url="user/listFollowing.do?userId=${row.id }"
					code="user.following" />
			</display:column>

		</display:table>
	</security:authorize>

	<b><spring:message code="user.name" />:&nbsp;</b>
	<jstl:out value="${user.name}" />
	<br />

	<b><spring:message code="user.surname" />:&nbsp;</b>
	<jstl:out value="${user.surname}" />
	<br />

	<b><spring:message code="user.email" />:&nbsp;</b>
	<jstl:out value="${user.email}" />
	<br />

	<b><spring:message code="user.phoneNumber" />:&nbsp;</b>
	<jstl:out value="${user.phoneNumber}" />
	<br />

	<b><spring:message code="user.address" />:&nbsp;</b>
	<jstl:out value="${user.address}" />
	<br />

	<b><spring:message code="user.postalAddress" />:&nbsp;</b>
	<jstl:out value="${user.postalAddress}" />
	<br />

	<b><spring:message code="user.pictures" /></b>
	<br />
	<jstl:forEach var="p" items="${user.pictures }">
		<h4>
			<img class="imagen" src="${p }" /> <br />
		</h4>
	</jstl:forEach>
	<br />

	<h4>
		<spring:message code="user.routes" />
		:&nbsp;
	</h4>
	<jstl:forEach items="${routes }" var="route">
		<spring:message code="user.name" var="name" />
		<jstl:out value="${route.name }" />

		<acme:links url="route/display.do?routeId=${route.id}"
			code="user.display" />

<<<<<<< HEAD
	</jstl:forEach>
=======
<h4>
	<spring:message code="user.compostelas" />
	:&nbsp;
</h4>
<table style="width:100%">
<jstl:forEach var="comp" items="${user.compostelas }" varStatus="status">
<tr>
<td><b>${status.index +1 }</b></td>
<td>${comp.walk.title }</td>
<td>
	<%!int count = 0;%>
			<jstl:forEach var="d" items="${comp.walk.daysOfEachHike }">
				<%
					count = count + 1;
				%>
				<b><jstl:out value="${numberHike }"/> <%=count%>: </b>
				<jstl:out value="${d }"/>
				<br/>
			</jstl:forEach>
		<%
			count = 0;
		%>
</td>
</tr>
</jstl:forEach>
</table>
>>>>>>> cdac3d4389950e687f22596ea87a04f5971ea942
</form:form>