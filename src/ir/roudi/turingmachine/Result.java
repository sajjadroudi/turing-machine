package ir.roudi.turingmachine;

public class Result {

    private final boolean isAccepted;
    private final String output;

    public Result() {
        isAccepted = false;
        output = null;
    }

    public Result(String result) {
        isAccepted = true;
        output = result;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public String getOutput() {
        return output;
    }

    @Override
    public String toString() {
        return isAccepted ? output : "Not accepted";
    }

}
