package io.jgodara.evior.eivorgitfront.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import io.jgodara.evior.eivorgitfront.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

  public User getUserByUsername(String username);

}
