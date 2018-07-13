package model;

import java.util.List;


public class CustomersData {
    private int id;
    private List<Customer> customerList;

    public CustomersData() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public int getId() {

        return id;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public CustomersData(int id, List<Customer> customerList) {

        this.id = id;
        this.customerList = customerList;
    }
}
