import java.io.*;
import java.time.*;
import java.time.format.*;
import java.util.*;
import java.util.regex.*;
public class CashRegister {
    public static void clearScreen(){
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
    }

    public static void getMenu(){
        System.out.println("Menu");
        System.out.println("1. Foods");
        System.out.println("2. Drinks");
        System.out.println("3. View Order");
        System.out.println("4. Modifications");
        System.out.println("0. Proceed to Payment");
        System.out.print("Enter your choice: ");
    }

    public static void Payment(){
        System.out.println("Payment");
    }

    public static boolean usernameValidation(String uname) {
        Pattern patternun = Pattern.compile("^[a-zA-Z\\d\\s]{5,15}$"); //rule
        Matcher matcherun = patternun.matcher(uname); //inspect/check
        boolean isMatchFoundun;
        isMatchFoundun = matcherun.matches(); //validate

        if (isMatchFoundun){
            return true;
        } else {
            return false;
        }
    }

    public static boolean passwrdValidation(String pword) {
        Pattern patternpw = Pattern.compile("^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#%^&*()_\\-|])[A-Za-z\\d\\!\\@\\#\\%\\^\\&\\*\\(\\)\\_\\\\\\-\\|]{8,15}$"); //(?=.*) = somewhere ahead there must be an uppercase, digit, and specs
        Matcher matcherpw = patternpw.matcher(pword);
        boolean isMatchFoundpw;
        isMatchFoundpw = matcherpw.matches();

        if (isMatchFoundpw){
            return true;
        } else {
            return false;
        }
    }

    public static void createFile(File file){
        try {
            if (file.createNewFile()) {
                System.out.println("File created successfully.");
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("Failed to create the file   .");
            e.printStackTrace();
        }
    }

    public static boolean getReceipt(File filename){
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
        return true;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        ArrayList<String> username= new ArrayList<>();
        ArrayList<String> password= new ArrayList<>();
        ArrayList<String> orders = new ArrayList<>();
        ArrayList<Double> price = new ArrayList<>();
        ArrayList<Integer> quantity = new ArrayList<>();
        String uname;
        String pword;
        int menuchoice = -1;
        int selectfoods = -1;
        int total;
        int modification;
        boolean newtransaction;
        boolean isLogout;
        boolean isValidUn;
        boolean isValidPw;
        File file = new File("transaction.txt");
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);

        //signup
        System.out.println("                                                                                                 ");
        System.out.println(" _ _ _ _____ __    _____ _____ _____ _____    _____ _____    _____ _____ _____ _____ _____ _____ ");
        System.out.println("| | | |   __|  |  |     |     |     |   __|  |_   _|     |  |   __|_   _|  _  | __  | __  |   __|");
        System.out.println("| | | |   __|  |__|   --|  |  | | | |   __|    | | |  |  |  |__   | | | |     |    -| __ -|__   |");
        System.out.println("|_____|_____|_____|_____|_____|_|_|_|_____|    |_| |_____|  |_____| |_| |__|__|__|__|_____|_____|");
        System.out.println("                                                                                                 ");
        System.out.println();
        System.out.println("                                      WELCOME TO STARBS  ");
        System.out.println("------------------------------------------------------------------------------------------------------");
        do{
            isLogout = false;
            do{
                System.out.println("Create your USERNAME (Username must only be alphanumeric and 5-15 characters long (spaces are allowed))");
                System.out.print("Create a username: ");
                uname = sc.nextLine();

                isValidUn = usernameValidation(uname);

                if (!isValidUn) {
                System.out.println("Invalid username. Please try again.\n");
                } else {
                    username.add(uname);
                }   

            } while (!isValidUn);
            System.out.println("Username Accepted\n");
                    
            do {
                System.out.println("Create a PASSWORD (Password MUST contain at least one UPPERCASE letter, one NUMBER, one SPECIAL CHARACTER, and be 8-20 characters long)");
                System.out.print("Create your password: ");
                pword= sc.nextLine();
    
                isValidPw = passwrdValidation(pword);
                    
                if(!isValidPw) {
                System.out.println("Invalid Password. Please Try Again");
                } else {
                    password.add(pword);
                }
            } while (!isValidPw);
                   
            //login
            clearScreen();
            System.out.println("Password Accepted\n"); 
            boolean isLoginSuccess;
            do {
                System.out.println("Login using your created USERNAME and PASSWORD");
                System.out.print("Username: ");
                String loginun = sc.nextLine();
    
                System.out.print("Password: ");
                String loginpw = sc.nextLine();
    
                isLoginSuccess = false;
    
                for(int i = 0; i < username.size(); i++){
                    if(loginun.equals(username.get(i)) && loginpw.equals(password.get(i))){
                        isLoginSuccess = true;
                    }
                }
    
                if(!isLoginSuccess){
                    System.out.println("Invalid Username or Password. Please Try Again");
                }
            } while (!isLoginSuccess);
            System.out.println("Login Successfull");
    
            //menu
            clearScreen();
            if(isLoginSuccess){
                do{
                    newtransaction = false;
                    System.out.println("                                                                                                 ");
                    System.out.println(" _ _ _ _____ __    _____ _____ _____ _____    _____ _____    _____ _____ _____ _____ _____ _____ ");
                    System.out.println("| | | |   __|  |  |     |     |     |   __|  |_   _|     |  |   __|_   _|  _  | __  | __  |   __|");
                    System.out.println("| | | |   __|  |__|   --|  |  | | | |   __|    | | |  |  |  |__   | | | |     |    -| __ -|__   |");
                    System.out.println("|_____|_____|_____|_____|_____|_|_|_|_____|    |_| |_____|  |_____| |_| |__|__|__|__|_____|_____|");
                    System.out.println("                                                                                                 ");
                    
                    do {
                        try {
                            getMenu();
                            menuchoice = sc.nextInt();
                            switch (menuchoice) {
                                case 0: // payment
                                    Payment();
                                    break;
                                case 1: // for foods menu
                                    clearScreen();
                                    String[] foods = {"Grilled Ham and Three Cheese on Sourdough Bread", "Chocolate Chip Crunch Cheesecake", "Strawberry Pistachio Crepe Cake", "SPAM Classic, Egg & Cheese on Potato Bread", "Roasted Ham, Egg, & Cheese on Croissant Bun" };
                                    Double[] fooodsprice = {125.00, 150.00, 165.00, 160.00, 140.00};
                
                                    for(int i = 0; i < foods.length; i++){ // to display the food list
                                    System.out.println((i + 1) + ". " + foods[i] + " - P" + fooodsprice[i]);
                                    }
                                    
                                    System.out.println("\n");
                                    try {
                                        System.out.print("Select food/s: ");
                                        selectfoods = sc.nextInt();
                                        if (selectfoods > 0 && selectfoods <= foods.length) {
                                            orders.add(foods[selectfoods - 1]);
                                            price.add(fooodsprice[selectfoods - 1]);
                                        }
                                    } catch (InputMismatchException e) {
                                        System.out.println("Invalid input! Please enter a number only.");
                                        sc.nextLine();
                                    }
                
                                    try {
                                        System.out.print("Quantity: "); // to indicate the quantity
                                        int foodquantity = sc.nextInt();
                                        quantity.add(foodquantity);
                                    } catch (InputMismatchException e) {
                                        System.out.println("Invalid input! Please enter a number only.");
                                        sc.nextLine();
                                    }
                
                                    clearScreen();
                                    System.out.println("Order Summary: "); // to show the order/s
                                    for (int i = 0; i < orders.size(); i++){
                                        System.out.println(quantity.get(i) + " x " + orders.get(i) + " - P" + price.get(i));
                                    }
                
                                    System.out.println("\n");
                                    System.out.println("Do you want to add another item/product? (0 if none) ");
                                    break;
                
                                case 2: // for drinks menu
                                    clearScreen(); 
                                    String[] drinks = {"Strawberry Mochi Pure Matcha Latte", "Honey Nougat Oatmilk Macchiato", "Green Tea Cream Frappuccino", "Chocolate Chip Cream Frappuccino", "Hot Brewed Coffee" };
                                    Double[] drinkprice= {125.00, 150.00, 165.00, 160.00, 140.00};
                
                                    for(int i = 0; i < drinks.length; i++){ // to display the drink list
                                    System.out.println((i + 1) + ". " + drinks[i] + " - P" + drinkprice[i]);
                                    }
                
                                    System.out.println("\n");
                                    try{
                                        System.out.print("Select drink/s: "); // for user to select their drink
                                        int selectdrink = sc.nextInt();
                                        if (selectdrink > 0 && selectdrink <= drinks.length) {
                                            orders.add(drinks[selectdrink - 1]);
                                            price.add(drinkprice[selectdrink - 1]);
                                        }
                                    } catch (InputMismatchException e) {
                                        System.out.println("Invalid input! Please enter a number only.");
                                        e.printStackTrace();
                                        sc.nextLine();
                                    }

                                    try{
                                        System.out.print("Quantity: "); // to indicate the quantity
                                        int drinkquantity = sc.nextInt();
                                        quantity.add(drinkquantity);
                                    } catch (InputMismatchException e) {
                                        System.out.println("Invalid input! Please enter a number only.");
                                        e.printStackTrace();
                                        sc.nextLine();
                                    }
                
                                    clearScreen();
                                    System.out.println("Order Summary: "); // to show the order/s
                                    for (int i = 0; i < orders.size(); i++){
                                        System.out.println(quantity.get(i) + " x " + orders.get(i) + " - P" + price.get(i));
                                    }
                
                                    System.out.println("Do you want to add another item/product? (0 if none) ");
                                    break; 
                
                                case 3: // View Order/s
                                    clearScreen();
                                    System.out.println("Order Summary: ");
                                    for (int i = 0; i < orders.size(); i++){
                                        System.out.println(quantity.get(i) + " x " + orders.get(i) + " - P" + price.get(i));
                                    }
                                    total = 0;
                                    for (int i = 0; i < price.size(); i++) { // to calculate the total 
                                        total += price.get(i) * quantity.get(i);
                                    }
                                    System.out.println("Your total: P" + total);
                                    System.out.println("\n");
                                    break;
                
                                case 4: // modification
                                clearScreen();
                                try {
                                    System.out.print("Do you want to remove or edit order/s? (1 = remove & 2 = edit): ");
                                    modification = sc.nextInt();
                                    if (modification == 1){ // to remove an order
                                        for (int i = 0; i < orders.size(); i++){
                                            System.out.println((i + 1) + ". "+ quantity.get(i) + " x " + orders.get(i) + " - P" + price.get(i));
                                        }
                                        System.out.print("Which item do you want to remove?: ");
        
                                        int remove = sc.nextInt() - 1;
                                                if (remove >= 0 && remove < orders.size()){
                                                orders.remove(remove);
                                                price.remove(remove);
                                                quantity.remove(remove);
                                                }
                                            
                                        clearScreen();
                                        System.out.println("Order updated^^");
                                        System.out.println("\n");
                                    } else if (modification == 2){ // to edit order quantity
                                        System.out.println("Which item do you want to modify? ");
                                        for (int i = 0; i < orders.size(); i++){
                                            System.out.println((i + 1) + ". "+ quantity.get(i) + " x " + orders.get(i) + " - P" + price.get(i));
                                        }
                    
                                        System.out.print("Select item number to modify: ");
                                        int modify = sc.nextInt() - 1;
                                        if (modify >= 0 && modify < orders.size()){
                                            System.out.print("Enter new quantity: ");
                                            int newqty = sc.nextInt();
                                            quantity.set(modify, newqty);
                                            clearScreen();
                                            System.out.println("Order updated^^");
                                            System.out.println("\n");
                                        }
                                    } else {
                                        clearScreen();
                                        System.out.println("Invalid choice!");
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid input! Please enter a number only.");
                                    sc.nextLine();
                                    break; // skip rest of case 4 on error
                                }
                                break;
                
                                default:
                                    System.out.println("Invalid!");
                                    clearScreen();
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input! Please enter a number.");
                            e.printStackTrace();
                            sc.nextLine();
                        }
                    } while (menuchoice !=0);
                        
                        clearScreen();
                        System.out.println("Order Summary: "); // to show the order/s
                        for (int i = 0; i < orders.size(); i++){
                            System.out.println(quantity.get(i) + " x " + orders.get(i) + " - P" + price.get(i));
                        }
                
                        total = 0;
                        for (int i = 0; i < price.size(); i++) { // to calculate the total 
                            total += price.get(i) * quantity.get(i);
                        }
                        System.out.println("Your total: P" + total);
                
                        System.out.print("Input your payment: P");
                        Double payment = sc.nextDouble();
                    
                        while (payment < total) { // if payment is insufficient
                            double lack = total - payment;
                            System.out.println("Insufficient payment (" + lack + ")");
                            System.out.print("Input your payment: P");
                            payment += sc.nextDouble();
                        }
                        
                        clearScreen();
                        if (payment == total) { // if payment is exact
                        }

                        createFile(file);
                        String lastIndex = username.get(username.size() - 1);

                        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                            writer.write("=====================================================\n");
                            writer.write("                   Order Receipt                     \n");
                            writer.write("=====================================================\n");
                            
                            for (int i = 0; i < orders.size(); i++) {
                                writer.write(quantity.get(i) + " x " + orders.get(i) + " - P" + price.get(i) + "\n");
                            }
                            
                            writer.write("-----------------------------------------------------\n");
                            
                            total = 0;
                            for (int i = 0; i < price.size(); i++) {
                                total += price.get(i) * quantity.get(i);
                            }
                            
                            writer.write("Your total: " + total + "\n");
                            writer.write("Received: " + payment + "\n");
                            
                            if (payment > total) {
                                double change = payment - total;
                                writer.write("Your change: " + change + "\n");
                            }
                            
                            writer.write("-----------------------------------------------------\n");
                            writer.write("          Thank you for ordering at Starbs!          \n");
                            writer.write("                 " + formattedDate + " \n ");
                            writer.write("                      " + lastIndex + "     \n ");
                            writer.write("=======================================================\n");
                            System.out.println("Receipt written successfully.");
                        } catch (IOException e) {
                            System.out.println("Failed to write to the file.");
                            e.printStackTrace();
                        }
                        
                getReceipt(file);
                
                System.out.println();
                try {
                    System.out.print("Do want to perform another transaction with this account? (Y/N): "); // to perform another transaction
                    char transactionchoice = sc.next().toUpperCase().charAt(0);
                    System.out.println("\n");

                    if(transactionchoice == 'Y') {
                        newtransaction = true;
                        orders.clear();
                        price.clear();
                        quantity.clear();
                        total = 0;
                        clearScreen();
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input! Please enter Y or N.");
                    sc.nextLine();
                    newtransaction = false;
                }
                } while (newtransaction);
                
            }
            
        try {
            System.out.print("Do you want to sign up to other account? (Y/N): ");
            char logout = sc.next().toUpperCase().charAt(0);
            sc.nextLine();

            if(logout == 'Y'){
                isLogout = true;
                newtransaction = true;
                orders.clear();
                price.clear();
                quantity.clear();
                total = 0;
                clearScreen();
            } else {
                System.out.println("                                      __ ");
                System.out.println(" _____ _           _                 |  |");
                System.out.println("|_   _| |_ ___ ___| |_    _ _ ___ _ _|  |");
                System.out.println("  | | |   | .'|   | '_|  | | | . | | |__|");
                System.out.println("  |_| |_|_|__,|_|_|_,_|  |_  |___|___|__|");
                System.out.println("                         |___|           ");
            }
        } catch (Exception e) {
            System.out.println("Invalid input! Please enter Y or N.");
            sc.nextLine(); // clear input buffer
            isLogout = false;
        }
        } while (isLogout);
    sc.close();    
    }
}