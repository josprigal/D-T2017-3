<%@page language="java" contentType="text/html; charset=ISO-8859-1"
        pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
          uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<h1>Banners</h1>
<jstl:forEach var="var" items="${banners}" varStatus="i">
    <div>
    <form:form method="post" modelAttribute="banner${i.index}" action="administrator/configuration/edit/banner/${var.id}.do">
        <acme:textbox path="url" code="url" />
        <acme:submit name="save" code="save" />
    </form:form>
    </div>
    <br />
</jstl:forEach>
<br />
<h1><spring:message code="cachedtime" /> </h1>

<form:form method="post" modelAttribute="configuration" action="administrator/configuration/edit/configuration.do">
    <acme:textbox path="hours" code="hours" />
    <acme:textbox path="minutes" code="minutes" />
    <acme:textbox path="seconds" code="seconds" />
    <br>
    <acme:submit name="save" code="save" />
</form:form>