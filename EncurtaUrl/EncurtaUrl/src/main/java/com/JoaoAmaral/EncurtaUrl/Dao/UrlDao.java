package com.JoaoAmaral.EncurtaUrl.Dao;

import com.JoaoAmaral.EncurtaUrl.Model.UrlModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlDao extends JpaRepository<UrlModel, Long> {
    Optional<UrlModel> findByShortHash(String shortHash);
    boolean existsByShortHash(String shortHash);
}
