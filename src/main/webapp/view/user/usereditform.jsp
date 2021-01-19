<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
  
        <h1>Edit User</h1>  
       <form:form method="POST" action="../editsave" modelAttribute="empVo">  
        <table >    
        <tr>  
        <td></td>    
         <td><form:hidden  path="id" /></td>  
         </tr>   
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
			<td>password (impossible de decrypter car Bcrypt crypte en un seul sens):</td> 
			<td><form:input path="password" /></td>
		</tr>
		<tr> <td> 

	<form:select path="roles">  
        <form:options items="${list}" itemLabel="libelle" itemValue="libelle"/>  

        </form:select>   
        </td> </tr> 

         <tr>    
          <td> </td>    
          <td><input type="submit" value="Edit Save" /></td>    
         </tr>    
        </table>    
       </form:form>  