package com.JustinMind.Nuad.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JustinMind.Nuad.model.PushNotificationMessageModel;
import com.JustinMind.Nuad.model.PushNotificationModel;
import com.JustinMind.Nuad.model.PushNotificationPayload;
import com.JustinMind.Nuad.service.GooglePushNotificationService;

@RestController
@RequestMapping(path = "/test")
public class TestController {

	@Autowired
	GooglePushNotificationService googleService;

	@GetMapping(path = "/push/{token}")
	public ResponseEntity<Object> push(@PathVariable(name = "token") String token) {

		PushNotificationModel notification = 
				new PushNotificationModel("Fecha Límite de pago próxima",
				"Paga o no entras al GYM!!");
		
		Map<String, String> data = new HashMap<>();
		data.put("extra1", "value1");
		data.put("extra2", "value2");
		
		PushNotificationMessageModel message =
				new PushNotificationMessageModel(token, notification, data);
		
		PushNotificationPayload payload = 
				new PushNotificationPayload(message);
		
		this.googleService.sendNotification(payload);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(path = "/push")
	public ResponseEntity<Object> pushGeneric() {

		PushNotificationModel notification = new PushNotificationModel("TEST SERVER",
				"Esta es una prueba genérica desde el servidor");
		Map<String, String> data = new HashMap<>();
		data.put("extra1", "value1");
		data.put("extra2", "value2");
		PushNotificationMessageModel message = new PushNotificationMessageModel(null, notification, data);
		PushNotificationPayload payload = new PushNotificationPayload(message);
		this.googleService.sendGenericNotification(payload);

		return new ResponseEntity<>(HttpStatus.OK);
	}

}
