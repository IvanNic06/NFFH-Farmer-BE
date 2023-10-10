package NHHFFarmerBE.FarmerBE.services;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import NHHFFarmerBE.FarmerBE.entities.Farmer;
import NHHFFarmerBE.FarmerBE.repositories.FarmerRepository;

@Service
public class FarmerService implements IFarmerService{
        
    @Autowired
    private final FarmerRepository farmerRepository;

    public FarmerService(FarmerRepository farmerRepository){
        this.farmerRepository = farmerRepository;
    }

    @Override
    public Farmer create(Farmer farmer){
        return this.farmerRepository.save(farmer);
    }

    @Override
    public void delete(int id) {
        farmerRepository.deleteById(id);
        return ;
    }

    @Override
    public List<Farmer> findAll() {
        List<Farmer> areaList = new ArrayList<>();
        this.farmerRepository.findAll().forEach(areaList::add);
        return areaList;
    }
}

