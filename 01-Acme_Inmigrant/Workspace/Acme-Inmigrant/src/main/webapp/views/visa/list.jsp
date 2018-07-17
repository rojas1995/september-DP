<%--
 * list.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<display:table pagesize="5" class="displaytag" keepStatus="true" name="visa" requestURI="${requestURI }">
	
	<security:authorize access="hasRole('ADMIN')">
	
		<display:column>
			<a href="visa/administrator/edit.do?visaId=${row.id}"> <spring:message
					code="visa.edit" />
			</a>
		</display:column>
		
		<acme:column property="classes" code="visa.classes"/>
		<acme:column property="description" code="visa.description"/>
		<acme:column property="price" code="visa.price"/>
		<acme:column property="invalidate" code="visa.invalidate"/>
		
		<display:column><acme:links url="" code="visa.country"/></display:column>
		<display:column><acme:links url="" code="visa.category"/></display:column>
	</security:authorize>

</display:table>

<security:authorize access="hasRole('ADMIN')">
	<div>
		<a href="visa/administrator/create.do"> <spring:message
				code="visa.create" />
		</a>
	</div>
</security:authorize>