import java.io.FileReader;

import classes.GeracaoCodigo;

public class Main {
	public static void main (String[] args) {
		try {
			LexicalAnalysis scanner = new LexicalAnalysis( new java.io.FileReader(args[0]) );
			parser p = new parser(scanner);
			p.parse();
			System.out.println("Compilou com sucesso!\n");
			System.out.println(GeracaoCodigo.getInstance().getCodigo());
		} catch (Exception e) {	
			System.err.println(e.getMessage());
		}
	}

}

