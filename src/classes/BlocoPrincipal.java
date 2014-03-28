package classes;

import java.util.ArrayList;
import java.util.List;

public class BlocoPrincipal {
	
	private static BlocoPrincipal instance;
	private List<Bloco> listaBlocos;
	private List<Classe> classes;
	private Bloco bloco;
	private int contexto;
	
	private BlocoPrincipal(){
		listaBlocos = new ArrayList<Bloco>();
		classes = new ArrayList<Classe>();
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
			classes.add(new Classe(tipo.split(";")[0], null, "classe"));
			bloco = new Bloco(contexto, tipo);
		}
		listaBlocos.add(bloco);
	}
	
	public void iniciaBloco(String tipo, String heranca) throws Exception{
		if (!contemClasse(heranca)){
			throw new Exception("Classe " + heranca + " não declarada na linha " + Verificador.linha);
		}
		
		contexto++;
		if (bloco != null){
			bloco = new Bloco(contexto, bloco.getTipo()+tipo);
		}else{
			classes.add(new Classe(tipo.split(";")[0], heranca, "classe"));
			bloco = new Bloco(contexto, tipo);
		}
		listaBlocos.add(bloco);
	}
	
	public void finalizaBloco(){
		contexto--;
		if (contexto > 0){
			String nomeClasse = bloco.getTipo().split(";")[0];
			for (Bloco bloco : listaBlocos) {
				if (bloco.getContexto() == contexto){
					if (this.bloco.getTipo().equals(bloco.getTipo()) || bloco.getTipo().equals(nomeClasse+";")){
						this.bloco = bloco; 
					}
				}
			}
		}else{
			bloco = null;
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
		String nomeClasse = bloco.getTipo().split(";")[0];
		for (Bloco bloco : listaBlocos) {
			if (this.bloco.getTipo().equals(bloco.getTipo()) || bloco.getTipo().equals(nomeClasse+";")){
				if (bloco.getContexto() <= contexto){
					ObjetoGramatica objeto = bloco.getObjeto(nome, "funcao");
					if (objeto != null){
						return ((Funcao) objeto);
					}
				}
			}
		}
		return null;
	}
	
	public boolean contemClasse(String nome){
		for (Classe classe : classes) {
			if (classe.getNome().equals(nome)){
				return true;
			}
		}
		return false;
	}
	
	public Classe getClasse(String nome){
		for (Classe classe : classes) {
			if (classe.getNome().equals(nome)){
				return classe;
			}
		}
		return null;
	}
}
