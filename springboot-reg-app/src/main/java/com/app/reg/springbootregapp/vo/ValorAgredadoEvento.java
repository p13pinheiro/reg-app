package com.app.reg.springbootregapp.vo;

public class ValorAgredadoEvento {

	private double valorTotal;
	private double valorPorParticipante;
	
	public ValorAgredadoEvento() {}
	
	public ValorAgredadoEvento(double valorTotal, double valorPorParticipante) {
		this.valorTotal = valorTotal;
		this.valorPorParticipante = valorPorParticipante;
	}
	
	public double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public double getValorPorParticipante() {
		return valorPorParticipante;
	}
	public void setValorPorParticipante(double valorPorParticipante) {
		this.valorPorParticipante = valorPorParticipante;
	}
}
