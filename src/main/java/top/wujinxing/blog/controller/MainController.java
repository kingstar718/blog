package top.wujinxing.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import top.wujinxing.blog.domain.Authority;
import top.wujinxing.blog.domain.User;
import top.wujinxing.blog.service.AuthorityService;
import top.wujinxing.blog.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 主页控制器
 * @author: wujinxing
 * @date: 2018/12/15 21:46
 * @description:
 */
@Controller
public class MainController {

    private static final long ROLE_USER_AUTHORITY_ID = 2L;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorityService authorityService;

    @GetMapping("/")
    public String root(){
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/login-error")
    public String loginError(Model model){
        model.addAttribute("loginError", true);
        model.addAttribute("errorMsg","登录失败,用户名或密码错误!");
        return "login";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @GetMapping("/search")
    public String search(){
        return "search";
    }

    @PostMapping("/register")
    public String registerUser(User user){
        List<Authority> authorities = new ArrayList<>();
        authorities.add(authorityService.getAuthorityById(ROLE_USER_AUTHORITY_ID));
        user.setAuthorities(authorities);
        userService.saveOrUpdateUser(user);
        return "redirect:/login";
    }
}
