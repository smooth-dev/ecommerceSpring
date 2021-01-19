package com.ecommerceJee.demo.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Article implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue
	private Integer id;
	private String libelle;
	private String description;
	private int prix ;
	private int quantite ;
	
	
//	@OneToMany(mappedBy = "article",  cascade = {
//	        CascadeType.PERSIST, 
//	        CascadeType.MERGE
//	    })
//	@Basic(fetch=FetchType.LAZY)
//	private List<Panier> paniers;
	
	@ManyToOne
	private Panier panier;
	
	
	public Article(String libelle,String description,int prix,int quantite) {
		this.libelle = libelle;
		this.description = description;
		this.prix = prix;
		this.quantite = quantite;
	}


	@Override
	public String toString()
	{
		return "Article [id="+ id+ ", libelle="+ libelle+ ", description="+ description+ ", prix="+ prix+ ", quantite="
				+ quantite+ "]";
	}


	

	 

}
