package com.rpontello.clones.bitly.database.repositories;

import com.rpontello.clones.bitly.database.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
