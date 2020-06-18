package application;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import application.Item;
import application.ItemDAO;

public class Controller extends HttpServlet
{
  private static final long serialVersionUID = 1L;
  private ItemDAO dao;
  
  public void init()
  {
	  final String url = getServletContext().getInitParameter("JDBC-URL");
	  final String username = getServletContext().getInitParameter("JDBC-USERNAME");
	  final String password = getServletContext().getInitParameter("JDBC-PASSWORD");
	  
    dao = new ItemDAO(url, username, password);
  }
  
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException
  {
    doGet(request, response);
  }
  
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException
  {
    final String action = request.getServletPath();
    
    try {
      switch (action) {
        case "/update":
          updateItem(request, response);
          break;
        default:
          viewItems(request, response);
          break;
      }
    } catch (SQLException e) {
      throw new ServletException(e);
    }
  }
  
  
  private void updateItem(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, ServletException, IOException
		{	
		  final String action = request.getParameter("action");
		  final int id = Integer.parseInt(request.getParameter("id"));
		  
		  Item item = dao.getItem(id);
		  switch (action) {
		    case "rent":
		      item.rentMe();
		      break;
		    case "return":
		      item.returnMe();
		      break;
		  }
		  dao.updateItem(item);
		  
		  response.sendRedirect(request.getContextPath() + "/");
		}

private void viewItems(HttpServletRequest request, HttpServletResponse response)
      throws SQLException, ServletException, IOException
  {
    List<Item> items = dao.getItems();
    request.setAttribute("items", items);
    
    RequestDispatcher dispatcher = request.getRequestDispatcher("inventory.jsp");
    dispatcher.forward(request, response);
  }
}