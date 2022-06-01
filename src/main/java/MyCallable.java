import java.util.concurrent.Callable;

public class MyCallable implements Callable<Integer> {

    int callCount;

    @Override
    public Integer call() throws Exception {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(2500);
                System.out.println("Поток " + Thread.currentThread().getName() + ".Всем привет!");
                callCount++;
            }
        } catch (InterruptedException err) {

        } finally {
            System.out.printf("%s завершен\n", Thread.currentThread().getName());
            return callCount;
        }
    }
}
