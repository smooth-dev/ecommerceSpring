package com.ecommerceJee.demo.controller.web;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.ecommerceJee.demo.domaine.RoleVo;
import com.ecommerceJee.demo.domaine.UserConverter;
import com.ecommerceJee.demo.domaine.UserVo;
import com.ecommerceJee.demo.model.Role;
import com.ecommerceJee.demo.model.User;
import com.ecommerceJee.demo.service.RoleService;
import com.ecommerceJee.demo.service.UserService;

@Controller
@RequestMapping("/user/")
public class UserController2 {

	@Autowired
	 UserService service;
	@Autowired
	 RoleService serviceRole;
	/**
	 * Lorsqu'on tape le lien http://localhost:8080, la page
	 * /WEB-INF/vues/index.jsp. Aucun objet n'est passé dans le Model.
	 */
	@RequestMapping("/")
	public String showWelcomeFile() {
		return "user/userindex";
	}
	
	@RequestMapping("/form")
	public String showform(Model m) {
		m.addAttribute("empVo", new User());
		List<RoleVo> list=serviceRole.getAllRoles();
		m.addAttribute("list",list);


		return "user/userform"; //page html !!!!!!!!!!!
	}

	
	@PostMapping(value = "/save")
	public String save(@ModelAttribute("empVo") UserVo emp) {
		List<RoleVo> rolesvo=emp.getRoles();
		String libelle=rolesvo.get(0).getLibelle();
		System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO"+rolesvo);
		System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO"+libelle);
		
		emp.setRoles(serviceRole.findByLibelle(libelle));
//		List<Role> roles= new ArrayList<Role>();
//		Role role = new Role(libelle);
//
//roles.add(role);
//emp.setRoles(roles);
		User u=UserConverter.toBo(emp);
	//	u.setRoles();

				service.save(UserConverter.toBo(emp));
		return "redirect:viewuser";// will redirect to viewuser request mapping
	}
	
	@RequestMapping("/viewuser")
	public String viewuser(Model m) {
		List<UserVo> list = service.getAllUsers();
		m.addAttribute("list", list);
		return "user/viewuser"; //html page !!!!!!!!!!
	}
	
	
//	@RequestMapping("/form")
//	public String showform(Model m) {
//		m.addAttribute("empVo", new User());
//		List<RoleVo> list=serviceRole.getAllRoles();
//		m.addAttribute("list",list);
//
//
//		return "user/userform"; //page html !!!!!!!!!!!
//	}
//	
	@RequestMapping(value = "/edit/{id}")
	public String edit(@PathVariable Integer id, Model m) {
		UserVo emp = null;
		emp = service.getUserById(id);
		List<RoleVo> list=serviceRole.getAllRoles();
		m.addAttribute("list",list);
		m.addAttribute("empVo", emp);
		return "user/usereditform"; //html apage !!!!!!!!!
	}
	
	@RequestMapping(value = "/editsave", method = RequestMethod.POST)
	public String editsave(@ModelAttribute("empVo") UserVo emp) {
		service.save(UserConverter.toBo(emp));
		return "redirect:viewuser";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable Integer id) {
		service.delete(id);
		return "redirect:../viewuser";
	}
	
	
	@RequestMapping("/nom/{choosennom}")
	public String getNom(@PathVariable String choosennom, Model m) {
		List<UserVo> list = service.findByNom(choosennom);
		m.addAttribute("list", list);
		return "user/viewuser";
	}
	

	
	@RequestMapping("/pagination/{pageid}/{size}")
	public String pagination(@PathVariable int pageid, @PathVariable int size, Model m) {
		List<UserVo> list = service.getAllUsers(pageid, size);
		m.addAttribute("list", list);
		return "user/viewuser";
	}
	
	@RequestMapping("/sort/{fieldName}")
	public String sortBy(@PathVariable String fieldName, Model m) {
		List<UserVo> list = service.sortBy(fieldName);
		m.addAttribute("list", list);
		return "user/viewuser";
	}
}