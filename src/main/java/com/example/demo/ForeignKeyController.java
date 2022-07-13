package com.example.demo;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
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
		model.addAttribute("target", "item_image_upload");
		return "item_upload";
	}
	
	@PostMapping("/item/imageUpload/{name}")
	public String itemImageUpload(
			@PathVariable("name") String name,
			@RequestParam(name="image", required=false) MultipartFile image) throws IOException {
		Optional<Item> o = itemRepository.findById(name);
		if (o == null || o.isEmpty()) {
			return "redirect:/items";
		}
		System.err.println("image: " + image);
		if (image != null) {
			System.err.println("empty: " + image.isEmpty());
		}
		Item item = o.get();
		item.setImage(image.getBytes());
		itemRepository.saveAndFlush(item);
		return "redirect:/items";
	}
	
	@GetMapping("/item/fileUpload/{name}")
	public String itemFileUpload(
			@PathVariable("name") String name,
			Model model) 
	{
		Optional<Item> o = itemRepository.findById(name);
		if (o == null || o.isEmpty()) {
			return "redirect:/items";
		}
		model.addAttribute("item", o.get());
		model.addAttribute("target", "item_file_upload");
		return "item_upload";
	}
	
    private String getExtension(String filename) {
        int dot = filename.lastIndexOf(".");
        if (dot > 0) {
          return filename.substring(dot).toLowerCase();
        }
        return "";
      }

    private String getUploadFileName(String fileName) {

        return fileName + "_" +
                DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")
                    .format(LocalDateTime.now())
                + getExtension(fileName);
    }

    private void createDirectory() {
        Path path = Paths.get("C:/upload/files");
        if (!Files.exists(path)) {
          try {
            Files.createDirectory(path);
          } catch (Exception e) {
            //エラー処理は省略
          }
        }
    }

   private void savefile(MultipartFile file) {
        String filename = getUploadFileName(file.getOriginalFilename());
        Path uploadfile = Paths.get("C:/upload/files/" + filename);
        try (OutputStream os = Files.newOutputStream(uploadfile, StandardOpenOption.CREATE)) {
          byte[] bytes = file.getBytes();
          os.write(bytes);
        } catch (IOException e) {
          //エラー処理は省略
        }
      }

	@PostMapping("/item/fileUpload/{name}")
	public String itemFileUpload(
			@PathVariable("name") String name,
			@RequestParam("image") MultipartFile image) throws IOException {
		Optional<Item> o = itemRepository.findById(name);
		if (o == null || o.isEmpty()) {
			return "redirect:/items";
		}
		Item item = o.get();
        savefile(image);

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
	@GetMapping("/time")
	public String time(Model model) {
		LocalDateTime now = LocalDateTime.now();
		model.addAttribute("ldt", now);
		model.addAttribute("dt", now.toEpochSecond(ZoneOffset.UTC));
		return "time";
	}
}
