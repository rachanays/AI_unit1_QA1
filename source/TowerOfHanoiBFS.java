import java.util.*;

class State {
    // Each peg is represented as a stack of integers (disks)
    List<Stack<Integer>> pegs;
    String moves;

    public State(List<Stack<Integer>> pegs, String moves) {
        this.pegs = pegs;
        this.moves = moves;
    }

    // Deep copy of state
    public State copy() {
        List<Stack<Integer>> newPegs = new ArrayList<>();
        for (Stack<Integer> peg : pegs) {
            Stack<Integer> newPeg = new Stack<>();
            newPeg.addAll(peg);
            newPegs.add(newPeg);
        }
        return new State(newPegs, moves);
    }

    // Encode state uniquely for visited check
    public String encode() {
        StringBuilder sb = new StringBuilder();
        for (Stack<Integer> peg : pegs) {
            sb.append(peg.toString());
        }
        return sb.toString();
    }
}

public class TowerOfHanoiBFS {
    public static void main(String[] args) {
        int n = 3; // number of disks
        bfsSolve(n);
    }

    public static void bfsSolve(int n) {
        // Initial state: all disks on peg 0
        List<Stack<Integer>> startPegs = new ArrayList<>();
        for (int i = 0; i < 3; i++)
            startPegs.add(new Stack<>());
        for (int i = n; i >= 1; i--)
            startPegs.get(0).push(i);

        State start = new State(startPegs, "");
        Queue<State> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.add(start);
        visited.add(start.encode());

        while (!queue.isEmpty()) {
            State current = queue.poll();

            // Goal check: all disks on peg 2
            if (current.pegs.get(2).size() == n) {
                System.out.println("Solution found:");
                System.out.println(current.moves);
                return;
            }

            // Generate next states
            for (int from = 0; from < 3; from++) {
                if (current.pegs.get(from).isEmpty())
                    continue;
                int disk = current.pegs.get(from).peek();
                for (int to = 0; to < 3; to++) {
                    if (from == to)
                        continue;
                    if (current.pegs.get(to).isEmpty() || current.pegs.get(to).peek() > disk) {
                        State next = current.copy();
                        next.pegs.get(from).pop();
                        next.pegs.get(to).push(disk);
                        next.moves = current.moves + "Move disk " + disk + " from " + from + " to " + to + "\n";
                        if (!visited.contains(next.encode())) {
                            visited.add(next.encode());
                            queue.add(next);
                        }
                    }
                }
            }
        }
    }
}
