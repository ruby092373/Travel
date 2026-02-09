package dao;

import java.util.List;

import model.TripProduct;

public interface TripProductDao {

    //create
    void add(TripProduct product);
    
    //read
    List<TripProduct> selectAll();
    
    //update
    void update(TripProduct product);
    
    //delete
    void deleteById(int id);

}
