package com.algaworks.brewer.repository.helper.cerveja;

import com.algaworks.brewer.dto.*;
import com.algaworks.brewer.model.*;
import com.algaworks.brewer.repository.filter.*;
import org.springframework.data.domain.*;

import java.util.*;

public interface CervejasQueries {

	public Page<Cerveja> filtrar(CervejaFilter filtro, Pageable pageable);
	
	public List<CervejaDTO> porSkuOuNome(String skuOuNome);
	
	public ValorItensEstoque valorItensEstoque();
	
}
