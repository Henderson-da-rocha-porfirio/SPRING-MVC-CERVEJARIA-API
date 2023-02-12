package com.algaworks.brewer.thymeleaf.processor;

import org.thymeleaf.context.*;
import org.thymeleaf.model.*;
import org.thymeleaf.processor.element.*;
import org.thymeleaf.templatemode.*;

public class PaginationElementTagProcessor extends AbstractElementTagProcessor {

	private static final String NOME_TAG = "pagination";
	private static final int PRECEDENCIA = 1000;
	
	public PaginationElementTagProcessor(String dialectPrefix) {
		super(TemplateMode.HTML, dialectPrefix, NOME_TAG, true, null, false, PRECEDENCIA);
	}
	
	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag,
			IElementTagStructureHandler structureHandler) {
		IModelFactory modelFactory = context.getModelFactory();
		
		IAttribute page = tag.getAttribute("page");
		
		IModel model = modelFactory.createModel();
		model.add(modelFactory.createStandaloneElementTag("th:block"
				, "th:replace"
				, String.format("fragments/Paginacao :: pagination (%s)", page.getValue())));
		
		structureHandler.replaceWith(model, true);
	}

}
