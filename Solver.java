import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class Solver {

    private final AtomicBoolean solved = new AtomicBoolean(false);
    private volatile int solution = -1;

    /**
     * @return int[5] values for the empty cells (in order)
     * @throws RuntimeException if no solution found
     */
    public int[] solve(Game game) throws InterruptedException {

        // 1) collect exactly 5 empty cells
        int[] emptyCells = new int[5];
        int idx = 0;

        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                if (game.board[i][j] == 0)
                    emptyCells[idx++] = i * 10 + j;

        if (idx != 5)
            throw new RuntimeException("Solver works only with exactly 5 empty cells");

        // 2) iterator for permutations
        Permutations permutations = new Permutations();
        Iterator<Integer> iterator = permutations.iterator();

        // 3) thread pool
        ExecutorService pool =
                Executors.newFixedThreadPool(
                        Runtime.getRuntime().availableProcessors()
                );

        VerifySolver verifier = new VerifySolver();

        // 4) distribute permutations
        while (iterator.hasNext() && !solved.get()) {

            int comb = iterator.next(); // one permutation per task

            pool.execute(() -> {
                if (solved.get()) return;

                for (int i = 0; i < 9; i++)
                    for (int j = 0; j < 9; j++) {
                        if (!verifier.verify(i, j, game.board, emptyCells, comb))
                            return;
                    }

                // valid solution found
                solution = comb;
                solved.set(true);
            });
        }

        // 5) shutdown & wait
        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.MINUTES);

        // 6) report if no solution
        if (!solved.get())
            throw new RuntimeException("No solution found");

        return extractSolution(solution);
    }

    // converts 5-digit permutation to int[5]
    private int[] extractSolution(int comb) {
        int[] res = new int[5];
        for (int i = 4; i >= 0; i--) {
            res[i] = comb % 10;
            comb /= 10;
        }
        return res;
    }
}