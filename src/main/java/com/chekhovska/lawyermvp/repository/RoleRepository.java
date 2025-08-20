package com.chekhovska.lawyermvp.repository;

import com.chekhovska.lawyermvp.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
