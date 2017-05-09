<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<p>
	<spring:message code="administrator.minimunAgeChorbies" />
	${minimunAgeChorbies}
</p>
<br>

<p>
	<spring:message code="administrator.maximunAgeChorbies" />
	${maximunAgeChorbies}
</p>
<br>

<p>
	<spring:message code="administrator.avgAgeChorbies" />
	${avgAgeChorbies}
</p>
<br>

<p>
	<spring:message code="administrator.ratioNotCreditCard" />
	${ratioNotCreditCard}
</p>
<br>

<p>
	<spring:message code="administrator.ratioLove" />
	${ratioLove}
</p>
<br>

<p>
	<spring:message code="administrator.ratioFriendship" />
	${ratioFriendship}
</p>
<br>

<p>
	<spring:message code="administrator.ratioActivities" />
	${ratioActivities}
</p>
<br>


<p>
	<spring:message code="administrator.minLikesChorbi" />
	${minLikesChorbi}
</p>
<br>

<p>
	<spring:message code="administrator.maxLikesChorbi" />
	${maxLikesChorbi}
</p>
<br>

<p>
	<spring:message code="administrator.avgLikesChorbi" />
	${avgLikesChorbi}
</p>
<br>


<p>
	<spring:message code="administrator.minChirpsChorbiSent" />
	${minChirpsChorbiSent}
</p>
<br>

<p>
	<spring:message code="administrator.maxChirpsChorbiSent" />
	${maxChirpsChorbiSent}
</p>
<br>

<p>
	<spring:message code="administrator.avgChirpsChorbiSent" />
	${avgChirpsChorbiSent}
</p>
<br>

<p>
	<spring:message code="administrator.minChirpsChorbiReceived" />
	${minChirpsChorbiReceived}
</p>
<br>


<p>
	<spring:message code="administrator.maxChirpsChorbiReceived" />
	${maxChirpsChorbiReceived}
</p>
<br>

<p>
	<spring:message code="administrator.avgChirpsChorbiReceived" />
	${avgChirpsChorbiReceived}
</p>
<br>
<p><spring:message code="administrator.numberOfChorbiesPerCity" /></p>
<c:forEach items="${numberOfChorbiesPerCity}" var="entry">
<p>${entry.key} : ${entry.value}</p>
</c:forEach>

<p><spring:message code="administrator.numberOfChorbiesPerCountry" /></p>
<c:forEach items="${numberOfChorbiesPerCountry}" var="variable">
<p>${variable.key} : ${variable.value}</p>
</c:forEach>
<%-- <display:table pagesize="5" class="displaytag" keepStatus="true"
	name="numberOfChorbiesPerCity" requestURI="${requestURI}"
	id="numberOfChorbiesPerCity">
	<spring:message code="administrator.numberOfChorbiesPerCity"
		var="name" />
	<display:column property="name" title="${name}" sortable="true" />
</display:table>

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="numberOfChorbiesPerCountry" requestURI="${requestURI}"
	id="numberOfChorbiesPerCountry">
	<spring:message code="administrator.numberOfChorbiesPerCountry"
		var="name" />
	<display:column property="name" title="${name}" sortable="true" />
</display:table> --%>

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="listChorbiesNumberOfLikes" requestURI="${requestURI}"
	id="listChorbiesNumberOfLikes">
	<spring:message code="administrator.listChorbiesNumberOfLikes"
		var="name" />
	<display:column property="name" title="${name}" sortable="true" />
</display:table>


<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="listChorbiesMoreChirpsReceived" requestURI="${requestURI}"
	id="listChorbiesMoreChirpsReceived">
	<spring:message code="administrator.listChorbiesMoreChirpsReceived"
		var="name" />
	<display:column property="name" title="${name}" sortable="true" />
</display:table>

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="listChorbiesMoreChirpsSent" requestURI="${requestURI}"
	id="listChorbiesMoreChirpsSent">
	<spring:message code="administrator.listChorbiesMoreChirpsSent"
		var="name" />
	<display:column property="name" title="${name}" sortable="true" />
</display:table>

<%-- This is part of version 2.0 from Acme-Chorbies --%>

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="listManagersMoreEventsCreated" requestURI="${requestURI}"
	id="listManagersMoreEventsCreated">
	<spring:message code="administrator.listManagersMoreEventsCreated"
		var="name" />
	<display:column property="name" title="${name}" sortable="true" />
</display:table>

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="listManagersFees" requestURI="${requestURI}"
	id="listManagersFees">
	<spring:message code="administrator.listManagersFees"
		var="name" />
		<spring:message code="administrator.fee"
		var="fee" />
	<display:column property="name" title="${name}" sortable="true" />
		<display:column title="${fee}" sortable="true" >-1</display:column>
	
</display:table>




<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="listChorbiesMoreEventsRegistered" requestURI="${requestURI}"
	id="listChorbiesMoreEventsRegistered">
	<spring:message code="administrator.listChorbiesMoreEventsRegistered"
		var="name" />
	<display:column property="name" title="${name}" sortable="true" />
</display:table>

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="listChorbiesFees" requestURI="${requestURI}"
	id="listChorbiesFees">
	<spring:message code="administrator.listChorbiesFees"
		var="name" />
		<spring:message code="administrator.fee"
		var="fee" />
	<display:column property="name" title="${name}" sortable="true" />
	<display:column title="${fee}" sortable="true" >${listChorbiesFees.getDue()}</display:column>
</display:table>


<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="listChorbiesAvgStars" requestURI="${requestURI}"
	id="listChorbiesAvgStars">
	<spring:message code="administrator.listChorbiesAvgStars"
		var="name" />
	<display:column property="name" title="${name}" sortable="true" />
</display:table>

<p>
	<spring:message code="administrator.avgStarsChorbi" />
	${avgStarsChorbi}
</p>
<br>
<p>
	<spring:message code="administrator.maxStarsChorbi" />
	${maxStarsChorbi}
</p>
<br>
<p>
	<spring:message code="administrator.minStarsChorbi" />
	${minStarsChorbi}
</p>
<br>
<br>
