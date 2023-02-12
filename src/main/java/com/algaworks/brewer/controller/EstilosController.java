package com.algaworks.brewer.controller;

import com.algaworks.brewer.controller.page.*;
import com.algaworks.brewer.model.*;
import com.algaworks.brewer.repository.*;
import com.algaworks.brewer.repository.filter.*;
import com.algaworks.brewer.service.*;
import com.algaworks.brewer.service.exception.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.data.web.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.mvc.support.*;

import javax.servlet.http.*;
import javax.validation.*;

@Controller
@RequestMapping("/estilos")
public class EstilosController {

	@Autowired
	private CadastroEstiloService cadastroEstiloService;
	
	@Autowired
	private Estilos estilos;
	
	@RequestMapping("/novo")
	public ModelAndView novo(Estilo estilo) {
		return new ModelAndView("estilo/CadastroEstilo");
	}
	
	@RequestMapping(value = {"/novo", "{\\d+}"}, method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Estilo estilo, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(estilo);
		}
		
		try {
			cadastroEstiloService.salvar(estilo);
		} catch (NomeEstiloJaCadastradoException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(estilo);
		}
		
		attributes.addFlashAttribute("mensagem", "Estilo salvo com sucesso");
		return new ModelAndView("redirect:/estilos/novo");
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<?> salvar(@RequestBody @Valid Estilo estilo, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(result.getFieldError("nome").getDefaultMessage());
		}
		
		estilo = cadastroEstiloService.salvar(estilo);
		return ResponseEntity.ok(estilo);
	}
	
	@GetMapping
	public ModelAndView pesquisar(EstiloFilter estiloFilter, BindingResult result
			, @PageableDefault(size = 2) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("estilo/PesquisaEstilos");
		
		PageWrapper<Estilo> paginaWrapper = new PageWrapper<>(estilos.filtrar(estiloFilter, pageable)
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable Long codigo) {
		Estilo estilo = estilos.buscarComCervejas(codigo);
		ModelAndView mv = novo(estilo);
		mv.addObject(estilo);
		return mv;
	}
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Long codigo) {
		cadastroEstiloService.excluir(codigo);
		
		return ResponseEntity.ok().build();
	}
}
