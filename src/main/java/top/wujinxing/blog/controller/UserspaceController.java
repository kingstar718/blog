package top.wujinxing.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import top.wujinxing.blog.domain.User;
import top.wujinxing.blog.service.UserService;
import top.wujinxing.blog.vo.Response;

/**
 *
 * 用户主页控制器
 * @author: wujinxing
 * @date: 2018/12/15 21:56
 * @description:
 */
@Controller
@RequestMapping("/u")
public class UserspaceController {


    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    public String userSpace(@PathVariable("username")String username){
        System.out.println("username: " + username);
        return "/userspace/u";  //原版写的是/u
    }

    @GetMapping("/{username}/profile")
    @PreAuthorize("authentication.name.equals(#username)")
    public ModelAndView profile(@PathVariable("username")String username, Model model){
        User user = (User) userDetailsService.loadUserByUsername(username);
        model.addAttribute("user",user);
        return new ModelAndView("/userspace/profile","userModel", model);
    }

    /**
     * 保存个人设置
     * @param user
     * @param result
     * @param redirect
     * @return
     */
    @PostMapping("/{username}/profile")
    @PreAuthorize("authentication.name.equals(#username)")
    public String saveProfile(@PathVariable("username") String username,User user) {
        User originalUser = userService.getUserById(user.getId());
        originalUser.setEmail(user.getEmail());
        originalUser.setName(user.getName());

        // 判断密码是否做了变更
        String rawPassword = originalUser.getPassword();
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodePasswd = encoder.encode(user.getPassword());
        boolean isMatch = encoder.matches(rawPassword, encodePasswd);
        if (!isMatch) {
            originalUser.setEncodePassword(user.getPassword());
        }

        userService.saveOrUpdateUser(originalUser);
        return "redirect:/u/" + username + "/profile";
    }

    /**
     * 获取编辑头像的界面
     * @param username
     * @param model
     * @return
     */
    @GetMapping("/{username}/avatar")
    @PreAuthorize("authentication.name.equals(#username)")
    public ModelAndView avatar(@PathVariable("username") String username, Model model) {
        User  user = (User)userDetailsService.loadUserByUsername(username);
        model.addAttribute("user", user);
        return new ModelAndView("/userspace/avatar", "userModel", model);
    }


    /**
     * 获取编辑头像的界面
     * @param username
     * @param model
     * @return
     */
    @PostMapping("/{username}/avatar")
    @PreAuthorize("authentication.name.equals(#username)")
    public ResponseEntity<Response> saveAvatar(@PathVariable("username") String username, User user) {
        String avatarUrl = user.getAvatar();

        User originalUser = userService.getUserById(user.getId());
        originalUser.setAvatar(avatarUrl);
        userService.saveOrUpdateUser(originalUser);

        return ResponseEntity.ok().body(new Response(true, "处理成功", avatarUrl));
    }


    @GetMapping("/{username}/blogs")
    public String listBlogByOrder(@PathVariable("username")String username,
                                  @RequestParam(value = "order",required = false, defaultValue = "new")String order,
                                  @RequestParam(value = "category")Long category,   //分类参数
                                  @RequestParam(value = "keyword")String keyword){  //关键字参数
        if(category != null){
            System.out.println("category: " + category);
            System.out.println("selflink: " + "redirect:/u/" + username + "blogs?category=" + keyword);
            return "/userspace/u";
        }else if(keyword != null && keyword.isEmpty() == false){
            System.out.println("keyword: " + keyword);
            System.out.println("selflink: " + "redirect:/u/" + username + "blogs?keyword=" + keyword);
            return "/userspace/u";
        }

        System.out.println("order: " + order);
        System.out.println("selflink: " + "redirect:/u/" + username + "blogs?order=" + order);
        return "/userspace/u";
    }

    @GetMapping("/{username}/blogs/{id}")
    public String listBlogById(@PathVariable("id")Long id){
        System.out.println("blogID: " + id);
        return "/userspace/blog";
    }

    @GetMapping("/{username}/blogs/edit")
    public String editBlog(){
        return "/userspace/blogedit";
    }
}
