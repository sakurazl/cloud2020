import java.time.ZonedDateTime;


public class T2 {
    public static void main(String[] args) {
        //获取当前时间加时区
        ZonedDateTime now = ZonedDateTime.now();
        System.out.println(now);
    }
}
