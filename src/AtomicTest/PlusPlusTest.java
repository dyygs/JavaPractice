package AtomicTest;

/**
 * Created by dy on 2017/5/26.
 */
public class PlusPlusTest {

    public static void main(String[] args) throws InterruptedException {
        Num num = new Num();
        ThreadA threadA = new ThreadA(num);
        ThreadB threadB = new ThreadB(num);
        threadA.start();
        threadB.start();
        Thread.sleep(200);
        System.out.println(num.count);
    }

    static class ThreadA extends Thread {
        private Num num;

        public ThreadA(Num num) {
            this.num = num;
        }

        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                num.count++;
            }
        }
    }

    static class ThreadB extends Thread {
        private Num num;

        public ThreadB(Num num) {
            this.num = num;
        }

        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                num.count++;
            }
        }
    }

    static class Num {
        int count = 0;

        public Num() {
        }
    }
}
