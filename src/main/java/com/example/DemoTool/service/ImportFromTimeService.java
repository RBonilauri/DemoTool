package com.example.DemoTool.service;

import com.example.DemoTool.model.ResponseObject;
import com.example.DemoTool.model.TimeData;
import org.lfenergy.operatorfabric.time.model.SpeedEnum;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

/**
 * class that processes the information imported from time service
 */
@Component
public class ImportFromTimeService {

    ImportFromAuthenticationService importFromAuthenticationService = new ImportFromAuthenticationService();

    /**
     * method to convert a character string (in yyyy-MM-dd format) to date in milliseconds
     *
     * @param choosenDate String
     * @return dateInLong
     * long milliseconds (between Epoch and choosenDate)
     */
    public long convertDateToLong(String choosenDate) {
        DateFormat formatter;
        Date date;
        long dateInLong = 0;
        try {
            formatter = new SimpleDateFormat("yyyy-MM-dd");
            date = formatter.parse(choosenDate);
            dateInLong = date.getTime();
        } catch (ParseException e) {
            System.out.println(e);
        }
        return dateInLong;
    }

    /**
     * method for converting an hour (format HH:mm) into a long
     *
     * @param choosenTime String
     * @return timeInLong
     * time in long at 01/01/1970
     * @throws ParseException
     */
    public long convertTimeToLong(String choosenTime) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date d = dateFormat.parse(choosenTime);
        long timeInLong = d.getTime();
        return timeInLong;
    }

    /**
     * method to obtain a date thanks to a long
     *
     * @param longDate long
     * @return dateText
     * date in String in the format dd/MM/yyyy
     */
    public String convertLongToStringDate(long longDate) {
        Date date = new Date(longDate);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy '-' HH:mm");
        String dateText = dateFormat.format(date);
        return dateText;
    }

    /**
     * methode permettant de faire une requete GET a http://localhost:2101/time
     * afin d'obtenir l'objet TimeData (deja mappé)
     *
     * @return timeData
     * timeData object
     */
    public TimeData getTimeData() {
        RestTemplate restTemplate = new RestTemplate();
        TimeData timeData = restTemplate.getForObject("http://localhost:2101/time", TimeData.class);
        return timeData;
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
        return speedEnum;
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
     * @return
     */
    public URI postNextCardWithoutMillisTime() {
        String uri = "http://localhost:2101/time/next/card";
        String timeFromEpoch = "\"" + getTimeData().getComputedNow() + "\"";
        String accessToken = "Bearer " + importFromAuthenticationService.postAuthenticationArguments();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.setAcceptCharset(Arrays.asList(Charset.forName("UTF-8")));
        headers.set("Authorization", accessToken);

        HttpEntity<String> request = new HttpEntity<>(timeFromEpoch, headers);

        RestTemplate restTemplate = new RestTemplate();
        URI response = restTemplate.postForLocation(uri, request);
        return response;
    }

    /**
     * @return
     */
    public ResponseObject postPreviousCardWithoutMillisTime() {
        String uri = "http://localhost:2101/time/previous/card";
        String timeFromEpoch = "\"" + getTimeData().getComputedNow() + "\"";
        String accessToken = "Bearer " + importFromAuthenticationService.postAuthenticationArguments();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.setAcceptCharset(Arrays.asList(Charset.forName("UTF-8")));
        headers.set("Authorization", accessToken);

        HttpEntity<String> request = new HttpEntity<>(timeFromEpoch, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseObject responseObject = restTemplate.postForObject(uri, request, ResponseObject.class);
        return responseObject;
    }

}