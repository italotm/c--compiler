package classes;

import java.util.ArrayList;
import java.util.List;

public class Verificador {
	
	public static List<Variavel> listaAux = new ArrayList<Variavel>();
	public static int linha = 0;
	
	public static void verificaTipo(String tipo, Variavel variavel) throws Exception{
		if (BlocoPrincipal.getInstance().contemClasse(tipo)){
			if (!(variavel.getTipo().equals(tipo))){
				Classe classe = BlocoPrincipal.getInstance().getClasse(variavel.getTipo());
				if (classe.getHeranca() == null){
					throw new Exception("Erro semantico na linha "+ linha);
				}else{
					if (!(classe.getHeranca().equals(tipo))){
						throw new Exception("Erro semantico na linha "+ linha);
					}else{
						variavel.setTipo(classe.getHeranca());
						BlocoPrincipal.getInstance().addBloco(variavel);
					}
				}
			}else{
				BlocoPrincipal.getInstance().addBloco(variavel);
			}
		}else{
			if (!(BlocoPrincipal.getInstance().contemClasse(variavel.getTipo()))){
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
								throw new Exception("Erro semantico na linha "+ linha);
							}
						}else{
							Funcao funcao = BlocoPrincipal.getInstance().getFuncaoContexto(variavel.getTipo());
							if (funcao != null){
								if (funcao.getRetorno().equals(tipo)){
									variavel.setTipo(tipo);
									BlocoPrincipal.getInstance().addBloco(variavel);
								}else{
									throw new Exception("Erro semantico na linha "+ linha);
								}
							}else{
								throw new Exception("Erro semantico na linha "+ linha);
							}
						}
					}
				}else{
					Funcao funcao = BlocoPrincipal.getInstance().getFuncaoContexto(variavel.getTipo());
					if (funcao != null){
						if (funcao.getRetorno().equals(tipo)){
							variavel.setTipo(tipo);
							BlocoPrincipal.getInstance().addBloco(variavel);
						}else{
							throw new Exception("Erro semantico na linha "+ linha);
						}
					}else{
						throw new Exception("Erro semantico na linha "+ linha);
					}
				}
			}else{
				if (!(tipo.equals(variavel.getTipo()))){
					throw new Exception("Erro semantico na linha "+ linha);
				}
			}
		}
	}
	
	public static void verificaAtribuicao(String var1, String var2, int linha) throws Exception{
		Variavel variavel1 = BlocoPrincipal.getInstance().getVariavelContexto(var1);
		Variavel variavel2 = BlocoPrincipal.getInstance().getVariavelContexto(var2);
		Funcao funcao = BlocoPrincipal.getInstance().getFuncaoContexto(var2);
		
		if (variavel1 == null){
			throw new Exception("Erro semantico na linha "+ linha);
		}else{
			if (variavel2 == null){
				if (funcao == null){
					if (!(var2.equals(variavel1.getTipo()))){
						Classe classe = BlocoPrincipal.getInstance().getClasse(var2);

						if (classe != null){
							if (!(classe.getNome().equals(variavel1.getTipo()) || classe.getHeranca().equals(variavel1.getTipo()))){
								throw new Exception("Erro semantico na linha "+ linha);
							}
						}else{
							throw new Exception("Erro semantico na linha "+ linha);
						}
					}
				}else{
					if (!(variavel1.getTipo().equals(funcao.getRetorno()))){
						throw new Exception("Erro semantico na linha "+ linha);
					}else{
						verificarMetodo(var2);
					}
				}
			}else{
				if (!(variavel1.getTipo().equals(variavel2.getTipo()))){
					throw new Exception("Erro semantico na linha "+ linha);
				}
			}
		}
	}
	
	public static void verificarVariavel(String var, int linha) throws Exception{
		if (BlocoPrincipal.getInstance().getVariavelContexto(var) == null){
			throw new Exception("Erro semantico na linha "+ linha);
		}
	}
	
	public static void verificarRelacao(String var1, String var2, int linha) throws Exception{
		String var1_aux = var1;
		Variavel variavel1 = BlocoPrincipal.getInstance().getVariavelContexto(var1);
		
		if (variavel1 != null){
			var1_aux = variavel1.getTipo();
		}
		
		if (!(var1_aux.equals("il") || var1_aux.equals("fl") || var1_aux.equals("lo") || var1_aux.equals("de") || var1_aux.equals("do") || var1_aux.equals("ch"))){
			throw new Exception("Erro semantico na linha "+ linha);
		}
		
		
		String var2_aux = var2;
		Variavel variavel2 = BlocoPrincipal.getInstance().getVariavelContexto(var2);
		
		if (variavel2 != null){
			var2_aux = variavel2.getTipo();
		}
		
		if (!(var2_aux.equals("il") || var2_aux.equals("fl") || var2_aux.equals("lo") || var2_aux.equals("de") || var2_aux.equals("do") || var2_aux.equals("ch"))){
			throw new Exception("Erro semantico na linha "+ linha);
		}
		
		if (!(var1_aux.equals(var2_aux))){
			throw new Exception("Erro semantico na linha "+ linha);
		}
	}
	
	public static void verificarIgualdade(String var1, String var2, int linha) throws Exception{
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
			throw new Exception("Erro semantico na linha "+ linha);
		}
	}
	
	public static void verificarFor(String exp, int linha) throws Exception{
		if (!(exp.equals("bo"))){
			throw new Exception("Erro semantico na linha "+ linha);
		}
	}
	
	public static void verificarMetodo(String exp) throws Exception{
		Funcao funcao = BlocoPrincipal.getInstance().getFuncaoContexto(exp);
		
		if (funcao == null){
			throw new Exception("Erro semantico na linha " + linha);
		}else{
			if (funcao.getParametros().size() == listaAux.size()){
				for (int i = 0; i < listaAux.size(); i++) {
					if (!(listaAux.get(i).getNome().equals(funcao.getParametros().get(i).getTipo()))){
						Variavel varAux = BlocoPrincipal.getInstance().getVariavelContexto(listaAux.get(i).getNome());
						if (varAux != null){
							if (!(varAux.getTipo().equals(funcao.getParametros().get(i).getTipo()))){
								throw new Exception("Erro semantico na linha "+ linha);
							}
						}else{
							Funcao f = BlocoPrincipal.getInstance().getFuncaoContexto(listaAux.get(i).getNome());
							if (f != null){
								if (!(f.getRetorno().equals(funcao.getParametros().get(i).getTipo()))){
									throw new Exception("Erro semantico na linha "+ linha);
								}
							}else{
								throw new Exception("Erro semantico na linha "+ linha);
							}
						}
					}
				}
			}else{
				throw new Exception("Erro semantico na linha " + linha);
			}
		}
		listaAux.clear();
	}
	
	public static void addFuncao(Funcao funcao) throws Exception{
		for (Variavel var : listaAux) {
			funcao.addParametro(var);
		}
		listaAux.clear();
		if (BlocoPrincipal.getInstance().getFuncaoContexto(funcao.getNome()) != null){
			throw new Exception("Erro semantico na linha " + linha);
		}else{
			BlocoPrincipal.getInstance().addBloco(funcao);
		}
	}
	
	public static void addParametro(Variavel var){
		if (var != null){
			listaAux.add(var);
		}
	}
	
	public static void verificarVariavelInteiro(String var, int linha) throws Exception{
		Variavel varAux = BlocoPrincipal.getInstance().getVariavelContexto(var);
		if (varAux == null){
			if (!(var.equals("il"))){
				throw new Exception("Erro semantico na linha "+ linha);
			}
		}else{
			if (!(varAux.getTipo().equals("il"))){
				throw new Exception("Erro semantico na linha "+ linha);
			}
		}
	}
}
