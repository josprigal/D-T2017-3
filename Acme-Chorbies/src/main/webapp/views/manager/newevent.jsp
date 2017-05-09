

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%--<jstl:if test="${hascreditcard eq false}">--%>
    <%--<spring:message code="nocreditcard" />--%>
    <%--<br>--%>
    <%--<a href="creditcard/edit.do"><spring:message code="edit" /> <spring:message code="creditcard" /> </a>--%>
<%--</jstl:if>--%>

    <%--<jstl:if test="${hascreditcard eq true}">--%>
    <form:form modelAttribute="event" name="event" method="POST">
        <acme:textbox path="title" code="title" />
        <acme:textbox path="description" code="description" />
        <acme:textbox path="moment" code="moment" />
        <acme:textbox path="picture" code="picture" />
        <acme:textbox path="seats" code="seats" />

        <acme:submit name="send" code="send" />
    </form:form>
    <%--</jstl:if>--%>