package oop.labx;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Tobias Johansson
 */
public abstract class Gate implements OutputCalculator {

    //--------------------------------------------------------------------------
    // Data field
    private final List<OutputCalculator> connectedInputs;

    private final int requiredInputs;

    //--------------------------------------------------------------------------
    // Constructor
    protected Gate(int requiredInputs) {
        this.connectedInputs = new LinkedList();
        this.requiredInputs = requiredInputs;
    }

    //--------------------------------------------------------------------------
    // Public methods
    public int numberOfInputs() {
        return connectedInputs.size();
    }

    public OutputCalculator getInput(int index) {
        if (index < 0 || index > connectedInputs.size() - 1) {
            System.out.println("Get input unsuccessful");
            return null;
        } else {
            return connectedInputs.get(index);
        }
    }

    public void printTruthTable() throws AllInputsNotDefinedException {
        String className = this.getClass().getSimpleName();
        System.out.println("Truthtable, " + className);
        Gate gate = null;
        switch (className) {
            case "AndGate":
                gate = new AndGate();
                break;
            case "OrGate":
                gate = new OrGate();
                break;
            case "NotGate":
                gate = new NotGate();
                break;
            case "XORGate":
                gate = new XORGate();
                break;
            default:
                throw new RuntimeException("Invalid gate class");
        }

        if (gate.requiredInputs == 1) {
            Generator g = new Generator(false);
            gate.addInput(g);

            for (int i = 0; i < 2; i++) {
                printTruth(g, gate);
                g.setOutput(!g.getOutput());
            }
        }

        if (gate.requiredInputs == 2) {
            Generator g1 = new Generator(false);
            Generator g2 = new Generator(false);
            gate.addInput(g1);
            gate.addInput(g2);

            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    printTruth(g1, g2, gate);
                    g2.setOutput(!g2.getOutput());
                }
                g1.setOutput(!g1.getOutput());
            }
        }
    }

    public void connectTo(Gate g) {
        g.addInput(this);
    }

    public void addInput(OutputCalculator o) {
        if (requiredInputs == connectedInputs.size()) {
            System.out.println("Add input unsuccessful");
        } else {
            connectedInputs.add(o);
        }
    }

    public void removeInput(int index) {
        if (index < 0 || index > connectedInputs.size() - 1) {
            System.out.println("Remove input unsuccessful");
        } else {
            connectedInputs.remove(index);
        }
    }

    //--------------------------------------------------------------------------
    // Helper methods    
    private char toChar(boolean b) {
        return !b ? '0' : '1';
    }

    private void printTruth(Generator g, Gate gate) throws
            AllInputsNotDefinedException {
        System.out.println(toChar(g.getOutput()) + " | "
                + toChar(gate.getOutput()));
    }

    private void printTruth(Generator g1, Generator g2, Gate gate)
            throws AllInputsNotDefinedException {
        System.out.println(toChar(g1.getOutput()) + " "
                + toChar(g2.getOutput()) + " | " + toChar(gate.getOutput()));
    }
}
