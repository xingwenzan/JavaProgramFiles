package IntroductoryExperiment.Animals;

public class example {
    public static void main(String[] args) {
        jumpcat jc = new jumpcat();
        jumpdog jd = new jumpdog();
        jc.setname("A梦");
        jd.setname("旺财");
        jc.setage(3);
        jd.setage(1);
        System.out.println(jc.getname() + "-" + jc.getage() + jd.getname() + "-" + jd.getage());
        jc.eat();
        jc.jump();
        jc.sleep();
        jd.eat();
        jd.jump();
        jd.sleep();
    }
}