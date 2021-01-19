<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<h1>Add New User</h1>
<form:form method="post" action="/signin" modelAttribute="userVo">
	<table>
		<tr>
			<td>prenom :</td>
			<td><form:input path="prenom" /></td>
		</tr>
		<tr>
			<td>nom :</td>
			<td><form:input path="nom" /></td>
		</tr>
		<tr>
			<td>username :</td>
			<td><form:input path="username" /></td>
		</tr>
		<tr>
			<td>password :</td>
			<td><form:input path="password" /></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit" value="Save" /></td>
		</tr>
	</table>
</form:form>
<a href="/modifierpassword">Modifier mon mot de passe</a>

<%-- 		${param.error}
 --%>
