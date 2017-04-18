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
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form modelAttribute="creditCard">

    <acme:textbox path="holderName" code="holder" />
    <form:label path="brandName">
        <spring:message code="brandName" />:
    </form:label>
    <form:select path="brandName" style="width:50%">
        <form:options items="${brandnames}" />
    </form:select>
    <form:errors cssClass="error" path="brandName" />

    <acme:textbox path="number" code="number" />

    <acme:textbox path="expirationMonth" code="expirationMonth" />

    <acme:textbox path="expirationYear" code="expirationYear"/>

    <acme:textbox path="cvvCode" code="ccv" />

    <acme:submit name="save" code="save" />
</form:form>