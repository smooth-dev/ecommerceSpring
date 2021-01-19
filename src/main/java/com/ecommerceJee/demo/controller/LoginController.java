package com.ecommerceJee.demo.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.ecommerceJee.demo.domaine.UserVo;
import com.ecommerceJee.demo.model.User;
import com.ecommerceJee.demo.service.UserService;

@Controller
public class LoginController
{
	@Autowired
	 UserService service;
	
	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public String login(Model m,HttpServletRequest request) {
		m.addAttribute("userVo", new User());
		return "login"; //page html !!!!!!!!!!!
	}
	
	@RequestMapping(value = { "/index"}, method = RequestMethod.GET)
	public String index(Model m,HttpServletRequest request) {
		//ce controller sert a completer le post de SpringSecurity !!!!!!!!!ATTENTION!!!!!!!!!!!!
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		//UserVo connecteduser=(UserVo)request.getSession().getAttribute("user"); //DECOMENT IF REGRESSED
		UserVo connecteduser=null; //comment  IF REGRESSED
		if(connecteduser==null) {
			UserVo user=(service.findByNom(auth.getName()).get(0));
			System.out.println("++++++++++++++++++++++"+user.getId());
			System.out.println(user);
			request.getSession().setAttribute("user", user);
		System.out.println(user);} 

		m.addAttribute("userName",  auth.getName());
		m.addAttribute("list",auth.getAuthorities());
	

		return "index"; //page html !!!!!!!!!!!
	}
	
	@RequestMapping(value = {"/modiferpassword"}, method = RequestMethod.GET)
	public String modifiermdp(Model m,HttpServletRequest request) {
		//ce controller sert a completer le post de SpringSecurity !!!!!!!!!ATTENTION!!!!!!!!!!!!
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		User connecteduser=(User)request.getSession().getAttribute("user");
		if(connecteduser==null) {
			UserVo user=(service.findByNom(auth.getName()).get(0));
			System.out.println(user);
			request.getSession().setAttribute("user", user);
		System.out.println("^^^^^^^^"+user);}
		

		m.addAttribute("userName",  auth.getName());
		m.addAttribute("list",auth.getAuthorities());
	

		return "/modiferpassword"; //page html !!!!!!!!!!!
	}


	@RequestMapping(value = { "/access-denied" }, method = RequestMethod.GET)
	public String denied(Model m) {
		m.addAttribute("userVo", new User());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		m.addAttribute("userName", "Ur logged as : " + auth.getName());


		return "acess-denied"; //page html !!!!!!!!!!!
	}
}
