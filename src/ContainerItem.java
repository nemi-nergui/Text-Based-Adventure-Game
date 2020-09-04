import java.util.ArrayList;


public class ContainerItem extends Item {
  
  private ArrayList <Item> items;
  
  public ContainerItem(String name, String type,String description)
  {
    super(name,type,description);
    items= new ArrayList<Item>();
  }
  
  public void addItem(Item i )
  {
    items.add(i);
  }
  public boolean hasItem(String iName)
  {
    boolean hasItem = false;
    String str = iName.toLowerCase();
    for(int i =0; i<items.size(); i++)
    {
      String str1= items.get(i).getName().toLowerCase();
      if(str.equals(str1))
      {
        hasItem=true;
      }
    }
    return hasItem;
  }
  public Item removeItem(String iName) 
  {
    Item item = null;
    for(int i =0; i<items.size();i++)
    {
      if(items.get(i).getName().equals(iName))
      {
        item=items.get(i);
        items.remove(i);
      }
    }
    return item;
  }
  
  @Override
  public String toString(){
  
    String str = "";
    for(int i =0; i<items.size();i++)
    {
      str = str + "+" + " " + items.get(i).getName()+"\n";
    }
    return super.toString() + " that contains: "  + "\n" + str;
  }
  
  public int getNumItem()
  {
    return items.size();
  }
}