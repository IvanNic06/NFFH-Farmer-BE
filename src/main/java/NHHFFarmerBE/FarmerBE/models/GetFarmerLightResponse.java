package NHHFFarmerBE.FarmerBE.models;

public class GetFarmerLightResponse {

    private String username;
    private String image;
    private String area;
    private String address;


    public GetFarmerLightResponse(String username, String image, String area, String address) {
        this.username = username;
        this.image = image;
        this.area = area;
        this.address = address;
    }


    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public String getImage() {
        return image;
    }


    public void setImage(String image) {
        this.image = image;
    }


    public String getArea() {
        return area;
    }


    public void setArea(String area) {
        this.area = area;
    }


    public String getAddress() {
        return address;
    }


    public void setAddress(String address) {
        this.address = address;
    }

    

    

    
}
