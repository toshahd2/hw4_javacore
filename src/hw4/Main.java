package hw4;

import java.util.Random;

public class Main {
    private static Customer[] customers = new Customer[2];
    private static Product[] products = new Product[5];
    public static void main(String[] args) {
        customers[0] = new Customer("Егоров", "Антон", 32, "79686046688", Gender.MALE);
        customers[1] = new Customer("Романова", "Анна", 65, "79161112233", Gender.FEMALE);

        products[0] = new Product("Пылесос", 10000.0, Category.PREMIUM);
        products[1] = new Product("Фен", 5000.0, Category.PREMIUM);
        products[2] = new Product("Утюг", 3000.0, Category.STANDARD);
        products[3] = new Product("Плита", 15000.0, Category.STANDARD);
        products[4] = new Product("Холодильник", 20000.0, Category.STANDARD);

        System.out.println("Сегодня переоценка товара " + products[4].getName());
        System.out.println("Старая цена: " + products[4].getPrice());
        products[4].setRandomDiscount();
        System.out.println("Новая цена: " + products[4].getPrice());
        System.out.println();

        System.out.println();

        Order[] orders = new Order[5];
        Random random = new Random();

        for (int i = 0; i < orders.length; i++) {
            Customer Customer = customers[random.nextInt(customers.length)];
            Product Product = products[random.nextInt(products.length)];

            int Quantity = random.nextInt((5 - 1) + 1) + 1;

            try {
                orders[i] = purchase(Customer.getLastName(), Product.getName(), Quantity);
                System.out.println("Товар: " + orders[i].getProduct().getName() + ", количество товаров: "
                        + orders[i].getQuantity() + ", стоимость: " + orders[i].getCost());
            } catch (CustomerException e) {
                System.out.println(e.getMessage() + " (" + Customer.getLastName() + ")");
            } catch (ProductException e) {
                System.out.println(e.getMessage() + " (" + Product.getName() + ")");
            } catch (AmountException e) {
                System.out.println(e.getMessage() + " (" + Quantity + ")");
            }
        }
        System.out.println();
    }
    public static <customer> Order purchase(String lastName, String productName, int quantity)
            throws CustomerException, ProductException, AmountException {
        Customer customer = null;
        for (Customer c : customers) {
            if (c.getLastName().equals(lastName)) {
                customer = c;
                break;
            }
        }

        if (customer == null) {
            throw new CustomerException("Несуществующий покупатель: " + lastName);
        }

        Product product = null;
        for (Product p : products) {
            if (p.getName().equals(productName)) {
                product = p;
                break;
            }
        }

        if (product == null) {
            throw new ProductException("Несуществующий товар: " + productName);
        }
        if (quantity <= 0 || quantity > 99) {
            throw new AmountException("Неверное количество: " +  " " + lastName + " " + productName);
        }

        return new Order(customer, product, quantity);
    }
}