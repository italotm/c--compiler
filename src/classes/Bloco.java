package classes;

import java.util.ArrayList;
import java.util.List;

public class Bloco {
	
	private int contexto;
	private List<ObjetoGramatica> objetos;
	private String tipo;
	
	public Bloco(int contexto, String tipo){
		this.contexto = contexto;
		objetos = new ArrayList<ObjetoGramatica>();
		this.tipo = tipo;
	}
	
	public void addObjeto(ObjetoGramatica objeto){
		objetos.add(objeto);
	}
	
	public ObjetoGramatica getObjeto(String nome, String tipoObjeto){
		for (ObjetoGramatica objeto : objetos) {
			if (objeto.getTipoObjeto().equals(tipoObjeto)){
				if (objeto.getNome().equals(nome)){
					return objeto;
				}
			}
		}
		return null;
	}
	
	public int getContexto(){
		return contexto;
	}
	
	public String getTipo(){
		return tipo;
	}
}
