import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/** This class provides a <code>main</code> method for a compiler,
 *  which reads in a source program from a file and writes out
 *  LLVM assembly code to another file.
 */

public class Compiler {

	/** Compiles a source file, writing out the LLVM code into another file.
	 *  @param args command line arguments; should contain two: the source and assembly filenames
	 */

	private static int counter = 0;

	static public void main(String[] args) throws Exception{

		String inputFileName = null;
		String outputFileName = null;
		boolean isDebugOn = false;

		if(args.length == 2){
			inputFileName = args[0];
			outputFileName = args[1];
		} else if(args.length == 3 && args[0].equals("-debug")){
			inputFileName = args[1];
			outputFileName = args[2];
			isDebugOn = true;
		} else {
			System.err.println("Usage: java Compiler [-debug] inputfile outputfile");
			System.exit(1);
		}

		Parser parser = new Parser(new Yylex(new FileReader(inputFileName)));

		java_cup.runtime.Symbol parseTree;

		if(isDebugOn)
			parseTree = parser.debug_parse();
		else
			parseTree = parser.parse();

		SequenceProc mainProc = (SequenceProc) parseTree.value;
		if (counter > 0){
			System.exit(42);
		}
		String mainCode = mainProc.toLLVM();

		try {
				FileWriter output = new FileWriter(outputFileName);
				output.write("target triple = \"" + Target.TRIPLE + "\"\n");
				output.write(mainCode);
				output.close();
		} catch(IOException e){
			System.err.println("I/O exception: " + e);
			System.exit(1);
		}
	}

	private Compiler(){}  // purely static class; no public default constructor

	private static final int ERRORLIMIT = 4;

	/**
	 *  Will take account for the errors made in the program and tell the user
	 *  upto five of the errors.
	 *  @param message is the error message to be displayed
	 */
	
	static void error(String message){
		System.out.print(message);
		if(counter++ == ERRORLIMIT){
			System.exit(25);
		}
	}
}
