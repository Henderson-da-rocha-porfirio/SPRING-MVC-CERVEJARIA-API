package com.algaworks.brewer.model.validation;

import com.algaworks.brewer.model.*;
import org.hibernate.validator.spi.group.*;

import java.util.*;

public class ClienteGroupSequenceProvider implements DefaultGroupSequenceProvider<Cliente> {

	@Override
	public List<Class<?>> getValidationGroups(Cliente cliente) {
		List<Class<?>> grupos = new ArrayList<>();
		grupos.add(Cliente.class);
		
		if (isPessoaSelecionada(cliente)) {
			grupos.add(cliente.getTipoPessoa().getGrupo());
		}
		
		return grupos;
	}

	private boolean isPessoaSelecionada(Cliente cliente) {
		return cliente != null && cliente.getTipoPessoa() != null;
	}

}
