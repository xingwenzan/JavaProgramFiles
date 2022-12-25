import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class Test1 {
    public static void main(String[] args) throws InterruptedException {
//        int[] c = {1,2,3};
//        System.out.println(avg(c));

//        Test1 f = new Test1();
//        int m = f.max(c);
//        System.out.println(m);

//        ProgressBar p = new ProgressBar();
//        p.progressBar(200);
    }
    // 静态代码块可直接调用
    public static int avg(int @NotNull [] a){
        int b=0;
        for(int i=0;i<a.length;i++){
                b+=a[i];
            }
        return b/a.length;
    }
    // 非静态代码块，不能直接调用
    public int max(int @NotNull [] a){
        int b=0;
        for (int i=0;i<a.length;i++){
            if (b>a[i]){
                b=b;
            }else {
                b=a[i];
            }
        }
        return b;
    }
}
