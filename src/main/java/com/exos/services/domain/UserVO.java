package com.exos.services.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.exos.services.enums.UserType;
import com.exos.services.enums.ValueOfEnum;
import lombok.*;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserVO {


    @NotBlank(message = "First Name should not be blank")
    @Size(max = 30, message = "First Name should atmost have {max} Chars")
    @Pattern(regexp = "^[A-Za-z]*$", message = "First Name should contain only Alphabets")
    private String firstName;

    @Size(max = 30, message = "Middle Name should atmost have {max} Chars")
    private String middleName;

    @NotBlank(message = "Last Name should not be blank")
    @Size(max = 30, message = "Last Name should atmost have {max} Chars")
    @Pattern(regexp = "^[A-Za-z]*$", message = "Last Name should contain only Alphabets")
    private String lastName;

    @ValueOfEnum(enumClass = UserType.class, message = "Type must be any of {STUDENT, EMPLOYEE, TRAINER}")
    private String type;

    @NotBlank(message = "Email should not be blank")
    @Size(max = 50, message = "Email should not exceed {max} characters")
    @Email(message = "Email should be valid email")
    private String email;

    @NotBlank(message = "Mobile should not be blank")
    @Pattern(regexp = "^[0-9]*$", message = "Mobile should contain only Numbers")
    private String mobile;

    private String qualification;
}
