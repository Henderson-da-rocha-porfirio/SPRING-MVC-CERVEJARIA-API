package com.algaworks.brewer.thymeleaf.processor;

import org.thymeleaf.context.*;
import org.thymeleaf.model.*;
import org.thymeleaf.processor.element.*;
import org.thymeleaf.templatemode.*;

public class MessageElementTagProcessor extends AbstractElementTagProcessor {

	private static final String NOME_TAG = "message";
	private static final int PRECEDENCIA = 1000;
	
	public MessageElementTagProcessor(String dialectPrefix) {
		super(TemplateMode.HTML, dialectPrefix, NOME_TAG, true, null, false, PRECEDENCIA);
	}
	
	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag,
			IElementTagStructureHandler structureHandler) {
		IModelFactory modelFactory = context.getModelFactory();
		
		IModel model = modelFactory.createModel();
		model.add(modelFactory.createStandaloneElementTag("th:block", "th:replace", "fragments/MensagemSucesso :: alert"));
		model.add(modelFactory.createStandaloneElementTag("th:block", "th:replace", "fragments/MensagensErroValidacao :: alert"));
		
		structureHandler.replaceWith(model, true);
	}

}
