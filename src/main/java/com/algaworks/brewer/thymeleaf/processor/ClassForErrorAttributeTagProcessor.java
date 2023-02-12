package com.algaworks.brewer.thymeleaf.processor;

import org.thymeleaf.context.*;
import org.thymeleaf.engine.*;
import org.thymeleaf.model.*;
import org.thymeleaf.processor.element.*;
import org.thymeleaf.spring4.util.*;
import org.thymeleaf.templatemode.*;

public class ClassForErrorAttributeTagProcessor extends AbstractAttributeTagProcessor {

	private static final String NOME_ATRIBUTO = "classforerror";
	private static final int PRECEDENCIA = 1000;
	
	public ClassForErrorAttributeTagProcessor(String dialectPrefix) {
		super(TemplateMode.HTML, dialectPrefix, null, false, NOME_ATRIBUTO, true, PRECEDENCIA, true);
	}
	
	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag, AttributeName attributeName,
			String attributeValue, IElementTagStructureHandler structureHandler) {
		
		boolean temErro = FieldUtils.hasErrors(context, attributeValue);
		
		if (temErro) {
			String classesExistentes = tag.getAttributeValue("class");
			structureHandler.setAttribute("class", classesExistentes + " has-error");
		}
	}

}
