package NHHFFarmerBE.FarmerBE.controller;


import NHHFFarmerBE.FarmerBE.entities.Area;
import NHHFFarmerBE.FarmerBE.entities.Farmer;
import NHHFFarmerBE.FarmerBE.entities.Product;
import NHHFFarmerBE.FarmerBE.models.AreaPageProductResponse;
import NHHFFarmerBE.FarmerBE.models.CreateFarmerResponse;
import NHHFFarmerBE.FarmerBE.models.LoginResponse;
import NHHFFarmerBE.FarmerBE.models.SellerPageProductResponse;
import NHHFFarmerBE.FarmerBE.models.SignupResponse;
import NHHFFarmerBE.FarmerBE.requests.CreateAreaInput;
import NHHFFarmerBE.FarmerBE.requests.CreateFarmerInput;
import NHHFFarmerBE.FarmerBE.requests.LoginInput;
import NHHFFarmerBE.FarmerBE.services.AreaService;
import NHHFFarmerBE.FarmerBE.services.FarmerService;

import java.util.List;
import java.util.Optional;

import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FarmerController {
    
    @Autowired
    public final FarmerService farmerService;

    public FarmerController(FarmerService farmerService){
        this.farmerService = farmerService;
    }

    // ADD A FARMER

    @PostMapping("/farmer")
    public ResponseEntity<SignupResponse> createFarmer(@RequestBody CreateFarmerInput createFarmerInput) {
        Farmer createdFarmer = farmerService.create(createFarmerInput.toFarmer());

        return new ResponseEntity<>(new SignupResponse(createdFarmer), HttpStatus.CREATED);
    }

    // GET ALL FARMERS

    @GetMapping("farmer")
    public ResponseEntity<List<Farmer>> allTasks() {
        List<Farmer> FarmerList = farmerService.findAll();
        return new ResponseEntity<>(FarmerList, HttpStatus.OK);
    }


    //  GET FARMER BY ID 

    @GetMapping("farmerFull/{id}")
    public ResponseEntity<Farmer> getFarmerByIDFULL(@PathVariable int id) {
        Optional<Farmer> farmer = farmerService.findById(id);

        if (farmer.isPresent()) {
            return new ResponseEntity<>(farmer.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("farmerLight/{id}")
    public ResponseEntity<CreateFarmerResponse> getFarmerByIDLIGHT(@PathVariable int id) {
        Optional<Farmer> Optionalfarmer = farmerService.findById(id);
        Farmer farmer;


        if (Optionalfarmer.isPresent()) {
            farmer = Optionalfarmer.get();
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        CreateFarmerResponse response = new CreateFarmerResponse(String.valueOf(farmer.getId()), farmer.getUsername());

        return new ResponseEntity<CreateFarmerResponse>(response, HttpStatus.OK);

    }

    // DELETE A FARMER BY ID

    @DeleteMapping("/farmer/{id}")
    public ResponseEntity<Void> deleteFarmer(@PathVariable int id) {
        farmerService.delete(id);

        return ResponseEntity.noContent().build();
    }


    // FIND FARMER BY AREA
    @GetMapping("farmer/areas")
    public ResponseEntity<AreaPageProductResponse> getFarmerByAreaPage(@RequestParam String area, int page) {
        int pageSize = 2;
        List<Farmer> farmerList = farmerService.findByArea(area);

        if (farmerList.isEmpty()){
            return new ResponseEntity<AreaPageProductResponse>(null, null, HttpStatus.NOT_FOUND);
        }

        List<Farmer> SubList;
        int totalPageNumber = (int) Math.ceil((double) farmerList.size()/pageSize);

        if (page > totalPageNumber){
            return new ResponseEntity<AreaPageProductResponse>(null, null, HttpStatus.NOT_FOUND);

        }
        
        if (page == totalPageNumber && totalPageNumber % pageSize != 0){
            SubList = farmerList.subList((page-1) * pageSize, page * pageSize - (page * pageSize - (farmerList.size() % pageSize)));
        }
        
        else{
            SubList = farmerList.subList((page-1) * pageSize, page * pageSize);
        }


        

        AreaPageProductResponse response = new AreaPageProductResponse(SubList, page, totalPageNumber);

        return new ResponseEntity<>(response, HttpStatus.OK);
        }


    @PostMapping("/farmer/signup")
    public ResponseEntity<Boolean> signUp(@RequestBody CreateFarmerInput createFarmerInput){
        
        Farmer createdFarmer = createFarmerInput.toFarmer();
        
        farmerService.create(createdFarmer);

        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    /*   SignUp
      
	    "username" : "Luciano_Moggi",
        "email" : "arbitroChiuso@nelloSpogliatoio.com",
        "password" : "Juventus <3",
        "image" : "cazzo",
        "area" : "torino",
        "address" : "via ladri dio cane"
	}
     */



    @PostMapping("/farmer/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginInput email){
        
        String mail = email.ToStringEmail();

        Farmer farmer = farmerService.findByEmail(mail);

        LoginResponse response = new LoginResponse(farmer.getPassword());
        
        return new ResponseEntity<LoginResponse>(response, HttpStatus.OK);

        }

    /*  Login

        {
            "email" : "{mail}"
        }
    */
}

