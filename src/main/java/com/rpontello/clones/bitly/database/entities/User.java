package com.rpontello.clones.bitly.database.entities;

import com.rpontello.clones.bitly.models.enums.UserTypeEnum;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "user")
@EntityListeners(AuditingEntityListener.class)
@Data
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "name")
    private String name;

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

}
