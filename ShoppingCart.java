import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShoppingCart {
    private int id;
    private int quantity;
    private double price;
    private double total;
    private int maxItems;
    private int maxKilos;
    private List<Item> items = new ArrayList<>();

    public ShoppingCart(int id) throws IOException {
        this.id = id;
        this.price = getPriceById(id);
        this.quantity = 0;
        this.total = 0.0;
        this.maxItems = 50;
        this.maxKilos = 50;
        readFromFile();
    }
    
    private int getQuantity(){
        return quantity;
    }

    public void addItem(int ID, int Quantity) throws IOException {
        if (ID < 1 || ID > 20) {
            System.out.println("Invalid item ID!");
            return;
        }
        double itemPrice = getPriceById(ID);
        String itemName = getNameById(ID);
        String itemType = getTypeById(ID);
        int addedQuantity = getQuantity();
        if (itemType.equals("kilo") && addedQuantity + Quantity > maxKilos) {
            System.out.println("You have reached the maximum number of this item in your cart!");
            return;
        } 
        else if (itemType.equals("unit") && addedQuantity + Quantity > maxItems) {
            System.out.println("You have reached the maximum kilos of this item in your cart!");
            return;
        } 
        else {
            this.items.add(new Item(ID, itemName, itemPrice, Quantity, itemType));
            System.out.println("You have added " + Quantity + " " + itemName + "(s) to your cart.");
            updateCartFile(ID, Quantity);
        } 
       
    }

    public void displayCart() throws IOException {
        File file = new File("shopping-cart.txt");
        if (!file.exists()) {
            System.out.println("Cart is empty.");
            return;
        }
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        double totalPrice = 0.0;
        System.out.println("Shopping Cart:");
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            int id = Integer.parseInt(parts[0]);
            int quantity = Integer.parseInt(parts[1]);
            double price = getPriceById(id);
            String name = getNameById(id);
            double itemPrice = price * quantity;
            totalPrice += itemPrice;
            System.out.println(quantity + " " + name + "(s) " + itemPrice + "$");
        }
        reader.close();
        System.out.println("Total price: " + totalPrice + "$");
    }
    
    

    private class Item {
        private int id;
        private String name;
        private double price;
        private String type;
        private int quantity;

        public Item(int id, String name, double price, int quantity, String type) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.quantity = quantity;
            this.type = type;
        }
        
        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public int getQuantity() {
            return quantity;
        }
        public String getType() {
            return type;
        }
    }


    public void saveCart() throws IOException{
            File file = new File("shopping-cart.txt");
            FileWriter writeToFile = new FileWriter(file);
            for (Item item : items) {
                writeToFile.write(item.getId() + "," + item.getQuantity() + "\n");
            }
            writeToFile.close();
        }
    
    
    public void emptyCart() {
        try {
            File file = new File("shopping-cart.txt");
            FileWriter writer = new FileWriter(file);
            writer.write("");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private double getPriceById(int id) {
        for (Item item : items) {
            if (item.getId() == id) {
                return item.getPrice();
            }
        }
        return 0.0;
    }

    private String getNameById(int id) {
        for (Item item : items) {
            if (item.getId() == id) {
                return item.getName();
            }
        }
        return "";
    }

    private String getTypeById(int id) {
        for (Item item : items) {
            if (item.getId() == id) {
                return item.getType();
            }
        }
        return "";
    }
    

    private void readFromFile() throws IOException{
    
            File file = new File("catalog.txt");
            Scanner scanner = new Scanner(file);
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().toLowerCase();
                String[] catalogParts = line.split("\\|");
                int id = Integer.parseInt(catalogParts[0].trim());
                String name = catalogParts[1].trim();
                double price = Double.parseDouble(catalogParts[2].trim());
                String type = catalogParts[3].trim();
                int quantity = getQuantity();
                items.add(new Item(id, name, price, quantity, type));
            }
            scanner.close();
        }
    

public void updateCartFile(int ID, int Quantity) throws IOException {
    
        File file = new File("shopping-cart.txt");
        Scanner scanner = new Scanner(file);
        List<String> lines = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");
            int id = Integer.parseInt(parts[0]);
            int quantity = Integer.parseInt(parts[1]);
            if (id == ID) {
                quantity += Quantity;
                line = ID + "," + quantity;
                Quantity = 0; 
            }
            lines.add(line);
        }
        if (Quantity > 0) { 
            lines.add(ID + "," + Quantity);
        }
        scanner.close();
        FileWriter writer = new FileWriter(file);
        for (String line : lines) {
            writer.write(line + "\n");
        }
        writer.close();
    }
}