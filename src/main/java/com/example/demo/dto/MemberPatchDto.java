package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.*;

@Getter
@AllArgsConstructor
public class MemberPatchDto {
    private long memberId;

    @Pattern(regexp ="/^[가-힣]{2,4}|^([ \\u00c0-\\u01ffa-zA-Z'\\-])+$")
    @NotBlank(message = "Name should not be blank")
    private String name;

    @NotBlank(message = "Email should not be blank")
    @Pattern(regexp ="^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$")
    private String email;

    @Pattern(regexp ="^\\d{2,3}-\\d{3,4}-\\d{4}$")
    @NotBlank(message = "Phone number should not be blank")
    private String phone;

    @NotNull(message = "Check agreement please")
    @Min(0)@Max(1)
    private int agreement; // 0 for disagree & 1 for agree

    @NotBlank(message = "Country Code should not be blank")
    @Pattern(regexp = "^[a-zA-Z]*$")
    private String countryCode;

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }
}
