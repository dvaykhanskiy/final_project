package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO
{
  private final String url;
  private final String username;
  private final String password;
  
  public ItemDAO(String url, String username, String password)
  {
    super();
    
    this.url = url;
    this.username = username;
    this.password = password;
  }
  
  public Item getItem(int id) throws SQLException
  {
    final String sql = "SELECT * FROM items WHERE item_code = ?";
    
    Item item = null;
    Connection conn = getConnection();
    PreparedStatement pstmt = conn.prepareStatement(sql);
    
    pstmt.setInt(1, id);
    ResultSet rs = pstmt.executeQuery();
    
    if (rs.next()) {
      String name = rs.getString("Name");
      String price = rs.getString("price");
      int stock = rs.getInt("stock");
      int available = rs.getInt("Available");
      
      item = new Item(id, name, price, stock, available);
    }
    
    rs.close();
    pstmt.close();
    conn.close();
    
    return item;
  }
  
  public List<Item> getItems() throws SQLException
  {
    final String sql = "SELECT * FROM items ORDER BY item_code ASC";
    
    List<Item> items = new ArrayList<>();
    Connection conn = getConnection();
    Statement stmt = conn.createStatement();
    ResultSet rs = stmt.executeQuery(sql);
    
    while (rs.next()) {
      int id = rs.getInt("item_code");
      String name = rs.getString("Name");
      String price = rs.getString("Price");
      int stock = rs.getInt("Stock");
      int available = rs.getInt("Available");
      
      items.add(new Item(id, name, price, stock, available));
    }
    
    rs.close();
    stmt.close();
    conn.close();
    
    return items;
  }
  
  public boolean insertItem(Item item) throws SQLException
  {
    final String sql = "INSERT INTO items (name, seller, price, available) " +
        "VALUES (?, ?, ?, ?)";
    
    Connection conn = getConnection();
    PreparedStatement pstmt = conn.prepareStatement(sql);
    
    pstmt.setString(1, item.getName());
    pstmt.setString(3, item.getPrice());
    pstmt.setInt(4, item.getAvailable());
    int affected = pstmt.executeUpdate();
    
    pstmt.close();
    conn.close();
    
    return affected == 1;
  }
  
  public boolean updateItem(Item item) throws SQLException
  {
    final String sql = "UPDATE items SET title = ?, author = ?, copies = ?, available = ? " +
        "WHERE item_code = ?";
    
    Connection conn = getConnection();
    PreparedStatement pstmt = conn.prepareStatement(sql);
    
    pstmt.setString(1, item.getName());
    pstmt.setString(3, item.getPrice());
    pstmt.setInt(4, item.getAvailable());
    pstmt.setInt(5, item.getId());
    int affected = pstmt.executeUpdate();
    
    pstmt.close();
    conn.close();
    
    return affected == 1;
  }
  
  public boolean deleteItem(Item item) throws SQLException
  {
    final String sql = "DELETE FROM items WHERE item_code = ?";
    
    Connection conn = getConnection();
    PreparedStatement pstmt = conn.prepareStatement(sql);
    
    pstmt.setInt(1, item.getId());
    int affected = pstmt.executeUpdate();
    
    pstmt.close();
    conn.close();
    
    return affected == 1;
  }
  
  private Connection getConnection() throws SQLException
  {
    final String driver = "com.mysql.cj.jdbc.Driver";
    
    try {
      Class.forName(driver);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    
    return DriverManager.getConnection(url, username, password);
  }
}