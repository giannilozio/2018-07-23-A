package it.polito.tdp.newufosightings.model;

public class Vicini {
	private String state1;
	private String state2;
	private Integer peso;
	
	
	public Vicini(String state1, String state2, Integer peso) {
		super();
		this.state1 = state1;
		this.state2 = state2;
		this.peso = peso;
	}
	
	public String getState1() {
		return state1;
	}
	public void setState1(String state1) {
		this.state1 = state1;
	}
	public String getState2() {
		return state2;
	}
	public void setState2(String state2) {
		this.state2 = state2;
	}
	public Integer getPeso() {
		return peso;
	}
	public void setPeso(Integer peso) {
		this.peso = peso;
	}
	
	

}
