package IntroductoryExperiment.Rectangle;

/**
 * 长方形类
 */
public class Rectangle {
    private int length, width;   // 长、宽

    /**
     * 长方形构造函数，用于在其他文件创建一个该类的对象
     *
     * @param length 长方形的长
     * @param width  长方形的宽
     */
    public Rectangle(int length, int width) {
        this.length = length;
        this.width = width;
    }

    // 获取长方形的长
    public int getLength() {
        return length;
    }

    // 获取长方形的宽
    public int getWidth() {
        return width;
    }

    // 重新设置长方形的长
    public void setLength(int length) {
        this.length = length;
    }

    // 重新设置长方形的宽
    public void setWidth(int width) {
        this.width = width;
    }

    // 返回长方形的周长
    public int round() {
        return (length + width) * 2;
    }

    // 返回长方形的面积
    public int area() {
        return length * width;
    }
}
