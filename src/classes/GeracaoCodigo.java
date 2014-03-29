package classes;

public class GeracaoCodigo {
	
	private static GeracaoCodigo instance;
	private int reg;
	private boolean atribuicao;
	private boolean metodo, flagFor;
	private int label;
	
	private GeracaoCodigo(){
		reg = -1;
		atribuicao = false;
		metodo = false;
		label = 0;
		flagFor = false;
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
		System.out.println("ST " + var + ", R" + reg+ "\n");
		atribuicao = false;
	}
	
	public void atribuicao(Variavel var){
		if (var.getTipo() != null){
			Funcao funcao = BlocoPrincipal.getInstance().getFuncaoContexto(var.getTipo());
			if (funcao != null){
				System.out.println("ST " + var.getNome() + ", EAX"+ "\n");
			}else{
				System.out.println("ST " + var.getNome() + ", R" + reg+ "\n");
			}
		}else{
			System.out.println("ST " + var.getNome() + ", R" + reg+ "\n");
		}
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
		System.out.println("ST " + var + ", R" + reg + "\n");
	}
	
	public void iniciaMetodo(){
		metodo = !metodo;
	}
	
	public void metodo(String nome){
		System.out.println("CALL " + nome);
		metodo = !metodo;
	}
	
	public void push(String var){
		if (metodo){
			System.out.println("PUSH " + var);
		}
	}
	
	public void declaraMetodo(String nome){
		System.out.println(".global " + nome);
		System.out.println(nome+":"+ "\n");
	}
	
	public void incremento(String var, String opt){
		reg++;
		System.out.println("LD R" + reg + ", " + var);
		
		if (opt.equals("mais")){
			System.out.println("ADD R" + reg + ", R" + reg + ", #1");
		}else{
			System.out.println("SUB R" + reg + ", R" + reg + ", #1");
		}
		System.out.println("ST " + var + ", R" + reg);
	}
	
	public void iniciaFor(){
		flagFor = true;
		System.out.println("label_for"+label+":\n");
		label++;
	}
}
