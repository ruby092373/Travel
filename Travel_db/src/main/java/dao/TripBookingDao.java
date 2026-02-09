package dao;

import java.util.List;

import model.TripBooking;
import vo.ViewBooking;

public interface TripBookingDao {
	 //create
	TripBooking add(TripBooking booking);
    
    //read
    List<ViewBooking> selectAllForView();
    
    //update
    void update(TripBooking booking);
    
    //delete
    void deleteById(int id);
}
