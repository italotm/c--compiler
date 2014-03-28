import java.io.FileReader;

public class Main {
	public static void main (String[] args) {
		try {
			LexicalAnalysis scanner = new LexicalAnalysis( new java.io.FileReader(args[0]) );
			parser p = new parser(scanner);
			p.parse();
			System.out.println("Compilou com sucesso!");
		} catch (Exception e) {	
			System.err.println(e.getMessage());
		}
	}

}

