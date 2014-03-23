package classes;

import java.util.ArrayList;
import java.util.List;

public class Verificador {
	
	public static List<Variavel> listaAux = new ArrayList<Variavel>();
	
	public static void verificaTipo(String tipo, Variavel variavel){
		if (BlocoPrincipal.getInstance().getVariavelContexto(variavel.getNome()) == null){
			
			if (variavel.getTipo() == null){
				variavel.setTipo(tipo);
			}
			
			if (tipo.equals(variavel.getTipo())){
				BlocoPrincipal.getInstance().addBloco(variavel);
			}else{
				if (BlocoPrincipal.getInstance().getVariavelContexto(variavel.getTipo()) != null){
					if (BlocoPrincipal.getInstance().getVariavelContexto(variavel.getTipo()).getTipo().equals(tipo)){
						variavel.setTipo(tipo);
						BlocoPrincipal.getInstance().addBloco(variavel);
					}else{
						System.out.println("Erro de tipo!");
					}
				}else{
					if (BlocoPrincipal.getInstance().getFuncaoContexto(variavel.getTipo()).getRetorno().equals(tipo)){
						variavel.setTipo(tipo);
						BlocoPrincipal.getInstance().addBloco(variavel);
					}else{
						System.out.println("Erro de tipo!");
					}
				}
			}
		}else{
			System.out.println("Variavel ja declarada");
		}
	}
	
	public static void verificaAtribuicao(String var1, String var2){
		Variavel variavel1 = BlocoPrincipal.getInstance().getVariavelContexto(var1);
		Variavel variavel2 = BlocoPrincipal.getInstance().getVariavelContexto(var2);
		Funcao funcao = BlocoPrincipal.getInstance().getFuncaoContexto(var2);
		
		if (variavel1 == null){
			System.out.println("Variavel não declarada");
		}else{
			if (variavel2 == null){
				if (funcao == null){
					if (!(var2.equals(variavel1.getTipo()))){
						System.out.println("Variavel não declarada");
					}
				}else{
					if (!(variavel1.getTipo().equals(funcao.getRetorno()))){
						System.out.println("Erro de tipo!");
					}else{
						verificarMetodo(var2);
					}
				}
			}else{
				if (!(variavel1.getTipo().equals(variavel2.getTipo()))){
					System.out.println("Erro de tipo!");
				}
			}
		}
	}
	
	public static void verificarVariavel(String var){
		if (BlocoPrincipal.getInstance().getVariavelContexto(var) == null){
			System.out.println("Variavel não declarada");
		}
	}
	
	public static void verificarRelacao(String var1, String var2){
		String var1_aux = var1;
		Variavel variavel1 = BlocoPrincipal.getInstance().getVariavelContexto(var1);
		
		if (variavel1 != null){
			var1_aux = variavel1.getTipo();
		}
		
		if (!(var1_aux.equals("il") || var1_aux.equals("fl") || var1_aux.equals("lo") || var1_aux.equals("de") || var1_aux.equals("do") || var1_aux.equals("ch"))){
			System.out.println("Erro de tipo");
			return;
		}
		
		
		String var2_aux = var2;
		Variavel variavel2 = BlocoPrincipal.getInstance().getVariavelContexto(var2);
		
		if (variavel2 != null){
			var2_aux = variavel2.getTipo();
		}
		
		if (!(var2_aux.equals("il") || var2_aux.equals("fl") || var2_aux.equals("lo") || var2_aux.equals("de") || var2_aux.equals("do") || var2_aux.equals("ch"))){
			System.out.println("Erro de tipo");
			return;
		}
		
		if (!(var1_aux.equals(var2_aux))){
			System.out.println("Erro de tipo!");
		}
	}
	
	public static void verificarIgualdade(String var1, String var2){
		String var1_aux = var1;
		Variavel variavel1 = BlocoPrincipal.getInstance().getVariavelContexto(var1);
		String var2_aux = var2;
		Variavel variavel2 = BlocoPrincipal.getInstance().getVariavelContexto(var2);
		
		if (variavel1 != null){
			var1_aux = variavel1.getTipo();
		}
		
		if (variavel2 != null){
			var2_aux = variavel2.getTipo();
		}
		
		if (!(var1_aux.equals(var2_aux))){
			System.out.println("Erro de tipo!");
		}
	}
	
	public static void verificarFor(String exp){
		if (!(exp.equals("bo"))){
			System.out.println("Erro de tipo!");
		}
	}
	
	public static void verificarMetodo(String exp){
		Funcao funcao = BlocoPrincipal.getInstance().getFuncaoContexto(exp);
		if (funcao == null){
			System.out.println("Funcao nao declarada!");
		}else{
			if (funcao.getParametros().size() == listaAux.size()){
				for (int i = 0; i < listaAux.size(); i++) {
					if (!(listaAux.get(i).getNome().equals(funcao.getParametros().get(i).getTipo()))){
						Variavel varAux = BlocoPrincipal.getInstance().getVariavelContexto(listaAux.get(i).getNome());
						if (varAux != null){
							if (!(varAux.getTipo().equals(funcao.getParametros().get(i).getTipo()))){
								System.out.println("Erro tipo!");
							}
						}else{
							Funcao f = BlocoPrincipal.getInstance().getFuncaoContexto(listaAux.get(i).getNome());
							if (f != null){
								if (!(f.getRetorno().equals(funcao.getParametros().get(i).getTipo()))){
									System.out.println("Erro de tipo!");
								}
							}else{
								System.out.println("Erro de tipo!");
							}
						}
					}
				}
			}
		}
		listaAux.clear();
	}
	
	public static void addFuncao(Funcao funcao){
		for (Variavel var : listaAux) {
			funcao.addParametro(var);
		}
		listaAux.clear();
		if (BlocoPrincipal.getInstance().getFuncaoContexto(funcao.getNome()) != null){
			System.out.println("Funcao ja declarada!");
		}else{
			BlocoPrincipal.getInstance().addBloco(funcao);
		}
	}
	
	public static void addParametro(Variavel var){
		if (var != null){
			listaAux.add(var);
		}
	}
}
