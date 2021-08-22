package com.rpontello.clones.bitly.database.repositories;

import com.rpontello.clones.bitly.database.entities.UrlAvailable;
import com.rpontello.clones.bitly.database.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<UrlAvailable> findFirstByIsAvailable(boolean isAvailable);
}
