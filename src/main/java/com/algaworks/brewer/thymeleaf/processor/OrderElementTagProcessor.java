package com.algaworks.brewer.thymeleaf.processor;

import org.thymeleaf.context.*;
import org.thymeleaf.model.*;
import org.thymeleaf.processor.element.*;
import org.thymeleaf.templatemode.*;

public class OrderElementTagProcessor extends AbstractElementTagProcessor {

	private static final String NOME_TAG = "order";
	private static final int PRECEDENCIA = 1000;
	
	public OrderElementTagProcessor(String dialectPrefix) {
		super(TemplateMode.HTML, dialectPrefix, NOME_TAG, true, null, false, PRECEDENCIA);
	}
	
	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag,
			IElementTagStructureHandler structureHandler) {
		IModelFactory modelFactory = context.getModelFactory();
		
		IAttribute page = tag.getAttribute("page");
		IAttribute field = tag.getAttribute("field");
		IAttribute text = tag.getAttribute("text");
		
		IModel model = modelFactory.createModel();
		model.add(modelFactory.createStandaloneElementTag("th:block"
				, "th:replace"
				, String.format("fragments/Ordenacao :: order (%s, %s, '%s')", page.getValue(), field.getValue(), text.getValue())));
		
		structureHandler.replaceWith(model, true);
	}

}
