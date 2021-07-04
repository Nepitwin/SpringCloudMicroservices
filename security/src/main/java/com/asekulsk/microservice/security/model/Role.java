package com.asekulsk.microservice.security.model;

import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * Role entity class to handle role management handling.
 *
 * @author Andreas Sekulski
 */
@Entity
@EntityListeners(value = AuditingEntityListener.class)
public class Role {

    /**
     * Private key to store roles.
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * Role type to store from user.
     */
    @Enumerated(value = EnumType.STRING)
    @Column(name = "RoleType", nullable = false, unique = true)
    private RoleType type;

    /**
     * Created date from role.
     */
    @CreatedDate
    @Type(type = "java.sql.Timestamp")
    @Column(updatable = false)
    private Date createdDate;

    /**
     * User to gain this role.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, updatable = false)
    private User user;

    /**
     * Default empty constructor to create role for a user.
     */
    public Role() {
    }

    /**
     * Primary key from role.
     *
     * @return Get primary key if stored by database otherwise NULL.
     */
    public Long getId() {
        return id;
    }

    /**
     * Role type to gain if user is admin or guest etc.
     *
     * @return Get enum role type from user.
     */
    public RoleType getType() {
        return type;
    }

    /**
     * Set role type to user.
     *
     * @param type Type to gain access management rights.
     */
    public void setType(RoleType type) {
        this.type = type;
    }

    /**
     * Get created date from role.
     *
     * @return Date creation from role.
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets created date from role.
     *
     * @param createdDate Created date from role.
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Get user from given role.
     *
     * @return User which role is gained.
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets role to this user.
     *
     * @param user User to set this role.
     */
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (id != null ? !id.equals(role.id) : role.id != null) return false;
        if (type != role.type) return false;
        return createdDate != null ? createdDate.equals(role.createdDate) : role.createdDate == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + type.hashCode();
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        return result;
    }
}
