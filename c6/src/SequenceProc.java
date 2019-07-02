import java.util.*;
public class SequenceProc{
	private static Map<String,Procedure> procList = new HashMap<String,Procedure>();
	private static Map<String, DeclareProc> decProcList = new HashMap<String,DeclareProc>();

	/** Empties the maps holding the procedures
	 */

	public SequenceProc(){
		procList.clear();
		decProcList.clear();
	}

	/** Constructs a <code>SequenceProc</code> with specified name and body.
	 *  @param proc the procedure
	 */

	public SequenceProc(Procedure proc){
		this.addProc(proc);
	}

	/** Constructs a <code>SequenceProc</code> with specified name and body.
	 *  @param decProc the procedure declaration
	 */

	public SequenceProc(DeclareProc decProc){
		this.addProc(decProc);
	}

	/** Allows a procedure to be added to the procedure list
	 *  @param proc the procedure
	 */
	  
	public void addProc(Procedure proc){
		String procName = proc.getName();
		if (decProcList.containsKey(procName)){
			decProcList.remove(procName);
		}
		procList.put(procName, proc);
	}

	/** Allows a procedure to be added to the procedure declaration list
	 *  @param proc the procedure
	 */

	public void addProc(DeclareProc proc){
		String procName = proc.getName();
		if (!decProcList.containsKey(procName) && !procList.containsKey(procName)){
			decProcList.put(procName,proc);
		}
	}

	/** Checks to see if a procedure has already been declared
	 *  @param procName the name of the procedure
	 *  @param paramSize the amount of procedures
	 */

	public static boolean decChecker(String procName, int paramSize){
		DeclareProc decProc = decProcList.get(procName);
		return ((decProc != null) && (paramSize == decProc.getParamSize()));
	}

	/** Checks to see if a procedure has been defined before
	 *  @param procName the name of the procedure
	 *  @param paramSize the amount of procedures
	 */

	public static boolean defChecker(String procName, int paramSize){
		Procedure proc = procList.get(procName);
		return ((proc != null) && (paramSize == proc.getParamSize()));
	}

	/** Checks to see if it is a definition 
	 *  @param procName the name of the procedure
	 */

	public static boolean isDef(String procName){
		return (procList.get(procName) != null);
	}

	/** Checks to see if a procedure is in the list
	 *  @param procName the name of the procedure
	 *  @param paramSize the amount of procedures
	 */

	public static boolean contains(String procName, int paramSize){
		Procedure proc = procList.get(procName);
		DeclareProc decProc = decProcList.get(procName);

		return ((decProc != null) && (paramSize == decProc.getParamSize()) 
			|| (proc != null) && (paramSize == proc.getParamSize()));
	}

	public String toLLVM(){
		String code = "";
		for (DeclareProc dec: decProcList.values()){
			code += dec.toLLVM();
		}
		for (Procedure p: procList.values()){
			code += p.toLLVM();
		}
		return code;
	}
}