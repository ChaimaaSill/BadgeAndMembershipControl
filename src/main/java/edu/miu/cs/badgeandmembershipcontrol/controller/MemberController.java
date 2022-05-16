package edu.miu.cs.badgeandmembershipcontrol.controller;

import edu.miu.cs.badgeandmembershipcontrol.domain.Member;
import edu.miu.cs.badgeandmembershipcontrol.repository.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping()
    public ResponseEntity<?> getMembers(){
        List<Member> memberList = memberService.getAllMembers();
        return new ResponseEntity<>(memberList, HttpStatus.OK);
    }

    @GetMapping(path = "/{memberId}")
    public ResponseEntity<?> getMember(@PathVariable String memberId){
        Member member = memberService.getMember(Long.parseLong(memberId));

        if(member == null){
            return new ResponseEntity<String>("No Member Found!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Member>(member, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> createMember(@RequestBody Member member){
        Member newMember = memberService.createMember(member);
        return new ResponseEntity<Member>(newMember, HttpStatus.OK);
    }

    @PutMapping(path = "/{memberId}")
    public ResponseEntity<?> updateMember(@PathVariable String memberId, @RequestBody Member member){
        if(memberService.getMember(Long.parseLong(memberId)) == null) {
            return new ResponseEntity<String>("No member by the Id " + memberId + " found", HttpStatus.NOT_FOUND);
        }
        Member updatedMember = memberService.updateMember(Long.parseLong(memberId), member);
        return new ResponseEntity<Member>(updatedMember, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{memberId}")
    public ResponseEntity<?> deleteMember(@PathVariable String memberId){
        if(!memberService.removeMember(Long.parseLong(memberId))){
            return new ResponseEntity<String>("No Member Found!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Successful", HttpStatus.OK);
    }
}
