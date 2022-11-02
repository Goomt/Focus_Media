package com.example.demo.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class MemberResponseDto {
    private long memberId;
    private String name;
    private String email;
    private int phone;
    private byte agreement; // 0 for disagree & 1 for agree
    private String countryCode;
}
