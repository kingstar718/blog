package top.wujinxing.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.wujinxing.blog.domain.Authority;
import top.wujinxing.blog.repository.AuthorityRepository;
import top.wujinxing.blog.service.AuthorityService;

/**
 * @author: wujinxing
 * @date: 2018/12/21 16:11
 * @description:
 */
@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public Authority getAuthorityById(Long id) {
        return authorityRepository.getOne(id);
    }
}
