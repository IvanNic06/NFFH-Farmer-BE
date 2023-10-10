package NHHFFarmerBE.FarmerBE.controller;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import NHHFFarmerBE.FarmerBE.entities.Product;
import NHHFFarmerBE.FarmerBE.requests.CreateProductInput;
import NHHFFarmerBE.FarmerBE.services.ProductService;

@RestController
public class ProductController {
    
    @Autowired
    public final ProductService productService;


    public ProductController(ProductService productService){
        this.productService = productService;
    }


    //Add an area 

    @PostMapping("/product")
    public ResponseEntity<Product> createTask(@RequestBody CreateProductInput createProductInput) {
        Product createdProduct = productService.create(createProductInput.toProduct());

        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }



    //Return all areas

    @GetMapping("/product")
    public ResponseEntity<List<Product>> allTasks() {
        List<Product> ProductList = productService.findAll();
        return new ResponseEntity<>(ProductList, HttpStatus.OK);
    }


    //Delete an Area using ID

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable int id) {
        productService.delete(id);

        return ResponseEntity.noContent().build();
    }


}
