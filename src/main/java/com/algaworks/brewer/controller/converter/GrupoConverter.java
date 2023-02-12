package com.algaworks.brewer.controller.converter;

import com.algaworks.brewer.model.*;
import org.springframework.core.convert.converter.*;
import org.springframework.util.*;

public class GrupoConverter implements Converter<String, Grupo>{

	@Override
	public Grupo convert(String codigo) {
		if (!StringUtils.isEmpty(codigo)) {
			Grupo grupo = new Grupo();
			grupo.setCodigo(Long.valueOf(codigo));
			return grupo;
		}
		
		return null;
	}

}
