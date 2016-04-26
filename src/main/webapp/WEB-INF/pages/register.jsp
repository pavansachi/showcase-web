<%@ page language="java" isELIgnored="false"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<title>Register</title>
</head>
<body>

	<form action="profile/create" method="post">

		<input type="text" class="style-4" name="username"
			placeholder="User Name" /> 
			<input type="text" class="style-4"
			name="password" placeholder="Password" /> 
			<input type="text"
			class="style-4" name="email" placeholder="Email" /> 
			<select
			name="roles" multiple="multiple">
			<option value="ROLE_USER">User</option>
			<option value="ROLE_ADMIN">Admin</option>
		</select>

		<div>
			<input type="submit" value="Register" class="button red small" />
		</div>
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>

</body>
</html>