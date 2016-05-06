package Module3_2;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class SquareSumImpl implements SquareSum {

    private List<Callable<Long>> tasks = new ArrayList<>();
    private List<Future<Long>> sums = new ArrayList<>();
    private final Phaser phaser = new Phaser();
    private long finalSum;

    @Override
    public long getSquareSum(int[] values, int numberOfThreads) {
        IntStream.range(0, numberOfThreads).forEach(i -> tasks.add(() -> {
           long sum = 0;

            phaser.register();

            int from = (i * values.length)/numberOfThreads;
            int to = ((i + 1) * values.length)/numberOfThreads;

            for (int k = from; k < to; k++) {
                sum += StrictMath.pow(values[k], 2);
            }

            phaser.arriveAndAwaitAdvance();
            phaser.arriveAndDeregister();
            return sum;
        }));

        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        try {
            sums = executorService.invokeAll(tasks);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executorService.shutdown();

        for (Future sum: sums) {
            try {
                finalSum += (long)sum.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return finalSum;
    }
}
