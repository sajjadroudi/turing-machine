package ir.roudi.turingmachine;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static ir.roudi.turingmachine.Constants.BLANK;

public class Main {

    public static void main(String[] args) {
        runUserTestcase();
//        runTestcase1();
//        runTestcase2();
//        runDefaultTestcase();
    }

    private static void runUserTestcase() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter states count: ");
        int stateCount = scanner.nextInt();
        State[] states = new State[stateCount];

        System.out.println("Enter final states count: ");
        int finalStateCount = scanner.nextInt();

        // Initialize final states
        System.out.println("Enter final state indexes: ");
        for (int i = 0; i < finalStateCount; i++) {
            int finalIndex = scanner.nextInt();
            states[finalIndex] = new State(finalIndex, true);
        }

        // Initialize other states
        for (int i = 0; i < stateCount; i++) {
            if (states[i] == null) {
                states[i] = new State(i, false);
            }
        }

        System.out.println("Enter transition count: ");
        int transitionCount = scanner.nextInt();

        System.out.println("Enter transitions in the form below");
        System.out.println("sourceState readCharacter destinationState writeCharacter direction");
        System.out.println("example: 1 0 2 1 R");
        System.out.println("(If you want to enter ⊡, enter 'blank')");
        TransitionFunction function = new TransitionFunction();
        for (int i = 0; i < transitionCount; i++) {
            int sourceIndex = scanner.nextInt();
            String read = scanner.next();

            int destinationIndex = scanner.nextInt();
            String write = scanner.next();
            char direction = scanner.next().charAt(0);

            function.addTransition(
                    states[sourceIndex],
                    read.equals("blank") ? BLANK : read.charAt(0),
                    states[destinationIndex],
                    write.equals("blank") ? BLANK : write.charAt(0),
                    direction == 'R' ? Direction.RIGHT : Direction.LEFT
            );
        }

        TuringMachine machine = new TuringMachine(states, function);

        while (true) {
            System.out.println("Enter string: ");
            String input = scanner.next();
            System.out.println(machine.execute(input));
        }

    }

    private static void runTestcase1() {

        State[] states = {
                new State(0, true, false),
                new State(1, false, false),
                new State(2, false, false),
                new State(3, false, true)
        };

        TransitionFunction function = new TransitionFunction();

        function.addTransition(
                states[0],
                '0',
                states[0],
                '0',
                Direction.RIGHT
        );

        function.addTransition(
                states[0],
                '1',
                states[1],
                '1',
                Direction.RIGHT
        );

        function.addTransition(
                states[1],
                '0',
                states[2],
                '0',
                Direction.RIGHT
        );

        function.addTransition(
                states[1],
                '1',
                states[0],
                '1',
                Direction.RIGHT
        );

        function.addTransition(
                states[2],
                '0',
                states[1],
                '0',
                Direction.RIGHT
        );

        function.addTransition(
                states[2],
                '1',
                states[2],
                '1',
                Direction.RIGHT
        );

        function.addTransition(
                states[0],
                BLANK,
                states[3],
                BLANK,
                Direction.RIGHT
        );

        TuringMachine machine = new TuringMachine(states, function);
        System.out.println(machine.execute("111"));

    }

    private static void runTestcase2() {
        State[] states = {
                new State(0, true, false),
                new State(1, false, false),
                new State(2, false, false),
                new State(3, false, false),
                new State(4, false, true)
        };

        TransitionFunction function = new TransitionFunction();

        function.addTransition(
                states[0],
                '1',
                states[0],
                '1',
                Direction.RIGHT
        );

        function.addTransition(
                states[0],
                '0',
                states[1],
                '1',
                Direction.RIGHT
        );

        function.addTransition(
                states[1],
                '1',
                states[1],
                '1',
                Direction.RIGHT
        );

        function.addTransition(
                states[1],
                BLANK,
                states[2],
                BLANK,
                Direction.LEFT
        );

        function.addTransition(
                states[2],
                '1',
                states[3],
                '0',
                Direction.LEFT
        );

        function.addTransition(
                states[3],
                '1',
                states[3],
                '1',
                Direction.LEFT
        );

        function.addTransition(
                states[3],
                BLANK,
                states[4],
                BLANK,
                Direction.RIGHT
        );

        TuringMachine machine = new TuringMachine(states, function);
        System.out.println(machine.execute("111011"));
    }

    private static void runDefaultTestcase() {
        State[] states = {
                new State(0, true, false),
                new State(1, false, false),
                new State(2, false, false),
                new State(3, false, false),
                new State(4, false, false),
                new State(5, false, false),
                new State(6, false, false),
                new State(7, false, false),
                new State(8, false, false),
                new State(9, false, false),
                new State(10, false, true),
                new State(11, false, false),
        };

        Map<String, State> m = new HashMap<>();
        m.put("0", states[0]);
        m.put("Right0", states[1]);
        m.put("Right1", states[2]);
        m.put("Search0L", states[3]);
        m.put("Search1L", states[4]);
        m.put("1", states[5]);
        m.put("Left0", states[6]);
        m.put("Left1", states[7]);
        m.put("Search0R", states[8]);
        m.put("Search1R", states[9]);
        m.put("Accept", states[10]);
        m.put("Reject", states[11]);

        TransitionFunction function = new TransitionFunction();

        function.addTransition(
                states[0], '0', states[1], BLANK, Direction.RIGHT
        );

        function.addTransition(
                states[1], '0', states[1], '0', Direction.RIGHT
        );

        function.addTransition(
                states[1], '0', states[1], '1', Direction.RIGHT
        );

        function.addTransition(
                states[0], '1', states[2], BLANK, Direction.RIGHT
        );

        //

        function.addTransition(
                states[2], '0', states[2], '0', Direction.RIGHT
        );

        function.addTransition(
                states[2], '1', states[2], '1', Direction.RIGHT
        );

        function.addTransition(
                states[1], BLANK, states[3], BLANK, Direction.LEFT
        );

        function.addTransition(
                states[3], '0', states[5], BLANK, Direction.LEFT
        );

        //

        function.addTransition(
                m.get("Right1"), BLANK, m.get("Search1L"), BLANK, Direction.LEFT
        );

        function.addTransition(
                m.get("Search1L"), '1', m.get("1"), BLANK, Direction.LEFT
        );

        function.addTransition(
                m.get("1"), '0', m.get("Left0"), BLANK, Direction.LEFT
        );

        function.addTransition(
                m.get("Left0"), '0', m.get("Left0"), '0', Direction.LEFT
        );

        //

        function.addTransition(
                m.get("Left0"), '1', m.get("Left0"), '1', Direction.LEFT
        );

        function.addTransition(
                m.get("1"), '1', m.get("Left1"), BLANK, Direction.LEFT
        );

        function.addTransition(
                m.get("Left1"), '0', m.get("Left1"), '0', Direction.LEFT
        );

        function.addTransition(
                m.get("Left1"), '1', m.get("Left1"), '1', Direction.LEFT
        );

        //

        function.addTransition(
                m.get("Left0"), BLANK, m.get("Search0R"), BLANK, Direction.RIGHT
        );

        function.addTransition(
                m.get("Search0R"), '0', m.get("0"), BLANK, Direction.RIGHT
        );

        function.addTransition(
                m.get("Left1"), BLANK, m.get("Search1R"), BLANK, Direction.RIGHT
        );

        function.addTransition(
                m.get("Search1R"), '1', m.get("0"), BLANK, Direction.RIGHT
        );

        //

        function.addTransition(
                m.get("Search0R"), '1', m.get("Reject"), '1', Direction.RIGHT
        );

        function.addTransition(
                m.get("Search1R"), '0', m.get("Reject"), '0', Direction.RIGHT
        );

        function.addTransition(
                m.get("Search0L"), '1', m.get("Reject"), '1', Direction.RIGHT
        );

        function.addTransition(
                m.get("Search1L"), '0', m.get("Reject"), '0', Direction.RIGHT
        );

        //

        function.addTransition(
                m.get("0"), BLANK, m.get("Accept"), BLANK, Direction.RIGHT
        );

        function.addTransition(
                m.get("1"), BLANK, m.get("Accept"), BLANK, Direction.RIGHT
        );

        function.addTransition(
                m.get("Search0L"), BLANK, m.get("Accept"), BLANK, Direction.RIGHT
        );

        function.addTransition(
                m.get("Search0R"), BLANK, m.get("Accept"), BLANK, Direction.RIGHT
        );

        //

        function.addTransition(
                m.get("Search1L"), BLANK, m.get("Accept"), BLANK, Direction.RIGHT
        );

        function.addTransition(
                m.get("Search1R"), BLANK, m.get("Accept"), BLANK, Direction.RIGHT
        );

        TuringMachine machine = new TuringMachine(states, function);
        System.out.println(machine.execute("0"));
        System.out.println(machine.execute("1"));
        System.out.println(machine.execute("10"));
        System.out.println(machine.execute("11"));
        System.out.println(machine.execute("100"));
        System.out.println(machine.execute("101"));
        System.out.println(machine.execute("110"));
        System.out.println(machine.execute("111"));
        System.out.println(machine.execute("1000"));
        System.out.println(machine.execute("1001"));
        System.out.println(machine.execute("1010"));
        System.out.println(machine.execute("1011"));
        System.out.println(machine.execute("1100"));
        System.out.println(machine.execute("1101"));
    }

}