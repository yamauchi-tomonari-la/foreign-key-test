package com.example.demo;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ForeignKeyController {
	@Autowired
	ItemRepository itemRepository;
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	FactoryRepository factoryRepository;
	
	@GetMapping("/login")
	public String login() {
		return "panda";
	}
	
	@GetMapping("/items")
	public String items(Model model) {
		List<Item> items = itemRepository.findAll();
		model.addAttribute("items", items);
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
	
	@GetMapping("/item/imageUpload/{name}")
	public String itemImageUpload(
			@PathVariable("name") String name,
			Model model) 
	{
		Optional<Item> o = itemRepository.findById(name);
		if (o == null || o.isEmpty()) {
			return "redirect:/items";
		}
		model.addAttribute("item", o.get());
		return "item_image_upload";
	}
	
	@PostMapping("/item/imageUpload/{name}")
	public String itemImageUpload(
			@PathVariable("name") String name,
			@RequestParam("image") MultipartFile image) throws IOException {
		Optional<Item> o = itemRepository.findById(name);
		if (o == null || o.isEmpty()) {
			return "redirect:/items";
		}
		Item item = o.get();
		item.setImage(image.getBytes());
		itemRepository.saveAndFlush(item);
		return "redirect:/items";
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

	@GetMapping("/delete/{name}")
	public String deleteItem(
			@PathVariable("name") String name,
			Model model) {
		try {
		itemRepository.deleteById(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/items";
	}
}
