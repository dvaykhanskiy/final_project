package application;

public class Item
{  
  private int id;
  private String name;
  private String price;
  private int stock;
  private int available;
    
  public Item(int id, String name, String price, int stock, int available)
  {
    super();
    
    this.id = id;
    this.name = name;
    this.price = price;
    this.stock = stock;
    this.available = available;
  }
  
  public int getId()
  {
    return id;
  }
  
  public String getName()
  {
    return name;
  }
  
  public String getPrice()
  {
    return price;
  }
  
  public int getStock()
  {
    return stock;
  }
  
  public void setCopies(int stock)
  {
    this.stock = stock;
  }
  
  public int getAvailable()
  {
    return available;
  }
  
  public void rentMe()
  {
    if (available > 0) {
      available--;
    }
  }
  
  public void returnMe()
  {
    if (available < stock) {
      available++;
    }
  }
}