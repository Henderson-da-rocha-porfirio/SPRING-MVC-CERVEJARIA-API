package com.algaworks.brewer.service;

import com.algaworks.brewer.model.*;
import com.algaworks.brewer.repository.*;
import com.algaworks.brewer.service.exception.*;
import com.algaworks.brewer.storage.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import javax.persistence.*;

@Service
public class CadastroCervejaService {

	@Autowired
	private Cervejas cervejas;
	
	@Autowired
	private FotoStorage fotoStorage; 
	
	@Transactional
	public void salvar(Cerveja cerveja) {
		cervejas.save(cerveja);
		
	}
	
	@Transactional
	public void excluir(Cerveja cerveja) {
		try {
		String foto = cerveja.getFoto();
		cervejas.delete(cerveja);
		cervejas.flush();
		fotoStorage.excluir(foto);
	} catch (PersistenceException e) {
		throw new ImpossivelExcluirEntidadeException("Impossível apagar cerveja. Já foi usada em alguma venda.");
	}
		
	}
	
}
