<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    

<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
       <form:form method="POST" action="/panier/addToCart/${sessionScope.user.nom}/${art.libelle}" modelAttribute="art">  

	<table border="2" width="70%" cellpadding="2">
		<tr>
			<th>Id</th>
			<th>Libelle</th>
			<th>description</th>
			<th>pRix</th>
			<th>quantite</th>
			<th>Edit</th>
			<th>Delete</th>
		</tr>
		<tr>
			  </tr>   
      <tr>
			
			<td><form:label path="libelle"/> ${art.libelle }</td>
		
			
			<td><form:label path="prix"/> ${art.description }</td>
			
					
			<td><form:label path="prix"/> ${art.prix }</td>
			
				
			<td><form:input path="quantite" /> </td>
			
			<td><a href="edit/${art.id}">Edit</a></td>
			<td><a href="delete/${art.id}">Delete</a></td>
          <td><input type="submit" value="ADD TO CART!" /></td>    
			
			<%-- <td><a
				href="/panier/addToCart/${sessionScope.user.nom}/${art.libelle}">
					<img alt="article img not found" src="/images/boutonadd.png">
			</a></td> --%>
		</tr>
		        </table>    
		
		</form:form> 
</body>
</html>