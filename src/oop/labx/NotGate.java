package oop.labx;

/**
 *
 * @author Tobias Johansson
 */
public class NotGate extends Gate {

    private static final int REQUIRED_INPUTS = 1;

    public NotGate() {
        super(REQUIRED_INPUTS);
    }

    @Override
    public boolean getOutput() throws AllInputsNotDefinedException {
        if (numberOfInputs() != REQUIRED_INPUTS) {
            throw new AllInputsNotDefinedException("NotGate inputs != 1");
        } else {
            return !getInput(0).getOutput();
        }
    }
}
