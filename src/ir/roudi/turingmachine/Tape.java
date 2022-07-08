package ir.roudi.turingmachine;

import java.util.LinkedList;

public class Tape {

    private LinkedList<Character> tape;
    private int center = 0;

    public Tape(String inputString) {
        tape = new LinkedList<>();
        Utils.addAll(tape, inputString);
    }

    public int size() {
        return getExclusiveEndIndex() - getStartIndex();
    }

    public char get(int index) {
        int i = getRealIndex(index);
        if(i < 0 || i >= tape.size())
            return Constants.BLANK;
        return tape.get(i);
    }

    public void set(int index, char ch) {
        int realIndex = getRealIndex(index);
        if(realIndex < 0) {
            int count = realIndex * -1;
            for(int i = 0; i < count; i++) {
                tape.addFirst(Constants.BLANK);
                center++;
            }
            realIndex = 0;
        } else if(realIndex >= tape.size()) {
            int count = tape.size() - realIndex + 1;
            for(int i = 0; i < count; i++) {
                tape.addLast(Constants.BLANK);
            }
        }

        tape.set(realIndex, ch);
    }

    private int getRealIndex(int index) {
        return center + index;
    }

    private int getStartIndex() {
        for(int i = 0; i < tape.size(); i++) {
            if(tape.get(i) != Constants.BLANK)
                return i;
        }
        return 0;
    }

    private int getExclusiveEndIndex() {
        for(int i = tape.size() - 1; i >= 0; i--) {
            if(tape.get(i) != Constants.BLANK)
                return i+1;
        }
        return 0;
    }

    public String getForm(State currState, int head) {
        StringBuilder builder = new StringBuilder("(");

        if(head < 0) {
            builder.append("[").append(currState).append("]");

            int count = -head;
            for(int i = 0; i < count; i++)
                builder.append(Constants.BLANK);
        }

        int end = Math.max(size(), head);
        for(int i = 0; i < end; i++) {
            if(head == i) {
                builder.append("[").append(currState).append("]");
            }
            builder.append(get(i));
        }

        if(head >= end) {
            int count = head - end;
            for(int i = 0; i < count; i++)
                builder.append(Constants.BLANK);
            builder.append("[").append(currState).append("]");
            builder.append(Constants.BLANK);
        }

        return builder.append(")").toString();
    }

    @Override
    public String toString() {
        return tape.toString();
    }
}
