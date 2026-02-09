package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import dao.TripBookingDao;
import model.TripBooking;
import util.Tool;
import vo.ViewBooking;

public class TripBookingDaoImpl implements TripBookingDao {

    @Override
    public TripBooking add(TripBooking booking) {
        Connection conn = Tool.getDb();
        String sql = "INSERT INTO trip_booking(customerId, productId, orderDate, numberOfTravelers) VALUES(?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, booking.getCustomerId());
            ps.setInt(2, booking.getProductId());
            ps.setDate(3, new java.sql.Date(booking.getOrderDate().getTime()));
            ps.setInt(4, booking.getNumberOfTravelers());
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                booking.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return booking;
    }

    @Override
    public List<ViewBooking> selectAllForView() {
        Connection conn = Tool.getDb();
        String sql = "SELECT b.id, c.name as customerName, p.name as productName, b.orderDate, b.numberOfTravelers, p.price " +
                     "FROM trip_booking b " +
                     "JOIN customer c ON b.customerId = c.id " +
                     "JOIN trip_product p ON b.productId = p.id " +
                     "ORDER BY b.id";
        List<ViewBooking> viewBookings = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ViewBooking vb = new ViewBooking();
                vb.setBookingId(rs.getInt("id"));
                vb.setCustomerName(rs.getString("customerName"));
                vb.setProductName(rs.getString("productName"));
                vb.setOrderDate(sdf.format(rs.getDate("orderDate")));
                int travelers = rs.getInt("numberOfTravelers");
                int price = rs.getInt("price");
                vb.setNumberOfTravelers(travelers);
                vb.setTotalPrice(travelers * price);
                viewBookings.add(vb);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return viewBookings;
    }

    @Override
    public void update(TripBooking booking) {
        Connection conn = Tool.getDb();
        String sql = "UPDATE trip_booking SET customerId=?, productId=?, orderDate=?, numberOfTravelers=? WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, booking.getCustomerId());
            ps.setInt(2, booking.getProductId());
            ps.setDate(3, new java.sql.Date(booking.getOrderDate().getTime()));
            ps.setInt(4, booking.getNumberOfTravelers());
            ps.setInt(5, booking.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(int id) {
        Connection conn = Tool.getDb();
        String sql = "DELETE FROM trip_booking WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
