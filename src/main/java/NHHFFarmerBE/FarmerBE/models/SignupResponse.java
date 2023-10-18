package NHHFFarmerBE.FarmerBE.models;

import NHHFFarmerBE.FarmerBE.entities.Farmer;

public class SignupResponse {
    private boolean success;

    public SignupResponse(Farmer farmer) {
        success = (farmer != null);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }


}
