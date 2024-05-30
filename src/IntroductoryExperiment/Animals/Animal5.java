package IntroductoryExperiment.Animals;

public abstract class Animal5 {
    String name;
    int age;

    public Animal5() {
    }

    public Animal5(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getname() {
        return name;
    }

    public void setage(int age) {
        this.age = age;
    }

    public int getage() {
        return age;
    }

    public abstract void eat();

    public void sleep() {
        System.out.println("睡觉");
    }
}
