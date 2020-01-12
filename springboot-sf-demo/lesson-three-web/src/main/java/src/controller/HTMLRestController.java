package src.controller;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@RestController
//@Controller
public class HTMLRestController {

    /**
     * 使用@Controller不加@ResponseBody会报404
     *
     * @return
     */

    @GetMapping(path = {"/html/demo"})  //等于@RequestMapping(path = {"/html/demo"},method = RequestMethod.GET)
    @PostMapping(path = {"/html/demo1"})
    //@ResponseBody
    public String html() {
        return "<html><body>Hello,World</body></html>";
    }

    @GetMapping(path = {"/html/demo/{message}"})
    public String html(@PathVariable(value = "message") String msg) {
        return "<html><body>" + msg + "</body></html>";
    }


    /**
     * 可以通过HttpServletRequest获取参数
     *
     * @param name
     * @param age
     * @return
     * @RequestParam提供了自动转型的功能，以及默认值等等特性
     */
    @GetMapping(path = {"/html/demo/param"})
    public String htmlParam(@RequestParam(value = "name", required = false, defaultValue = "Empty") String name,
                            @RequestParam(value = "age", required = false, defaultValue = "0") Integer age) {
        return "<html><body> Request Parameter1 value name: " + name
                + " , parameter2 value age:" + age +
                " </body></html>";
    }


    @GetMapping(path = {"/html/demo/header"})
    public String htmlHeader(@RequestHeader(value = "Accept") String accept
            , @CookieValue(value = "cookie", defaultValue = "Empty", required = false) String c
            , RequestEntity entity) {
        System.out.println(entity);
        return "<html><body>" + "Accept:" + accept + "<br>cookie:" + c + "</body></html>";
    }

    @GetMapping(path = {"/html/demo/response/entity"})
    public ResponseEntity<String> htmlResponseEntity() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.put("selfHeader", Arrays.asList("Hello,World"));
        ResponseEntity responseEntity = new ResponseEntity(
                "<html><body>htmlResponseEntity</body></html>",
                httpHeaders,
                HttpStatus.OK);
        return responseEntity;
    }

}
