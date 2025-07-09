package com.exos.services.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class User {

    @Id
    private String email;
    private String firstName;
    private String middleName;
    private String lastName;
    private String type;
    private String mobile;
    private String qualification;
}
