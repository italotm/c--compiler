package classes;

import java.util.ArrayList;
import java.util.List;

public class Bloco {
	
	private String nome;
	private List<Variavel> variaveis;
	private List<Bloco> blocos;
	
	public Bloco(String nome){
		this.nome = nome;
		variaveis = new ArrayList<Variavel>();
		blocos = new ArrayList<Bloco>();
	}
	
	public void addVariavel(Variavel variavel){
		variaveis.add(variavel);
	}
	
	public Variavel getVariavel(String var){
		for (Variavel variavel : variaveis) {
			if (var.equals(variavel.getNome())){
				return variavel;
			}
		}
		return null;
	}
	
	public String getNome(){
		return nome;
	}
}
