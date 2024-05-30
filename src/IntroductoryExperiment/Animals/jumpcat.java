package IntroductoryExperiment.Animals;

public class jumpcat extends cat5 implements jumpping {
    public jumpcat() {
    }

    public jumpcat(String name, int age) {
        super(name, age);
    }

    public void jump() {
        System.out.println("猫跳高");
    }
}
