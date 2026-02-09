package service;

import model.Customer;
import model.Employee;

public interface LoginService {
	// 顧客註冊
    boolean isCustomerUsernameTaken(String username);
    Customer registerCustomer(Customer customer);

    // 登入驗證
    Customer customerLogin(String username, String password);
    Employee employeeLogin(String username, String password);
}
