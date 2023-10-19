package com.example.demo.controller;

import com.example.demo.model.Member;
import com.example.demo.model.Menu;
import com.example.demo.model.MenuType;
import com.example.demo.repository.MenuRepository;
import com.example.demo.repository.MenuTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class MenuController {

    @Autowired
    private MenuRepository menuRepository;
    
    @Autowired
    private MenuTypeRepository menutypeRepository;
    
    private List<Menu> data = new ArrayList<Menu>();


    @GetMapping("/menu")
    public ResponseEntity<Object> getAllMenus() {
        try {
            List<Menu> menus = menuRepository.findAll();
            return new ResponseEntity<>(menus, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/menu")
    public ResponseEntity<Object> addMenu(@RequestBody Menu menu) {
        try {
           
            Menu newMenu = menuRepository.save(menu);
            return new ResponseEntity<>(newMenu, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{menuId}")
    public ResponseEntity<Object> getMenuById(@PathVariable Integer menuId) {
        try {
            Optional<Menu> menu = menuRepository.findById(menuId);
            if (menu.isPresent()) {
                return new ResponseEntity<>(menu.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Menu not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{menuId}")
    public ResponseEntity<Object> updateMenu(@PathVariable Integer menuId, @RequestBody Menu updatedMenu) {
        try {
            Optional<Menu> menu = menuRepository.findById(menuId);

            if (menu.isPresent()) {
                Menu menuToEdit = menu.get();
                menuToEdit.setNameMenu(updatedMenu.getNameMenu());
                menuToEdit.setDetailMenu(updatedMenu.getDetailMenu());
                menuToEdit.setIngerMenu(updatedMenu.getIngerMenu());
                menuToEdit.setGastroMenu(updatedMenu.getGastroMenu());
                menuToEdit.setMenuType(updatedMenu.getMenuType());

                menuRepository.save(menuToEdit);
                return new ResponseEntity<>(menuToEdit, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Menu not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{menuId}")
    public ResponseEntity<Object> deleteMenu(@PathVariable Integer menuId) {
        try {
            Optional<Menu> menu = menuRepository.findById(menuId);

            if (menu.isPresent()) {
                menuRepository.delete(menu.get());
                return new ResponseEntity<>("Delete SUCCESS", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Menu not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
   

	@GetMapping("/menu/{memberId}")
	public ResponseEntity<Object> getMenuByMemberId(@PathVariable int memberId) {
	    try {
	        List<Menu> menus = menuRepository.findMenuByMemberId(memberId);
	        if (menus.isEmpty()) {
	            return new ResponseEntity<>("No menu items found for the member ID: " + memberId, HttpStatus.NOT_FOUND);
	        } else {
	            return new ResponseEntity<>(menus, HttpStatus.OK);
	        }
	    } catch (Exception e) {
	        return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
}
