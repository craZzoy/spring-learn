package code.com.aop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zwz on 2019/5/1.
 */
@Controller
public class TestController {
    @RequestMapping("/demo")
    @ResponseBody
    public String doTest() {
        System.out.println("123");
        return "haha";
    }
}
