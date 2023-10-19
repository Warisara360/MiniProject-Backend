package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.MenuType;
import com.example.demo.repository.MenuTypeRepository; // You need to create a repository for MenuType

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class MenuTypeController {

    @Autowired
    private MenuTypeRepository menuTypeRepository; 

    @GetMapping("/menutype")
    public ResponseEntity<Object> getAllMenuTypes() {
        try {
            List<MenuType> menuTypes = menuTypeRepository.findAll();
            return new ResponseEntity<>(menuTypes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    

    @PostMapping("/menutype")
    public ResponseEntity<Object> addMenuType(@RequestBody MenuType menuType) {
        try {
            MenuType newMenuType = menuTypeRepository.save(menuType);
            return new ResponseEntity<>(newMenuType, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/menutype/{menuTypeId}")
    public ResponseEntity<Object> getMenuTypeById(@PathVariable Integer menuTypeId) {
        try {
            Optional<MenuType> menuType = menuTypeRepository.findById(menuTypeId);
            if (menuType.isPresent()) {
                return new ResponseEntity<>(menuType, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("MenuType not found", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/menutype/{menuTypeId}")
    public ResponseEntity<Object> updateMenuType(@PathVariable Integer menuTypeId, @RequestBody MenuType updatedMenuType) {
        try {
            Optional<MenuType> menuType = menuTypeRepository.findById(menuTypeId);

            if (menuType.isPresent()) {
                MenuType menuTypeToEdit = menuType.get();
                menuTypeToEdit.setMenuType_name(updatedMenuType.getMenuType_name());

                menuTypeRepository.save(menuTypeToEdit);
                return new ResponseEntity<>(menuTypeToEdit, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("MenuType not found", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/menutype/{menuTypeId}")
    public ResponseEntity<Object> deleteMenuType(@PathVariable Integer menuTypeId) {
        try {
            Optional<MenuType> menuType = menuTypeRepository.findById(menuTypeId);

            if (menuType.isPresent()) {
                menuTypeRepository.delete(menuType.get());
                return new ResponseEntity<>("Delete SUCCESS", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("MenuType not found", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
