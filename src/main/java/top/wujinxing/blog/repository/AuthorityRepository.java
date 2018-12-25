package top.wujinxing.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.wujinxing.blog.domain.Authority;

/**
 * @author: wujinxing
 * @date: 2018/12/21 16:02
 * @description:
 */
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
