package com.example.demo;


import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.neovisionaries.i18n.CountryCode;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class CountryTest {
    public static void main(String[] args) {
        String code = "kr".toUpperCase();

        CountryCode cc = CountryCode.getByCode(code);
        System.out.println(cc);
        System.out.println(cc.getName());
        System.out.println(cc.getAlpha2());
        System.out.println(cc.getAlpha3());
        System.out.println(cc.getNumeric());
        System.out.println(PhoneNumberUtil.getInstance().getCountryCodeForRegion(cc.toString()));

        String phone = "010-1234-5678";
        phone = phone.replace("-", " ");
        if (phone.startsWith("0")) {
            phone = phone.substring(1);
        }
        int regionCode = PhoneNumberUtil.getInstance().getCountryCodeForRegion(cc.toString());
        System.out.println("+" + regionCode + " " + phone);

    }
}
