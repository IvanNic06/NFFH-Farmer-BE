package NHHFFarmerBE.FarmerBE.controller;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import NHHFFarmerBE.FarmerBE.entities.Farmer;
import NHHFFarmerBE.FarmerBE.entities.Product;
import NHHFFarmerBE.FarmerBE.models.AreaPageProductResponse;
import NHHFFarmerBE.FarmerBE.models.CreateProductResponse;
import NHHFFarmerBE.FarmerBE.models.ModifyProductResponse;
import NHHFFarmerBE.FarmerBE.models.SellerPageProductResponse;
import NHHFFarmerBE.FarmerBE.models.verifytoken.VerifyHandler;
import NHHFFarmerBE.FarmerBE.models.verifytoken.VerifyTokenRequest;
import NHHFFarmerBE.FarmerBE.models.verifytoken.VerifyTokenResponse;
import NHHFFarmerBE.FarmerBE.requests.CreateProductInput;
import NHHFFarmerBE.FarmerBE.services.FarmerService;
import NHHFFarmerBE.FarmerBE.services.ProductService;
import NHHFFarmerBE.FarmerBE.repositories.FarmerRepository;
import NHHFFarmerBE.FarmerBE.repositories.ProductRepository;

@RestController
public class ProductController {
    
    @Autowired
    public final ProductService productService;
    public final ProductRepository productrepo;
    public final FarmerService farmerService;
    public final FarmerRepository farmerRepository;


    public ProductController(
        ProductService productService, 
        ProductRepository productrepo,
        FarmerService farmerService,
        FarmerRepository farmerRepository
    ){
        this.productService = productService;
        this.productrepo = productrepo;
        this.farmerService = farmerService;
        this.farmerRepository = farmerRepository;
    }


    //Add a product

    @PostMapping("/products")
    public ResponseEntity<CreateProductResponse> createTask(
        @RequestHeader("token") String token,
        @RequestBody CreateProductInput createProductInput
        ) {

        VerifyHandler handler = new VerifyHandler(this.farmerService);
        Farmer farmer = handler.verify(token);
        
        /*CreateProductResponse res = new CreateProductResponse(farmer.getUsername(), farmer.getUsername());
        return new ResponseEntity<CreateProductResponse>(res, HttpStatus.OK);*/
        
        if(createProductInput.toProduct().getSeller().equals(farmer.getUsername())) {
            Product createdProduct = productService.create(createProductInput.toProduct());
            CreateProductResponse response = new CreateProductResponse(String.valueOf(createdProduct.getId()), createdProduct.getTitle());
            return new ResponseEntity<CreateProductResponse>(response, HttpStatus.CREATED);
        } else {
            CreateProductResponse res = new CreateProductResponse("", "");
            res.setSuccess(false);
            return new ResponseEntity<CreateProductResponse>(res, HttpStatus.OK);
        }

    }



    //Return all products

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


    //Delete a product using ID

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable int id) {
        productService.delete(id);

        return ResponseEntity.noContent().build();
    }


    @GetMapping("/products")
    public ResponseEntity<SellerPageProductResponse> getProductBySellerPage(@RequestParam String seller, int page){
        
        int pageSize = 1;
        List<Product> productList = productService.getProductBySeller(seller);

        if (productList.isEmpty()){
            return new ResponseEntity<SellerPageProductResponse>(null, null, HttpStatus.NOT_FOUND);
        }

        List<Product> SubList;
        int totalPageNumber = (int) Math.ceil((double) productList.size()/pageSize);

        if (page > totalPageNumber){
            return new ResponseEntity<SellerPageProductResponse>(null, null, HttpStatus.NOT_FOUND);

        }
        
        if (page == totalPageNumber && totalPageNumber % pageSize != 0){
            SubList = productList.subList((page-1) * pageSize, page * pageSize - (page * pageSize - (productList.size() % pageSize)));
        }

        else{
            SubList = productList.subList((page-1) * pageSize, page * pageSize);
        }

        

        SellerPageProductResponse response = new SellerPageProductResponse(SubList, page, totalPageNumber);

        return new ResponseEntity<SellerPageProductResponse>(response, HttpStatus.OK);
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
