<%-- list.jsp --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="pets" requestURI="${requestURI }" id="row">
	
	<acme:column property="name" code="pet.name" />
	<acme:column property="type" code="pet.type" />
	<acme:column property="foodExpense" code="pet.foodExpense" />
	<acme:column property="identifier" code="pet.identifier" />
	
	<spring:message code="pet.date" var="momentHeader" />
	<spring:message var="formatDate" code="format.date"/>
	<display:column property="date" title="${momentHeader}" format="${formatDate}" sortable="true" />
	
	<acme:column property="age" code="pet.age" />
	<acme:column property="chip" code="pet.chip" />
	
</display:table>