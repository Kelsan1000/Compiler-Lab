import java.util.*;

/** An Abstract Syntax Tree (AST) node representing a procedure.
 *  Limitations:
 *  <ol>
 *   <li>the procedure must not expect any arguments (the <code>main</code>
 *       procedure is actually passed arguments, but will ignore them)</li>
 *   <li>the procedure's return type must be <code>int</code></li>
 *   <li>there is currently no provision for allocating local variables</li>
 *  </ol>
 */

public class Procedure {

	private String funcName;
	private Stmt body;
	private List<String> params, value;

	/** Constructs a <code>Procedure</code> with specified name and body.
	 *  @param funcName the procedure's name
	 *  @param body the statement to execute when the procedure is called
	 *  @param param the parameter when handling a condition in the Procedure
	 *  <code>value</code> grabs the value of our <code>param</code>
	 */

	public Procedure(String funcName, List<String> params, Stmt body){
		this.funcName = funcName;
		this.params = params;
		this.body = body;
		value = new ArrayList<String>();
		for (String param: params) {
			value.add(SymbolTable.getTable().getVal(param));
		}

	}

	/** Returns a <code>String</code> which is the name of the procedure.
	 */

	public String getName(){
		return funcName;
	}

	/** Returns a <code>int</code> which is the amount of paramters in the 
	 *  list of parameters, params
	 */

	public int getParamSize() {
		return params.size();
	}

	/** Returns a <code>String</code> which organizes the allocations to be on top
	 *  @param code the code
	 */

	private String organize(String code){
		Scanner sc = new Scanner(code);
		String alloca = "";
		String other = "";
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			if (line.contains("alloca")) {
				alloca += line + "\n";
			} else{
				other += line + "\n";
			}
		}
		sc.close();
		return alloca + other;
	}

	/** Generate the LLVM code that defines this procedure.
	 *  @return a string of LLVM code. To handle allocation issues
	 *  we pause the procedure, extract all instances where variables are being declared
	 *  and then reinsert them into the code at the beginning.
	 */

	public String toLLVM(){
		String start = "";
		String bodyCode = "\ndefine i32 @";
		bodyCode += funcName;
		bodyCode += "(";

		for (int i = 0; i < params.size(); i++) {
			String param = params.get(i);
			String paramVal = value.get(i);
			String tempReg = NameAllocator.getTempAllocator().next();

			if (i > 0) {
				bodyCode += ", ";
			}
			bodyCode += "i32 " + tempReg;

			start += "    " + paramVal + " = alloca i32; parameter " + param +
					"\n    store i32" + tempReg + ", i32* " + paramVal + "\n";
		}
		bodyCode += ") {\n";

		String total = bodyCode;
		total += start;
		total += organize(body.toLLVM());
		total += "}\n";

		return total;
	}
}


