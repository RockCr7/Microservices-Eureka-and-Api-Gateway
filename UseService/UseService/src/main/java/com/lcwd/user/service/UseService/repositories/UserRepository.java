package com.lcwd.user.service.UseService.repositories;

import com.lcwd.user.service.UseService.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
}
