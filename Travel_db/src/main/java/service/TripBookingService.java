package service;

import java.util.Date;
import java.util.List;

import model.Customer;
import model.TripBooking;
import model.TripProduct;
import vo.ViewBooking;

public interface TripBookingService {
	// create
	TripBooking addBooking(TripBooking tripBooking);
    
    // read
	List<ViewBooking> getAllBookingsForView();
    
    // update
    void updateBooking(TripBooking booking);
    
    // delete
    void deleteBookingById(int id);
}