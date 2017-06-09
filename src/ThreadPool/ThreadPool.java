package ThreadPool;

import java.util.LinkedList;

/**
 * Created by dy on 2017/5/26.
 */
public class ThreadPool {

    private int threadPoolSize;
    private LinkedList<Runnable> tasks = new LinkedList<>();

    public ThreadPool(int threadPoolSize) {
        this.threadPoolSize = threadPoolSize;
        initThreadPool();
    }

    public void addTask(Runnable task) {
        synchronized (tasks) {
            tasks.add(task);
            tasks.notifyAll();
        }
    }

    private void initThreadPool() {
        for (int i = 0; i < threadPoolSize; i++) {
            new CosumeThread("thread" + i).start();
        }
    }

    class CosumeThread extends Thread {

        private Runnable task;

        public CosumeThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            System.out.println("启动： " + this.getName());
            while (true) {
                synchronized (tasks) {
                    while (tasks.isEmpty()) {
                        try {
                            tasks.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    task = tasks.removeLast();
                    //System.out.println(getName() + "接受了任务!");
                    tasks.notifyAll();
                }
                task.run();
                System.out.println(getName() + "接收，并执行了任务!");

            }

        }
    }
}
