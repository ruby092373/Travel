package dao;

import model.Employee;

public interface EmployeeDao {

    //read
    Employee selectByUsername(String username);

}