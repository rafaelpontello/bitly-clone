package com.rpontello.clones.bitly.database.repositories;

import com.rpontello.clones.bitly.database.entities.UrlAvailable;
import org.springframework.data.repository.CrudRepository;

public interface UrlAvailableRepository extends CrudRepository<UrlAvailable, Long> {
    boolean existsByUrl(String customUrl);
}
