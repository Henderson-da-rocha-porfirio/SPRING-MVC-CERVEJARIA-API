package com.algaworks.brewer.thymeleaf.processor;

import org.thymeleaf.*;
import org.thymeleaf.context.*;
import org.thymeleaf.engine.*;
import org.thymeleaf.model.*;
import org.thymeleaf.processor.element.*;
import org.thymeleaf.standard.expression.*;
import org.thymeleaf.templatemode.*;

import javax.servlet.http.*;

public class MenuAttributeTagProcessor extends AbstractAttributeTagProcessor {

	private static final String NOME_ATRIBUTO = "menu";
	private static final int PRECEDENCIA = 1000;
	
	public MenuAttributeTagProcessor(String dialectPrefix) {
		super(TemplateMode.HTML, dialectPrefix, null, false, NOME_ATRIBUTO, true, PRECEDENCIA, true);
	}
	
	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag, AttributeName attributeName,
			String attributeValue, IElementTagStructureHandler structureHandler) {
		
		IEngineConfiguration configuration = context.getConfiguration();
		IStandardExpressionParser parser = StandardExpressions.getExpressionParser(configuration);
		IStandardExpression expression = parser.parseExpression(context, attributeValue);
		String menu = (String) expression.execute(context);
		
		HttpServletRequest request = ((IWebContext) context).getRequest();
		String uri = request.getRequestURI();
		
		if (uri.matches(menu)) {
			String classesExistentes = tag.getAttributeValue("class");
			structureHandler.setAttribute("class", classesExistentes + " is-active");
		}
	}

}
