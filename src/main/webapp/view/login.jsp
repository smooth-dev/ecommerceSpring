<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<h1>Add New  User</h1>
<form:form method="post" action="/login" modelAttribute="userVo">
	<table>
		<%-- <tr>
			<td>liffbelle :</td>
			<td><form:input path="prenom" /></td>
		</tr>
		<tr>
			<td>description :</td>
			<td><form:input path="nom" /></td>
		</tr> --%>
		<tr>
			<td>username :</td>
			<td><form:input path="username" value="${sessionScope.user.nom}"/></td>
		</tr>
		<tr>
			<td>password :</td>
			<td><form:input  path="password" value=""/></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit" value="Save" /></td>
		</tr>
	</table>
</form:form>
		${param.error}
		

