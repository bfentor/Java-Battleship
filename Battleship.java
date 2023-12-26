import java.util.Scanner;
/*
 * Ideas board:
 * 
 *      - Save game system
 *          You can save mid game. All the positions and states of 
 *          the ships would be saved to a file which you can load 
 *          on the main menu.
 *          
 *      - AI opponent
 *          Instead of playing with a real person with hotswap,
 *          you can play against a CPU. These wouldn't just be 
 *          random decisions either, but made based on how they
 *          are doing in the game
 *          
 *      - Some hilarious quips from the AI
 *          If you sink too many battleships too quickly they might
 *          make note of it. They might also say some other funny 
 *          and random things.
 *       
 *      - Netplay
 *          An opponent can boot up another copy of the game on a 
 *          different system (on the local network) and connect with
 *          another player. (Option on the main menu screen).
 *          
 *      - Chat system for Netplay
 *          Pretty self explanatory.
 *          
 */


/**
 * Player class for the Battleship game
 *
 * @author Balazs Fentor
 * @version 1.0
 */
public class Battleship {
    // clear screen Unix
    final static String ANSI_HOME = "\u001b[H";
    final static String ESC = "\033[";
    final static String cls = ESC + "2J" + ANSI_HOME;
    
    // squars
    final static char hollowSquare = 9633;
    final static char filledSquare = 9632;
    final static char crossedSquare = 9639;
    //final static char crossedSquare = 9949;
        
    // names
    final static String[] names = {"Carrier", "Battleship", "Cruiser", "Submarine", "Destroyer"};
    
    // opponent
    static Ship[] opponentShips = new Ship[5];
    static ShipPart[][] opponentBoard = new ShipPart[10][10];
    
    // player
    static Ship[] playerShips = new Ship[5];
    static ShipPart[][] playerBoard = new ShipPart[10][10];
    
    public static void main(String[] args) {
        // test        
        //test();
        menu();
    }
    public static void test() {
        preparePlayerBoard();
        playerBoard[1][8].setDestroyed(true);
        playerBoard[0][8].setDestroyed(true);
        printPlayerBoard();
        testPrint();
        System.out.println(playerBoard[9][2]);
        System.out.println(playerBoard[4][6]);
        checkSunkShip();
        checkSunkShip();
    }
    public static void testPrint() {
        System.out.printf("%n%-6s %-6s %-12s %-2s %-2s %n", "State", "Hor?", "Type", "X", "Y");
        System.out.println("================================");
        for (Ship test : playerShips)
            System.out.println(test);
    }
    public static void preparePlayerBoard() {
        // for testing
        /*
        playerShips[0] = new Ship(false, false, "Carrier", 5, 10, 2);
        playerShips[1] = new Ship(false, true, "Battleship", 4, 5, 10);
        playerShips[2] = new Ship(false, true, "Cruiser", 3, 3, 7);
        playerShips[3] = new Ship(false, false, "Submarine", 3, 2, 4);
        playerShips[4] = new Ship(false, true, "Destroyer", 2, 1, 9);
        */
        for (int i = 0; i < playerShips.length; i++) {
            for (int k = 0; k < playerShips[i].getNum(); k++) {
                if (playerShips[i].isHorizontal())
                    playerBoard[playerShips[i].getX()+k-1][playerShips[i].getY()-1] = new ShipPart(false, names[i]);
                else 
                    playerBoard[playerShips[i].getX()-1][playerShips[i].getY()+k-1] = new ShipPart(false, names[i]);
            }
        }
    }
    public static void checkSunkShip() {
        for (int i = 0; i < playerShips.length; i++) {
            int totDest = 0;
            if (!playerShips[i].isDestroyed())
                for (int k = 0; k < playerShips[i].getNum(); k++) {
                    if (playerShips[i].isHorizontal()) {
                        if (playerBoard[playerShips[i].getX()+k-1][playerShips[i].getY()-1].isDestroyed())
                            totDest++;
                    } else {
                        if (playerBoard[playerShips[i].getX()-1][playerShips[i].getY()+k-1].isDestroyed())
                            totDest++;
                    }
                }
            if (totDest == playerShips[i].getNum()) {
                System.out.println("You sunk my " + names[i] + "!");
                playerShips[i].setDestroyed(true);
            }   
        }
    }
    public static void printPlayerBoard() {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (int y = 0; y < playerBoard.length; y++) {
            System.out.printf("%-2d", y+1);
            for (int x = 0; x < playerBoard[y].length; x++) {
                if (playerBoard[x][y] != null) {
                    if (!playerBoard[x][y].isDestroyed()) 
                        System.out.print(filledSquare + " ");
                    else 
                        System.out.print(crossedSquare + " ");
                } else 
                    System.out.print(hollowSquare + " ");
            }
            System.out.println();
        }
    }
    public static void menu() {
        System.out.println(cls);
        
        Scanner in = new Scanner(System.in);
       
        //printPlayerBoard();
        
        System.out.println("Battleship\n");
        System.out.println("1. Solo Game");
        System.out.println("2. Settings");
        System.out.println("3. Exit");
        
        int choice = Integer.parseInt(in.nextLine());
        
        switch (choice) {
            case 1:
                soloGame(in);
                break;
            
            case 2: 
                settings(in);
                break;
            
            case 3: 
                System.out.println("Have a nice day!");
                System.exit(0);
        }
    }
    public static void clearPlayerBoard() {
        for (Ship test : playerShips)
                test = null;
        for (int i = 0; i < playerBoard.length; i++)
            for (int k = 0; k < playerBoard[i].length; k++)    
                playerBoard[i][k] = null;
    }
    public static void chooseShips(Scanner in) {
        for (int i = 0; i < playerShips.length; i++) {
            System.out.print(cls); 
            clearPlayerBoard();
            System.out.println("\nSolo Game\n");
            printPlayerBoard();
            System.out.println();
            System.out.println("Choose position for " + names[i]);
            System.out.print("X = ");
            int x = Integer.parseInt(in.nextLine());
            System.out.print("Y = ");
            int y = Integer.parseInt(in.nextLine());
            System.out.println("Choose orientation for " + names[i]);
            System.out.println("1. Vertical");
            System.out.println("2. Horizontal");
            int choice = Integer.parseInt(in.nextLine());
            boolean horizontal = true;
            if (choice == 1)
                horizontal = false;
                
            int num = 0;
                
            switch (i) {
                case 0:
                    num = 5;
                    break;
                    
                case 1:
                    num = 4;
                    break;
                
                case 2:
                    num = 3;
                    break;
                
                case 3: 
                    num = 3;
                    break;
                    
                case 4:
                    num = 2;
                    break;
            }
                
            playerShips[i] = new Ship(false, horizontal, names[i],num, x, y);
        }
        try {
            preparePlayerBoard(); 
        } catch(Exception e)  {
            System.out.println("Invalid Placement");
        }
        System.out.println(cls);
        printPlayerBoard();
        System.out.println("1. Proceed");
        System.out.println("2. Restart");
        int choice = Integer.parseInt(in.nextLine());
        if (choice == 1) 
            System.out.println("Proceeding...");
        else 
            chooseShips(in);
    }
    public static void soloGame(Scanner in) {
        chooseShips(in);
        testPrint();
    }
    public static void settings(Scanner in) {
        System.out.print(cls); 
        System.out.println("\nSettings\n");
        System.out.println("1. setting");
        System.out.println("2. setting");
        in.next();
    }
}