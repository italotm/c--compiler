package classes;

public class Variavel implements ObjetoGramatica{
	
	private String nome, tipo, tipoObjeto;
	
	public Variavel(String nome, String tipo, String tipoObjeto){
		this.nome = nome;
		this.tipo = tipo;
		this.tipoObjeto = tipoObjeto;
	}
	
	public String getNome(){
		return nome;
	}
	
	public String getTipo(){
		return tipo;
	}
	
	public void setTipo(String tipo){
		this.tipo = tipo;
	}

	@Override
	public String getTipoObjeto() {
		return tipoObjeto;
	}
}
