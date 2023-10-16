package NHHFFarmerBE.FarmerBE.requests;

import java.util.Date;

import NHHFFarmerBE.FarmerBE.entities.Area;
import NHHFFarmerBE.FarmerBE.entities.Farmer;


public record LoginInput(String email) {
    public String ToStringEmail(){
        return email;
    }
}