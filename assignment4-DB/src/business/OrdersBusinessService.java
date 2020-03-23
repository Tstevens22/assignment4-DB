package business;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import beans.Order;

@Alternative
@Stateless
@Local(OrdersBusinessInterface.class)
public class OrdersBusinessService implements OrdersBusinessInterface {

	List<Order> orders = new ArrayList<Order>();

	@Override
	public void test() {
		System.out.println("===== Hello from the Business Service =====");
	}

	public OrdersBusinessService() {

		orders.add(new Order("000", "This is product 1", (float) 1.00, 1));
		orders.add(new Order("001", "This is product 2", (float) 2.00, 2));
		orders.add(new Order("002", "This is product 3", (float) 3.00, 3));
		orders.add(new Order("003", "This is product 4", (float) 4.00, 4));
		orders.add(new Order("004", "This is product 5", (float) 5.00, 5));
		orders.add(new Order("005", "This is product 6", (float) 6.00, 6));

	}

	@Override
	public List<Order> getOrders() {
		// TODO Auto-generated method stub
		return orders;
	}

	@Override
	public void setOrders(List<Order> orders) {
		// TODO Auto-generated method stub
		this.orders = orders;

	}

}
