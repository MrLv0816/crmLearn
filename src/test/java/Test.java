import javax.swing.*;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author MrLv
 * @date 2021/12/6
 * @apiNote
 */
public class Test {
    @org.junit.jupiter.api.Test
    public void test(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String format = simpleDateFormat.format(new Date());
        System.out.println(format);


    }
}
