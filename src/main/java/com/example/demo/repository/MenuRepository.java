package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Menu;

public interface MenuRepository extends JpaRepository<Menu, Integer>{
	@Query("SELECT m FROM Menu m WHERE m.member.memberId = :memberId")
    List<Menu> findMenuByMemberId(@Param("memberId") int memberId);
}
