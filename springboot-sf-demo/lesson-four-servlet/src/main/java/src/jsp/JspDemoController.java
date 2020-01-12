package src.jsp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JspDemoController {

    @GetMapping(path = {"/", "/index"})
    public String index(Model model) {
        model.addAttribute("message", "jack");
        return "index";
    }
}
