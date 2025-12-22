import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class Solver {

    private final AtomicBoolean solved = new AtomicBoolean(false);
    private volatile int solution = -1;

    public int[] solve(Game game) throws InterruptedException {


        int[] emptyCells = new int[5];
        int idx = 0;

        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                if (game.board[i][j] == 0)
                    emptyCells[idx++] = i * 10 + j;

        if (idx != 5)
            throw new RuntimeException("Solver works only with exactly 5 empty cells");


        Permutations permutations = new Permutations();
        Iterator<Integer> iterator = permutations.iterator();


        ExecutorService pool =
                Executors.newFixedThreadPool(
                        Runtime.getRuntime().availableProcessors()
                );

        VerifySolver verifier = new VerifySolver();


        while (iterator.hasNext() && !solved.get()) {

            int comb = iterator.next();

            pool.execute(() -> {
                if (solved.get()) return;

                for (int i = 0; i < 9; i++)
                    for (int j = 0; j < 9; j++) {
                        if (!verifier.verify(i, j, game.board, emptyCells, comb))
                            return;
                    }


                solution = comb;
                solved.set(true);
            });
        }

        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.MINUTES);

        if (!solved.get())
            throw new RuntimeException("No solution found");

        return extractSolution(solution);
    }

    private int[] extractSolution(int comb) {
        int[] res = new int[5];
        for (int i = 4; i >= 0; i--) {
            res[i] = comb % 10;
            comb /= 10;
        }
        return res;
    }
}