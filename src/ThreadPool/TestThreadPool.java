package ThreadPool;

/**
 * Created by dy on 2017/5/26.
 */
public class TestThreadPool {
    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool(10);
        for(int i = 0;i < 10; i++) {
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                        //System.out.println("make money!");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            };
            try {
                Thread.sleep(1000);
                threadPool.addTask(task);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
