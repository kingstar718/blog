package top.wujinxing.blog.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import top.wujinxing.blog.domain.User;

import java.util.Optional;

/**
 * @author: wujinxing
 * @date: 2018/12/21 13:10
 * @description:用户服务接口
 */
public interface UserService {
    /**
     * 新增,修改,保存用户
     * @param user
     * @return
     */
    User saveOrUpdateUser(User user);

    /**
     * 注册用户
     * @param user
     * @return
     */
    User registerUser(User user);

    /**
     * 删除用户
     * @param id
     */
    void removeUser(Long id);

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    User getUserById(Long id);

    /**
     * 根据用户名进行分页查询
     * @param name
     * @param pageable
     * @return
     */
    Page<User> listUsersByNameLike(String name, Pageable pageable);
}
