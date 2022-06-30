package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer>{
	public List<Item> findByFactoryName(String name);
	public List<Item> findByCategoryName(String name);
}
