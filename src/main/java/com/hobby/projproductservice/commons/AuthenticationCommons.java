package com.hobby.projproductservice.commons;

import com.hobby.projproductservice.dtos.fakestore.ResponseStatus;
import com.hobby.projproductservice.dtos.fakestore.ValidateTokenResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationCommons {

    private RestTemplate restTemplate;
    private String userServiceBaseUrl;

    @Autowired
    public AuthenticationCommons(RestTemplate restTemplate, @Value("${USER_SERVICE_ISSUER_URI}") String userServiceBaseUrl) {
        this.restTemplate = restTemplate;
        this.userServiceBaseUrl = userServiceBaseUrl;
    }

    public ValidateTokenResponseDto validateToken(String token) {
        String url = userServiceBaseUrl + "/users/validateToken/" + token;
        ResponseEntity<ValidateTokenResponseDto> responseEntity = restTemplate.postForEntity(
                url, null, ValidateTokenResponseDto.class);
        return responseEntity.getBody();
    }
}
