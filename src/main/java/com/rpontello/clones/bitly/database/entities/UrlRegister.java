package com.rpontello.clones.bitly.database.entities;


import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "url_register")
@EntityListeners(AuditingEntityListener.class)
@Data
public class UrlRegister {

    @Id
    @GeneratedValue
    @Column(name = "register_id")
    private UUID id;

    @Column(name = "url_base")
    private String urlBase;

    @OneToOne
    @JoinColumn(name = "url_available_id")
    private UrlAvailable urlAvailable;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "created_date")
    @CreatedDate
    private LocalDateTime createdDate;

    public UrlRegister(String urlBase, User user){
        this.urlBase = urlBase;
        this.user = user;
    }

}


