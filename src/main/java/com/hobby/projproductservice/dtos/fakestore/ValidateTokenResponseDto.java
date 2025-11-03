package com.hobby.projproductservice.dtos.fakestore;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.hobby.projuserservice.models.User}
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ValidateTokenResponseDto implements Serializable {
    Long id;
    String name;
    String email;
    List<RoleDto> roles;
    boolean isEmailVerified;
    ResponseStatus responseStatus;
    String message;

    /**
     * DTO for {@link com.hobby.projuserservice.models.Role}
     */
    @Getter
    @Setter
    public static class RoleDto implements Serializable {
        Long id;
        String name;
    }
}