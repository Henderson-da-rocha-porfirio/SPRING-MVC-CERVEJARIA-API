package com.algaworks.brewer.controller.converter;

import com.algaworks.brewer.model.*;
import org.springframework.core.convert.converter.*;
import org.springframework.util.*;

public class EstadoConverter implements Converter<String, Estado> {

	@Override
	public Estado convert(String codigo) {
		if (!StringUtils.isEmpty(codigo)) {
			Estado estado = new Estado();
			estado.setCodigo(Long.valueOf(codigo));
			return estado;
		}
		
		return null;
	}

}
