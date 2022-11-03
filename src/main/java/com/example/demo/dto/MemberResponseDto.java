package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
@Getter
@AllArgsConstructor
public class MemberResponseDto {
    private long memberId;
    private String name;
    private String email;
    private String countryCode;
    private String phone;
    private String modifiedPhone;
    private int agreement;
}
