package io.spring.cloud.samples.commerce.ui.services.items;

public class Item {
    private Long id;
    private String name;
    private String category;
    private String description;
    private String price;

    public Item() { 
    }

    public Item(Long id, String name, String category, String description, String price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
		this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    
    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}