package com.algaworks.brewer.controller.converter;

import com.algaworks.brewer.model.*;
import org.springframework.core.convert.converter.*;
import org.springframework.util.*;

public class EstiloConverter implements Converter<String, Estilo> {

	@Override
	public Estilo convert(String codigo) {
		if (!StringUtils.isEmpty(codigo)) {
			Estilo estilo = new Estilo();
			estilo.setCodigo(Long.valueOf(codigo));
			return estilo;
		}
		
		return null;
	}

}
