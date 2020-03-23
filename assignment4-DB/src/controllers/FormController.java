package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import beans.User;
import business.OrdersBusinessInterface;

@ManagedBean
@SessionScoped
public class FormController {

	@Inject
	OrdersBusinessInterface services;

	public String onSubmit() throws SQLException {

		// get the user values from the input form
		FacesContext context = FacesContext.getCurrentInstance();
		User user = context.getApplication().evaluateExpressionGet(context, "#{user}", User.class);

		// prints a message to the console to tell us which business service is
		services.test();

		// gets the orders from the database
		getAllOrders();
		
		//inserts a new order into the table 
		insertOrders();
		
		//displays the table with the new data inserted
		getAllOrders();

		// puts the user object into a POST request
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", user);

		// shows the next page
		return "ResponsePage.xhtml";
	}

	public OrdersBusinessInterface getService() {
		return services;

	}

	private void getAllOrders() throws SQLException {

		// connect to the database
		String dbURL = "jdbc:mysql://localhost:3306/testDB";
		String user = "root";
		String pass = "root";

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(dbURL, user, pass);
			System.out.println("===== Success!! =====");

			// create a SQL statement
			stmt = conn.createStatement();

			// execute the statement
			rs = stmt.executeQuery("select * from testDB.Orders");

			// process the rows in the result set
			while (rs.next()) {
				System.out.println("ID = " + rs.getInt("ID") + " Order Number = " + rs.getString("ORDER_NO")
						+ " Product Name = " + rs.getString("PRODUCT_NAME") + " Price = " + "$" + rs.getFloat("PRICE")
						+ " Quantity = " + rs.getInt("QUANTITY"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("===== Failure!! =====");
			e.printStackTrace();
			// close the connection to the database
			rs.close();
			stmt.close();
			conn.close();
		}

	}

	private void insertOrders() throws SQLException {
		// connect to the database
		String dbURL = "jdbc:mysql://localhost:3306/testDB";
		String user = "root";
		String pass = "root";

		Connection conn = null;
		Statement stmt = null;
		int rowsAffected = 0;

		try {
			conn = DriverManager.getConnection(dbURL, user, pass);
			System.out.println("===== Success!! =====");

			// create a SQL statement
			stmt = conn.createStatement();

			// execute the statement
			rowsAffected = stmt.executeUpdate("insert into testDB.Orders values(null,'00025','This is a new insertion', 25.00, 100)");

			//success message
			System.out.println("Rows affected " + rowsAffected);
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("===== Failure!! =====");
			e.printStackTrace();
			// close the connection to the database
			stmt.close();
			conn.close();
		}

	}

}
