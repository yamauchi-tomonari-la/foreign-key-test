package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ForeignKeyController {
	@Autowired
	ItemRepository itemRepository;
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	FactoryRepository factoryRepository;
	
	@GetMapping("/items")
	public String items(Model model) {
		List<Item> items = itemRepository.findAll();
		model.addAttribute("items", items);
		model.addAttribute("num", 3.141592);
		return "item_list";
	}
	
	@GetMapping("/items/factory/{name}")
	public String items(
			@PathVariable("name") String name,
			Model model) {
		List<Item> items = itemRepository.findByFactoryName(name);
		model.addAttribute("items", items);
		return "item_list";
	}
	
	@GetMapping("/items/category/{name}")
	public String itemsByCategoryName(
			@PathVariable("name") String name,
			Model model) {
		List<Item> items = itemRepository.findByCategoryName(name);
		model.addAttribute("items", items);
		return "item_list";
	}
	
	@GetMapping("/categories")
	public String categories(Model model) {
		List<Category> categories = categoryRepository.findAll();
		model.addAttribute("categories", categories);
		return "category_list";
	}
	
	@GetMapping("/factories")
	public String factories(Model model) {
		List<Factory> factories = factoryRepository.findAll();
		model.addAttribute("factories", factories);
		return "factory_list";
	}

}
