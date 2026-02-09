package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dao.TripProductDao;
import model.TripProduct;
import util.Tool;

public class TripProductDaoImpl implements TripProductDao {
    @Override
    public void add(TripProduct product) {
        Connection conn = Tool.getDb();
        String sql = "INSERT INTO trip_product(name, destination, price, description) VALUES(?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, product.getName());
            ps.setString(2, product.getDestination());
            ps.setInt(3, product.getPrice());
            ps.setString(4, product.getDescription());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<TripProduct> selectAll() {
        Connection conn = Tool.getDb();
        String sql = "SELECT * FROM trip_product";
        List<TripProduct> products = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                TripProduct p = new TripProduct();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setDestination(rs.getString("destination"));
                p.setPrice(rs.getInt("price"));
                p.setDescription(rs.getString("description"));
                products.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public void update(TripProduct product) {
        Connection conn = Tool.getDb();
        String sql = "UPDATE trip_product SET name=?, destination=?, price=?, description=? WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, product.getName());
            ps.setString(2, product.getDestination());
            ps.setInt(3, product.getPrice());
            ps.setString(4, product.getDescription());
            ps.setInt(5, product.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(int id) {
        Connection conn = Tool.getDb();
        String sql = "DELETE FROM trip_product WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
