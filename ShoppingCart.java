import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class represents a shopping cart.
 * It has a list of items, a total price and a maximum number of items and kilos.
 * It can add items to the cart, display the cart and get the total price.k
 *
 * @author Sara Adel
 * @version 1.0
 * @since 9 May 2023
 */
public class ShoppingCart {
    /**
     * The ID of the shopping cart.
     */
    private int id;
    /**
     * The quantity of items in the cart.
     */
    private int quantity;
    /**
     * The total price of the cart.
     */
    private float price;
    /**
     * The total price of the cart.
     */
    private float total;
    /**
     * The maximum number of units of one item in the cart.
     */
    private int maxItems;
    /**
     * The maximum number of kilos of one item in the cart.
     */
    private int maxKilos;
    /**
     * The list of items in the cart.
     */
    private List<Item> items = new ArrayList<>();

    /**
     * this is a constructor for the shopping cart.
     * It initializes the ID, price, quantity, total, maxItems and maxKilos.
     *
     * @param id the ID of the shopping cart
     */
    public ShoppingCart(int id) throws IOException {
        
        this.id = id;
        this.price = getPriceById(id);
        this.quantity = 0;
        this.total =  0;
        this.maxItems = 50;
        this.maxKilos =  50;
        readFromFile();
        
    }

    /**
     * this method returns the quantity
     */
    private int getQuantity() {
        
        return quantity;
    }

    /**
     * this method add item to the shopping cart.
     *
     * @param ID       the ID of the item
     * @param Quantity the quantity of the item
     * @throws IOException if an I/O error occurs
     */
    public void addItem(int ID, int Quantity) throws IOException {
        if (ID < 1 || ID > 20) {
            
            System.out.println("Invalid item ID!");
            return;
        }
        float itemPrice = getPriceById(ID);
        String itemName = getNameById(ID);
        String itemType = getTypeById(ID);
        int addedQuantity = getQuantity();
        if (itemType.equals("kilo") && addedQuantity + Quantity > maxKilos) {
            System.out.println("You have reached the maximum number of this item in your cart! ");
            return;
        } else if (itemType.equals("unit") && addedQuantity + Quantity > maxItems) {
            System.out.println("You have reached the maximum kilos of this item in your cart! ");
            return;
        } else {
            this.items.add(new Item(ID, itemName, itemPrice, Quantity, itemType));
            System.out.println("You have added " + Quantity + " " + itemName + "(s) to your cart. ");
            updateCartFile(ID, Quantity);
        }

    }

    /**
     * this method displays the cart
     *
     * @throws IOException if an I/O error occurs
     */
    public void displayCart() throws IOException {
        File file = new File("shopping-cart.txt ");
        if (!file.exists()) {
            System.out.println("Cart is empty. ");
            return;
        }
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        float totalPrice = 0;
        System.out.println("-----------------------Shopping Cart----------------------- ");
        while ((line = reader.readLine()) !=  null) {
            String[] parts = line.split(",");
            int id = Integer.parseInt(parts[0]);
            int quantity = Integer.parseInt(parts[1]);
            float price = getPriceById(id);
            String name = getNameById(id);
            float itemPrice = price * quantity;
            totalPrice += itemPrice;
            System.out.println(quantity + " " + name + "(s) " + itemPrice + "$ ");
        }
        reader.close();
        
        System.out.println("Total price: " + totalPrice + "$");
        System.out.println("-----------------------------------------------------------");
    }

    /**
     * this method gets the total price of the cart
     *
     * @throws IOException if an I/O error occurs
     */
    public float getTotal() throws IOException {
        
        File file = new File("shopping-cart.txt");
        if (!file.exists()) {
            System.out.println("Cart is empty.");
            return 0;
            
        }
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        float totalPrice = 0;
        
        while ((line = reader.readLine()) != null) {
            
            String[] parts = line.split(",");
            int id = Integer.parseInt(parts[0]);
            int quantity = Integer.parseInt(parts[1]);
            float price = getPriceById(id);
            String name = getNameById(id);
            float itemPrice = price * quantity;
            totalPrice += itemPrice;
            
        }
        reader.close();
        return totalPrice;
        
    }

    /**
     * this method saves the cart to the cart file
     *
     * @throws IOException if an I/O error occurs
     */
    public void saveCart() throws IOException {
        File file = new File("shopping-cart.txt");
        FileWriter writeToFile = new FileWriter(file);
        for (Item item : items) {
            writeToFile.write(item.getId() + "," + item.getQuantity() + "\n");
            
        }
        writeToFile.close();
    }

    /**
     * this method empties the cart
     */
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

    /**
     * this method returns the price of item
     *
     * @param id the ID of the item
     * @return the price of the item
     */
    private float getPriceById(int id) {
        
        for (Item item : items) {
            if (item.getId() == id) {
                
                return item.getPrice();
            }
        }
        return 0;
        
    }

    /**
     * this method returns the name of the item
     *
     * @param id the ID of the item
     * @return the name of the item
     */
    private String getNameById(int id) {
        for (Item item : items) {
            if (item.getId() == id) {
                
                return item.getName();
            }
        }
        return "";
    }

    /**
     * this method returns the type of the item
     *
     * @param id the ID of the item
     * @return the type of the item
     */
    private String getTypeById(int id) {
        for (Item item : items) {
            
            if (item.getId() == id) {
                
                return item.getType();
            }
        }
        return "";
    }

    /**
     * this method reads from the catalog file
     *
     * @throws IOException if an I/O error occurs
     */
    private void readFromFile() throws IOException {

        File file = new File("catalog.txt");
        Scanner scanner = new Scanner(file);
        scanner.nextLine();
        while (scanner.hasNextLine()) {
            
            String line = scanner.nextLine().toLowerCase();
            String[] catalogParts = line.split("\\|");
            int id = Integer.parseInt(catalogParts[0].trim());
            String name = catalogParts[1].trim();
            float price = Float.parseFloat(catalogParts[2].trim());
            String type = catalogParts[3].trim();
            int quantity = getQuantity();
            items.add(new Item(id, name, price, quantity, type));
        }
        scanner.close();
    }

    /**
     * this method updates the cart file
     *
     * @param ID       the ID of the item
     * @param Quantity the quantity of the item
     * @throws IOException if an I/O error occurs
     */
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

    /**
     * this class represents an item
     * it has an ID, name, price, type and quantity
     * it has getters for all of them
     * it has a constructor that sets all of them
     */
    private class Item {
        private int id;
        private String name;
        private float price;
        private String type;
        private int quantity;

        public Item(int id, String name, float price, int quantity, String type) {
            
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

        public float getPrice() {
            return price;
        }

        public int getQuantity() {
            return quantity;
        }

        public String getType() {
            return type;
        }
    }
}
