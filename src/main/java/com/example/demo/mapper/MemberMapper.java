package com.example.demo.mapper;

import com.example.demo.dto.MemberPatchDto;
import com.example.demo.dto.MemberPostDto;
import com.example.demo.dto.MemberResponseDto;
import com.example.demo.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberMapper {
    Member memberPostToMember(MemberPostDto requestBody);

    Member memberPatchToMember(MemberPatchDto requestBody);
    MemberResponseDto memberToMemberResponse(Member member);

    List<MemberResponseDto> membersToMemberResponses(List<Member> members);
}
