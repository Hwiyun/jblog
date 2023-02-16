package com.douzone.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.jblog.security.AuthUser;
import com.douzone.jblog.service.UserService;
import com.douzone.jblog.vo.UserVo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
//	jblog05/user/join
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() {
		return "user/join";
	}
	
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(UserVo vo, Model model) {
		if(userService.compareEqualId(vo)) {
			userService.join(vo);
		} else {
			return "redirect:/user/join";
		}
		return "redirect:/user/joinsuccess";
	}
	
//	jblog05/user/joinsuccess
	@RequestMapping("/joinsuccess")
	public String joinSuccess() {
		return "user/joinsuccess";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "user/login";
	}

//	@RequestMapping(value = "/login", method = RequestMethod.POST)
//	public String login(HttpSession session, UserVo vo, Model model) {
//		UserVo authUser = userService.getUser(vo);
//
//		if (authUser == null) {
//			model.addAttribute("id", vo.getId());
//			return "redirect:/user/login";
//		}
//
//		session.setAttribute("authUser", authUser);
//
//		return "redirect:/";
//	}	
   
}
