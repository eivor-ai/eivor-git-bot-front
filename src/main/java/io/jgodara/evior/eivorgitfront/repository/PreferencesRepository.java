package io.jgodara.evior.eivorgitfront.repository;

import io.jgodara.evior.eivorgitfront.model.Preferences;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreferencesRepository extends JpaRepository<Preferences, Long> {
}
