package com.algaworks.brewer.controller;

import com.algaworks.brewer.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Controller
public class DashboardController {

	@Autowired
	private Vendas vendas;

	@Autowired
	private Cervejas cervejas;

	@Autowired
	private Clientes clientes;

	@GetMapping("/")
	public ModelAndView dashboard() {
		ModelAndView mv = new ModelAndView("Dashboard");

		mv.addObject("vendasNoAno", vendas.valorTotalNoAno());
		mv.addObject("vendasNoMes", vendas.valorTotalNoMes());
		mv.addObject("ticketMedio", vendas.valorTicketMedioNoAno());

		mv.addObject("valorItensEstoque", cervejas.valorItensEstoque());
		mv.addObject("totalClientes", clientes.count());

		return mv;
	}

}
