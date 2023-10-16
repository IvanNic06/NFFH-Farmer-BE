package NHHFFarmerBE.FarmerBE.controller;

import NHHFFarmerBE.FarmerBE.entities.Area;
import NHHFFarmerBE.FarmerBE.entities.Farmer;
import NHHFFarmerBE.FarmerBE.entities.Product;
import NHHFFarmerBE.FarmerBE.models.AreaPageProductResponse;
import NHHFFarmerBE.FarmerBE.models.SellerPageProductResponse;
import NHHFFarmerBE.FarmerBE.requests.CreateAreaInput;
import NHHFFarmerBE.FarmerBE.requests.CreateFarmerInput;
import NHHFFarmerBE.FarmerBE.services.AreaService;
import NHHFFarmerBE.FarmerBE.services.FarmerService;

import java.util.List;
import java.util.Optional;
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
    public ResponseEntity<Farmer> createFarmer(@RequestBody CreateFarmerInput createFarmerInput) {
        Farmer createdFarmer = farmerService.create(createFarmerInput.toFarmer());

        return new ResponseEntity<>(createdFarmer, HttpStatus.CREATED);
    }

    // GET ALL FARMERS

    @GetMapping("farmer")
    public ResponseEntity<List<Farmer>> allTasks() {
        List<Farmer> FarmerList = farmerService.findAll();
        return new ResponseEntity<>(FarmerList, HttpStatus.OK);
    }


    //  GET FARMER BY ID 

    @GetMapping("farmer/{id}")
    public ResponseEntity<Farmer> getFarmerByID(@PathVariable int id) {
        Optional<Farmer> farmer = farmerService.findById(id);

        if (farmer.isPresent()) {
            return new ResponseEntity<>(farmer.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
        int pageSize = 1;
        List<Farmer> farmerList = farmerService.findByArea(area);
        List<Farmer> SubList = farmerList.subList((page-1) * pageSize, page * pageSize);
        int totalPageNumber = (int) Math.ceil((double) farmerList.size()/pageSize);

        AreaPageProductResponse response = new AreaPageProductResponse(farmerList, pageSize, totalPageNumber);

        return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

