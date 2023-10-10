package NHHFFarmerBE.FarmerBE.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import NHHFFarmerBE.FarmerBE.entities.Product;
import NHHFFarmerBE.FarmerBE.repositories.ProductRepository;


@Service
public class ProductService implements IProductService{
        
    @Autowired
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public Product create(Product farmer){
        return this.productRepository.save(farmer);
    }

    @Override
    public void delete(int id) {
        productRepository.deleteById(id);
        return ;
    }

    @Override
    public List<Product> findAll() {
        List<Product> areaList = new ArrayList<>();
        this.productRepository.findAll().forEach(areaList::add);
        return areaList;
    }
}
