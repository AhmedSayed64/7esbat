package model;


public class Customer {
    private String Customer_name;
    private int status;
    private double cost;
    private double credit;
    private String details;
    private String date;


    public Customer(){

    }

    public Customer(String customer_name, int status, double cost, double credit, String details, String date) {

        Customer_name = customer_name;
        this.status = status;
        this.cost = cost;
        this.credit = credit;
        this.details = details;
        this.date = date;
    }

    public String getCustomer_name() {

        return Customer_name;
    }

    public int getStatus() {
        return status;
    }

    public double getCost() {
        return cost;
    }

    public double getCredit() {
        return credit;
    }

    public String getDetails() {
        return details;
    }

    public String getDate() {
        return date;
    }

    public void setCustomer_name(String customer_name) {
        Customer_name = customer_name;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
