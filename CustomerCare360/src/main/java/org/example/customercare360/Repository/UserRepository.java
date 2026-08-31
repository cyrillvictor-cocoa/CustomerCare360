package org.example.customercare360.Repository;

import org.example.customercare360.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User,Integer>{
    Optional<User> findByUserName(String userName);
    boolean existsByUserName(String username);
    boolean existsByEmail(String email);

}
