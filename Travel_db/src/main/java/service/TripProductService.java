package service;

import java.util.List;

import model.TripProduct;

public interface TripProductService {
		void addProduct(TripProduct product);
	    List<TripProduct> getAllProducts();
	    void updateProduct(TripProduct product);
	    void deleteProductById(int id);
}
