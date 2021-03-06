<%--
 * join.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="${requestURI}" modelAttribute="stand">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="numMaxVoluntaries" />
	<form:hidden path="fliers" />
	<form:hidden path="isOfCompany" />
	<form:hidden path="voluntaries" />
	<form:hidden path="company" />
	
	<jstl:if test="${stand.employee == null}">
		<acme:select items="${employees}" itemLabel="name" code="stand.employee" path="employee"/>
	</jstl:if>

	<!-- Buttons -->

	<acme:submit name="save" code="stand.affiliate" />
	<acme:cancel url="/" code="stand.cancel" />

</form:form>