package com.JoaoAmaral.EncurtaUrl.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

@Data
public class UrlRequestDto {

    @NotBlank(message = "A URL não pode ser vazia.")
    @URL(message = "URL inválida.")
    private String originalUrl;
}