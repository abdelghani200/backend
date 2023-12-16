package com.aftas.backend.controllers;

import com.aftas.backend.models.dtos.member.MemberDto;
import com.aftas.backend.models.dtos.member.MemberDtoRes;
import com.aftas.backend.services.interfaceServices.memberService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@CrossOrigin
public class ControllerMember {

    @Autowired
    private memberService ServiceMember;
    @Autowired
    private ModelMapper modelMapper;


    @GetMapping("/sansPagination")
    public List<MemberDtoRes> getAllMembers() {
        return ServiceMember.AllMembers();
    }
    @GetMapping
    public ResponseEntity<Page<MemberDtoRes>> getAllMembers(Pageable pageable) {
        Page<MemberDtoRes> members = ServiceMember.getAllMembers(pageable);
        return ResponseEntity.ok(members);
    }

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
