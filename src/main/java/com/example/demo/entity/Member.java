package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long memberId;

    @Column(nullable = false)
    private String name;

    //중복된 사용자 이메일 허용 안함
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false, name = "country_code")
    String countryCode;

    //중복된 사용자 핸드폰 번호 허용 안함
    @Column(nullable = false)
    String modifiedPhone;

    //0은 수집여부 거부, 1은 수집 여부 허용
    @Column(nullable = false)
    private byte agreement;
}
