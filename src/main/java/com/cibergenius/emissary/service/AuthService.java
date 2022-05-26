package com.cibergenius.emissary.service;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.cibergenius.emissary.entities.TokenModel;
import com.google.gson.Gson;

@Service
public class AuthService implements IAuthService {
	
	private Gson gson = new Gson();
	
	@Value("${cibergenius.server}")
	String server;
	
	@Value("${cibergenius.user}")
	String user;
	
	@Value("${cibergenius.pass}")
	String pass;	
	
	@Value("${url.cibergenius.auth}")
	String authUrl;	
	
	@Value("${url.cibergenius.params}")
	String queryParams;	
	
	@Value("${cibergenius.auth.username}")
	String aUser;
	
	@Value("${cibergenius.auth.password}")
	String aPass;
	
	@Override
	public TokenModel getToken() {
		try {
			String plainCreds = aUser+":"+aPass;
			byte[] plainCredsBytes = plainCreds.getBytes();
			byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
			String base64Creds = new String(base64CredsBytes);
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders head = new HttpHeaders();
			String token = "Basic ";
			token = token.concat(base64Creds);
			head.set("Authorization", token);
			HttpEntity<String> entity = new HttpEntity<String>("", head);
			String url = server.concat(authUrl.concat(queryParams));
			url = url.replace("$USER$", user);
			url = url.replace("$PASS$", pass);
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
			return gson.fromJson(response.getBody(), TokenModel.class);
		} catch (HttpClientErrorException | HttpServerErrorException e) {
			throw e;
		}
	}

}
