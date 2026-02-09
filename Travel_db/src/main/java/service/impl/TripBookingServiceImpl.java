package service.impl;

import java.util.List;
import dao.TripBookingDao;
import dao.impl.TripBookingDaoImpl;
import model.TripBooking;
import service.TripBookingService;
import vo.ViewBooking;

public class TripBookingServiceImpl implements TripBookingService {

    private TripBookingDao bookingDao = new TripBookingDaoImpl();

    @Override
    public TripBooking addBooking(TripBooking tripBooking) {
        if (tripBooking == null) {
            return null;
        }
        if (tripBooking.getNumberOfTravelers() <= 0) {
            return null;
        }
        if (tripBooking.getOrderDate() == null) {
            return null;
        }
        return bookingDao.add(tripBooking);
    }

    @Override
    public List<ViewBooking> getAllBookingsForView() {
        return bookingDao.selectAllForView();
    }

    @Override
    public void updateBooking(TripBooking booking) {
        bookingDao.update(booking);
    }

    @Override
    public void deleteBookingById(int id) {
        if (id > 0) {
            bookingDao.deleteById(id);
        }
    }
}
