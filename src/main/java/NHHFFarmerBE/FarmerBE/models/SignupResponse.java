package NHHFFarmerBE.FarmerBE.models;

import NHHFFarmerBE.FarmerBE.entities.Farmer;

public class SignupResponse {
    private boolean success;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SignupResponse(Farmer farmer) {
        success = (farmer != null);
        if(farmer != null) {
            this.id = String.valueOf(farmer.getId());
        }
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }


}
