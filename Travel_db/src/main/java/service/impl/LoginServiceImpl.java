package service.impl;

import dao.CustomerDao;
import dao.EmployeeDao;
import dao.impl.CustomerDaoImpl;
import dao.impl.EmployeeDaoImpl;
import model.Customer;
import model.Employee;
import service.LoginService;

public class LoginServiceImpl implements LoginService {
	
	private CustomerDao customerDao = new CustomerDaoImpl();
    private EmployeeDao employeeDao = new EmployeeDaoImpl();

	@Override
	public boolean isCustomerUsernameTaken(String username) {
		return customerDao.selectByUsername(username) != null;
	}

	@Override
	public Customer registerCustomer(Customer customer) {
		if (isCustomerUsernameTaken(customer.getUsername())) {
            return null; // 帳號已被使用，註冊失敗
        }
        return customerDao.add(customer);
    }


	@Override
	public Customer customerLogin(String username, String password) {
		Customer c = customerDao.selectByUsername(username);
        if (c != null && c.getPassword().equals(password)) {
            return c; // 帳號密碼正確，登入成功
        }
        return null; // 登入失敗
    }

	@Override
	public Employee employeeLogin(String username, String password) {
		Employee e = employeeDao.selectByUsername(username);
        if (e != null && e.getPassword().equals(password)) {
            return e; // 帳號密碼正確，登入成功
        }
        return null; // 登入失敗
    }
}
