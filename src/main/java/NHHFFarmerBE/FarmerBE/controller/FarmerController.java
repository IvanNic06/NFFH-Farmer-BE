package NHHFFarmerBE.FarmerBE.controller;

import NHHFFarmerBE.FarmerBE.entities.Area;
import NHHFFarmerBE.FarmerBE.entities.Farmer;
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

    //Add a farmer

    @PostMapping("/farmer")
    public ResponseEntity<Farmer> createFarmer(@RequestBody CreateFarmerInput createFarmerInput) {
        Farmer createdFarmer = farmerService.create(createFarmerInput.toFarmer());

        return new ResponseEntity<>(createdFarmer, HttpStatus.CREATED);
    }

    @GetMapping("farmer")
    public ResponseEntity<List<Farmer>> allTasks() {
        List<Farmer> FarmerList = farmerService.findAll();
        return new ResponseEntity<>(FarmerList, HttpStatus.OK);
    }

    @DeleteMapping("/farmer/{id}")
    public ResponseEntity<Void> deleteFarmer(@PathVariable int id) {
        farmerService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
