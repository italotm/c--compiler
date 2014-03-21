package classes;

import java.util.ArrayList;
import java.util.List;

public class BlocoPrincipal {
	
	private static BlocoPrincipal instance;
	private List<Bloco> listaBlocos;
	private Bloco bloco;
	
	private BlocoPrincipal(){
		listaBlocos = new ArrayList<Bloco>();
	}
	
	public static BlocoPrincipal getInstance(){
		if (instance == null){
			instance = new BlocoPrincipal();
		}
		return instance;
	}
	
	public void iniciaBloco(String nome){
		bloco = new Bloco(nome);
		listaBlocos.add(bloco);
	}
	
	public void addBloco(Variavel variavel){
		bloco.addVariavel(variavel);
	}
	
	public Variavel getVariavel(String var){
		for (Bloco bloco : listaBlocos) {
			if (bloco.getVariavel(var) != null){
				return bloco.getVariavel(var);
			}
		}
		return null;
	}
	
	public boolean contemBloco(String nome){
		for (Bloco bloco : listaBlocos) {
			if (bloco.getNome().equals(nome)){
				return true;
			}
		}
		return false;
	}
}
