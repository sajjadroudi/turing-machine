package ir.roudi.turingmachine;

import java.util.*;

public class TuringMachine {

    private final Set<State> states;
    private final TransitionFunction transitionFunction;
    private int head;

    public TuringMachine(
            State[] states,
            TransitionFunction transitionFunction
    ) {
        this.states = new HashSet<>();
        Collections.addAll(this.states, states);

        this.transitionFunction = transitionFunction;
    }

    public Result execute(String input) {
        // Reset
        Tape tape = new Tape(input);
        head = 0;
        State currState = getInitialState();
        char currChar = tape.get(head);

        StringBuilder builder = new StringBuilder()
                .append(tape.getForm(currState, head));

        while(transitionFunction.existsTransition(currState, currChar)) {
            builder.append(" ⊢ ");

            Transition transition = transitionFunction.findTransition(currState, currChar);

            tape.set(head, transition.getWrite());

            // Move
            currState = transition.getDestination();
            moveHead(transition);
            currChar = tape.get(head);

            builder.append(tape.getForm(currState, head));
        }

        if(currState.isFinal())
            return new Result(builder.toString());

        return new Result();
    }

    private State getInitialState() {
        for(State state : states) {
            if(state.isInitial())
                return state;
        }
        return null;
    }

    private void moveHead(Transition transition) {
        head += (transition.getDirection() == Direction.RIGHT) ? 1 : -1;
    }

}
