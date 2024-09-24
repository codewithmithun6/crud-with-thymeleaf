package com.codewithmithun.crudwiththymeleaf.repositiries;

import com.codewithmithun.crudwiththymeleaf.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByUserName(String username);
}
