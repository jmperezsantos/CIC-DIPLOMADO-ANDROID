package com.JustinMind.Nuad.service;

import com.JustinMind.Nuad.model.PushNotificationPayload;

/**
 * Interfaz para manejo de envío de push notification
 * 
 * @author Manuel Pérez
 *
 */
public interface GooglePushNotificationService {

	/**
	 * Método para envío de push notifications
	 * 
	 * @param payload
	 *            El contenido de la notificación a enviar
	 */
	public void sendNotification(PushNotificationPayload payload);

	/**
	 * Método para envío de push notifications genéricas
	 * 
	 * @param payload
	 *            El contenido de la notificación a enviar
	 */
	public void sendGenericNotification(PushNotificationPayload payload);

}
