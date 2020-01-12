package src.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class JSONRestController {

    @Autowired
    @Qualifier("currentUser")
    //@Resource(name = "currentUser")
    private User user;

    @Bean
    public User currentUser() {
        User user = new User();
        user.setName("JSON");
        user.setAge(20);
        return user;
    }


    @GetMapping(path = "/json/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public User user() {
        user.add(ControllerLinkBuilder
                .linkTo(ControllerLinkBuilder.methodOn
                        (JSONRestController.class).setUserName(user.getName()))
                .withSelfRel());
        user.add(ControllerLinkBuilder
                .linkTo(ControllerLinkBuilder.methodOn
                        (JSONRestController.class).setAge(user.getAge()))
                .withSelfRel());
        return user;
    }

    @GetMapping(path = "/json/user/set/name", produces = MediaType.APPLICATION_JSON_VALUE)
    public User setUserName(@RequestParam String name) {
        user.setName(name);
        return user;
    }

    @GetMapping(path = "/json/user/set/age", produces = MediaType.APPLICATION_JSON_VALUE)
    public User setAge(@RequestParam Integer age) {
        user.setAge(age);
        return user;
    }
}
