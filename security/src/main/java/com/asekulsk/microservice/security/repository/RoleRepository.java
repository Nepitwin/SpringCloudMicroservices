package com.asekulsk.microservice.security.repository;

import com.asekulsk.microservice.security.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Role repository class to check user authorities.
 *
 * @author Andreas Sekulski
 */
@Transactional(readOnly = true)
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * Get list from all roles from a given user.
     *
     * @param userId User id to gain all roles.
     * @return List from all roles by user.
     */
    List<Role> findByUserId(Long userId);
}
