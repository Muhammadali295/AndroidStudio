package com.example.grocera;

public class SnacksModel {
    private String name,company_name,image,price;
    private int quantity;
    private float totalprice;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompanyname() {return company_name;}

    public void setCompanyname(String company_name) {this.company_name = company_name;}

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
