package io.jgodara.evior.eivorgitfront.repository;

import io.jgodara.evior.eivorgitfront.model.Integration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IntegrationsRepository extends JpaRepository<Integration, Long> {

  public List<Integration> findByUser_Username(String username);

}
