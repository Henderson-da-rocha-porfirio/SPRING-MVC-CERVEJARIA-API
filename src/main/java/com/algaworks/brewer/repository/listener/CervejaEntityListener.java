package com.algaworks.brewer.repository.listener;

import com.algaworks.brewer.model.*;
import com.algaworks.brewer.storage.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.context.support.*;

import javax.persistence.*;

public class CervejaEntityListener {
	
	@Autowired
	private FotoStorage fotoStorage;
	
	
	@PostLoad
	public void postLoad(final Cerveja cerveja) {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		cerveja.setUrlFoto(fotoStorage.getUrl(cerveja.getFotoOuMock()));
		cerveja.setUrlThumbnailFoto(fotoStorage.getUrl(FotoStorage.THUMBNAIL_PREFIX + cerveja.getFotoOuMock()));
	}

}
