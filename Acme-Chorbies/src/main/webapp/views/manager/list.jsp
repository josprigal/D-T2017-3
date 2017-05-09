

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
<a href="chirp/broadcast.do"><spring:message code="broadcast" /> </a>
<br>
<a href="managing/event/new.do"> <spring:message code="newevent" /></a>
<display:table pagesize="5" class="displaytag" keepStatus="true"
               name="events" requestURI="${requestURI}" id="row">


    <spring:message code="event.title" var="titleHeader" />
    <display:column title="${titleHeader}" sortable="false" >
       ${row.title}
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
    <spring:message code="edit" var="edit" />
    <display:column title="">
        <a href="managing/event/edit/${row.id}.do"><spring:message code="edit" /> </a>
        | <a href="managing/event/delete/${row.id}.do"><spring:message code="delete" /> </a>
    </display:column>

</display:table>
