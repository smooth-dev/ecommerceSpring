<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h1>Votre Panier </h1>
Id client:${sessionScope.user.id} <br/>
Id panier:${sessionScope.panier.id} 
<table border="2" width="70%" cellpadding="2">
	<tr>
		<th>id/th>
		<th>libelle</th>
		<th>prix</th>
	<th>	quantite</th>
		
		<th>Delete</th>
	</tr>
	<tr>
	<c:forEach var="list" items="${art}">
	<tr>
		<td>${list.id}</td>
		<td>${list.libelle}</td>
		<td>${list.prix}</td>
		<td>${list.quantite}</td>
		<%-- <td><a href="edit/${list.id}">Edit</a></td> --%>
			<td> <a href="deletefrompanier/${list.id}/${list.libelle}">Delete</a> </td>
		</tr>		
		
		
	</c:forEach> 
	
					
		
		
	</tr>


	
</table>
<br />
<a href="/article/viewarticle">Ajouter un autre  Article</a>
