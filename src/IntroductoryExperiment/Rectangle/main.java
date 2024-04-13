package IntroductoryExperiment.Rectangle;

// 该测试代码用于测试长方形类
public class main {
    public static void main(String[] args) {
        int l = 10, w = 2;   // 长设为 10，宽设为 2
        Rectangle r = new Rectangle(l, w);   // 创建长方形对象，长为 l，宽为 w

        // 输出长方形参数
        System.out.println("长：" + r.getLength() + "\n宽：" + r.getWidth());
        System.out.println("周长：" + r.round() + "\n面积：" + r.area());
        System.out.println();

        // 重设长方形参数
        l = 5;
        w = 3;
        r.setLength(l);
        r.setWidth(w);

        // 输出长方形参数
        System.out.println("长：" + r.getLength() + "\n宽：" + r.getWidth());
        System.out.println("周长：" + r.round() + "\n面积：" + r.area());
    }
}
