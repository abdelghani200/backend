package com.aftas.backend.services.interfaceServices;

import com.aftas.backend.models.dtos.member.MemberDto;
import com.aftas.backend.models.dtos.member.MemberDtoRes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface memberService {

    MemberDto saveMember(MemberDto member);

    MemberDto updateMember(MemberDto memberDto, Long id);

    void deleteMember(Long id);

    MemberDto rechercheMember(String searchTerm);

    List<MemberDtoRes> AllMembers();

    Page<MemberDtoRes> getAllMembers(Pageable pageable);

}
