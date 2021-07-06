/*
 * Copyright 2018 Andreas Sekulski
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.asekulsk.microservice.web.spring.security.repository;

import com.asekulsk.microservice.web.spring.security.model.Role;
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
