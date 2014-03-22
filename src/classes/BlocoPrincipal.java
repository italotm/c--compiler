package classes;

import java.util.ArrayList;
import java.util.List;

public class BlocoPrincipal {
	
	private static BlocoPrincipal instance;
	private List<Bloco> listaBlocos;
	private Bloco bloco;
	private int contexto;
	
	private BlocoPrincipal(){
		listaBlocos = new ArrayList<Bloco>();
		contexto = 0;
	}
	
	public static BlocoPrincipal getInstance(){
		if (instance == null){
			instance = new BlocoPrincipal();
		}
		return instance;
	}
	
	public void iniciaBloco(String tipo){
		contexto++;
		if (bloco != null){
			bloco = new Bloco(contexto, bloco.getTipo()+tipo);
		}else{
			bloco = new Bloco(contexto, tipo);
		}
		listaBlocos.add(bloco);
	}
	
	public void finalizaBloco(){
		contexto--;
		for (Bloco bloco : listaBlocos) {
			if (bloco.getContexto() == contexto){
				this.bloco = bloco; 
			}
		}
	}
	
	public void addBloco(ObjetoGramatica objetoGramatica){
		bloco.addObjeto(objetoGramatica);
	}
	
	public Variavel getVariavelContexto(String var){
		String nomeClasse = bloco.getTipo().split(";")[0];
		for (Bloco bloco : listaBlocos) {
			if (this.bloco.getTipo().equals(bloco.getTipo()) || bloco.getTipo().equals(nomeClasse+";")){
				if (bloco.getContexto() <= contexto){
					ObjetoGramatica objeto = bloco.getObjeto(var, "variavel");
					if (objeto != null){
						return ((Variavel) objeto);
					}
				}
			}
		}
		return null;
	}
	
	public Funcao getFuncaoContexto(String nome){
		for (Bloco bloco : listaBlocos) {
			if (bloco.getContexto() <= contexto){
				ObjetoGramatica objeto = bloco.getObjeto(nome, "funcao");
				if (objeto != null){
					return ((Funcao) objeto);
				}
			}
		}
		return null;
	}
}
