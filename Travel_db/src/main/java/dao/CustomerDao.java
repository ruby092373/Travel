package dao;

import java.util.List;

import model.Customer;

public interface CustomerDao {
	
	//create
    Customer add(Customer customer);
    
    //read
    Customer selectByUsername(String username);
	Customer selectById(int id);
	List<Customer> selectAll();

}
