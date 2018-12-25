package top.wujinxing.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 博客主页控制器
 * @author: wujinxing
 * @date: 2018/12/15 21:51
 * @description:
 */
@Controller
@RequestMapping("/blogs")
public class BlogController {

    /**
     *
     * @param order 排序规则 默认为new
     * @param tag   标签,关键字
     * @return
     */
    @GetMapping
    public String listBlogs(@RequestParam(value = "order", required = false, defaultValue = "new")String order,
                            @RequestParam(value = "tag",required = false)Long tag){
        System.out.println("order: " + order + "; tag: " + tag);
        return "redirect:/index?order=" + order + "&tag=" + tag;
    }
}
