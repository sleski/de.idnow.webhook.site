package de.idnow.webhook.site;

public class Response {

	private final int webhookId;

	public Response(int webhookId) {
		this.webhookId = webhookId;
	}

	public int getWebhookId() {
		return webhookId;
	}
}
