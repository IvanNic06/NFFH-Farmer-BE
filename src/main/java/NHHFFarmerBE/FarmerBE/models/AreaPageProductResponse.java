package NHHFFarmerBE.FarmerBE.models;

import java.util.List;

import NHHFFarmerBE.FarmerBE.entities.Farmer;

public class AreaPageProductResponse {
    
    private List<Farmer> stores;
    private int page;
    private int total;      //Total page number
    
    public AreaPageProductResponse(List<Farmer> stores, int page, int total){
        this.stores = stores;
        this.page = page;
        this.total = total;
    }
    
    
    public List<Farmer> getstores() {
        return stores;
    }
    public void setstores(List<Farmer> stores) {
        this.stores = stores;
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
