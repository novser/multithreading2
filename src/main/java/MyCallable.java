import java.util.concurrent.Callable;

public class MyCallable implements Callable<Integer> {

    private int callCount;
    private int timeout;

    public MyCallable(int timeout) {
        this.timeout = timeout;
    }

    @Override
    public Integer call() throws Exception {
        long beginTime = System.currentTimeMillis();
        try {
            while (System.currentTimeMillis() - beginTime < timeout) {
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
