package service.impl;

import java.util.List;

import dao.CustomerDao;
import dao.impl.CustomerDaoImpl;
import model.Customer;
import service.CustomerService;

public class CustomerServiceImpl implements CustomerService {
	
	private CustomerDao customerDao = new CustomerDaoImpl();

	@Override
	public List<Customer> getAllCustomers() {
		
		return customerDao.selectAll();
	}
}
