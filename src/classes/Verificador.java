package classes;

public class Verificador {
	
	public static void verificaTipo(String tipo, Variavel variavel){
		if (BlocoPrincipal.getInstance().getVariavel(variavel.getNome()) == null){
		
			if (variavel.getTipo() == null){
				variavel.setTipo(tipo);
			}
			
			if (tipo.equals(variavel.getTipo())){
				BlocoPrincipal.getInstance().addBloco(variavel);
			}else{
				if (BlocoPrincipal.getInstance().getVariavel(variavel.getTipo()) != null){
					if (BlocoPrincipal.getInstance().getVariavel(variavel.getTipo()).getTipo().equals(tipo)){
						variavel.setTipo(tipo);
						BlocoPrincipal.getInstance().addBloco(variavel);
					}else{
						System.out.println("Erro de tipo!");
					}
				}else{
					System.out.println("Erro de tipo!");
				}
			}
		}else{
			System.out.println("Variavel ja declarada");
		}
	}
	
	public static void verificaAtribuicao(String var1, String var2){
		Variavel variavel1 = BlocoPrincipal.getInstance().getVariavel(var1);
		Variavel variavel2 = BlocoPrincipal.getInstance().getVariavel(var2);
		
		if (variavel1 == null){
			System.out.println("Variavel não declarada");
		}else{
			if (variavel2 == null){
				if (!(var2.equals(variavel1.getTipo()))){
					System.out.println("Variavel não declarada");
				}
			}else{
				if (!(variavel1.getTipo().equals(variavel2.getTipo()))){
					System.out.println("Erro de tipo!");
				}
			}
		}
	}
	
	public static void verificarVariavel(String var){
		if (BlocoPrincipal.getInstance().getVariavel(var) == null){
			System.out.println("Variavel não declarada");
		}
	}
	
	public static void verificarRelacao(String var1, String var2){
		String var1_aux = var1;
		Variavel variavel1 = BlocoPrincipal.getInstance().getVariavel(var1);
		
		if (variavel1 != null){
			var1_aux = variavel1.getTipo();
		}
		
		if (!(var1_aux.equals("il") || var1_aux.equals("fl") || var1_aux.equals("lo") || var1_aux.equals("de") || var1_aux.equals("do") || var1_aux.equals("ch"))){
			System.out.println("Erro de tipo");
			return;
		}
		
		
		String var2_aux = var2;
		Variavel variavel2 = BlocoPrincipal.getInstance().getVariavel(var2);
		
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
		Variavel variavel1 = BlocoPrincipal.getInstance().getVariavel(var1);
		String var2_aux = var2;
		Variavel variavel2 = BlocoPrincipal.getInstance().getVariavel(var2);
		
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
		if (!(BlocoPrincipal.getInstance().contemBloco(exp))){
			System.out.println("Funcao nao declarada!");
		}
	}
}
