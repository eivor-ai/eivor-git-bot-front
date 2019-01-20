package io.jgodara.evior.eivorgitfront.model;

import java.util.Set;
import javax.persistence.*;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "user_id")
  private long id;

  @Column(name = "username")
  @Length(min = 5, message = "*The username should have at least 8 characters")
  private String username;

  @Column(name = "password")
  @Length(min = 5, message = "*Your password must have at least 5 characters")
  private String password;

  @Column(name = "active")
  private boolean active;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "id")
  private Set<Integration> integrations;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "user_role",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  /**
   * @return the integrations
   */
  public Set<Integration> getIntegrations() {
    return integrations;
  }


  /**
   * @param integrations the integrations to set
   */
  public void setIntegrations(Set<Integration> integrations) {
    this.integrations = integrations;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }
}
