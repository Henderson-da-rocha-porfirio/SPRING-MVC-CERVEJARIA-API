package com.algaworks.brewer.controller;

import com.algaworks.brewer.dto.*;
import com.algaworks.brewer.service.*;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Controller
@RequestMapping("/relatorios")
public class RelatoriosController {
	
	@Autowired
	private RelatorioService relatorioService;
	
	@GetMapping("/vendasEmitidas")
	public ModelAndView relatorioVendasEmitidas() {
		ModelAndView mv = new ModelAndView("relatorio/RelatorioVendasEmitidas");
		mv.addObject(new PeriodoRelatorio());
		return mv;
	}
	
	@PostMapping("/vendasEmitidas")
	public ResponseEntity<byte[]> gerarRelatorioVendasEmitidas(PeriodoRelatorio periodoRelatorio) throws Exception {
		byte[] relatorio = relatorioService.gerarRelatorioVendasEmitidas(periodoRelatorio);
		
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE)
				.body(relatorio);
		
	}

}