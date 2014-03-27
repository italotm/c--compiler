package classes;

public class Classe implements ObjetoGramatica{
	
	private String nome, heranca, tipoObjeto;
	
	public Classe(String nome, String heranca, String tipoObjeto){
		this.nome = nome;
		this.heranca = heranca;
		this.tipoObjeto = tipoObjeto;
	}
	
	@Override
	public String getTipoObjeto() {
		return tipoObjeto;
	}

	@Override
	public String getNome() {
		return nome;
	}
	
	public String getHeranca(){
		return heranca;
	}

}
