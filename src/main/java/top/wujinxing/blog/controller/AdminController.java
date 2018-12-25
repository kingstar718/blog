package top.wujinxing.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import top.wujinxing.blog.vo.Menu;

import java.util.ArrayList;
import java.util.List;

/**
 * 后台管理控制器
 * @author: wujinxing
 * @date: 2018/12/15 22:17
 * @description:
 */
@Controller
@RequestMapping("/admins")
public class AdminController {

    /**
     * 获取用户管理后台页面
     * @param model
     * @return
     */
    @GetMapping
    public ModelAndView listUsers(Model model){

        List<Menu> list = new ArrayList<>();

        list.add(new Menu("用户管理","/users"));
        list.add(new Menu("角色管理","/roles"));
        list.add(new Menu("博客管理","/blogs"));
        list.add(new Menu("评论管理","/commits"));
        model.addAttribute("list",list);

        return new ModelAndView("/admins/index", "model", model);
    }
}
