package NHHFFarmerBE.FarmerBE.models.verifytoken;

public class VerifyTokenResponse {
    
    private boolean success;
    
    private String email;
    
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public VerifyTokenResponse() {

    }
}
