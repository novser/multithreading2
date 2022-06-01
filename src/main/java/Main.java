import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        int countTasks = 4;

        List<Callable<Integer>> tasks = new ArrayList<>();
        for (int i = 0; i < countTasks; i++) {
            tasks.add(new MyCallable());
        }

        ExecutorService pool = Executors.newFixedThreadPool(countTasks);

        try {
            List<Future<Integer>> futureTasks = pool.invokeAll(tasks, 15, TimeUnit.SECONDS);
            for (int i = 0; i < futureTasks.size(); i++) {
                Future<Integer> futureTask = futureTasks.get(i);
                System.out.println("Результат - " + futureTask.get());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            pool.shutdown();
        }

    }
}
