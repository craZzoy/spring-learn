package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Created by zwz on 2019/5/14.
 */
@Controller
@RequestMapping({"/", "/homepage"})
public class HomeController {

    @RequestMapping(method = RequestMethod.GET)
    public String home() {
        return "home";
    }

    @RequestMapping("/error")
    public String error() {
        throw new RuntimeException("has exception");
    }
}
