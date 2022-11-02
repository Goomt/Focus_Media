package com.example.demo.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class MemberPatchDto {
    private long memberId;
    @Pattern(regexp ="/^[가-힣]{2,4}|^([ \\u00c0-\\u01ffa-zA-Z'\\-])+$")
    @NotBlank(message = "Name should not be blank")
    private String name;

    @Pattern(regexp =
            "/^([\\w-]+(?:\\.[\\w-]+)*)@((?:[\\w-]+\\.)*\\w[\\w-]{0,66})\\.([a-z]{2,6}(?:\\.[a-z]{2})?)$/\n")
    @NotBlank(message = "Email should not be blank")
    private String email;

    @Pattern(regexp ="^\\s*(?:\\+?(\\d{1,3}))?[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*(\\d{4})(?: *x(\\d+))?\\s*$\n")
    @NotBlank(message = "Phone number should not be blank")
    private int phone;

    @NotBlank(message = "Check agreement please")
    private byte agreement; // 0 for disagree & 1 for agree

    @NotBlank(message = "Country Code should not be blank")
    @Pattern(regexp = "/^[a-zA-Z]*$/")
    @Max(2|3)
    private String countryCode;

    public void setMemberId(long MemberId) {
        this.memberId = memberId;
    }
}
