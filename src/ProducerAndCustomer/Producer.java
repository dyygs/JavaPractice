package ProducerAndCustomer;

/**
 * Created by dy on 2017/5/25.
 */
public class Producer extends Thread{

    private int produceNum;
    private Godown godown;

    public Producer(int produceNum, Godown godown) {
        this.produceNum = produceNum;
        this.godown = godown;
    }

    @Override
    public void run() {
        godown.produce(produceNum);
    }
}
