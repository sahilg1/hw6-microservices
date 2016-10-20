package io.spring.cloud.samples.commerce.ui.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.spring.cloud.samples.commerce.ui.services.items.Item;
import io.spring.cloud.samples.commerce.ui.services.items.ItemService;
import java.util.LinkedHashMap;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class UiController {

    @Autowired
    ItemService service;

    

    @RequestMapping("/items")
    public List<Item> randomItems() {
        return service.randomItems2();
    }
    
    @RequestMapping("/category/{cat}")
    public Iterable<Item> itemsByCategory(@PathVariable("cat") String category) {
        return service.categoryItems(category);
    }
    
    @RequestMapping("/item/{id}")
    public Iterable<Item> itemByPrice(@PathVariable("id") String myId){
        return service.itemPrice(myId);
    }
}
