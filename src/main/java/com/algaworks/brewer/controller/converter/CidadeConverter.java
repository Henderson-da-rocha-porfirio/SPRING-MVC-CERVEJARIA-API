package com.algaworks.brewer.controller.converter;

import com.algaworks.brewer.model.*;
import org.springframework.core.convert.converter.*;
import org.springframework.util.*;

public class CidadeConverter implements Converter<String, Cidade> {

	@Override
	public Cidade convert(String codigo) {
		if (!StringUtils.isEmpty(codigo)) {
			Cidade cidade = new Cidade();
			cidade.setCodigo(Long.valueOf(codigo));
			return cidade;
		}
		
		return null;
	}

}
