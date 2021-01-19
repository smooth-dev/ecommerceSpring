package com.ecommerceJee.demo.repository;

import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ecommerceJee.demo.model.Article;
import com.ecommerceJee.demo.model.Panier;

@Repository
public interface ArticleRepository
extends JpaRepository<Article, Integer> {
	@Query(value ="SELECT * FROM article WHERE id IN (SELECT MIN(id) FROM article GROUP BY libelle)",nativeQuery = true)
	//@Query("SELECT DISTINCT a.libelle FROM Article a")
	List<Article> findAllDistinct();
	
//List<Article> findDistinctByLibelle();
List<Article> findByLibelle(String libelle);
List<Article> findByPanier(Panier panier);
List<Article> deleteByLibelle(String libelle);
//List<Article> findByLibelleList(String libelle);



//List<EmployeeEntity> findByFirstName(String name);
}
