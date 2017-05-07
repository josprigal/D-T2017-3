

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
        pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
          uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<h1>
    <spring:message code="event.NearEvents" />
</h1>
<display:table pagesize="5" class="displaytag" keepStatus="true"
               name="events" requestURI="${RequestURI}" id="row">


    <spring:message code="event.title" var="titleHeader" />
    <display:column title="${titleHeader}" sortable="false" >
        <jstl:if test="${row.isHighlighted() eq true}">
            <jstl:set var="style" value="highlight"/>
        </jstl:if>
        <jstl:if test="${row.isAvailable() eq false}">
            <jstl:set var="style" value="grey"/>
        </jstl:if>
        <span class="${style}">${row.title}</span>
    </display:column>

    <spring:message code="event.description" var="descriptionHeader" />
    <display:column property="description" title="${descriptionHeader}"
                    sortable="false" />

    <spring:message code="event.moment" var="momentHeader" />
    <display:column property="moment" title="${momentHeader}"
                    sortable="true" />
    <spring:message code="event.seats" var="seatsHeader" />
    <display:column property="seats" title="${seatsHeader}"
                    sortable="true" />
    <spring:message code="event.picture" var="pictureHeader" />
    <display:column property="picture" title="${pictureHeader}"
                    sortable="true" />

    <spring:message code="register" var="register" />
    <display:column title="${register}">
        <jstl:if test="${row.chorbies.contains(principal) eq false and principal ne null}">
            <a href="/event/register/${row.id}.do"><spring:message code="register" /> </a>
        </jstl:if>
        <jstl:if test="${row.chorbies.contains(principal) eq true and principal ne null}">
        <a href="/event/unregister/${row.id}.do"><spring:message code="unregister" /></a>
        </jstl:if>
    </display:column>

</display:table>
