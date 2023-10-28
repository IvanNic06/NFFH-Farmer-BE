package NHHFFarmerBE.FarmerBE.models;

import java.util.List;

import NHHFFarmerBE.FarmerBE.entities.Farmer;

public class AreaPageProductResponse {
    
    private List<Farmer> farmers;
    private int page;
    private int total;      //Total page number
    
    public AreaPageProductResponse(List<Farmer> farmers, int page, int total){
        this.farmers = farmers;
        this.page = page;
        this.total = total;
    }
    
    
    public List<Farmer> getFarmers() {
        return farmers;
    }
    public void setFarmers(List<Farmer> farmers) {
        this.farmers = farmers;
    }
    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }
    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }

    
}
