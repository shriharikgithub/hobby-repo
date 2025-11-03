package com.hobby.projproductservice.commons;

import com.hobby.projproductservice.dtos.fakestore.ResponseStatus;
import com.hobby.projproductservice.dtos.fakestore.ValidateTokenResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationCommons {

    private RestTemplate restTemplate;

    @Autowired
    public AuthenticationCommons(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ValidateTokenResponseDto validateToken(String token) {
        ResponseEntity<ValidateTokenResponseDto> responseEntity = restTemplate.postForEntity(
                "http://localhost:8081/users/validateToken/" + token, null, ValidateTokenResponseDto.class);
        return responseEntity.getBody();
    }
}
