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
		private Integer id;
	    private Integer memberId;
		private String fristName;
		private String lastName;
		private String email;
		
		
		public Member(Integer memberId, String fristName, String lastName, String email) {
			super();
			this.memberId = memberId;
			this.fristName = fristName;
			this.lastName = lastName;
			this.email = email;
			
		}

		public Member() {
			super();
		}		

		
		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
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

		
		@ManyToOne
		@JoinColumn(name = "typemem_id")
		private Typemem typemem;
        
		
		@OneToMany
		@JoinColumn(name = "member_id")
		private List<Menu> menus;
		
		
		public Typemem getTypemem() {
		return typemem;
		}
		public void setTypmmem(Typemem typemem) {
			this.typemem = typemem;
		}

		
		public List<Menu> getMenus() {
			return menus;
		}

		public void setMenus(List<Menu> menus) {
			this.menus = menus;
		}
		
}