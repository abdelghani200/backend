package com.aftas.backend.controllers;

import com.aftas.backend.models.dtos.member.MemberDto;
import com.aftas.backend.services.interfaceServices.memberService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
public class ControllerMember {

    @Autowired
    private memberService ServiceMember;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public MemberDto saveMember(@Valid @RequestBody MemberDto memberDto){
        return ServiceMember.saveMember(memberDto);
    }

    @PutMapping("/{id}")
    public MemberDto updateMember(@Valid @RequestBody MemberDto memberDto,@PathVariable Long id){
        return ServiceMember.updateMember(memberDto, id);
    }

    @DeleteMapping("/{id}")
    public void deleteMember(@PathVariable Long id) {
        ServiceMember.deleteMember(id);
    }

    @GetMapping("/search")
    public MemberDto searchMembers(@RequestParam String searchTerm){
        return ServiceMember.rechercheMember(searchTerm);
    }


}
