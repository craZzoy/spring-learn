package com.web;

import com.common.exception.SpittleNotFoundException;
import com.dao.inte.SpittleRepository;
import com.vo.Spitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;

import javax.servlet.http.Part;
import javax.validation.Valid;
import java.io.IOException;

/**
 * Created by zwz on 2019/6/3.
 */
@Controller
@RequestMapping("/spittle")
public class SpitterController {

    @Autowired
    private SpittleRepository repository;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute(new Spitter());
        return "register";
    }

    /**
     * 每个输入域对应一个part这里有体现
     * @return
     */
    /*@RequestMapping(value = "/register",method = RequestMethod.POST)
    public String processRegistration(
            @RequestPart("profilePicture") byte[] profilePicture,
            @Valid Spitter spitter,
            Errors errors){

    }*/

    /**
     * 每个输入域对应一个part这里有体现，基于Servlet3的容器，使用Part
     *
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String processRegistration(
            @RequestPart("profilePicture") Part profilePicture,
            @Valid Spitter spitter,
            Errors errors) {
        try {
            profilePicture.write("D:\\user\\" + profilePicture.getSubmittedFileName());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "register";
    }

    @RequestMapping(value = "/{spittleId}", method = RequestMethod.GET)
    public String spittle(@PathVariable("spittleId") Long spitterId, Model model) {
        Spitter spittle = repository.findOne();
        if (spittle == null) {
            throw new SpittleNotFoundException();
        }
        model.addAttribute(spitterId);
        return "spittle";
    }
}
