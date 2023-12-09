package com.aftas.backend.services.implServices;

import com.aftas.backend.exception.NotFoundException;
import com.aftas.backend.models.dtos.member.MemberDto;
import com.aftas.backend.models.entities.Member;
import com.aftas.backend.repository.memberRepository;
import com.aftas.backend.services.interfaceServices.memberService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class memberServiceImpl implements memberService {

    @Autowired
    private memberRepository Repo_member;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public MemberDto saveMember(MemberDto memberDto) {
        Member member = modelMapper.map(memberDto, Member.class);
        Member savedMember = Repo_member.save(member);

        return modelMapper.map(savedMember, MemberDto.class);
    }


}
