package oop.labx;

/**
 *
 * @author Tobias Johansson
 */
public class Generator implements OutputCalculator {

    private boolean output;

    public Generator(boolean output) {
        this.output = output;
    }

    @Override
    public boolean getOutput() {
        return output;
    }

    public void setOutput(boolean output) {
        this.output = output;
    }
}
