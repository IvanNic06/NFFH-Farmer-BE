package NHHFFarmerBE.FarmerBE.models;

public class LoginResponse {
    
    private String password;
    private boolean success;


    public LoginResponse(String password){
        this.password = password;
        this.success = true;
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
