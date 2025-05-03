package com.JoaoAmaral.EncurtaUrl.Controller;

import com.JoaoAmaral.EncurtaUrl.Dto.UrlRequestDto;
import com.JoaoAmaral.EncurtaUrl.Dto.UrlResponseDto;
import com.JoaoAmaral.EncurtaUrl.Service.UrlService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class UrlController {
    @Autowired
    private final UrlService urlService;

    @Operation(summary = "Encurtar uma URL")
    @ApiResponse(responseCode = "200", description = "URL encurtada com sucesso.")
    @PostMapping("/encurtar")
    public ResponseEntity<UrlResponseDto> shortenUrl(@Valid @RequestBody UrlRequestDto requestDto) {
        UrlResponseDto response = urlService.shortenUrl(requestDto);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Redirecionar via hash")
    @ApiResponse(responseCode = "302", description = "Redirecionamento bem-sucedido.")
    @GetMapping("/{hash}")
    public ResponseEntity<Void> redirect(@PathVariable String hash) {
        String originalUrl = urlService.getOriginalUrl(hash);
        return ResponseEntity.status(302)
                .header(HttpHeaders.LOCATION, originalUrl)
                .build();
    }
}
