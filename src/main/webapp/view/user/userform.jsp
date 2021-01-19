<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<h1>Add New User</h1>
<form:form method="post" action="save" modelAttribute="empVo">
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
			<td>choisissez le role :</td>
		</tr>
		<tr>
			<td><form:select path="roles">
					<form:options items="${list}" itemLabel="libelle"
						itemValue="libelle" />

				</form:select></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit" value="Save" /></td>
		</tr>
	</table>
</form:form>
${param.error}

