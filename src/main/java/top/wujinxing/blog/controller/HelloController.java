package top.wujinxing.blog.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: wujinxing
 * @date: 2018/12/15 21:37
 * @description:
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello(){
        return "Welcome My Blog!";
    }
}
