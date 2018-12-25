package top.wujinxing.blog.service;

import top.wujinxing.blog.domain.Authority;

/**
 * @author: wujinxing
 * @date: 2018/12/21 16:09
 * @description:  Authority 服务接口
 */
public interface AuthorityService {

    /**
     * 根据ID查询Authority
     * @param id
     * @return
     */
    Authority getAuthorityById(Long id);
}
