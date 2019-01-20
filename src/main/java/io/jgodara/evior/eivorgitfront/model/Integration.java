package io.jgodara.evior.eivorgitfront.model;

import javax.persistence.*;

@Entity
@Table(name = "integration")
public class Integration {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "app_name")
  private String appName;

  @Column(name = "secret")
  private String secret;

  @Column(name = "oauth_token")
  private String token;

  @Column(name = "bot_username")
  private String botUsername;

  @Column(name = "server_url")
  private String serverUrl;

  @ManyToOne
  private User user;

  @OneToOne(mappedBy = "integration", cascade = CascadeType.ALL, fetch = FetchType.LAZY,
      optional = false)
  private Preferences preferences;

  /**
   * @return the id
   */
  public long getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * @return the appName
   */
  public String getAppName() {
    return appName;
  }

  /**
   * @param appName the appName to set
   */
  public void setAppName(String appName) {
    this.appName = appName;
  }

  /**
   * @return the botUsername
   */
  public String getBotUsername() {
    return botUsername;
  }

  /**
   * @param botUsername the botUsername to set
   */
  public void setBotUsername(String botUsername) {
    this.botUsername = botUsername;
  }

  /**
   * @return the secret
   */
  public String getSecret() {
    return secret;
  }

  /**
   * @param secret the secret to set
   */
  public void setSecret(String secret) {
    this.secret = secret;
  }

  /**
   * @return the serverUrl
   */
  public String getServerUrl() {
    return serverUrl;
  }

  /**
   * @param serverUrl the serverUrl to set
   */
  public void setServerUrl(String serverUrl) {
    this.serverUrl = serverUrl;
  }

  /**
   * @return the token
   */
  public String getToken() {
    return token;
  }

  /**
   * @param token the token to set
   */
  public void setToken(String token) {
    this.token = token;
  }

  /**
   * @return the user
   */
  public User getUser() {
    return user;
  }

  /**
   * @param user the user to set
   */
  public void setUser(User user) {
    this.user = user;
  }

  public Preferences getPreferences() {
    return preferences;
  }

  public void setPreferences(Preferences preferences) {
    this.preferences = preferences;
  }
}
