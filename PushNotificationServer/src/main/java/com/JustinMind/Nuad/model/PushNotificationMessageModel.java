package com.JustinMind.Nuad.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class PushNotificationMessageModel {

	/**
	 * Token del dispositivo al que le será enviada la notificación
	 */
	@JsonInclude(Include.NON_NULL)
	private String token;

	/**
	 * Estructura que será mostrada al llegar la notificación push al móvil
	 */
	private PushNotificationModel notification;

	/**
	 * Datos arbitrarios (extras) que se puenden enviar en la notificación
	 */
	private Map<String, String> data;

	public Map<String, String> getData() {
		return data;
	}

	public void setData(Map<String, String> data) {
		this.data = data;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public PushNotificationModel getNotification() {
		return notification;
	}

	public void setNotification(PushNotificationModel notification) {
		this.notification = notification;
	}

	public PushNotificationMessageModel(String token, PushNotificationModel notification, Map<String, String> data) {

		this.token = token;
		this.notification = notification;
		this.data = data;

	}

	public PushNotificationMessageModel() {

	}

	@Override
	public String toString() {
		return "PushNotificationMessageModel [token=" + token + ", notification=" + notification + "]";
	}

}
