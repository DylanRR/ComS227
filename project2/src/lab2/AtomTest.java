/**
 * @author Dylanrr
 * @version Java 8 build 1.8.0_181-b13
 * @Desc Test class for Atom.java
 */
package lab2;

public class AtomTest {

	/** Main method
	 * @call Atom method to create new constructor
	 * @set Atom's name to a string
	 * Prints Atom name, Atomic mass, Atomic charge
	 * @call decay method
	 * Prints Atom name, Atomic mass, Atomic charge
	 * @param args
	 */	
	public static void main(String[] args) {

		Atom Uranium238 = new Atom(92,146,92);
		String uraniumName = "Uranium-238";
		System.out.println(" Before Decay \n" + "Name: " + uraniumName + "\n" + "Atomic mass: " + Uranium238.getAtomicMass() + "\n" + "Atomic charge: " + Uranium238.getAtomicCharge());
		Uranium238.decay();
		System.out.println("\n \n After Decay \n" + "Name: " + uraniumName + "\n" + "Atomic mass: " + Uranium238.getAtomicMass() + "\n" + "Atomic charge: " + Uranium238.getAtomicCharge());
	}
}
