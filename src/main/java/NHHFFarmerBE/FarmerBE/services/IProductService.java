package NHHFFarmerBE.FarmerBE.services;

import java.util.List;
import java.util.Optional;

import NHHFFarmerBE.FarmerBE.entities.Farmer;
import NHHFFarmerBE.FarmerBE.entities.Product;

public interface IProductService {

    Product create(Product area);
    
    List<Product> findAll();
    
    void delete(int id);
    
    List<Product> getProductBySeller(String seller);

    Optional<Product> findById(int id);
}
