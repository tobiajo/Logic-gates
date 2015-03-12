package oop.labx;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tobias Johansson
 */
public class OOPLabX {

    public static void main(String[] args) {
        try {
            new AndGate().printTruthTable();
            new OrGate().printTruthTable();
            new NotGate().printTruthTable();
            new XORGate().printTruthTable();
            new Adder().printTruthTable();
        } catch (AllInputsNotDefinedException ex) {
            Logger.getLogger(OOPLabX.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
