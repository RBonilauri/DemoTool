package com.example.DemoTool.service;

import com.example.DemoTool.model.TokenAuthentication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * class that processes the information imported from authentication service
 */
public class ImportFromAuthenticationService {

    /**
     * methode envoyant une requete POST sous forme de JSON avec les attributs de la
     * classe Authentication a http://localhost:2002/auth/token la requete renvoie un token
     * de type TokenAuthentication
     *
     * @return String permettant de completer l'entete des requetes pour TimeData
     * @throws RestClientException
     */
    public String postAuthenticationArguments() throws RestClientException {
        String uri = "http://localhost:2002/auth/token";
        String parameters = "username=admin&password=test&grant_type=password&client_id=clientIdPassword";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<String> request = new HttpEntity<>(parameters, headers);

        RestTemplate restTemplate = new RestTemplate();
        TokenAuthentication result = restTemplate.postForObject(uri, request, TokenAuthentication.class);
        return result.getAccess_token();
    }
}
