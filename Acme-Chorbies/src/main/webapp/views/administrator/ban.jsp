<%@page language="java" contentType="text/html; charset=ISO-8859-1"
        pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
          uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>


<display:table pagesize="5" class="displaytag" keepStatus="true" name="chorbis" requestURI="${requestURI}" id="row">

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

    <spring:message code="chorbie.ban" var="ban" />
    <display:column title="ban" sortable="true">
        <a href="administrator/ban/${row.id}.do">
            <jstl:if test="${row.userAccount.locked eq false}">
                ${ban}
            </jstl:if>
            <jstl:if test="${row.userAccount.locked eq true}">
                <spring:message code="chorbie.unban"/>
            </jstl:if>
        </a>
    </display:column>

</display:table>
