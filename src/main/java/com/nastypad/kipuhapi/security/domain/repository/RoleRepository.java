package com.nastypad.kipuhapi.security.domain.repository;

import com.nastypad.kipuhapi.security.domain.model.entity.Role;
import com.nastypad.kipuhapi.security.domain.model.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleId(Long id);
    Optional<Role> findByRole(RoleEnum role);
    Boolean existsByRole(RoleEnum role);
}
