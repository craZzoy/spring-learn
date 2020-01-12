package com.controller;

import com.common.exception.SpittleNotFoundException;
import com.vo.NewSpittle;
import com.dao.inte.SpittleRepository;
import com.vo.Spittle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by zwz on 2019/5/18.
 */
@Controller
@RequestMapping("/spittles")
public class SpittleController {

    public SpittleController() {
    }

    private SpittleRepository spittleRepository;

    @Autowired
    public SpittleController(SpittleRepository spittleRepository) {
        this.spittleRepository = spittleRepository;
    }

    /**
     * model 可用Map类型替换
     *
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String spittles(Model model) {
        model.addAttribute(spittleRepository.findSpittle(Long.MAX_VALUE, 20));
        return "spittles";
    }

    /**
     * 返回注册表单视图名
     *
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute(new NewSpittle());
        return "registerForm";
    }

    /**
     * 注册
     * 搭配@Valid对spittle数据进行校验
     *
     * @param spittle
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String processRegistration(@Valid NewSpittle spittle, Errors errors) {

        /**
         * 校验有误重新填写
         */
        if (errors.hasErrors()) {
            return "registerForm";
        }

        spittleRepository.save(spittle);

        return "redirect:/spittles/" + spittle.getUsername();
    }

    /**
     * @return
     */
    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public String showSpittleProfile(@PathVariable String username, Model model) {
        NewSpittle spittle = spittleRepository.findByUsername(username);
        model.addAttribute(spittle);
        return "profile";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String toUpload() {
        return "upload";
    }

    @RequestMapping(value = "/{spittleId}", method = RequestMethod.GET)
    public String spittle(@PathVariable("spittleId") Long spitterId, Model model) {
        Spittle spittle = spittleRepository.findOne();
        if (spittle == null) {
            throw new SpittleNotFoundException();
        }
        model.addAttribute(spitterId);
        return "spittle";
    }

}
