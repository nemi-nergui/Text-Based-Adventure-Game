import java.util.Scanner;
import java.util.ArrayList;
/*
@Author: Nemkhe Nergui
@Date: Apr 23, 2020
*/

public class Main {
  
  private static Location currentLocation;
  private static ContainerItem myInventory;
  
  public static void main(String[] args) {
    
   
   createWorld();
   
   Scanner sc = new Scanner(System.in);
   
   boolean quit= false;
   
   while(!quit)
   {
     System.out.print("Enter command: ");
     String str = sc.nextLine();
     String [] a = str.split(" ");
     
     switch(a[0].toLowerCase()) {
       
       case "go":
         if(a.length<2)
         {
           System.out.println("You should put 1 direction name");
         }
         
         else if(a.length==2 && currentLocation.canMove(a[1]))
         {
           currentLocation=currentLocation.getLocation(a[1]);
           System.out.println("You are now at "+ currentLocation.getLoc());
         }
         else
         {
           System.out.println("There is no door, direction does not exist");
         }
         break;
       case "look":
         System.out.println(currentLocation.getLoc()+ "- " + currentLocation.getLocationDescription() + " items: ");
                for(int i=0; i<currentLocation.numItem(); i++)
                {
                  System.out.println("-" + currentLocation.getItem(i).getName());
                }
          break;
        case "examine":
          if(a.length>2 && a.length<2){
           System.out.println("Must put only one item");
          }
          else if(currentLocation.hasItem(a[1]) && a.length==2)
          {
            System.out.println(currentLocation.getItem(a[1]).getName()+ "-" + currentLocation.getItem(a[1]).getDescription());
          }
          else{
            System.out.println("Cannot find that item");
          }
          break;
        case "inventory":
          if(myInventory.getNumItem()==0)
          {
            System.out.println("The handbag is empty");
          }
          else
          {
            System.out.println(myInventory);
          }
          break;
        case "take":
          if(a.length<2)
          {
            System.out.println("You must put item name");
          }
          else if(a.length==2)
          {
            if(currentLocation.hasItem(a[1]))
            {
              myInventory.addItem(currentLocation.removeItem(a[1]));
//Item x = currentLocation.removeItem(a[1]);
             //myInventory.addItem(x);
            }
            else
            {
              System.out.println("Item not found");
            }
          }
          else if(a.length==4)
          {
            String b = a[2].toLowerCase();
            if(!b.equals("from"))
            {
              System.out.println("You should use from to take item from container");
            }
            else if(currentLocation.hasItem(a[3])==false)
            {
              System.out.println("Container does not exist");
            }
            else 
            {
              ContainerItem c = (ContainerItem)currentLocation.getItem(a[3]);
              if(c.hasItem(a[1])==false)
              {
                System.out.println("Item does not exist in container");
              }
              else
              {
               myInventory.addItem(c.removeItem(a[1]));
              }
            }
          }
          else
          {
            System.out.println("You can only take 1 item name from 1 container ");
          }
          break;
        case "drop": 
          if(a.length>2 && a.length<2)
          {
            System.out.println("Must put only 1 item");
          }
          else if(myInventory.hasItem(a[1]) && a.length==2)
          {
            currentLocation.addItem(myInventory.removeItem(a[1]));
          }
          else
          {
            System.out.println("Item does not exist in handbag");
          }
          break;
        case "put":
          if(a.length!=4)
          {
            System.out.println("Put only 1 item and 1 container");
          }
          else if(a.length==4)
          {
            String d = a[2].toLowerCase();
            if(!d.equals("in"))
            {
              System.out.println("Only put -> in some container ");
            }
            else if(currentLocation.hasItem(a[3]))
            {
              if(!myInventory.hasItem(a[1]))
              {
                System.out.println("Item does not exist in handbag");
              }
              else{
                ContainerItem c1 = (ContainerItem)currentLocation.getItem(a[3]);
                c1.addItem(myInventory.removeItem(a[1]));
              }
            }
            else{
              System.out.println("Container does not exist");
            }
          }
          else{
            System.out.println("Can’t process command, try again");
          }
          break;
        case "help":
          System.out.println("List of commands: ");
          System.out.println("look: displays current location’s name and description along with items at the location \n" + "go ___ : moves current location to the location of given ___ \n "+ "inventory: displays items that are stored in the inventory \n"+ "take ___ : removes matching item from current location and adds to inventory \n"+ "take __ from __ : removes matching item from container at the current location and add it to inventory \n"+ "examine ___ : examines given item at the location \n"+ "put ___ in ___ : removes matching item from inventory and adds it to the matching container \n"+ "drop ___ : removes matching item from inventory and adds it to current location items \n"+ "quit: quits the program \n"+ "help: displays the list of commands");
          break;
        case "quit":
          quit=true;
          break;
        default:
          System.out.println("I don’t know how to do that");
     }
   }
   
  }
  
  public static void createWorld()
  {
    Location kitchen = new Location("Kitchen", "The castle’s vintage kitchen");
    Location bedroom= new Location("Bedroom", "The place where King sleeps");
    Location bathroom= new Location("Bathroom", "The place where King takes care of private business");
    Location hallway= new Location("Hallway", "Endless hallway through the castle");
    
    //hallway points to bedroom
    hallway.connect("east", bedroom);
    bedroom.connect("west",hallway);
    
    // hallway points to bathroom
    hallway.connect("north",bathroom);
    bathroom.connect("south",hallway);
    
    // hallway points to kitchen
    hallway.connect("west",kitchen);
    kitchen.connect("east",hallway);
    
    // bedroom points to bathroom
    bedroom.connect("east",bathroom);
    bathroom.connect("west",bedroom);
    
    kitchen.connect("south",bathroom);
    bathroom.connect("north",kitchen);
    
    
    Item knife = new Item("Knife","Utensil","Silver,sharp well polished");
    Item bread = new Item("Bread","Food","Irish soda bread");
    Item butter = new Item("Butter", "Food","Unsalted, organic");
    kitchen.addItem(knife);
    kitchen.addItem(bread);
    kitchen.addItem(butter);
    
    Item lamp = new Item("Lamp","Decor","Vintage style");
    Item bed = new Item("Bed","Furniture","King size");
    Item pillow= new Item("Pillow","Decor","Soft and big");
    bedroom.addItem(lamp);
    bedroom.addItem(bed);
    bedroom.addItem(pillow);
    
    Item soap= new Item("Soap","Sanitary stuff","Handmade");
    Item shampoo= new Item("Shampoo","Hair product","Vanilla scent");
    Item towel = new Item("Towel","Cloth","Body towel");
    bathroom.addItem(soap);
    bathroom.addItem(shampoo);
    bathroom.addItem(towel);
    
    Item candle = new Item("Candle","Decor","Citrus scent");
    Item rug= new Item("Rug","Decor","Leopard pattern");
    Item sword = new Item("Sword","Weapon","Samurain sword");
    hallway.addItem(candle);
    hallway.addItem(rug);
    hallway.addItem(sword);
    
    //test items
    Item pearl = new Item("Pearl", "Jewelry","Pink pearl");
    Item rose = new Item ("Rose","Flower","Red, has thorns");
    Item lipstick = new Item("Lipstick","Makeup", "Red color");
    
    // starting point 
    currentLocation=hallway;
    
    myInventory = new ContainerItem("Tote", "Bag", "Eco-friendly, handmade tote bag");
    
    ContainerItem chest = new ContainerItem("Chest", "furnitur","Oak wooden vintage chest");
    chest.addItem(pearl);
    bedroom.addItem(chest);
    
    ContainerItem basket = new ContainerItem("Basket","container","Handmade, almond color");
    basket.addItem(rose);
    kitchen.addItem(basket);
    
    ContainerItem purse = new ContainerItem("Purse","container", "100% leather");
    purse.addItem(lipstick);
    bathroom.addItem(purse);
    
    
  }
}