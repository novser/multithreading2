import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        int countTasks = 4;
        int timeout = 15000;

        // invokeAll
        System.out.println("Запуск всех задач");
        List<Callable<Integer>> tasks = getTasks(countTasks, timeout);
        ExecutorService pool = Executors.newFixedThreadPool(countTasks);
        try {
            List<Future<Integer>> futureTasks = pool.invokeAll(tasks);
            for (int i = 0; i < futureTasks.size(); i++) {
                Future<Integer> futureTask = futureTasks.get(i);
                System.out.println("Результат " + (i + 1) + " - " + futureTask.get());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            pool.shutdown();
        }

        System.out.println("---------------------------------------------");

        // invokeAny
        System.out.println("Запуск всех задач, дожидаемся самой первой");
        List<Callable<Integer>> tasksAny = getTasks(countTasks, timeout);
        ExecutorService poolAny = Executors.newFixedThreadPool(countTasks);
        try {
            Integer fastestResult = poolAny.invokeAny(tasksAny);
            System.out.println("Самый быстрый результат - " + fastestResult);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            poolAny.shutdown();
        }
    }

    private static List<Callable<Integer>> getTasks(int countTasks, int timeout) {
        List<Callable<Integer>> tasks = new ArrayList<>();
        for (int i = 0; i < countTasks; i++) {
            tasks.add(new MyCallable(timeout));
        }
        return tasks;
    }

}
