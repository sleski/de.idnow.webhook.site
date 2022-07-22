package de.idnow.webhook.site.controller;

import de.idnow.webhook.site.model.Webhook;
import de.idnow.webhook.site.service.WebhookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Objects;

@Path("/webhook")
public class WebhookController {

	private static final Logger log = LoggerFactory.getLogger(WebhookController.class);

	private final WebhookService webhookService;

	@Inject
	public WebhookController(WebhookService webhookService) {
		this.webhookService = webhookService;
	}

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Webhook> getAll() {
		return webhookService.getAll();
	}

	@GET
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String add() {
		webhookService.add();
		return "Added!";
	}

	@POST
	@Path("/save/{hash}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response save(@PathParam("hash") String hash, JsonObject body) {
		log.debug(" hash = {}, json = {}", hash, body);
		Webhook newWebhook = new Webhook();
		newWebhook.setHash(hash);
		newWebhook.setPayload(Objects.isNull(body) ? "" : body.toString());
		int savedId = webhookService.save(newWebhook);
		log.info("new webhook saved, id = {}", savedId);
		return Response.ok(new de.idnow.webhook.site.Response(savedId)).build();
	}

	@POST
	@Path("/webhook/save/{token}/response/{status_code}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveAndReturnGivenStatus(@PathParam("token") String token,@PathParam("status_code") Integer statusCode, JsonObject body) {
		log.debug(" token = {}, json = {}", token, body);
		Webhook newWebhook = new Webhook();
		newWebhook.setHash(token);
		newWebhook.setPayload(Objects.isNull(body) ? "" : body.toString());
		int savedId = webhookService.save(newWebhook);
		log.info("new webhook saved, id = {}, returns status = ", savedId, statusCode);
		return Response.status(statusCode).build();
	}

	@GET
	@Path("/id/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getById(@PathParam("id") String id) {
		Objects.requireNonNull(id, "Webhook id can not be null!");

		try {
			int intId = Integer.parseInt(id);
			Webhook webhookById = webhookService.getById(intId);
			if (Objects.nonNull(webhookById)) {
				return Response.ok(webhookById).build();
			}
		} catch (NumberFormatException numberFormatException) {
			Response.status(500, "Can not parse id to number, id = " + id);
		}
		return Response.status(404).build();
	}

	@GET
	@Path("/hash/{hash}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getByHash(@PathParam("hash") String hash) {
		Objects.requireNonNull(hash, "Webhook hash can not be null!");

			List<Webhook> webhooks = webhookService.getByHash(hash);
				return Response.ok(webhooks).build();
	}

}
