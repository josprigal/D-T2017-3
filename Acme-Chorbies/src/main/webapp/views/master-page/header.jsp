<%--
 * header.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<div>
	<img src="images/chorbies.png" alt="Acme Chorbies" />
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv"><spring:message	code="master.page.administrator" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="administrator/configuration/edit.do"><spring:message code="master.page.administrator.configuration" /></a></li>
					<li><a href="administrator/dashboard.do"><spring:message code="master.page.administrator.dashboard" /></a></li>
                    <li><a href="administrator/ban.do"><spring:message code="master.page.administrator.ban" /></a></li>
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('CHORBI')">
			<li><a class="fNiv">CHORBI</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="event/listRegisteredEvents.do"><spring:message code="listRegisteredEvents" /></a></li>
					<li><a href="chorbi/edit.do"><spring:message code="editprofile" /></a></li>
					<li><a href="chorbi/searchtemplate/edit.do"><spring:message code="editsearchtemplate" /></a> </li>
					<li><a href="chorbi/search.do"><spring:message code="search" /></a> </li>
					<li><a href="chirp/list.do">Chirps</a> </li>
					<li><a href="like/chorbiesLikedMe.do"><spring:message code="usersLikedMe" /> </a> </li>
				</ul>
			</li>
		</security:authorize>

        <security:authorize access="hasRole('MANAGER')">
            <li><a class="fNiv">MANAGER</a>
                <ul>
                    <li class="arrow"></li>
                    <li><a href="managing/event/all.do"><spring:message code="yourmanagedevents"/> </a> </li>
                </ul>
            </li>
        </security:authorize>

			<li><a class="fNiv">MENU</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="event/all.do"><spring:message code="allevents" /></a></li>
					<li><a href="event/listAvailableEvents.do"><spring:message code="master.page.listAvailableEvents" /></a></li>
				</ul>
			</li>
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
		</security:authorize>
		
		<security:authorize access="isAuthenticated()">
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>
            <security:authorize access="hasAnyRole('CHORBI','MANAGER')">
                    <li><a href="creditcard/edit.do"><spring:message code="editcreditcard" /> </a> </li>
            </security:authorize>
					<li><a href="chorbi/chorbies.do"><spring:message code="master.page.chorbies" /></a></li>
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
			</li>
		</security:authorize>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

