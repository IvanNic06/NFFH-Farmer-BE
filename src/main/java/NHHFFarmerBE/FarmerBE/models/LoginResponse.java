package NHHFFarmerBE.FarmerBE.models;

import NHHFFarmerBE.FarmerBE.entities.Farmer;

public class LoginResponse {
    
    private String password;
    private boolean success;
    private String id;
    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    private String username;


    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public LoginResponse(Farmer farmer){
        if (farmer != null) {
            success = true;
            id = String.valueOf(farmer.getId());
            password = farmer.getPassword();
            username = farmer.getUsername();
        } else {
            success = false;
            password = "";
            username = "";
            id = "";
        }
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public boolean isSuccess() {
        return success;
    }


    public void setSuccess(boolean success) {
        this.success = success;
    }

    

}
