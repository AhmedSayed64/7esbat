package model;


public class CustomerDetails {
    private int status;
    private double cost;
    private double credit;
    private String details;
    private String date;


    public CustomerDetails(int status, double cost, double credit, String details, String date) {
        this.status = status;
        this.cost = cost;
        this.credit = credit;
        this.details = details;
        this.date = date;
    }

    public CustomerDetails() {

    }

    public int getStatus() {

        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
