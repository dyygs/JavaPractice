package HeroAttack;

/**
 * Created by dy on 2017/5/24.
 */
public class Hero {

    private String name;
    private int hp;
    private int damage;

    public void attack(Hero hero) {
        try {
            Thread.sleep(1000);
            int actualDamage = damage;
            if (hero.getHp() - actualDamage < 0) {
                actualDamage = hero.getHp();
                hero.setHp(hero.getHp() - actualDamage);
                System.out.format("%s对%s造成了%d伤害, %s剩余血量%d\n", name, hero.getName(),actualDamage,
                        hero.getName(),hero.getHp());
                System.out.print(hero.getName() + "死亡！\n");
            } else {
                hero.setHp(hero.getHp() - actualDamage);
                System.out.format("%s对%s造成了%d伤害, %s剩余血量%d\n", name, hero.getName(),actualDamage,
                        hero.getName(),hero.getHp());
            }

            while (isAlive() && targetIsAlive(hero)) {
                attack(hero);
            }
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean targetIsAlive(Hero hero) {
        return hero.getHp() > 0 ? true:false;
    }

    private boolean isAlive() {
        return hp > 0 ? true:false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
