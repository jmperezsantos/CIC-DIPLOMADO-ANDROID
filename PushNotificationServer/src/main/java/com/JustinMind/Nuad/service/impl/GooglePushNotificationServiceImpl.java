package com.JustinMind.Nuad.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.JustinMind.Nuad.model.PushNotificationPayload;
import com.JustinMind.Nuad.service.GooglePushNotificationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;

/**
 * Implementación de servicio para manejo de envío de Push Notifications
 * 
 * @ @author Manuel Pérez
 */

@Service
public class GooglePushNotificationServiceImpl implements GooglePushNotificationService {

	private static final Log LOGGER = LogFactory.getLog(GooglePushNotificationServiceImpl.class);

	@Value("${google.firebase.project-id}")
	private String projectId;

	@Value("${google.firebase.url}")
	private String firebaseUrl;

	@Value("${google.firebase.scope}")
	private String scope;

	@Value("${google.firebase.service_account_file}")
	private String serviceAccount;

	@Autowired
	private ResourceLoader resourceLoader;

	@Override
	public void sendNotification(PushNotificationPayload payload) {

		String token = this.getAccessToken();

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", token));

		String url = this.firebaseUrl.replace("{PROJECT_ID}", this.projectId);

		try {

			ObjectMapper mapper = new ObjectMapper();

			String json = mapper.writeValueAsString(payload);

			LOGGER.info(json);

			HttpEntity<String> entity = new HttpEntity<String>(json, headers);

			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

			LOGGER.info(response.getBody());

		} catch (JsonProcessingException e) {

			LOGGER.error("JsonProcessingException: " + e.getMessage());

		} catch (RestClientException e) {

			LOGGER.error("RestClientException: " + e.getMessage());

		}

	}

	@Override
	public void sendGenericNotification(PushNotificationPayload payload) {

		String token = this.getAccessToken();

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", token));
		headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

		String url = this.firebaseUrl.replace("{PROJECT_ID}", this.projectId);

		try {

			ObjectMapper mapper = new ObjectMapper();

			String json = mapper.writeValueAsString(payload);

			LOGGER.info(json);

			HttpEntity<String> entity = new HttpEntity<String>(json, headers);

			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

			LOGGER.info(response.getBody());

		} catch (JsonProcessingException e) {

			LOGGER.error(e.getMessage());

		} catch (RestClientException e) {

			LOGGER.error(e.getMessage());

		}
	}

	private String getAccessToken() {

		GoogleCredential googleCredential = null;
		try {

			Resource resource = this.resourceLoader.getResource(this.serviceAccount);

			File file = resource.getFile();

			FileInputStream inputStream = new FileInputStream(file);

			googleCredential =
					GoogleCredential.fromStream(inputStream).createScoped(Arrays.asList(this.scope));

			if (googleCredential.refreshToken()) {
				LOGGER.info("Token Refreshed");
			} else {
				LOGGER.info("Token NOT Refreshed");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return googleCredential.getAccessToken();

	}

}
