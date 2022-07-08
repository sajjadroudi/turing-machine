package ir.roudi.turingmachine;

import java.util.Objects;

public class Transition {

    private final State source;
    private final State destination;
    private final Direction direction;
    private final char read;
    private final char write;

    public Transition(State source, char read, State destination, char write, Direction direction) {
        this.source = source;
        this.destination = destination;
        this.direction = direction;
        this.read = read;
        this.write = write;
    }

    public State getSource() {
        return source;
    }

    public State getDestination() {
        return destination;
    }

    public Direction getDirection() {
        return direction;
    }

    public char getRead() {
        return read;
    }

    public char getWrite() {
        return write;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transition that = (Transition) o;
        return read == that.read && write == that.write && Objects.equals(source, that.source)
                && Objects.equals(destination, that.destination) && direction == that.direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, destination, direction, read, write);
    }

    @Override
    public String toString() {
        return "δ(" + source + ", " + read + ") = (" + destination + ", " +
                write + ", " + (direction == Direction.RIGHT ? 'R' : 'L') + ")";
    }
}
