package com.example.client.caller;
import com.example.client.dto.Human;
import com.example.client.dto.HumansResponse;
import com.example.client.dto.Policeman;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import javax.annotation.PostConstruct;
import java.util.ArrayList;





@Service
public class Caller {

    private RestTemplate restTemplate;

    @Autowired
    public Caller(RestTemplate restTemplate) {
        this.restTemplate=restTemplate;
    }

    @PostConstruct
    public void after() throws JsonProcessingException {
        List<Human> results =fetchHumans();
        printHumans(results);

    }


    public List<Human> fetchHumans()  {
        String url = "http://127.0.0.1:8090/v1/lab";
        List<Human> results = new ArrayList<>();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        ResponseEntity<HumansResponse> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, HumansResponse.class);
        results=responseEntity.getBody().getList();
        return results;
    }


    public void printHumans( List<Human> results){
        for(Human current:results){
            if(current instanceof Policeman){
                Policeman temp=(Policeman)current;
                System.out.println("POLICEMAN: "+current);
            } else if(current instanceof Human){
                System.out.println("HUMAN: "+current);
            }
        }
    }









}
