package com.exos.services.model;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserId implements Serializable {
    private Long id;
    private String email;
}
