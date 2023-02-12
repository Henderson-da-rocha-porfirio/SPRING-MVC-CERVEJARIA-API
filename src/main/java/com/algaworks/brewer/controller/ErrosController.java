package com.algaworks.brewer.controller;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class ErrosController {
	
	@GetMapping("/404")
	public String paginaNaoEncontrada() {
		return "404";
	}
	
	@RequestMapping("/500")
	public String erroServidor() {
		return "500";
}

}
