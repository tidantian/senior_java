package com.framework.spring.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login")
public class Login {
	private String username = "tidan";
	private String password = "abc123";

	@RequestMapping("/show")
	public ModelAndView show() {
		ModelAndView mv = new ModelAndView("login");
		return mv;

	}

	@RequestMapping("/check")
	public ModelAndView list(User user) {
		ModelAndView mv = new ModelAndView();
		if (user != null && user.getUsername().equals(username)
				&& user.getPassword().equals(password)) {
			mv.setViewName("list");
			mv.addObject("user", user);
			return mv;
		}

		mv.setViewName("login");
		mv.addObject("user", user);
		mv.addObject("loginfailed", true);
		return mv;

	}
}
