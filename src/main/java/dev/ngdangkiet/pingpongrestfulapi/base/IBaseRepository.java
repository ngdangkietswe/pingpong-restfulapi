package dev.ngdangkiet.pingpongrestfulapi.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author ngdangkiet
 * @since 9/20/2023
 */

@NoRepositoryBean
public interface IBaseRepository<E> extends JpaRepository<E, Long> {
}
