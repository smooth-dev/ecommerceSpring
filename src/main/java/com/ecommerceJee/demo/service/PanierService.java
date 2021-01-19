package com.ecommerceJee.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ecommerceJee.demo.domaine.ArticleConverter;
import com.ecommerceJee.demo.domaine.PanierConverter;
import com.ecommerceJee.demo.domaine.PanierVo;
import com.ecommerceJee.demo.domaine.UserConverter;
import com.ecommerceJee.demo.domaine.UserVo;
import com.ecommerceJee.demo.exception.RecordNotFoundException;
import com.ecommerceJee.demo.model.Article;
import com.ecommerceJee.demo.model.Panier;
import com.ecommerceJee.demo.model.User;
import com.ecommerceJee.demo.repository.ArticleRepository;
import com.ecommerceJee.demo.repository.PanierRepository;
import com.ecommerceJee.demo.repository.UserRepository;

@Service
public class PanierService 
{
	 @Autowired
	    PanierRepository repository;
	 @Autowired
	    ArticleRepository articlerepository;
	 @Autowired
	    UserRepository userrepository;
	 @Autowired
	    ArticleService articleservice;
	 
 /*
public PanierVo addToCart(Integer clientID,String articleLibelle) {
	List<Panier> list=repository.findByUserId(5);
	System.out.println("TAVVVVVVVvvvvvvvvvvvvvvvvvvvvvvvvvvvv "+list.size());	
	Panier panier=list.get(0);
	System.out.println("addtocar size"+panier);

//		 Panier panier=(repository.findByUserId(clientID)).get(0);
//		 System.out.println(panier+"[PanierService]");
		 Article article=articlerepository.findByLibelle(articleLibelle);
		 System.out.println("<<<<<<<<<article>>>>>>>>>"+article);
		 panier.getArticles().add(article);
	        PanierVo paniervo = PanierConverter.toVo(panier);
	        return paniervo;

		 
	 }
	*/
public Panier addToCart(int qte,String clientID,String articleLibelle,HttpServletRequest request) {
	User client=userrepository.findByUsername(clientID);
//System.out.println("DEBUG[PanierService]//////client"+client);
	Panier connectedpanier=(Panier)request.getSession().getAttribute("panier");
	if(connectedpanier==null) {
		// 
		List<Panier> list=repository.findByUser(client);
		 System.out.println("DEBUG[PanierService]////////list"+list);	

				 connectedpanier=list.get(0);
	}


	int prix=articleservice.findByLibelle(articleLibelle).getPrix();
		Article art1 = new Article(articleLibelle,articleLibelle,prix,qte);
boolean verif=articleservice.decrement(articleLibelle,qte);
if(!verif) return null;
		// Article article=articlerepository.findByLibelle(articleLibelle);
		 art1.setPanier(connectedpanier);
			articlerepository.save(art1);

//		 ArticleVo articlevo = ArticleConverter.toVo(article);
//		 articlevo.setPanier(PanierConverter.toVo(panier));
//		 panier.getArticles().add(ArticleConverter.toBo(articlevo));
		System.out.println("/://://///:::/"+connectedpanier);
		 repository.save(connectedpanier);
		 
			request.getSession().setAttribute("panier", connectedpanier);

		 

		 
		 System.out.println("DEBUG[PanierService]");
		 System.out.println("<<<<<<<<<article>>>>>>>>>"+art1);
		 System.out.println("<<<<<<<<<panier>>>>>>>>>"+connectedpanier);
		 
//	        PanierVo paniervo = PanierConverter.toVo(panier);
	        
//	        System.out.println(paniervo);
	        return connectedpanier;

		 
	 }

	 public List<PanierVo> getAllPaniers()
	    {
		 
		 List<Panier> pa=repository.findAll(); //debug
		 System.out.println("999999999999999999999999999"+pa); //debug
	        List<PanierVo> panierList = PanierConverter.toVoList(repository.findAll());
	         
	        if(panierList.size() > 0) {
	            return panierList;
	        } else {
	            return new ArrayList<PanierVo>();
	        }
	    }
//	 public Panier getPanierById(Integer id) throws RecordNotFoundException
//	    {
//	        Optional<Panier> panier = repository.findById(id);
//	         
//	        if(panier.isPresent()) {
//	            return panier.get();
//	        } else {
//	            throw new RecordNotFoundException("No Panier record exist for given id");
//	        }
//	    }
	 
	   public PanierVo getPanierById(Integer id) {
			boolean trouve = repository.existsById(id);
			if (!trouve)
				return null;
			return PanierConverter.toVo(repository.getOne(id));
		}
	 
	 public PanierVo createOrUpdatePanier(PanierVo entity) throws RecordNotFoundException
	    {
	        Optional<Panier> panier = repository.findById(entity.getId());
	         
	        if(panier.isPresent())
	        {
	            Panier newEntity = panier.get();
	            newEntity.setUser(UserConverter.toBo(entity.getUser()));
	            newEntity.setArticles(ArticleConverter.toBoList(entity.getArticles()));

	 
	            newEntity = repository.save(newEntity);
	             
	            return entity;
	        } else {
	            repository.save(PanierConverter.toBo(entity));
	            return entity;
	        }
	    }
	 
	 
	 public List<PanierVo> getAllPaniers(int pageId, int size) {
			Page<Panier> result = repository.findAll(PageRequest.of(pageId, size, Direction.ASC, "libelle"));
			return PanierConverter.toVoList(result.getContent());
		}
	 
	 public List<PanierVo> findByUser(String libelle) {
		 
		 User client=userrepository.findByUsername(libelle);
			List<Panier> list=repository.findByUser(client);
			return PanierConverter.toVoList(list);
		}
	 
	 public List<PanierVo> sortBy(String fieldName) {
		 List<Panier>  list =repository.findAll(Sort.by(fieldName));
			return PanierConverter.toVoList(list);
	}
	 
		 
	 @Transactional
	    public Panier save(Panier entity) 
	    {
	       
	            return repository.save(entity);     
	        
	    }
	 

	  public void deletePanierById(Integer id) throws RecordNotFoundException
	    {
	        Optional<Panier> role = repository.findById(id);
	         
	        if(role.isPresent())
	        {
	            repository.deleteById(id);
	        } else {
	            throw new RecordNotFoundException("No Panier record exist for given id");
	        }
	    }
	  
	  @Transactional
		public void delete(Integer id) {
			repository.deleteById(id);
		}
	  
	 @Transactional
		public void deleteAll() {
			repository.deleteAll();
		}
}