package com.example.DemoTool.service;

import com.example.DemoTool.model.TimeData;
import org.lfenergy.operatorfabric.time.model.SpeedEnum;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * class that processes the information imported from time service
 */
@Component
public class ImportFromTimeService {

    ImportFromAuthenticationService importFromAuthenticationService = new ImportFromAuthenticationService();

    /**
     * method to make a GET request at http://localhost:2101/time to obtain the TimeData object
     *
     * @return a TimeData object
     */
    public TimeData getTimeData() {
        String uri = "http://localhost:2101/time";
        String accessToken = "Bearer " + importFromAuthenticationService.postAuthenticationArguments();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.setAcceptCharset(Arrays.asList(Charset.forName("UTF-8")));
        headers.set("Authorization", accessToken);

        HttpEntity<TimeData> request = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<TimeData> timeData = restTemplate.exchange(uri, HttpMethod.GET, request, TimeData.class);
        return timeData.getBody();
    }

    /**
     * method for sending a POST request to http://localhost:2101/time/speed
     * to set the flow velocity to the desired value
     *
     * @param currentSpeed
     * @return speedEnum
     * String corresponding to the new flow velocity
     */
    public String postSpeed(String currentSpeed) {
        String uri = "http://localhost:2101/time/speed";
        String speedEnum = "\"" + currentSpeed + "\"";
        String accessToken = "Bearer " + importFromAuthenticationService.postAuthenticationArguments();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.setAcceptCharset(Arrays.asList(Charset.forName("UTF-8")));
        headers.set("Authorization", accessToken);

        HttpEntity<String> request = new HttpEntity<>(speedEnum, headers);

        RestTemplate restTemplate = new RestTemplate();
        SpeedEnum response = restTemplate.postForObject(uri, request, SpeedEnum.class);
        return String.valueOf(response);
    }

    /**
     * method for sending a POST request to http://localhost:2101/time/current
     * to set the virtual time to the desired value
     *
     * @param newDate String date (at format dd/mm/yyyy) who will be use by convertDateToLong()
     * @return response (de type URI pour l'instant)
     */
    public URI postCurrentTime(String newDate) {
        String uri = "http://localhost:2101/time/current";
        String currentDate = "\"" + newDate + "\"";
        String accessToken = "Bearer " + importFromAuthenticationService.postAuthenticationArguments();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.setAcceptCharset(Arrays.asList(Charset.forName("UTF-8")));
        headers.set("Authorization", accessToken);

        HttpEntity<String> request = new HttpEntity<>(currentDate, headers);

        RestTemplate restTemplate = new RestTemplate();
        URI response = restTemplate.postForLocation(uri, request);
        return response;
    }

    /**
     * method to send a post request to the time service to get the next card
     *
     * @param millisTime
     * @return the response of the request (200 OK or 404 no card Found)
     */
    public String postNextCard(long millisTime) {
        String uri = "http://localhost:2101/time/" + millisTime + "/next/card";
        String accessToken = "Bearer " + importFromAuthenticationService.postAuthenticationArguments();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.setAcceptCharset(Arrays.asList(Charset.forName("UTF-8")));
        headers.set("Authorization", accessToken);

        HttpEntity<String> request = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.POST, request, String.class);

        String codeValue = String.valueOf(responseEntity.getStatusCode());
        System.out.println(codeValue);
        return codeValue;
    }

    /**
     * method to send a post request to the time service to get the previous card
     *
     * @param millisTime
     * @return the response of the request (200 OK or 404 no card Found)
     */
    public String postPreviousCard(long millisTime) {
        String uri = "http://localhost:2101/time/" + millisTime + "/previous/card";
        String accessToken = "Bearer " + importFromAuthenticationService.postAuthenticationArguments();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.setAcceptCharset(Arrays.asList(Charset.forName("UTF-8")));
        headers.set("Authorization", accessToken);

        HttpEntity<String> request = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.POST, request, String.class);

        String codeValue = String.valueOf(responseEntity.getStatusCode());
        System.out.println(codeValue);
        return codeValue;
    }

    /**
     * method to reset time configuration
     *
     * @return the request's response
     */
    public String deleteTime() {
        String uri = "http://localhost:2101/time";
        String accessToken = "Bearer " + importFromAuthenticationService.postAuthenticationArguments();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.setAcceptCharset(Arrays.asList(Charset.forName("UTF-8")));
        headers.set("Authorization", accessToken);

        HttpEntity<String> request = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.DELETE, request, String.class);

        String codeValue = String.valueOf(responseEntity.getStatusCode());
        System.out.println(codeValue);
        return codeValue;
    }
}