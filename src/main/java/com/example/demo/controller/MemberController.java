package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Member;
import com.example.demo.model.Typemem;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.TypememRepository;

@RestController
public class MemberController {
	
		@Autowired
		MemberRepository memberRepository;
		
		@Autowired
		TypememRepository typememRepository;
		
		
		
		private List<Member> data = new ArrayList<Member>();	
		
		@GetMapping("/member")
		public ResponseEntity<Object> getMember(){
			try {
				List<Member> members = memberRepository.findAll();
						return new ResponseEntity<> (members,HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<> ("Internal sever error",HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		}
		
		@PostMapping("/member")
		public ResponseEntity<Object> addMember(@RequestBody Member body) {
			
			try {
			
				Optional<Typemem> typemem= typememRepository.findById(3);
				
				body.setTypmmem(typemem.get());
				
				Member member = memberRepository.save(body);
				
				
				return new ResponseEntity<>(member, HttpStatus.CREATED);	
			}catch (Exception e){
				e.printStackTrace();
				return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
			
		
			@GetMapping("/member{memberId}")
			public ResponseEntity<Object> getMemberDetail(@PathVariable Integer memberId) {
				
			 try {
				 Optional<Member> member = memberRepository.findById(memberId);
				 if(member.isPresent()) {
					 return new ResponseEntity<>(member, HttpStatus.OK);
			 }else {
				 return new ResponseEntity<> ("Employee not found", HttpStatus.BAD_REQUEST);
				 }
			 }catch (Exception e){
					return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
		
			
			@PutMapping("/member/{memberId}")
				public ResponseEntity<Object> updateMember(@PathVariable Integer memberId,@RequestBody Member body) {
				
				try {
					Optional<Member> member = memberRepository.findById(memberId);
					if (member.isPresent()) {
						Member memberEdit= member.get();
						memberEdit.setFristName(body.getFristName());
						memberEdit.setLastName(body.getLastName());
						memberEdit.setEmail(body.getEmail());
						memberEdit.setId(body.getId());
						
						memberRepository.save(member.get());
						
						return new ResponseEntity<>(memberEdit , HttpStatus.OK);
					}else {
						return new ResponseEntity<>("Member not found", HttpStatus.BAD_REQUEST);
					}
				}catch (Exception e) {
					return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
			
			
			
			@DeleteMapping("/member/{memberId}")
			public ResponseEntity<Object> deleteMember(@PathVariable Integer memberId) {
			
				try {
					Optional<Member> member = memberRepository.findById(memberId);
					
				if (member.isPresent()) {
					memberRepository.delete(member.get());
					return new ResponseEntity<>("DELETE SUCSESS", HttpStatus.OK);
				}
				else {
					return new ResponseEntity<>("Member not fond",HttpStatus.BAD_REQUEST);
				}
				}catch (Exception e) {
				return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
				
			}

			}
			

			public MemberRepository getMemberRepository() {
				return memberRepository;
			}

			public void setMemberRepository( MemberRepository memberRepository) {
				this.memberRepository = memberRepository;
			}

			public TypememRepository getTypememRepository() {
				return typememRepository;
			}

			public void setTypememRepository(TypememRepository typememRepository) {
				this.typememRepository = typememRepository;
			}

	
		
			public List<Member> getData() {
				return data;
			}

			public void setData(List<Member> data) {
				this.data = data;
			}
			}
