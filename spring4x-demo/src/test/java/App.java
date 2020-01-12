import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zwz on 2019/5/4.
 */
public class App {
    public static void main(String[] args) {
        System.out.println("starting...");
        BeanFactory factory = new ClassPathXmlApplicationContext("applicationContext.xml");
    }
}
