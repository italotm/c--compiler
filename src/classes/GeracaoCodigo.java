package classes;

public class GeracaoCodigo {
	
	private static GeracaoCodigo instance;
	private int reg;
	private boolean atribuicao;
	private boolean metodo;
	
	private GeracaoCodigo(){
		reg = -1;
		atribuicao = false;
		metodo = false;
	}
	
	public static GeracaoCodigo getInstance(){
		if (instance == null){
			instance = new GeracaoCodigo();
		}
		return instance;
	}
	
	public void iniciaAtribuicao(){
		atribuicao = true;
	}
	
	public void atribuicao(String var){
		System.out.println("ST " + var + ", R" + reg);
		atribuicao = false;
	}
	
	public void LD(String var){
		if (atribuicao){
			reg++;
			System.out.println("LD R" + reg + ", " + var);
		}
	}
	
	public void soma(){
		System.out.println("ADD R" + (reg) + ", R" + reg + ", R" + (reg-1));
	}
	
	public void subtracao(){
		System.out.println("SUB R" + reg + ", R" + reg + ", R" + (reg-1));
	}
	
	public void divisao(){
		System.out.println("DIV R" + (reg) + ", R" + reg + ", R" + (reg-1));
	}
	
	public void multiplicacao(){
		System.out.println("MUL R" + (reg) + ", R" + reg + ", R" + (reg-1));
	}
	
	public void field(String tipo, String var){
		String tipoAux = "#\"\"";
		if (tipo.equals("il")){
			tipoAux = "#0";
		}else if(tipo.equals("bo")){
			tipoAux = "#false";
		}
		reg++;
		System.out.println("LD R" + reg + ", " + tipoAux);
		System.out.println("ST " + var + ", R" + reg);
	}
	
	public void iniciaMetodo(){
		metodo = true;
	}
	
	public void metodo(String nome){
		System.out.println("CALL " + nome);
		metodo = false;
	}
	
	public void push(String var){
		if (metodo){
			System.out.println("PUSH " + var);
		}
	}
}
