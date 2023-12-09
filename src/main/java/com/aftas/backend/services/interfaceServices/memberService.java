package com.aftas.backend.services.interfaceServices;

import com.aftas.backend.models.dtos.member.MemberDto;
import org.springframework.stereotype.Service;

@Service
public interface memberService {

    MemberDto saveMember(MemberDto member);



}
