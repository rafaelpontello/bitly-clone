package com.rpontello.clones.bitly.database.entities;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "url_available")
@EntityListeners(AuditingEntityListener.class)
@Data
public class UrlAvailable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "available_id")
    private Long id;

    @Column(name = "url")
    private String url;

    @Column(name = "is_available")
    private boolean isAvailable;

    @Column(name = "is_custom")
    private boolean isCustom;

    @Column(name = "last_access")
    private LocalDateTime lastAccess;

    @Column(name = "created_date", updatable = false)
    @CreatedDate
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    @LastModifiedDate
    private LocalDateTime updatedDate;

    public UrlAvailable(String url) {
        this.url = url;
        this.isAvailable = false;
        this.isCustom = true;
        this.lastAccess = LocalDateTime.now();
    }


}
