package com.JoaoAmaral.EncurtaUrl.Service;

import com.JoaoAmaral.EncurtaUrl.Dao.UrlDao;
import com.JoaoAmaral.EncurtaUrl.Dto.UrlRequestDto;
import com.JoaoAmaral.EncurtaUrl.Dto.UrlResponseDto;
import com.JoaoAmaral.EncurtaUrl.Model.UrlModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Optional;

@Service
public class UrlService {

    private final UrlDao urlDao;

    @Value("${app.base-url:http://localhost:8080}")
    private String baseUrl;

    private static final String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int HASH_LENGTH = 8;
    private final SecureRandom random = new SecureRandom();

    public UrlService(UrlDao urlDao) {
        this.urlDao = urlDao;
    }

    public UrlResponseDto shortenUrl(UrlRequestDto requestDto) {
        String hash = generateUniqueHash();

        UrlModel urlModel = UrlModel.builder()
                .originalUrl(requestDto.getOriginalUrl())
                .shortHash(hash)
                .build();

        urlDao.save(urlModel);

        return new UrlResponseDto(requestDto.getOriginalUrl(), baseUrl + "/" + hash);
    }

    public String getOriginalUrl(String hash) {
        Optional<UrlModel> optional = urlDao.findByShortHash(hash);
        return optional.map(UrlModel::getOriginalUrl).orElseThrow(() ->
                new IllegalArgumentException("Hash não encontrado: " + hash));
    }

    private String generateUniqueHash() {
        StringBuilder hash;
        do {
            hash = new StringBuilder();
            for (int i = 0; i < HASH_LENGTH; i++) {
                hash.append(ALPHANUMERIC.charAt(random.nextInt(ALPHANUMERIC.length())));
            }
        } while (urlDao.existsByShortHash(hash.toString()));

        return hash.toString();
    }
}