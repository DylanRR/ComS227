/**
 * @author Dylanrr
 * @version Java 8 build 1.8.0_181-b13
 */
package lab2;

public class Atom {

	/** Instance variables **/
	private int protons;
	private int neutrons;
	private int electrons;
	
	/** 
	 * Main Constructor
	 * @param<integer> Protons
	 * @param<integer> Neutrons
	 * @param<integer> Electrons 
	**/
	public Atom(int givenProtons, int givenNeutrons, int givenElectrons) {
		protons = givenProtons;
		neutrons = givenNeutrons;
		electrons = givenElectrons;
	}	
	/**
	 * Atomic mass method
	 * Protons + neutrons 
	 * @return result
	 */
	public int getAtomicMass() {
		int result = protons + neutrons;
		return result;
	}
	
	/**
	 * Atomic charge method
	 * Protons - electrons
	 * @return result
	 */
	public int getAtomicCharge() {
		int result = protons - electrons;
		return result;
	}
	
	/**
	 * Decay Method
	 * protons - 2
	 * neutrons - 2
	 */
	public void decay() {
		protons = protons - 2;
		neutrons = neutrons - 2;
	}
}
