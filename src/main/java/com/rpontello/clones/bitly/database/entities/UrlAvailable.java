package com.rpontello.clones.bitly.database.entities;

import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "url_available")
@EntityListeners(AuditingEntityListener.class)
@Data
public class UrlAvailable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "available_id")
    private Long id;

    @Column(name = "url_hash")
    private String urlHash;

    @Column(name = "is_available")
    private boolean isAvailable;

    @Column(name = "updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;

}
