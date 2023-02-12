package com.algaworks.brewer.config;

import com.algaworks.brewer.service.*;
import com.algaworks.brewer.storage.*;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackageClasses = { CadastroCervejaService.class, FotoStorage.class})
public class ServiceConfig {

	
}
