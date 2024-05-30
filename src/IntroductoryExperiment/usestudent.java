package IntroductoryExperiment;

public class usestudent {
    public static void main(String[] args) {
        // Student 类应用
        Student s = new Student("Warm", "100");
        s.disp();

        // Undergraduate 类应用
        Undergraduate u = new Undergraduate("Warm", "100", "Big");
        u.disp();

        // Postgraduate 类应用
        Postgraduate p = new Postgraduate("Warm", "100", "Nan");
        p.disp();
    }


    /* **************************** 以下都是内部类 ************************************* */
    /* ****************** 由于在 main 中使用，必须都设为 static ************************** */
    public static class Student {
        protected String num;  // 学号
        protected String name; // 姓名

        // 构造方法
        public Student(String name, String num) {
            this.name = name;
            this.num = num;
        }

        // 输出学号和姓名信息
        public void disp() {
            System.out.println("学号: " + num + ", 姓名: " + name);
        }
    }

    public static class Undergraduate extends Student {
        private String degree; // 学位

        // 构造方法
        public Undergraduate(String name, String num, String degree) {
            super(name, num);  // 调用父类的构造方法
            this.degree = degree;
        }

        // 输出学号、姓名和学位信息
        @Override
        public void disp() {
            super.disp(); // 调用父类的disp方法
            System.out.println("学位: " + degree);
        }
    }

    public static class Postgraduate extends Student {
        private String research; // 研究方向

        // 构造方法
        public Postgraduate(String name, String num, String research) {
            super(name, num);  // 调用父类的构造方法
            this.research = research;
        }

        // 输出学号、姓名和研究方向信息
        @Override
        public void disp() {
            super.disp(); // 调用父类的disp方法
            System.out.println("研究方向: " + research);
        }
    }

}
