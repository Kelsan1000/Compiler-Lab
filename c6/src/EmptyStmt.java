/** A <code>EmptyStmt</code> takes in nothing and returns empty string
 */

public class EmptyStmt extends Stmt {

	

	/** Constructs a <code>EmptyStmt</code> from nothing
	 */

	public EmptyStmt(){
		
	}

	public String toLLVM(){
		return "";	
	}


}
