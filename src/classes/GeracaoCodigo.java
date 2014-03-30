package classes;

public class GeracaoCodigo {
	
	public static String relacao = "";
	private static GeracaoCodigo instance;
	private int reg;
	private boolean atribuicao;
	private boolean metodo, flagFor;
	private int label;
	private String incrementoFor;
	private String condicaoFor;
	private boolean exprFor;
	private String result;
	
	private GeracaoCodigo(){
		reg = -1;
		atribuicao = false;
		metodo = false;
		label = 0;
		flagFor = false;
		incrementoFor = "";
		condicaoFor = "";
		exprFor = false;
		result = "";
	}
	
	public static GeracaoCodigo getInstance(){
		if (instance == null){
			instance = new GeracaoCodigo();
		}
		return instance;
	}
	
	public String getCodigo(){
		return result;
	}
	
	public void iniciaAtribuicao(){
		atribuicao = true;
	}
	
	public void atribuicao(String var){
		result += "ST " + var + ", R" + reg+ "\n\n";
		atribuicao = false;
	}
	
	public void atribuicao(Variavel var){
		if (var.getTipo() != null){
			Funcao funcao = BlocoPrincipal.getInstance().getFuncaoContexto(var.getTipo());
			if (funcao != null){
				result += "ST " + var.getNome() + ", EAX"+ "\n\n";
			}else{
				result += "ST " + var.getNome() + ", R" + reg+ "\n\n";
			}
		}else{
			result += "ST " + var.getNome() + ", R" + reg+ "\n\n";
		}
		atribuicao = false;
	}
	
	public void LD(String var){
		if ((atribuicao || flagFor) && !exprFor){
			reg++;
			result += "LD R" + reg + ", " + var + "\n";
			
			if (flagFor){
				condicaoFor += "LD R" + reg + ", " + var + "\n";
			}
		}
	}
	
	public void soma(){
		result += "ADD R" + (reg) + ", R" + reg + ", R" + (reg-1) + "\n";
	}
	
	public void subtracao(){
		result += "SUB R" + reg + ", R" + reg + ", R" + (reg-1) + "\n";
	}
	
	public void divisao(){
		result += "DIV R" + (reg) + ", R" + reg + ", R" + (reg-1) + "\n";
	}
	
	public void multiplicacao(){
		result += "MUL R" + (reg) + ", R" + reg + ", R" + (reg-1) + "\n";
	}
	
	public void field(String tipo, String var){
		String tipoAux = "#\"\"";
		if (tipo.equals("il")){
			tipoAux = "#0";
		}else if(tipo.equals("bo")){
			tipoAux = "#false";
		}
		reg++;
		result += "LD R" + reg + ", " + tipoAux + "\n";
		result += "ST " + var + ", R" + reg + "\n\n";
	}
	
	public void iniciaMetodo(){
		metodo = !metodo;
	}
	
	public void metodo(String nome){
		result += "CALL " + nome + "\n";
		metodo = !metodo;
	}
	
	public void push(String var){
		if (metodo){
			result += "PUSH " + var + "\n";
		}
	}
	
	public void declaraMetodo(String nome){
		result += ".global " + nome + "\n";
		result += nome+":"+ "\n\n";
	}
	
	public void incremento(String var, String opt){
		reg++;
		
		if (flagFor){
			incrementoFor = "LD R" + reg + ", " + var + "\n";
			if (opt.equals("mais")){
				incrementoFor += "ADD R" + reg + ", R" + reg + ", #1" + "\n";
			}else{
				incrementoFor += "SUB R" + reg + ", R" + reg + ", #1" + "\n";
			}
			incrementoFor += "ST " + var + ", R" + reg + "\n";
			
		}else{
			result += "LD R" + reg + ", " + var + "\n";
			
			if (opt.equals("mais")){
				result += "ADD R" + reg + ", R" + reg + ", #1" + "\n";
			}else{
				result += "SUB R" + reg + ", R" + reg + ", #1" + "\n";
			}
			result += "ST " + var + ", R" + reg + "\n";
		}
	}
	
	public void iniciaFor(){
		result += ".INICIA_FOR:\n\n";
	}
	
	public void loopFor(){
		flagFor = false;
		exprFor = false;
		result += ".LF"+label+":\n\n";
		label++;
	}
	
	public void exp(){
		flagFor = true;
	}
	
	public void expressaoFor(){
		if (flagFor){
			exprFor = true;
			if (relacao.equals("menor")){
				result += "SUB R" + reg + ", R" + reg + ", R" + (reg-1) + "\n";
				result += "BGEZ R" + reg + ", .L" + (label+1) + "\n\n"; //condicao contraria
				condicaoFor += "SUB R" + reg + ", R" + reg + ", R" + (reg-1) + "\n";
				condicaoFor += "BLTZ R" + reg + ", .LF" + (label) + "\n"; //condicao certa
			}else if (relacao.equals("maior")){
				result += "SUB R" + reg + ", R" + reg + ", R" + (reg-1) + "\n";
				result += "BLEZ R" + reg + ", .L" + (label+1) + "\n\n"; //condicao contraria
				condicaoFor += "SUB R" + reg + ", R" + reg + ", R" + (reg-1) + "\n";
				condicaoFor += "BGTZ R" + reg + ", .LF" + (label) + "\n"; //condicao certa
			}
		}
	}
	
	public void finalizaFor(){
		result += incrementoFor + "\n";
		result += condicaoFor + "\n";
		result += ".L"+label+":\n\n";
		incrementoFor = "";
		condicaoFor = ""; 
		label++;
	}
}


