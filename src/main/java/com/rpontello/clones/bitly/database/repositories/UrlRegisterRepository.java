package com.rpontello.clones.bitly.database.repositories;

import com.rpontello.clones.bitly.database.entities.UrlRegister;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface UrlRegisterRepository extends CrudRepository<UrlRegister, UUID> {
    Optional<UrlRegister> findByUrlAvailable_Url(String hash);
}
