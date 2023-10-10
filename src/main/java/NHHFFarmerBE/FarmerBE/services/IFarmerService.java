package NHHFFarmerBE.FarmerBE.services;

import NHHFFarmerBE.FarmerBE.entities.Farmer;
import java.util.*;


public interface IFarmerService {
    Farmer create(Farmer farmer);

    List<Farmer> findAll();
    
    void delete(int id);
}
