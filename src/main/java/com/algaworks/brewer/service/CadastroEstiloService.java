package com.algaworks.brewer.service;

import com.algaworks.brewer.model.*;
import com.algaworks.brewer.repository.*;
import com.algaworks.brewer.service.exception.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Service
public class CadastroEstiloService {

	@Autowired
	private Estilos estilos;
	
	@Transactional
	public Estilo salvar(Estilo estilo) {
		Optional<Estilo> estiloExistente = estilos.findByNomeIgnoreCase(estilo.getNome());
		if (estiloExistente.isPresent() && !estiloExistente.get().equals(estilo)) {
			throw new NomeEstiloJaCadastradoException("Nome do estilo já cadastrado");
		}
		
		return estilos.saveAndFlush(estilo);
	}
	
	@Transactional
	public void excluir(Long codigo) {
		estilos.delete(codigo);
	}
}
