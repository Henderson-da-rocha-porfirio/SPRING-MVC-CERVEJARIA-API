package com.algaworks.brewer.service;

import com.algaworks.brewer.model.*;
import com.algaworks.brewer.repository.*;
import com.algaworks.brewer.service.exception.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Service
public class CadastroCidadeService {

	@Autowired
	private Cidades cidades;
	
	@Transactional
	public void salvar(Cidade cidade) {
		Optional<Cidade> cidadeExistente = cidades.findByNomeAndEstado(cidade.getNome(), cidade.getEstado());
		if (cidadeExistente.isPresent() && !cidadeExistente.get().equals(cidade)) {
			throw new NomeCidadeJaCadastradaException("Nome de cidade já cadastrado");
		}
		
		cidades.save(cidade);
	}

	@Transactional
	public void excluir(Long codigo) {
		cidades.delete(codigo);
	}
}
