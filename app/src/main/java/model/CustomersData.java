package model;

import java.util.List;


public class CustomersData {
    public int getId() {
        return id;
    }

    private int id;
    private String name;
    private List<CustomerDetails> customerDetailsList;

    public CustomersData(String name, List<CustomerDetails> customerDetailsList) {
        this.name = name;
        this.customerDetailsList = customerDetailsList;
    }

    public CustomersData() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CustomerDetails> getCustomerDetailsList() {
        return customerDetailsList;
    }

    public void setCustomerDetailsList(List<CustomerDetails> customerDetailsList) {
        this.customerDetailsList = customerDetailsList;
    }

}
