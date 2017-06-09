package AtomicTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by dy on 2017/5/26.
 */
public class AtomicTest {

    private static int a = 0;
    private static AtomicInteger b = new AtomicInteger();
    public static void main(String[] args) {
        int num = 1000;
        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < num ; i++) {
            Thread thread = new Thread() {
                @Override
                public void run() {
                    try {
                        //先让t1飞2秒
                        Thread.sleep(20);
                    } catch (InterruptedException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    a++;
                }
            };
            thread.start();
            list.add(thread);
        }
        for (Thread t : list) {
            try {
                t.join();
            } catch (InterruptedException e) {

            }
        }
        list.clear();
        System.out.println(a + "\n");
        for (int i = 0; i < num ; i++) {
            Thread thread = new Thread() {
                @Override
                public void run() {
                    b.incrementAndGet();
                }
            };
            thread.start();
            list.add(thread);
        }
        for (Thread t : list) {
            try {
                t.join();
            } catch (InterruptedException e) {

            }
        }
        System.out.print(b);

    }
}
