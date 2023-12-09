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

    @Override
    public MemberDto updateMember(MemberDto memberDto, Long id) {
        Member existingMember = Repo_member.findById(id)
                .orElseThrow(()-> new NotFoundException("Member not found with ID" + id));

        existingMember.setName(memberDto.getName());
        existingMember.setFamilyName(memberDto.getFamilyName());
        existingMember.setAccessionDate(memberDto.getAccessionDate());
        existingMember.setNationality(memberDto.getNationality());
        existingMember.setIdentityNumber(memberDto.getIdentityNumber());
        existingMember.setIdentityNumber(memberDto.getIdentityNumber());

        Member updateMember = Repo_member.save(existingMember);

        return modelMapper.map(updateMember, MemberDto.class);
    }

    @Override
    public void deleteMember(Long id) {
        Member member = Repo_member.findById(id)
                .orElseThrow(()-> new NotFoundException("Member not found with ID" + id));

        Repo_member.delete(member);
    }

    @Override
    public MemberDto rechercheMember(String searchTerm) {
        List<Member> searchResults = Repo_member.searchMembers(searchTerm);
        if (searchResults.isEmpty()) {
            throw new NotFoundException("No members found matching the search term: " + searchTerm);
        }

        return modelMapper.map(searchResults.get(0), MemberDto.class);
    }


}
