package hw4;

import java.util.Random;

public class Product {
    private String name;
    private double price;
    private Category category;
    public Product(String name, double price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;

    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setRandomDiscount() throws TooMuchSaleException {
        Discount[] discounts = Discount.values();
        Random random = new Random();
        Discount discount = discounts[random.nextInt(discounts.length)];
        if (category == Category.PREMIUM && discount.getValue() > 15) {
            throw new TooMuchSaleException("Скидка на товар категории Премиум не может быть более 15%");
        }
        double newPrice = price - (price * discount.getValue() / 100.0);
        price = newPrice;
    }
}
