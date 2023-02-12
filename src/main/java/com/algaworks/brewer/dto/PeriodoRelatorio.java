package com.algaworks.brewer.dto;

import java.time.*;

public class PeriodoRelatorio {
	
	private LocalDate dataInicio;
	private LocalDate dataFim;
	
	public LocalDate getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}
	public LocalDate getDataFim() {
		return dataFim;
	}
	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}
	
}
