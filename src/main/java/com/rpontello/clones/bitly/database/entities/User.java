package com.rpontello.clones.bitly.database.entities;

import com.rpontello.clones.bitly.config.security.Roles;
import com.rpontello.clones.bitly.models.enums.UserTypeEnum;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "user")
@EntityListeners(AuditingEntityListener.class)
@Data
public class User implements UserDetails, Serializable {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private UserTypeEnum type;

    @OneToMany(mappedBy = "user")
    private List<UrlRegister> urlRegisteredList;

    @Column(name = "created_date", updatable = false)
    @CreatedDate
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    @LastModifiedDate
    private LocalDateTime updatedDate;

    private boolean enabled;

    private Set<Roles> authorities = new HashSet<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.enabled = true;
    }

    @Override
    public boolean isAccountNonExpired() {
        return enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return enabled;
    }

}
