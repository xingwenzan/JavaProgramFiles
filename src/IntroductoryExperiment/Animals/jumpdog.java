package IntroductoryExperiment.Animals;

public class jumpdog extends dog5 implements jumpping {
    public jumpdog() {
    }

    public jumpdog(String name, int age) {
        super(name, age);
    }

    public void jump() {
        System.out.println("狗跳高");
    }
}
