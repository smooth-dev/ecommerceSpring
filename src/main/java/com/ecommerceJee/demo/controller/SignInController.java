package com.ecommerceJee.demo.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.ecommerceJee.demo.domaine.UserConverter;
import com.ecommerceJee.demo.domaine.UserVo;
import com.ecommerceJee.demo.exception.RecordNotFoundException;
import com.ecommerceJee.demo.model.User;
import com.ecommerceJee.demo.service.UserService;

@Controller
public class SignInController
{
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	 UserService service;
	
	@RequestMapping(value = { "/signin" }, method = RequestMethod.GET)
	public String signin(Model m) {
		m.addAttribute("userVo", new UserVo());
		return "signin"; //page html !!!!!!!!!!!
	}
	
	@RequestMapping(value = { "/modifierpassword"}, method = RequestMethod.GET)
	public String modifierpassword(Model m) throws RecordNotFoundException {
		m.addAttribute("userVo", new UserVo());
		return "modifierpassword";
	}
	@PostMapping(value = "/modifierpassword")
	public String modifierpasswrd(@ModelAttribute("empVo") UserVo user,HttpServletRequest request,Model m) throws RecordNotFoundException {
		UserVo uservo =( (UserVo)request.getSession().getAttribute("user") );

		 String username=null;
		if(uservo!=null)
		username=uservo.getUsername();
			
		if(username==null)
		username=service.findByUsername2(user.getUsername());
		if(username==null)
		System.out.println("nonono");
		
//		service.createOrUpdateUser(user);
		UserVo ss=service.findByUsername(username);
		String encodedancien=ss.getPassword();
		String ancien=user.getPassword();
		String nouveau = user.getNouveaupassword();
		boolean isPasswordMatch = bCryptPasswordEncoder.matches(ancien,encodedancien);
		
		System.out.println("DEBUG[modifierpassWord(post)]");
		System.out.println("ancien:"+ancien+"     nouveau:"+nouveau);
		System.out.println("=========================encoded"+encodedancien+"match:"+isPasswordMatch);
		
		if(isPasswordMatch) {
			ss.setPassword(nouveau); 
			service.createOrUpdateUser(ss);
		}
		request.getSession().setAttribute("user",ss);

		//		m.addAttribute("userVo", new User());
		return "redirect:login"; //page html !!!!!!!!!!!
	}
	
//	@RequestMapping(value = { "/index"}, method = RequestMethod.GET)
//	public String index(Model m) {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		m.addAttribute("userName",  auth.getName());
//		m.addAttribute("list",auth.getAuthorities());
//		return "index"; //page html !!!!!!!!!!!
//	}
	
	@PostMapping(value = "/signin")
	public String save(@ModelAttribute("empVo") UserVo user,HttpServletRequest request) {
		
		User user1=service.save(UserConverter.toBo(user));
		  request.getSession().setAttribute("user",UserConverter.toVo(user1));
		return "redirect:login";// will redirect to viewarticle request mapping
	}
}
