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

import com.example.demo.model.Menu;
import com.example.demo.repository.MenuRepository;
import com.example.demo.repository.MenuTypeRepository;

@RestController
public class MenuController {
	
	@Autowired
	MenuRepository menuRepository;
	
	@Autowired
	MenuTypeRepository menuTypeRepository;
	
	private List<Menu> data = new ArrayList<Menu>();	
	
	@GetMapping("/menu")
	public ResponseEntity<Object> getMenu(){
		try {
			List<Menu> menus = menuRepository.findAll();
					return new ResponseEntity<> (menus,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<> ("Internal sever error",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PostMapping("/menu")
	public ResponseEntity<Object> addMenu(@RequestBody Menu body) {
		
		try {
			Menu menu = menuRepository.save(body);
			return new ResponseEntity<>(menu, HttpStatus.CREATED);	
		}catch (Exception e){
			e.printStackTrace();
			return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
		
	
		@GetMapping("/menu{menuId}")
		public ResponseEntity<Object> getMenuDetail(@PathVariable Integer menuId) {
			
		 try {
			 Optional<Menu> menu = menuRepository.findById(menuId);
			 if(menu.isPresent()) {
				 return new ResponseEntity<>(menu, HttpStatus.OK);
		 }else {
			 return new ResponseEntity<> ("Menu not found", HttpStatus.BAD_REQUEST);
			 }
		 }catch (Exception e){
				return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	
		
		@PutMapping("/menu/{menuId}")
			public ResponseEntity<Object> updateMenu(@PathVariable Integer menuId,@RequestBody Menu body) {
			
			try {
				Optional<Menu> menu= menuRepository.findById(menuId);
				if (menu.isPresent()) {
					Menu menuEdit= menu.get();
					menuEdit.setNameMenu(body.getNameMenu());
					menuEdit.setDetailMenu(body.getDetailMenu());
					menuEdit.setIngerMenu(body.getIngerMenu());
					menuEdit.setGastroMenu(body.getGastroMenu());
					menuEdit.setId(body.getId());
					
					menuRepository.save(menu.get());
					
					return new ResponseEntity<>(menuEdit , HttpStatus.OK);
				}else {
					return new ResponseEntity<>("Menu not found", HttpStatus.BAD_REQUEST);
				}
			}catch (Exception e) {
				return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		
		
		@DeleteMapping("/menu/{menuId}")
		public ResponseEntity<Object> deleteMenu(@PathVariable Integer menuId) {
		
			try {
				Optional<Menu> menu = menuRepository.findById(menuId);
				
			if (menu.isPresent()) {
				menuRepository.delete(menu.get());
				return new ResponseEntity<>("DELETE SUCSESS", HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>("Member not fond",HttpStatus.BAD_REQUEST);
			}
			}catch (Exception e) {
			return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
			
		}

		}
		

		public MenuRepository getMenuRepository() {
			return menuRepository;
		}

		public void setMenuRepository( MenuRepository menuRepository) {
			this.menuRepository = menuRepository;
		}

		public MenuTypeRepository getmenuMenuTypeRepository() {
			return menuTypeRepository;
		}

		public void setMenuTypeRepository(MenuTypeRepository menuTypeRepository) {
			this.menuTypeRepository = menuTypeRepository;
		}


	
		public List<Menu> getData() {
			return data;
		}

		public void setData(List<Menu> data) {
			this.data = data;
		}

}
