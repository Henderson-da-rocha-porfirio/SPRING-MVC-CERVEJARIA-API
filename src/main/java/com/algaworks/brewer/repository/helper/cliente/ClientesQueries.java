package com.algaworks.brewer.repository.helper.cliente;

import com.algaworks.brewer.model.*;
import com.algaworks.brewer.repository.filter.*;
import org.springframework.data.domain.*;

public interface ClientesQueries {

	public Page<Cliente> filtrar(ClienteFilter filtro, Pageable pageable);
	
}