

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<form:form modelAttribute="chirp" name="chirp" action="chirp/new.do">
    <acme:textbox path="subject" code="subject"/>
    <acme:textarea path="text" code="text" />
    <acme:textbox path="attachments" code="attachments"/>

    <form:label path="recipent">
        <spring:message code="recipent" />:
    </form:label>
    <form:select path="recipent" style="width:50%">
        <form:options items="${chorbis}" itemLabel="userAccount.username" />
    </form:select>
    <form:errors cssClass="error" path="recipent" />

    <acme:submit name="submit" code="submit" />
</form:form>