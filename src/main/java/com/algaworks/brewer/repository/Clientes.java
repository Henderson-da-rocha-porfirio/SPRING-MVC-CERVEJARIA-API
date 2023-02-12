package com.algaworks.brewer.repository;

import com.algaworks.brewer.model.*;
import com.algaworks.brewer.repository.helper.cliente.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface Clientes extends JpaRepository<Cliente, Long>, ClientesQueries {

	public Optional<Cliente> findByCpfOuCnpj(String cpfOuCnpj);

	public List<Cliente> findByNomeStartingWithIgnoreCase(String nome);

}
