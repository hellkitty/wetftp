package org.iptime.dinky.controller;

import javax.servlet.http.HttpSession;

import org.iptime.dinky.RSA;
import org.iptime.dinky.domain.Member;
import org.iptime.dinky.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MemberController {

	@Autowired
	MemberService service;
	
	@RequestMapping(value="/memberLogin", method=RequestMethod.GET)
	public String memberLogin(Model model, HttpSession session){
		RSA rsa = RSA.getEncKey();
		
		model.addAttribute("publicKeyModulus", rsa.getPublicKeyModulus());
		model.addAttribute("publicKeyExponent", rsa.getPublicKeyExponent());
		session.setAttribute("__rsaPrivateKey__", rsa.getPrivateKey());
		
		return "login";
	}
	
	@RequestMapping(value="/memberLogin", method=RequestMethod.POST)
	public String memberLoginProc(Member member, HttpSession session){
		
		Member memberInfo = service.memberLogin(member);
		if (memberInfo!=null){
			session.setAttribute("member", memberInfo);
			return "redirect:/";
		} else {
			return "login";
		}
	}
	
	@RequestMapping(value="/memberRegister", method=RequestMethod.GET)
	public String memberRegister(){
		return "register";
	}
	
	@RequestMapping(value="/memberRegister", method=RequestMethod.POST)
	public String memberRegisterProc(Member member){
		return service.registerMember(member) ? "redirect:/memberLogin" : "register";
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession session){
		
		session.invalidate();
		
		return "redirect:/memberLogin";
	}
	
}
