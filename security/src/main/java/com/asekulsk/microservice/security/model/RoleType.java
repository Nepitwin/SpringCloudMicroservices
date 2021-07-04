package com.asekulsk.microservice.security.model;

/**
 * Enumeration class for roles which user can be gained.
 */
public enum RoleType {

    ROLE_ADMIN,
    ROLE_USER;

    /**
     * Find role type by type name.
     *
     * @param type Type to search for role.
     * @return NULL if role not exists otherwise RoleType.
     */
    public static RoleType findByType(String type) {
        for (RoleType role : values()) {
            if (role.name().equals(type)) {
                return role;
            }
        }
        return null;
    }
}
