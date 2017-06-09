package ProducerAndCustomer;

/**
 * Created by dy on 2017/5/25.
 */
public class ProducerAndCustomer {

    public static void main(String[] args) {
        Godown godown = new Godown(30);
        Consumer c1 = new Consumer(50, godown);
        Consumer c2 = new Consumer(20, godown);
        Consumer c3 = new Consumer(30, godown);
        Producer p1 = new Producer(10, godown);
        Producer p2 = new Producer(10, godown);
        Producer p3 = new Producer(10, godown);
        Producer p4 = new Producer(10, godown);
        Producer p5 = new Producer(10, godown);
        Producer p6 = new Producer(10, godown);
        Producer p7 = new Producer(80, godown);

        p7.setName("p7");
        p7.setPriority(Thread.MAX_PRIORITY);
        p7.start();

        c1.setName("c1");
        c1.start();

        c2.setName("c2");
        c2.start();

        c3.setName("c3");
        c3.start();

        p1.setName("p1");
        p1.start();

        p2.setName("p2");
        p2.start();

        p3.setName("p3");
        p3.start();

        p4.setName("p4");
        p4.start();

        p5.setName("p5");
        p5.start();

        p6.setName("p6");
        p6.start();



    }
}
