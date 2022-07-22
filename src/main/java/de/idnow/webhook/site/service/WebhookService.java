package de.idnow.webhook.site.service;

import de.idnow.webhook.site.model.Webhook;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Singleton
public class WebhookService {

	private final EntityManager entityManager;

	@Inject
	public WebhookService(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void add() {
		Webhook webhook = new Webhook();
		webhook.setHash("lalamido");
		webhook.setPayload("lalamido");
		save(webhook);
	}

	@Transactional
	public int save(Webhook webhook) {
		Webhook saved = entityManager.merge(webhook);
		return saved.getId();
	}

	public List<Webhook> getAll() {
		TypedQuery<Webhook> namedQuery = entityManager
				.createQuery( "SELECT e FROM Webhook e ORDER BY e.id desc", Webhook.class);
		return namedQuery.getResultList();
	}

	public Webhook getById(int intId) {
		Webhook webhook = entityManager.getReference(Webhook.class, intId);
		return webhook;
	}

	public List<Webhook> getByHash(String hash) {
		List<Webhook> resultList = entityManager.createNativeQuery("SELECT * FROM webhook WHERE hash = ?1", Webhook.class)
				.setParameter(1, hash).getResultList();
		return resultList;
	}
}
