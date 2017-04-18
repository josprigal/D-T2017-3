

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<a href="chirp/new.do"><spring:message code="newchirp" /> </a>

<h1><spring:message code="chirpsReceived"/> </h1>
<display:table pagesize="5" class="displaytag" keepStatus="true" name="chirpsReceived" requestURI="${requestURI}" id="row">


    <spring:message code="subject" var="subjectHeader"/>
    <display:column property="subject" title="${subjectHeader}" sortable="true"/>

    <spring:message code="text" var="textHeader"/>
    <display:column property="text" title="${textHeader}" sortable="true"/>

    <spring:message code="moment" var="momentHeader"/>
    <display:column property="sent" title="${momentHeader}" sortable="true"/>

    <spring:message code="attachments" var="attachmentsHeader"/>
    <display:column property="attachments" title="${attachments}" sortable="true"/>

    <spring:message code="senderby" var="sender"/>
    <display:column  title="${sender}" sortable="true">
            ${row.sender.userAccount.username}
    </display:column>

    <spring:message code="senderby" var="sender"/>
    <display:column  title="${sender}" sortable="true">
        ${row.sender.userAccount.username}
    </display:column>

    <spring:message code="senderby" var="sender"/>
    <display:column  title="${sender}" sortable="true">
        ${row.sender.userAccount.username}
    </display:column>

    <display:column  sortable="true">
        <a href="chirp/reply/${row.id}.do"><spring:message code="reply" /> </a>
    </display:column>

    <display:column>
        <a href="chirp/delete/${row.id}.do"><spring:message code="delete"/> </a>
    </display:column>

</display:table>


<h1><spring:message code="chirpsSents"/> </h1>
<display:table pagesize="5" class="displaytag" keepStatus="true" name="chirpsSent" requestURI="${requestURI}" id="row">


    <spring:message code="subject" var="subjectHeader"/>
    <display:column property="subject" title="${subjectHeader}" sortable="true"/>

    <spring:message code="text" var="textHeader"/>
    <display:column property="text" title="${textHeader}" sortable="true"/>

    <spring:message code="moment" var="momentHeader"/>
    <display:column property="sent" title="${momentHeader}" sortable="true"/>

    <spring:message code="attachments" var="attachmentsHeader"/>
    <display:column property="attachments" title="${attachments}" sortable="true"/>

    <spring:message code="senderby" var="sender"/>
    <display:column  title="${sender}" sortable="true">
        ${row.sender.userAccount.username}
    </display:column>

    <spring:message code="senderby" var="sender"/>
    <display:column  title="${sender}" sortable="true">
        ${row.sender.userAccount.username}
    </display:column>

    <spring:message code="senderby" var="sender"/>
    <display:column  title="${sender}" sortable="true">
        ${row.sender.userAccount.username}
    </display:column>


    <display:column  sortable="true">
        <a href="chirp/resend/${row.id}.do"><spring:message code="resend" /> </a>
    </display:column>

    <display:column>
        <a href="chirp/delete/${row.id}.do"><spring:message code="delete"/> </a>
    </display:column>

</display:table>

