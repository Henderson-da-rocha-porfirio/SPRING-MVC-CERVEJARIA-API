package com.algaworks.brewer.repository.helper.estilo;

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

public class EstilosImpl implements EstilosQueries {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Estilo> filtrar(EstiloFilter filtro, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Estilo.class);
		
		paginacaoUtil.preparar(criteria, pageable);
		adicionarFiltro(filtro, criteria);
		
		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}
	
	@Transactional(readOnly = true)
	@Override
	public Estilo buscarComCervejas(Long codigo) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Estilo.class);
		criteria.createAlias("cervejas",  "c", JoinType.LEFT_OUTER_JOIN);
		criteria.add(Restrictions.eq("codigo", codigo));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (Estilo) criteria.uniqueResult();	
		
	}
	
	private Long total(EstiloFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Estilo.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private void adicionarFiltro(EstiloFilter filtro, Criteria criteria) {
		if (filtro != null) {
			if (!StringUtils.isEmpty(filtro.getNome())) {
				criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
			}
		}
	}


}
