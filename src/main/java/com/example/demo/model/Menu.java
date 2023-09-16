package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "menu")
public class Menu {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	 
	private Integer id;
	private String nameMenu;
	private String detailMenu;
	private String ingerMenu;
	private String gastroMenu;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNameMenu() {
		return nameMenu;
	}
	public void setNameMenu(String nameMenu) {
		this.nameMenu = nameMenu;
	}
	public String getDetailMenu() {
		return detailMenu;
	}
	public void setDetailMenu(String detailMenu) {
		this.detailMenu = detailMenu;
	}
	public String getIngerMenu() {
		return ingerMenu;
	}
	public void setIngerMenu(String ingerMenu) {
		this.ingerMenu = ingerMenu;
	}
	public String getGastroMenu() {
		return gastroMenu;
	}
	public void setGastroMenu(String gastroMenu) {
		this.gastroMenu = gastroMenu;
	}
	
	
	
	

	@ManyToOne
	@JoinColumn(name = "menuType_id")
	private MenuType menuType;
	
	public MenuType getMenuType() {
	return menuType;
	}
	public void setMenuType(MenuType menuType) {
	this.menuType = menuType;
	}
	
	@ManyToOne
    @JoinColumn(name = "member_id")
	private Member member;
	
	public void setMember(Member member) {
		this.member = member;
	}
}
