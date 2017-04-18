

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>


<jstl:if test="${not empty creditcard}">
    <spring:message code="${creditcard}" />
</jstl:if>

<jstl:if test="${empty creditcard}">

<display:table pagesize="5" class="displaytag" keepStatus="true" name="chorbies" requestURI="${requestURI}" id="row">


	 <spring:message code="name" var="nameHeader"/>
    <display:column property="name" title="${nameHeader}" sortable="true"/>

     <spring:message code="surnames" var="surnameHeader"/>
    <display:column property="surname" title="${surnameHeader}" sortable="true"/>

    <spring:message code="chorbie.description" var="descriptionHeader"/>
    <display:column property="description" title="${descriptionHeader}" sortable="true"/>
    
     <spring:message code="chorbie.birth" var="birthHeader"/>
    <display:column property="birth" title="${birthHeader}" sortable="true"/>
    
    <spring:message code="chorbie.picture" var="picture"/>
    <display:column property="picture" title="${picture}" sortable="false"/>
    
    <spring:message code="chorbie.relationship" var="relationship"/>
    <display:column property="relationship" title="${relationship}" sortable="false"/>
    
     <spring:message code="chorbie.gender" var="gender"/>
    <display:column property="gender" title="${gender}" sortable="false"/>

    <spring:message code="chorbie.likes" var="editHeader"/>
    <display:column title="${editHeader }">
			<a href="chorbi/c/${row.id}/likes.do">
				<spring:message	code="chorbie.likes" />
			</a>
	</display:column>


</display:table>
</jstl:if>
