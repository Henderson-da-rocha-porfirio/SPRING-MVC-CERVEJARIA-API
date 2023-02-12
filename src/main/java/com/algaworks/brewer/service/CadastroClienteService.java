package com.algaworks.brewer.service;

import com.algaworks.brewer.model.*;
import com.algaworks.brewer.repository.*;
import com.algaworks.brewer.service.exception.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Service
public class CadastroClienteService {

	@Autowired
	private Clientes clientes;
	
	@Transactional
	public void salvar(Cliente cliente) {
		Optional<Cliente> clienteExistente = clientes.findByCpfOuCnpj(cliente.getCpfOuCnpjSemFormatacao());
		if (clienteExistente.isPresent() && !clienteExistente.get().equals(cliente)) {
			throw new CpfCnpjClienteJaCadastradoException("CPF/CNPJ já cadastrado");
		}
		
		clientes.save(cliente);
	}
	
	@Transactional
	public void excluir(Long codigo) {
		clientes.delete(codigo);
	}
	
}
