package NHHFFarmerBE.FarmerBE.controller;

import NHHFFarmerBE.FarmerBE.entities.Area;
import NHHFFarmerBE.FarmerBE.requests.CreateAreaInput;
import NHHFFarmerBE.FarmerBE.services.AreaService;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AreaController {
    
    @Autowired
    public final AreaService areaService;


    public AreaController(AreaService areaService){
        this.areaService = areaService;
    }


    //Add an area 

    @PostMapping("/area")
    public ResponseEntity<Area> createArea(@RequestBody CreateAreaInput createTaskInput) {
        Area createdArea = areaService.create(createTaskInput.toArea());

        return new ResponseEntity<>(createdArea, HttpStatus.CREATED);
    }



    //Return all areas

    @GetMapping("/area")
    public ResponseEntity<List<Area>> allAreas() {
        List<Area> AreaList = areaService.findAll();
        return new ResponseEntity<>(AreaList, HttpStatus.OK);
    }


    //Return areas in a particular page (Page size = 12)
    @GetMapping("/area/{page_number}")
    public ResponseEntity<List<Area>> getAreasPerPage(@PathVariable int page_number){
        int page_size = 3;
        
        List<Area> AreaList = areaService.findAll();
        
        List<Area> SubList = AreaList.subList(page_number * page_size, (page_number + 1) * page_size);

        return new ResponseEntity<>(SubList, HttpStatus.OK);
    }


    //Delete an Area using ID

    @DeleteMapping("/area/{id}")
    public ResponseEntity<Void> deleteArea(@PathVariable int id) {
        areaService.delete(id);

        return ResponseEntity.noContent().build();
    }


}
