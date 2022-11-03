package com.example.demo.service;

import com.example.demo.entity.Member;
import com.example.demo.exception.BusinessLogicException;
import com.example.demo.exception.ExceptionCode;
import com.example.demo.repository.MemberRepository;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.neovisionaries.i18n.CountryCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member createMember(Member member) {
        // email 기반 이미 가입된 회원 구분
        verifyExistEmail(member.getEmail());
        //국제 전화번호 생성
        String newPhone = modifiedPhone(member.getPhone(), member.getCountryCode());
        member.setModifiedPhone(newPhone);

        Member savedMember = memberRepository.save(member);
        return savedMember;
    }
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public Member updateMember(Member member) {
        Member findMember = findVerifiedMember(member.getMemberId());

        // 변경 하지 않을 시 기존 데이터 유지
        Optional.ofNullable(member.getName())
                .ifPresent(name -> findMember.setName(name));
        Optional.ofNullable(member.getAgreement())
                .ifPresent(agreement -> findMember.setAgreement(agreement));
        Optional.ofNullable(member.getPhone())
                .ifPresent(phone -> findMember.setPhone(phone));
        Optional.ofNullable(member.getCountryCode())
                .ifPresent(countryCode -> findMember.setCountryCode(countryCode.toUpperCase()));

        // 변경된 전화번호를 기반으로 국제 전화번호 다시 지정
        String newPhone = modifiedPhone(findMember.getPhone(), findMember.getCountryCode());
        findMember.setModifiedPhone(newPhone);

        return memberRepository.save(findMember);
    }

    @Transactional(readOnly = true)
    public Member findMember(long memberId) { //  멤버 아이디를 사용한 특정 회원 조회
        return findVerifiedMember(memberId);
    }

    public Page<Member> findMembers(int page, int size) { // 페이지네이션을 사용한 전체 회원 조회
        return memberRepository.findAll(PageRequest.of(page, size,
                Sort.by("memberId").descending()));
    }

    @Transactional
    public void delete(long memberId) {
        Member findMember = findVerifiedMember(memberId);
        memberRepository.delete(findMember);
    }

    private void verifyExistEmail(String email) { // email을 기반으로 사용자 가입여부 판단
        Optional<Member> member = memberRepository.findByEmail(email);
        if (member.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.EMAIL_EXIST);
        }
    }

    @Transactional(readOnly = true) // member-id 기반 사용자 지정
    public Member findVerifiedMember(long memberId) {
        Optional<Member> optionalMember =
                memberRepository.findById(memberId);
        Member findMember = // 옵셔널 사용, orElseThrow 기능으로 구분
                optionalMember.orElseThrow(()-> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        return findMember;
    }

    //국가코드 기반 국제 전화번호 생성 기능
    public String modifiedPhone(String phone, String countryCode) {
        String modifiedPhone = phone.replace("-", " ");

        if (modifiedPhone.startsWith("0")) {
            modifiedPhone.substring(1);
        }

        CountryCode cc = CountryCode.getByCode(countryCode.toUpperCase()); // 국가코드 라이브러리 사용 국가 지정
        int regionCode = PhoneNumberUtil.getInstance().getCountryCodeForRegion(cc.toString()); // 2글자 영어 국가코드 -> 국제 전화번호 코드 변환
        return "+" + regionCode + " " + modifiedPhone;
    }

}
