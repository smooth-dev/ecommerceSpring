<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<h1>Add New User</h1>
<form:form method="post" action="/modifierpassword" modelAttribute="userVo">
	<table>
		
		<tr>
			<td>username :</td>
			<td><form:input path="username" value="${user.username}" /></td>
		</tr>
		 <tr>
			<td>ancien password :</td>
			<td><form:input path="password" /></td>
		</tr>
		<tr>
			<td>nouveau password :</td>
			<td><form:input path="nouveaupassword" /></td>
		</tr> 
		<tr>
			<td></td>
			<td><input type="submit" value="Save" /></td>
		</tr>
	</table>
</form:form>
<%-- 		${param.error}
 --%>
