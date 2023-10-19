package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Member;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.MenuRepository;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class MemberController {

    @Autowired
    MemberRepository memberRepository;


	@Autowired
	MenuRepository menuRepository;
	


    private List<Member> data = new ArrayList<Member>();

    @PostMapping("/loginMember")
    public ResponseEntity<Object> loginMember(@RequestBody Member loginRequest) {
        try {
            Optional<Member> memberFound = memberRepository.findByEmail(loginRequest.getEmail());
            if (memberFound.isPresent() && memberFound.get().getPassword().equals(loginRequest.getPassword())) {
                memberFound.get().setPassword(null);
                return new ResponseEntity<>(memberFound, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Invalid credentials.", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/member")
    public ResponseEntity<Object> getMember() {
        try {
            List<Member> members = memberRepository.findAll();
            return new ResponseEntity<>(members, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal server error", HttpStatus.OK);
        }
    }

    @PostMapping("/member")
    public ResponseEntity<Object> addMember(@RequestBody Member body) {
        try {
            Member member = memberRepository.save(body);
            return new ResponseEntity<>(member, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/member/{user_id}")
    public ResponseEntity<Object> memberDetail(@PathVariable Integer user_id) {
        try {
            Optional<Member> member = memberRepository.findById(user_id);
            if (member.isPresent()) {
                return new ResponseEntity<>(member, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Member not found", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/member/{user_id}")
    public ResponseEntity<Object> updateMember(@PathVariable Integer user_id, @RequestBody Member body) {
        try {
            Optional<Member> member = memberRepository.findById(user_id);

            if (member.isPresent()) {
                Member memberEdit = member.get();
                memberEdit.setFristName(body.getFristName());
                memberEdit.setLastName(body.getLastName());
                memberEdit.setEmail(body.getEmail());
                memberEdit.setPassword(body.getPassword());

                memberRepository.save(member.get());
                return new ResponseEntity<>(member, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Member not found", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/member/{user_id}")
    public ResponseEntity<Object> deleteMember(@PathVariable Integer user_id) {
        try {
            Optional<Member> member = memberRepository.findById(user_id);

            if (member.isPresent()) {
                memberRepository.delete(member.get());
                return new ResponseEntity<>("Delete SUCCESS", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Member not found", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
