package com.algaworks.brewer.repository;

import com.algaworks.brewer.model.*;
import com.algaworks.brewer.repository.helper.cerveja.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface Cervejas extends JpaRepository<Cerveja, Long>, CervejasQueries {

}
