package classes;

import java.util.List;

public class Funcao implements ObjetoGramatica{
	
	private String nome, retorno, tipoObjeto;
	private List<Variavel> parametros;
	
	public Funcao(String nome, String retorno, List<Variavel> parametros, String tipoObjeto){
		this.nome = nome;
		this.retorno = retorno;
		this.parametros = parametros;
		this.tipoObjeto = tipoObjeto;
	}
	
	public String getNome(){
		return nome;
	}
	
	public String getRetorno(){
		return retorno;
	}
	
	public List<Variavel> getParametros(){
		return parametros;
	}

	@Override
	public String getTipoObjeto() {
		return tipoObjeto;
	}
}
