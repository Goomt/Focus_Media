package com.example.demo.mapper;

import com.example.demo.dto.MemberPatchDto;
import com.example.demo.dto.MemberPostDto;
import com.example.demo.dto.MemberResponseDto;
import com.example.demo.entity.Member;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-04T02:50:29+0900",
    comments = "version: 1.5.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 11.0.15 (Eclipse Adoptium)"
)
@Component
public class MemberMapperImpl implements MemberMapper {

    @Override
    public Member memberPostToMember(MemberPostDto requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Member member = new Member();

        member.setName( requestBody.getName() );
        member.setEmail( requestBody.getEmail() );
        member.setPhone( requestBody.getPhone() );
        member.setCountryCode( requestBody.getCountryCode() );
        member.setAgreement( (byte) requestBody.getAgreement() );

        return member;
    }

    @Override
    public Member memberPatchToMember(MemberPatchDto requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Member member = new Member();

        member.setMemberId( requestBody.getMemberId() );
        member.setName( requestBody.getName() );
        member.setEmail( requestBody.getEmail() );
        member.setPhone( requestBody.getPhone() );
        member.setCountryCode( requestBody.getCountryCode() );
        member.setAgreement( (byte) requestBody.getAgreement() );

        return member;
    }

    @Override
    public MemberResponseDto memberToMemberResponse(Member member) {
        if ( member == null ) {
            return null;
        }

        long memberId = 0L;
        String name = null;
        String email = null;
        String countryCode = null;
        String phone = null;
        String modifiedPhone = null;
        int agreement = 0;

        memberId = member.getMemberId();
        name = member.getName();
        email = member.getEmail();
        countryCode = member.getCountryCode();
        phone = member.getPhone();
        modifiedPhone = member.getModifiedPhone();
        agreement = member.getAgreement();

        MemberResponseDto memberResponseDto = new MemberResponseDto( memberId, name, email, countryCode, phone, modifiedPhone, agreement );

        return memberResponseDto;
    }

    @Override
    public List<MemberResponseDto> membersToMemberResponses(List<Member> members) {
        if ( members == null ) {
            return null;
        }

        List<MemberResponseDto> list = new ArrayList<MemberResponseDto>( members.size() );
        for ( Member member : members ) {
            list.add( memberToMemberResponse( member ) );
        }

        return list;
    }
}
