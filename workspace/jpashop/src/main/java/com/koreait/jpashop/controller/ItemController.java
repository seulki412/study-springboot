package com.koreait.jpashop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.koreait.jpashop.domain.Item;
import com.koreait.jpashop.dto.ItemForm;
import com.koreait.jpashop.service.ItemService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ItemController {

	private final ItemService itemService;
	
	//request : items/new
	//response : items/createItemForm
	
	@GetMapping("items/new")
	public String createForm(Model model) {
		model.addAttribute("form", new ItemForm());
		return "items/createItemForm";
	}
	
	// 저장로직
	// request : /items/new
	// response : /
	@PostMapping("/items/new")
	public String saveItem(ItemForm itemForm) {
		Item item = new Item();
		item.setName(itemForm.getName());
		item.setPrice(itemForm.getPrice());
		item.setStockQuantity(itemForm.getStockQuantity());
		itemService.saveItem(item);
		return "redirect:/";
	}
	
	// request : items
	// response : items/itemList
	@GetMapping("items")
	public String findItems(Model model) {
		model.addAttribute("items",itemService.finditems());
		return "items/itemList";
	}
	
	
	
	
}
