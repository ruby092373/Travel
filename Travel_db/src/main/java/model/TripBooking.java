package model;

import java.io.Serializable;
import java.util.Date;

public class TripBooking implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int id;
    private int customerId;
    private int productId;
    private Date orderDate;
    private int numberOfTravelers;

    public TripBooking() {}

    public TripBooking(int customerId, int productId, Date orderDate, int numberOfTravelers) {
        this.customerId = customerId;
        this.productId = productId;
        this.orderDate = orderDate;
        this.numberOfTravelers = numberOfTravelers;
    }

    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }
    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }
    public Date getOrderDate() { return orderDate; }
    public void setOrderDate(Date orderDate) { this.orderDate = orderDate; }
    public int getNumberOfTravelers() { return numberOfTravelers; }
    public void setNumberOfTravelers(int numberOfTravelers) { this.numberOfTravelers = numberOfTravelers; }
}
