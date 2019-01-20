package io.jgodara.evior.eivorgitfront.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import io.jgodara.evior.eivorgitfront.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

  public Role getRoleByRole(String role);
}
