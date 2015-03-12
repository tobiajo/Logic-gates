package oop.labx;

/**
 * Calculator, full adder.
 *
 * Inputs: A, B, Cin
 * Outputs: S, Cout
 *
 * http://en.wikipedia.org/wiki/Adder_(electronics)
 *
 * @author Tobias Johansson
 */
public class Adder {

    private final Generator a;
    private final Generator b;
    private final Generator cIn;

    private final Gate xor1;
    private final Gate xor2;
    private final Gate and1;
    private final Gate and2;
    private final Gate or;

    public Adder() {
        this.a = new Generator(false);
        this.b = new Generator(false);
        this.cIn = new Generator(false);

        this.xor1 = new XORGate();
        this.xor2 = new XORGate();
        this.and1 = new AndGate();
        this.and2 = new AndGate();
        this.or = new OrGate();

        // Connect generators
        xor1.addInput(a);
        and2.addInput(a);
        xor1.addInput(b);
        and2.addInput(b);
        xor2.addInput(cIn);
        and1.addInput(cIn);

        // Connect gates
        xor1.connectTo(xor2);
        xor1.connectTo(and1);
        /* xor2 = output S */
        and1.connectTo(or);
        and2.connectTo(or);
        /* or = output Cout */
    }

    //--------------------------------------------------------------------------
    // Print
    public void printTruthTable() throws AllInputsNotDefinedException {
        System.out.println("Truthtable, Adder");
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    System.out.println(toChar(getA()) + " "
                            + toChar(getB()) + " " + toChar(getCin()) + " | "
                            + toChar(getCout()) + " " + toChar(getS()));
                    setCin(!getCin());
                }
                setB(!getB());
            }
            setA(!getA());
        }
    }

    private char toChar(boolean b) {
        return !b ? '0' : '1';
    }

    //--------------------------------------------------------------------------
    // Input
    public boolean getA() {
        return a.getOutput();
    }

    public boolean getB() {
        return b.getOutput();
    }

    public boolean getCin() {
        return cIn.getOutput();
    }

    public void setA(boolean output) {
        a.setOutput(output);
    }

    public void setB(boolean output) {
        b.setOutput(output);
    }

    public void setCin(boolean output) {
        cIn.setOutput(output);
    }

    //--------------------------------------------------------------------------
    // Output
    public boolean getCout() throws AllInputsNotDefinedException {
        return or.getOutput();
    }

    public boolean getS() throws AllInputsNotDefinedException {
        return xor2.getOutput();
    }
}
