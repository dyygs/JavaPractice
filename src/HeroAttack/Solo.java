package HeroAttack;

/**
 * Created by dy on 2017/5/24.
 */
public class Solo {

    public static void main(String[] args) {
        Hero hero1 = new Hero();
        Hero hero2 = new Hero();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t1尝试占有hero1");
                synchronized (hero1) {
                    System.out.println("t1已占有hero1");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("t1尝试占有hero2");
                    synchronized (hero2) {
                        System.out.println("t1已占有hero2");
                    }
                }

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t2尝试占有hero2");
                synchronized (hero2) {
                    System.out.println("t2已占有hero2");
                    System.out.println("t2尝试占有hero1");
                    synchronized (hero1) {
                        System.out.println("t2已占有hero1");
                    }
                }
            }
        }).start();
    }
}
