package NHHFFarmerBE.FarmerBE.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import NHHFFarmerBE.FarmerBE.entities.Farmer;


@Repository
public interface FarmerRepository extends CrudRepository<Farmer, Integer> {

}


