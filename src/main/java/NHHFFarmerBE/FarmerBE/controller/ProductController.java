package NHHFFarmerBE.FarmerBE.controller;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import NHHFFarmerBE.FarmerBE.entities.Farmer;
import NHHFFarmerBE.FarmerBE.entities.Product;
import NHHFFarmerBE.FarmerBE.models.ModifyProductResponse;
import NHHFFarmerBE.FarmerBE.models.SellerPageProductResponse;
import NHHFFarmerBE.FarmerBE.requests.CreateProductInput;
import NHHFFarmerBE.FarmerBE.services.ProductService;
import NHHFFarmerBE.FarmerBE.repositories.ProductRepository;

@RestController
public class ProductController {
    
    @Autowired
    public final ProductService productService;
    public final ProductRepository productrepo;


    public ProductController(ProductService productService, ProductRepository productrepo){
        this.productService = productService;
        this.productrepo = productrepo;
    }


    //Add an area 

    @PostMapping("/products")
    public ResponseEntity<Product> createTask(@RequestBody CreateProductInput createProductInput) {
        Product createdProduct = productService.create(createProductInput.toProduct());

        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }



    //Return all areas

    @GetMapping("/allproducts")
    public ResponseEntity<List<Product>> allTasks() {
        List<Product> ProductList = productService.findAll();
        return new ResponseEntity<>(ProductList, HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        Optional<Product> producttemp = productService.findById(id);

        if (producttemp.isPresent()){
            Product product = producttemp.get();
            return new ResponseEntity<Product>(product, HttpStatus.OK);

        }

        Product product = null;
        return new ResponseEntity<Product>(product, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    //Delete an Area using ID

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable int id) {
        productService.delete(id);

        return ResponseEntity.noContent().build();
    }


    @GetMapping("/products")
    public ResponseEntity<SellerPageProductResponse> getProductBySellerPage(@RequestParam String seller, int page){
        int pageSize = 2;
        List<Product> productList = productService.getProductBySeller(seller);
        List<Product> SubList = productList.subList((page-1) * pageSize, page * pageSize);

        int totalPageNumber = (int) Math.ceil((double) productList.size()/pageSize);

        SellerPageProductResponse response = new SellerPageProductResponse(SubList, page, totalPageNumber);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/modifyproduct")
    public ResponseEntity<ModifyProductResponse> modifyProductById(@RequestParam String id, @RequestBody CreateProductInput createProductInput){
        
        Product createdProduct = createProductInput.toProduct();

        int idString = Integer.valueOf(id);

        Optional<Product> optionalProduct = productService.findById(idString);


        if (optionalProduct.isPresent()){
            
            Product product = optionalProduct.get();
            product.setTitle(createdProduct.getTitle());
            product.setSeller(createdProduct.getSeller());
            product.setImage(createdProduct.getImage());
            product.setDescription(createdProduct.getDescription());
            product.setPrice(createdProduct.getPrice());
            product.setWeight(createdProduct.getWeight());
            product.setAvailability(createdProduct.getAvailability());

            ModifyProductResponse response = new ModifyProductResponse(id, product.getTitle());
            productService.create(product);
            return new ResponseEntity<ModifyProductResponse>(response, HttpStatus.OK);
        }

        else{

            ModifyProductResponse response = null;
            return new ResponseEntity<ModifyProductResponse>(response, HttpStatus.NOT_MODIFIED);

        }



    }



}
