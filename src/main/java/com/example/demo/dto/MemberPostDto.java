package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

import javax.persistence.Column;

@Getter
@AllArgsConstructor
public class MemberPostDto {
    //한글 최소 2-4자, 영어사용 가능
    @Pattern(regexp ="^[가-힣]{2,4}|^([ \\u00c0-\\u01ffa-zA-Z'\\-])+$")
    @NotBlank(message = "Name should not be blank")
    private String name;

    @Pattern(regexp ="^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$")
    @NotBlank(message = "Email should not be blank")
    private String email;

    @Pattern(regexp ="^\\d{2,3}-\\d{3,4}-\\d{4}$")
    @NotBlank(message = "Phone number should not be blank")
    private String phone;

    //2-3글자 국가코드 입력 가능
    @NotBlank(message = "Country Code should not be blank")
    @Pattern(regexp = "^[a-zA-Z]{2,3}$")
    private String countryCode;

    // 0 for disagree & 1 for agree
    @NotNull(message = "Check agreement please")
    @Min(0)@Max(1)
    private int agreement;
}
