package com.algaworks.brewer.repository.helper.cidade;

import com.algaworks.brewer.model.*;
import com.algaworks.brewer.repository.filter.*;
import org.springframework.data.domain.*;

public interface CidadesQueries {

	public Page<Cidade> filtrar(CidadeFilter filtro, Pageable pageable);
	
	public Cidade buscarComEstados(Long codigo); 
	
}
