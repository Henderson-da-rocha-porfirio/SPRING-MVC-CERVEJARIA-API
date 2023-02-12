package com.algaworks.brewer.repository;

import com.algaworks.brewer.model.*;
import com.algaworks.brewer.repository.helper.cidade.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface Cidades extends JpaRepository<Cidade, Long>, CidadesQueries {

	public List<Cidade> findByEstadoCodigo(Long codigoEstado);

	public Optional<Cidade> findByNomeAndEstado(String nome, Estado estado);
	
}
