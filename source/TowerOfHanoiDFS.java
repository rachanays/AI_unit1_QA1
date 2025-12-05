public class TowerOfHanoiDFS {

    // Recursive DFS function
    public static void solveHanoi(int n, char source, char auxiliary, char destination) {
        // Base case: only one disk
        if (n == 1) {
            System.out.println("Move disk 1 from " + source + " to " + destination);
            return;
        }

        // Step 1: Move n-1 disks from source to auxiliary (DFS into subproblem)
        solveHanoi(n - 1, source, destination, auxiliary);

        // Step 2: Move the nth disk from source to destination
        System.out.println("Move disk " + n + " from " + source + " to " + destination);

        // Step 3: Move n-1 disks from auxiliary to destination (DFS into subproblem)
        solveHanoi(n - 1, auxiliary, source, destination);
    }

    public static void main(String[] args) {
        int n = 3; // number of disks
        System.out.println("Tower of Hanoi solution using DFS for " + n + " disks:");
        solveHanoi(n, 'A', 'B', 'C'); // A=source, B=auxiliary, C=destination
    }
}
