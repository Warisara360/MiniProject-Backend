package com.example.demo.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="member")
public class Member {
	
	    @Id
		@GeneratedValue(strategy = GenerationType.AUTO)
	    private Integer memberId;
	    
		private String fristName;
		private String lastName;
		private String email;
		private String password;
		
		public Member() {
	    }
		
		public Member(Integer memberId, String fristName, String lastName, String email, String password) {
			super();
			this.memberId = memberId;
			this.fristName = fristName;
			this.lastName = lastName;
			this.email = email;
			this.password = password;
		}
		public Integer getMemberId() {
			return memberId;
		}
		public void setMemberId(Integer memberId) {
			this.memberId = memberId;
		}
		public String getFristName() {
			return fristName;
		}
		public void setFristName(String fristName) {
			this.fristName = fristName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		
	

}