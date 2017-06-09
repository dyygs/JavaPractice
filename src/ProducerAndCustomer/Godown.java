package ProducerAndCustomer;

/**
 * Created by dy on 2017/5/25.
 */
public class Godown {

    private static final int maxNum = 100;
    private int currentNum;

    public Godown(int currentNum) {
        this.currentNum = currentNum;
    }

    public synchronized void produce(int produceNum) {
        int leftNum = produceNum;
        while (leftNum > 0 ) {
            if (currentNum  < maxNum) {
                if (leftNum + currentNum > maxNum) {
                    leftNum -= maxNum - currentNum;
                    System.out.println("已经生产了" + (maxNum - currentNum) + "个产品，现仓储量为" + maxNum + "。生产者：" + Thread.currentThread().getName());
                    currentNum = maxNum;
                } else {
                    //可以消费
                    currentNum += leftNum;
                    System.out.println("已经生产了" + leftNum + "个产品，现仓储量为" + currentNum + "。生产者：" +  Thread.currentThread().getName());
                    leftNum = 0;
                    notifyAll();
                }
            }
            if (leftNum == 0) {
                notifyAll();
            } else {
                try {
                    //当前的生产线程等待
                    wait();
                } catch (InterruptedException e) {
//                    System.out.println("中断异常");
                    e.printStackTrace();
                }
            }

        }

    }

    public synchronized void consume(int consume) {
        int leftNum = consume;
        while (leftNum > 0 ) {
            if (currentNum > 0) {
                if (leftNum > currentNum) {
                    leftNum -= currentNum;
                    System.out.println("已经消费了" + currentNum + "个产品，现仓储量为0。消费者：" + Thread.currentThread().getName());
                    currentNum = 0;
                } else {
                    //可以消费
                    currentNum -= leftNum;
                    System.out.println("已经消费了" + leftNum + "个产品，现仓储量为" + currentNum + "。消费者：" + Thread.currentThread().getName());
                    leftNum = 0;
                    notifyAll();
                }
            }
            if (leftNum == 0) {
                notifyAll();
            } else {
                try {
                    //当前的生产线程等待
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
