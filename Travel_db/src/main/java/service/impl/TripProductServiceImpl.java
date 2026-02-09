package service.impl;

import java.util.List;
import dao.TripProductDao;
import dao.impl.TripProductDaoImpl;
import model.TripProduct;
import service.TripProductService;

public class TripProductServiceImpl implements TripProductService {

    private TripProductDao productDao = new TripProductDaoImpl();

    @Override
    public void addProduct(TripProduct product) {
        productDao.add(product);
    }

    @Override
    public List<TripProduct> getAllProducts() {
        return productDao.selectAll();
    }

    @Override
    public void updateProduct(TripProduct product) {
        productDao.update(product);
    }

    @Override
    public void deleteProductById(int id) {
        productDao.deleteById(id);
    }
}
