package NHHFFarmerBE.FarmerBE.models.verifytoken;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestBodySpec;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;
import org.springframework.web.reactive.function.client.WebClient.UriSpec;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import NHHFFarmerBE.FarmerBE.entities.Farmer;
import NHHFFarmerBE.FarmerBE.models.LoginResponse;
import NHHFFarmerBE.FarmerBE.repositories.FarmerRepository;
import NHHFFarmerBE.FarmerBE.services.FarmerService;
import reactor.core.publisher.Mono;

public class VerifyHandler {

    private String authAddress = "http://auth:9701/verifyToken";
        
    public final FarmerService farmerService;

    public VerifyHandler(FarmerService farmerService) {
        this.farmerService = farmerService;
    }
    
    
    public Farmer verify(String token) {

        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<VerifyTokenRequest> req = new HttpEntity<VerifyTokenRequest>(
            new VerifyTokenRequest(token)
        );
        ResponseEntity<VerifyTokenResponse> res = restTemplate.postForEntity(authAddress, req, VerifyTokenResponse.class);


        /*Farmer farmer = new Farmer();
        farmer.setUsername(res.getBody().getEmail());
        return farmer;*/

        if(res.getBody().isSuccess()) {
            return this.farmerService.findByEmail(res.getBody().getEmail());
        } else {
            return null;
        }
    }
}
