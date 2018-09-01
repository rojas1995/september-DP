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
	name="actors" requestURI="${requestURI }" id="row">

	<security:authorize access="hasRole('ADMIN')">
		<display:column>
			<jstl:if test="${role == 'employee'}">
				<jstl:if test="${row.ban == false}">
					<form name="submitForm" method="POST" action="administrator/banEmployee.do?employeeId=${row.id }">
				    	<acme:submit name="banEmployee" code="actor.ban"/>
					</form>
				</jstl:if>
				
				<jstl:if test="${row.ban == true}">
					<form name="submitForm" method="POST" action="administrator/debanEmployee.do?employeeId=${row.id }">
				    	<acme:submit name="debanEmployee" code="actor.deban"/>
					</form>
				</jstl:if>
			</jstl:if>
			
			<jstl:if test="${role == 'client'}">
				<jstl:if test="${row.ban == false}">
					<form name="submitForm" method="POST" action="administrator/banClient.do?clientId=${row.id }">
				    	<acme:submit name="banClient" code="actor.ban"/>
					</form>
				</jstl:if>
				
				<jstl:if test="${row.ban == true}">
					<form name="submitForm" method="POST" action="administrator/debanClient.do?clientId=${row.id }">
				    	<acme:submit name="debanClient" code="actor.deban"/>
					</form>
				</jstl:if>
			</jstl:if>
			
			<jstl:if test="${role == 'voluntary'}">
				<jstl:if test="${row.ban == false}">
					<form name="submitForm" method="POST" action="administrator/banVoluntary.do?voluntaryId=${row.id }">
				    	<acme:submit name="banVoluntary" code="actor.ban"/>
					</form>
				</jstl:if>
				
				<jstl:if test="${row.ban == true}">
					<form name="submitForm" method="POST" action="administrator/debanVoluntary.do?voluntaryId=${row.id }">
				    	<acme:submit name="debanVoluntary" code="actor.deban"/>
					</form>
				</jstl:if>
			</jstl:if>
			
			<jstl:if test="${role == 'veterinary'}">
				<jstl:if test="${row.ban == false}">
					<form name="submitForm" method="POST" action="administrator/banVeterinary.do?veterinaryId=${row.id }">
				    	<acme:submit name="banVeterinary" code="actor.ban"/>
					</form>
				</jstl:if>
				
				<jstl:if test="${row.ban == true}">
					<form name="submitForm" method="POST" action="administrator/debanVeterinary.do?veterinaryId=${row.id }">
				    	<acme:submit name="debanVeterinary" code="actor.deban"/>
					</form>
				</jstl:if>
			</jstl:if>
			
			</display:column>
	</security:authorize>
	

	<acme:column property="name" code="actor.name" />
	<acme:column property="surname" code="actor.surname" />
	<acme:column property="email" code="actor.email" />
	<acme:column property="ban" code="actor.ban" />
	
</display:table>