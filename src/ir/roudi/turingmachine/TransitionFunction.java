package ir.roudi.turingmachine;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class TransitionFunction {

    private final Set<Transition> transitions;

    public TransitionFunction() {
        transitions = new HashSet<>();
    }

    public void addTransition(Transition transition) {
        transitions.add(transition);
    }

    public void addTransition(State source, char read, State destination, char write, Direction direction) {
        Objects.requireNonNull(source);
        Objects.requireNonNull(destination);
        addTransition(new Transition(source, read, destination, write, direction));
    }

    public Transition findTransition(State source, char read) {
        for(Transition transition : transitions) {
            if(transition.getSource().equals(source)
            && transition.getRead() == read) {
                return transition;
            }
        }

        return null;
    }

    public boolean existsTransition(State source, char read) {
        return findTransition(source, read) != null;
    }

}
