package io.jgodara.evior.eivorgitfront.model;

import javax.persistence.*;

@Entity
@Table(name = "preferences")
public class Preferences {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  // Merge Request Fields

  @Column(name = "mr_matcher")
  private String mrPattern;

  @Column(name = "mr_failed_content")
  private String mrFailedComment;


  @Column(name = "mr_accepted_content")
  private String mrAcceptedComment;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "integration_id")
  private Integration integration;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getMrPattern() {
    return mrPattern;
  }

  public void setMrPattern(String mrPattern) {
    this.mrPattern = mrPattern;
  }

  public String getMrFailedComment() {
    return mrFailedComment;
  }

  public void setMrFailedComment(String mrFailedComment) {
    this.mrFailedComment = mrFailedComment;
  }

  public String getMrAcceptedComment() {
    return mrAcceptedComment;
  }

  public void setMrAcceptedComment(String mrAcceptedComment) {
    this.mrAcceptedComment = mrAcceptedComment;
  }

  public Integration getIntegration() {
    return integration;
  }

  public void setIntegration(Integration integration) {
    this.integration = integration;
  }
}
