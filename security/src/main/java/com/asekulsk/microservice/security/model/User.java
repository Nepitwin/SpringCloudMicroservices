package com.asekulsk.microservice.security.model;

import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * User management entity class.
 *
 * @author Andreas Sekulski
 */
@Entity
@EntityListeners(value = AuditingEntityListener.class)
public class User implements UserDetails {

    /**
     * Primary key number from user.
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * Username from user.
     */
    private String username;

    /**
     * Password crypted as hash value.
     */
    private String password;

    /**
     * Created date from user.
     */
    @CreatedDate
    @Type(type = "java.sql.Timestamp")
    @Column(updatable = false)
    private Date createdDate;

    /**
     * Last modification from user changes.
     */
    @CreatedDate
    @Type(type = "java.sql.Timestamp")
    @Column(updatable = false)
    private Date lastModifiedDate;

    /**
     * List from all roles which user has granted to access.
     */
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Role> roles;

    /**
     * Default user constructor.
     */
    public User() {
    }

    /**
     * @return Get stored primary key from user otherwise null.
     */
    public Long getId() {
        return id;
    }

    /**
     * @return Get username from user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set username from user.
     *
     * @param username Username to change or set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return Get password from user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set password from user to set.
     *
     * @param password Password to store.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return Get creation date from user.
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * Set creation date from user.
     *
     * @param createdDate Creation date from user.
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * @return Get last modification date from user.
     */
    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    /**
     * Sets date from last modification from user.
     *
     * @param lastModifiedDate Modification date from user.
     */
    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    /**
     * @return Get list from alle granted roles as list.
     */
    public List<Role> getRoles() {
        return roles;
    }

    /**
     * Sets list from roles to setup user management rights.
     *
     * @param roles List from all roles to set.
     */
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getType().name());
            authorities.add(authority);
        }
        return authorities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (!username.equals(user.username)) return false;
        if (!password.equals(user.password)) return false;
        if (createdDate != null ? !createdDate.equals(user.createdDate) : user.createdDate != null) return false;
        return lastModifiedDate != null ? lastModifiedDate.equals(user.lastModifiedDate) : user.lastModifiedDate == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + username.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        return result;
    }
}
