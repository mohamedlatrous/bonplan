package com.example.bonplan;

public class Product {
    String name, price, date, image,tel , description;
    public Product(String name, String price, String date, String image, String tel, String description) {
        this.name = name;
        this.price = price+" DT";
        this.date = date;
        this.image = image;
        this.tel = tel;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getDate() {
        return date;
    }

    public String getImage() {
        return image;
    }

    public String getTel() {
        return tel;
    }

    public String getDescription() {
        return description;
    }
}
