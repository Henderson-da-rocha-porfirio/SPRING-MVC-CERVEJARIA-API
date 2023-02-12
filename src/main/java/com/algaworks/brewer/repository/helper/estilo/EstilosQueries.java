package com.algaworks.brewer.repository.helper.estilo;

import com.algaworks.brewer.model.*;
import com.algaworks.brewer.repository.filter.*;
import org.springframework.data.domain.*;

public interface EstilosQueries {
	
	public Page<Estilo> filtrar(EstiloFilter filtro, Pageable pageable);
	
	public Estilo buscarComCervejas(Long codigo);
	
}
