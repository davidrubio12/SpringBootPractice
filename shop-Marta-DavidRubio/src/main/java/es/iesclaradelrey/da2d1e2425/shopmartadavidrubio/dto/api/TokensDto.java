package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.dto.api;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TokensDto {
    private String accessToken;
    private String refreshToken;
}
