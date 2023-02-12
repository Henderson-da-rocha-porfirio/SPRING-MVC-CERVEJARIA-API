package com.algaworks.brewer.repository.helper.cidade;

import com.algaworks.brewer.model.*;
import com.algaworks.brewer.repository.filter.*;
import com.algaworks.brewer.repository.paginacao.*;
import org.hibernate.*;
import org.hibernate.criterion.*;
import org.hibernate.sql.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.transaction.annotation.*;
import org.springframework.util.*;

import javax.persistence.*;

public class CidadesImpl implements CidadesQueries {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Cidade> filtrar(CidadeFilter filtro, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Cidade.class);
		
		paginacaoUtil.preparar(criteria, pageable);
		adicionarFiltro(filtro, criteria);
		criteria.createAlias("estado", "e");
				
		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}
	
	@Transactional(readOnly = true)
	@Override
	public Cidade buscarComEstados(Long codigo) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Cidade.class);
		criteria.createAlias("estado",  "e", JoinType.LEFT_OUTER_JOIN);
		criteria.add(Restrictions.eq("codigo", codigo));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (Cidade) criteria.uniqueResult();
	}
	
	private Long total(CidadeFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Cidade.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private void adicionarFiltro(CidadeFilter filtro, Criteria criteria) {
		if (filtro != null) {
			if (filtro.getEstado() != null) {
				criteria.add(Restrictions.eq("estado", filtro.getEstado()));
			}
			
			if (!StringUtils.isEmpty(filtro.getNome())) {
				criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
			}
		}
	}


}
