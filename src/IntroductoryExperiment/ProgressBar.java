package IntroductoryExperiment;

import static java.lang.Thread.sleep;

public class ProgressBar {
    /*
     * 用于模拟进度条输出
     */
    public static void main(String[] args) throws InterruptedException {//pass 掉异常反应
        int a = 200;
        int b = 5;
        int m = b/a;
        float n = (float) b/a*100;
        float z = n%10;
        System.out.println(m);
        System.out.println(n);
        System.out.println(n%10);
        System.out.println(z);
        System.out.println(0.0==0);
    }
    public String progressBar(double total) throws InterruptedException{//pass 掉异常反应

        for (double j=1.0; j < total+1; j++) {
            double m =  j/total*100;
            double n = m%10;
            if(n==0.0){
                System.out.print('▮'); //用于输出进度条，一个 '▮' 代表 10%
            }
            System.out.print(m+"%"); //输出进度百分比
            sleep(250); //等待 250 毫秒
            for (int k = 0; k <= String.valueOf(m).length(); k++) { //通过转义符 "\b" 删掉之前的进度百分比
                System.out.print("\b");
            }
        }
        return "加载完成";
    }

}
