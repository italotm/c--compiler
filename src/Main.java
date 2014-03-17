import java.io.FileReader;

public class Main {
	public static void main (String[] args) {
		try {
			LexicalAnalysis scanner = new LexicalAnalysis( new java.io.FileReader(args[0]) );
			parser p = new parser(scanner);
			System.out.println(p.parse());
		} catch (Exception e) {	
			System.out.println(e);
		}
	}

}

