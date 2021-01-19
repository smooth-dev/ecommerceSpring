<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<h1>Articles List</h1>
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
	<c:forEach var="art" items="${list}">
		<tr>
			<td>${art.id}</td>
			<td>${art.libelle}</td>
			<td>${art.description}</td>
			<td>${art.prix}</td>
			<td>${art.quantite}</td>				
			<td><a href="edit/${art.id}">Edit</a></td>
			<td><a href="delete/${art.id}">Delete</a></td>
						<%-- <td> <a href="/panier/addToCart/${sessionScope.user.nom}/${art.libelle}"> <img alt="article img not found" src="/images/boutonadd.png"> </a></td> --%>
			<td> <a href="/article/details/${art.libelle}"> <img alt="article img not found" src="/images/boutonadd.png"> </a></td>
		</tr>
	</c:forEach>
</table>
<br />
<a href="form">Addd New Article</a>
