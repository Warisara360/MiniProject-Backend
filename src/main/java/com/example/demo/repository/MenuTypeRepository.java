package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.MenuType;

public interface MenuTypeRepository extends JpaRepository<MenuType,Integer> {

}