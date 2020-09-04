import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Location {
  private String name;
  private String description;
  private ArrayList <Item> items;
  private HashMap<String,Location> connections;
  
  public Location (String aName, String aDescription)
  {
    name=aName;
    description=aDescription;
    items = new ArrayList<Item>();
    connections=new HashMap<String,Location>();
  }
  
  public String getLoc() { return name; }
  
  public String getLocationDescription() { return description; }
  
  public void setLocation( String newName) { name=newName;}
  
  public  void setDescription( String newDescrip) { description=newDescrip; }
  
  public void addItem(Item newItem) 
  {
    items.add(newItem);
  }
  
  public boolean hasItem( String itemName)
  {
    boolean hasItem=false;
    String str = itemName.toLowerCase();
    for( int i =0; i<items.size(); i++)
    {
      String str1 =items.get(i).getName().toLowerCase();
      if(str.equals(str1)) 
      {
        hasItem=true;
      }
    }
    return hasItem;
  }
  public Item getItem( String itemName)
  {
    Item temp = null;
    String str = itemName.toLowerCase();
    
    if(hasItem(str))
    {
      for(int i=0; i< items.size();i++)
      {
        String str1 = items.get(i).getName().toLowerCase();
        if(str.equals(str1))
        {
          temp=items.get(i);
        }
      }
    }
    return temp;
  }
  
  public Item getItem(int index)
  {
    Item temp = null;
    if(index>=0 && index<items.size())
    {
      temp=items.get(index);
    }
    return temp;
  }

  public int numItem() { return items.size(); }
  
  public Item removeItem(String itemName)
  {
      Item item = getItem(itemName);
      return items.remove(items.indexOf(item));
     
  }
  
  public void connect(String direction, Location plocation)
  {
    String str = direction.toLowerCase();
    
    //connections= new HashMap<>();

    
    connections.put(str,plocation);
    
  }
  
  public boolean canMove(String direction)
  {
    String str = direction.toLowerCase();
    boolean canMove = false;
    
    Location value = connections.get(str);
    
    if(value != null)
    {
      canMove=true;
    }
    return canMove;
  }
  public Location getLocation(String direction)
  {
    String str = direction.toLowerCase();
    Location temp = null;

    
     if(canMove(str) && connections.containsKey(str))
    {
        temp=connections.get(direction);
      }
     return temp;
  }
  
  
}