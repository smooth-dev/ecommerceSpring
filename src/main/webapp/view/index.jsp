
<h2> Welcome ${userName} </h2>  ${sessionScope.user.nom} ${sessionScope.user.prenom}
Votre role est :  ${list}

<br/><br/><br/><br/>


 <h3 style="color:green"> Accessible</h3>
<a href="/panier/monpanier">Consulter mon panier</a>   

<a href="/article/viewarticle">Voir catalogue</a>  
 

<br/><br/><br/><br/><br/>

<h2 style="color:red"> Section Admin only</h2>
<h3 style="color:red"> Gestion articles </h3>


<a href="/article/admin/viewarticle">Gerer les articles</a> 
<br/><br/>

<h3 style="color:red"> Gestion Roles</h3>

<a href="/role/form">Ajouter un  Role</a>   
<a href="/role/viewrole">Voir les roles</a>  

<br/>
 <h3 style="color:red"> Gestion paniers</h3>
<a href="/panier/form">Ajouter Paniers</a>  
<a href="/panier/viewpanier">Lister les  Paniers</a>

<br/>

<h3 style="color:red"> Gestion utilisateurs</h3>
<a href="/user/form">Ajouter </a>  
<a href="/user/viewuser">Lister les utilisateurs</a>  
