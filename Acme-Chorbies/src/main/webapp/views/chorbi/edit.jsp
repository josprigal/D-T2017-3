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

<h1>
	<spring:message code="register.title" />
</h1>

<form:form  modelAttribute="chorbi"
	method="POST">

	<h2>
		<spring:message code="personaldata" />
	</h2>
	<div class="form-group-2">
		<form:label path="email">
			<spring:message code="email" />:
        </form:label>
		<form:input path="email" type="email" required="required" />
		<form:errors cssClass="error" path="email" />
		<br>
		<form:label path="name">
			<spring:message code="name" />:
        </form:label>
		<form:input path="name" required="required" />
		<form:errors cssClass="error" path="name" />
		<br>



		<form:label path="surname">
			<spring:message code="surnames" />:
        </form:label>
		<form:input path="surname" required="required" />
		<form:errors cssClass="error" path="surname" />
		<br>

		<form:label path="birth">
			<spring:message code="chorbie.birth" />:
        </form:label>
		<form:input path="birth" required="required" />
		<form:errors cssClass="error" path="birth" />
		<br>



		<form:label path="phone">
			<spring:message code="phone" />:
        </form:label>
		<form:input path="phone"
			pattern="((\+[0-9]{1,3})?\s*(\([0-9]{3}\))?\s*([a-zA-Z0-9\- ]{4,}))$" />
		<form:errors cssClass="error" path="phone" />
		<br>

		<form:label path="picture">
			<spring:message code="chorbie.picture" />:
        </form:label>
		<br>
		<form:textarea path="picture" required="required" />
		<form:errors cssClass="error" path="picture" />
		<br>

		<form:label path="description">
			<spring:message code="chorbie.description" />:
        </form:label>
		<br>
		<form:textarea path="description" required="required" />
		<form:errors cssClass="error" path="description" />
		<br>

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


	</div>

	<input name="register" type="submit"
		value="<spring:message code="save" />" />
</form:form>