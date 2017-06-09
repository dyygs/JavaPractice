package ProducerAndCustomer;

/**
 * Created by dy on 2017/5/25.
 */
public class Consumer extends Thread{
    private int needNum;
    private Godown godown;

    public Consumer(int needNum, Godown godown) {
        this.needNum = needNum;
        this.godown = godown;
    }

    @Override
    public void run() {
        godown.consume(needNum);
    }
}
