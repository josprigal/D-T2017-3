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



<form:form  modelAttribute="searchtemplate" name="searchtemplate"
	method="POST">

        <acme:textbox path="age" code="age" />
        <acme:textbox path="keyword" code="keyword" />
        <h2><spring:message code="coordinates" /> </h2>
        <acme:textbox path="coordinates.city" code="city" />
        <acme:textbox path="coordinates.province" code="province" />
        <acme:textbox path="coordinates.state" code="state" />
        <acme:textbox path="coordinates.country" code="country" />

		<form:label path="relationship">
			<spring:message code="chorbie.relationship" />:
        </form:label>
		<form:select path="relationship" style="width:50%">
			<form:options items="${relationships}" />
		</form:select>
		<form:errors cssClass="error" path="relationship" />

		<br>

		<form:label path="gender">
			<spring:message code="chorbie.gender" />:
        </form:label>
		<form:select path="gender" style="width:50%">
			<form:options items="${genders}" />
		</form:select>
		<form:errors cssClass="error" path="gender" />

    <br>



<div>	<input name="save" type="submit"
                value="<spring:message code="save" />" /></div>

</form:form>