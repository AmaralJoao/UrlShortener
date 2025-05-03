package com.JoaoAmaral.EncurtaUrl.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UrlResponseDto {
    private String originalUrl;
    private String shortUrl;
}
