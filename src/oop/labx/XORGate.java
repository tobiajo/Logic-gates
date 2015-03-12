package oop.labx;

/**
 *
 * @author Tobias Johansson
 */
public class XORGate extends Gate {

    private static final int REQUIRED_INPUTS = 2;

    public XORGate() {
        super(REQUIRED_INPUTS);
    }

    @Override
    public boolean getOutput() throws AllInputsNotDefinedException {
        if (numberOfInputs() != REQUIRED_INPUTS) {
            throw new AllInputsNotDefinedException("XORGate inputs != 2");
        } else {
            return getInput(0).getOutput() != getInput(1).getOutput();
        }
    }
}
