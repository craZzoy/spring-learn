import com.aspect.Audience;
import com.aspect.Encoreable;
import com.aspect.Performance;
import com.config.ConcertConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by zwz on 2019/5/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConcertConfig.class)
public class ConcertTest {

    @Autowired
    public Performance performance;

    //@Autowired
    public Encoreable encoreable;

    @Test
    public void testConcert() {
        System.out.println(performance.getClass().getName());
        if (performance instanceof Encoreable) {
            encoreable = (Encoreable) performance;
            encoreable.performEncore();
        }
        performance.perform();
        performance.countPerson(10);

    }
}
