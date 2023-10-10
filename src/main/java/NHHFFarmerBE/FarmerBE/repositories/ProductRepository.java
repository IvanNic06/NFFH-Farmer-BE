package NHHFFarmerBE.FarmerBE.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import NHHFFarmerBE.FarmerBE.entities.Product;


@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

    
}
