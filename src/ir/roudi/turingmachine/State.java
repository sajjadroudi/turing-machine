package ir.roudi.turingmachine;

public class State {

    private final int index;
    private final boolean isInitial;
    private final boolean isFinal;

    public State(int index, boolean isStart, boolean isFinal) {
        this.index = index;
        this.isInitial = isStart;
        this.isFinal = isFinal;
    }

    public State(int index, boolean isFinal) {
        this(index, index == 0, isFinal);
    }

    public int getIndex() {
        return index;
    }

    public boolean isInitial() {
        return isInitial;
    }

    public boolean isFinal() {
        return isFinal;
    }

    @Override
    public String toString() {
        return "q" + index;
    }

}
