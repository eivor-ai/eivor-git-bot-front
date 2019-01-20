package io.jgodara.evior.eivorgitfront.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import io.jgodara.evior.eivorgitfront.model.Role;
import io.jgodara.evior.eivorgitfront.model.User;
import io.jgodara.evior.eivorgitfront.repository.RoleRepository;
import io.jgodara.evior.eivorgitfront.repository.UserRepository;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

  private static final Log logger = LogFactory.getLog(ApplicationStartup.class);

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  @Override
  public void onApplicationEvent(ApplicationReadyEvent event) {

    Role role = roleRepository.getRoleByRole("ADMIN");
    if (role == null) {
      role = new Role();
      role.setRole("ADMIN");
      // roleRepository.saveAndFlush(role);
    }

    User user = userRepository.getUserByUsername("admin");
    if (user == null) {
      user = new User();
      user.setUsername("admin");
      user.setActive(true);

      String adminkey = UUID.randomUUID().toString().replaceAll("-", "");
      logger.info("Admin password is " + adminkey);

      user.setPassword(passwordEncoder.encode(adminkey));

      Set<Role> roles = new HashSet<>();
      roles.add(role);
      user.setRoles(roles);

      userRepository.saveAndFlush(user);
    }
  }

}
