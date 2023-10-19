package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "menutype")
public class MenuType {
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	
	private Integer menuTypeId;
	private String menuType_name;
	
	
	public MenuType() {
		super();
	}
	
	public MenuType(Integer menuTypeId, String menuType_name) {
		super();
		this.menuTypeId = menuTypeId;
		this.menuType_name = menuType_name;
	}
	
	public Integer getMenuTypeId() {
		return menuTypeId;
	}
	public void setMenuTypeId(Integer menuTypeId) {
		this.menuTypeId = menuTypeId;
	}
	public String getMenuType_name() {
		return menuType_name;
	}
	public void setMenuType_name(String menuType_name) {
		this.menuType_name = menuType_name;
	}
	
	
	
	

}
