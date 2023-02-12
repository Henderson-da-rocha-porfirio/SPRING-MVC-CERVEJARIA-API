package com.algaworks.brewer.controller;

import org.springframework.security.core.annotation.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class SegurancaController {

	@GetMapping("/login")
	public String login(@AuthenticationPrincipal User user) {
		if (user != null) {
			return "redirect:/cervejas";
		}
		
		return "Login";
	}
	
	@GetMapping("/403")
	public String acessoNegado() {
		return "403";
	}
	
}
