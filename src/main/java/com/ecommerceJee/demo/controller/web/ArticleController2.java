package com.ecommerceJee.demo.controller.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.ecommerceJee.demo.domaine.ArticleConverter;
import com.ecommerceJee.demo.domaine.ArticleVo;
import com.ecommerceJee.demo.exception.RecordNotFoundException;
import com.ecommerceJee.demo.model.Article;
import com.ecommerceJee.demo.service.ArticleService;

@Controller
@RequestMapping("/article/")
public class ArticleController2 {

	@Autowired
	 ArticleService service;

	/**
	 * Lorsqu'on tape le lien http://localhost:8080, la page
	 * /WEB-INF/vues/index.jsp. Aucun objet n'est passé dans le Model.
	 */
	@RequestMapping("/")
	public String showWelcomeFile() {
		return "article/articleindex";
	}
	

	@RequestMapping("/admin/form")
	public String showform(Model m) {
		m.addAttribute("empVo", new Article());
		return "article/admin/articleform"; //page html !!!!!!!!!!!
	}
	
	@PostMapping(value = "/save")
	public String save(@ModelAttribute("empVo") ArticleVo emp) {
				service.save(ArticleConverter.toBo(emp));
		return "redirect:viewarticle";// will redirect to viewarticle request mapping
	}
	
	@PostMapping(value = "admin/save")
	public String adminsave(@ModelAttribute("empVo") ArticleVo emp) {
				service.save(ArticleConverter.toBo(emp));
		return "redirect:viewarticle";// will redirect to viewarticle request mapping
	}
	
	@RequestMapping("/admin/viewarticle")
	public String adminviewarticle(Model m) {
		List<ArticleVo> list = service.getAllArticles();
		m.addAttribute("list", list);
		return "article/admin/viewarticle"; //html page !!!!!!!!!!
	}
	
	@RequestMapping("/viewarticle")
	public String viewarticle(Model m) {
		List<ArticleVo> list = service.getAllArticlesClient();
		m.addAttribute("list", list);
		return "article/viewarticle"; //html page !!!!!!!!!!
	}
	
	@RequestMapping(value = "/edit/{id}")
	public String edit(@PathVariable Integer id, Model m) {
		ArticleVo emp = null;
		emp = service.getArticleById(id);
		m.addAttribute("empVo", emp);
		return "article/articleeditform"; //html apage !!!!!!!!!
	}
	
	@RequestMapping(value = "/admin/edit/{id}")
	public String adminedit(@PathVariable Integer id, Model m) {
		ArticleVo emp = null;
		emp = service.getArticleById(id);
		m.addAttribute("empVo", emp);
		return "article/admin/articleeditform"; //html apage !!!!!!!!!
	}
	
	
	@RequestMapping(value = "/editsave", method = RequestMethod.POST)
	public String editsave(@ModelAttribute("empVo") ArticleVo emp) {
		ArticleVo art=service.getArticleById(emp.getId());
		art.setQuantite(emp.getQuantite());
		service.save(ArticleConverter.toBo(art));
		return "redirect:viewarticle";
	}
	
	@RequestMapping(value = "/admin/editsave", method = RequestMethod.POST)
	public String admineditsave(@ModelAttribute("empVo") ArticleVo emp) throws RecordNotFoundException {
		//ArticleVo art=service.getArticleById(emp.getId());
		service.createOrUpdateArticle(emp);
		return "redirect:viewarticle";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable Integer id) {
		service.delete(id);
		return "redirect:../viewarticle";
	}
	
	@RequestMapping(value = "admin/delete/{libelle}", method = RequestMethod.GET)
	public String admindelete(@PathVariable String libelle) {
		boolean verif=service.deleteArticleFromAdmin(libelle);
		if(verif)
		return "redirect:../viewarticle";  
		return "redirect:../viewarticle?error=erreur impossible de supprimer cet article"; 
	}
	
	
	@RequestMapping("/details/{choosenlibelle}")
	public String getLibelle(@PathVariable String choosenlibelle, Model m) {
		ArticleVo art = service.findByLibelle(choosenlibelle);
		m.addAttribute("art", art);
		return "article/viewarticledetails";
	}
	
	
	

	
	@RequestMapping("/pagination/{pageid}/{size}")
	public String pagination(@PathVariable int pageid, @PathVariable int size, Model m) {
		List<ArticleVo> list = service.getAllArticles(pageid, size);
		m.addAttribute("list", list);
		return "article/viewarticle";
	}
	
	@RequestMapping("/sort/{fieldName}")
	public String sortBy(@PathVariable String fieldName, Model m) {
		List<ArticleVo> list = service.sortBy(fieldName);
		m.addAttribute("list", list);
		return "article/viewarticle";
	}
}