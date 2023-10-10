package NHHFFarmerBE.FarmerBE.services;

import java.util.List;
import NHHFFarmerBE.FarmerBE.entities.Product;

public interface IProductService {

    Product create(Product area);
    
    List<Product> findAll();
    
    void delete(int id);
}
