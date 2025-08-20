package com.chekhovska.lawyerclient.dto;

import com.chekhovska.lawyerclient.model.Role;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class UserVO {
    private Long id;

    private String name;

    private String email;

    private Role role;

    private String refreshTokenKey;

    //private OwnSecurityVO ownSecurity;
}
