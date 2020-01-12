package src.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class XMLRestController {

    /**
     * 默认为xml，可省略produces application/xml
     * 通过produces指定返回媒体类型
     *
     * @return
     */
    @GetMapping(path = "/xml/user", produces = MediaType.APPLICATION_XML_VALUE)
    public User user(RequestEntity entity) {
        HttpHeaders headers = entity.getHeaders();
        List<MediaType> accept = headers.getAccept();
        User user = new User();
        user.setName("XML");
        user.setAge(30);
        return user;
    }
}
