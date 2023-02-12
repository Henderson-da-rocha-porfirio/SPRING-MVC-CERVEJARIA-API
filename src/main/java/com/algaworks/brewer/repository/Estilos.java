package com.algaworks.brewer.repository;

import com.algaworks.brewer.model.*;
import com.algaworks.brewer.repository.helper.estilo.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface Estilos extends JpaRepository<Estilo, Long>, EstilosQueries {

	public Optional<Estilo> findByNomeIgnoreCase(String nome);
	
}
