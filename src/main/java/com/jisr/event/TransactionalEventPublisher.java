package com.jisr.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * A service for publishing application events after the current transaction commits.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class TransactionalEventPublisher {

	private final ApplicationEventPublisher applicationEventPublisher;

	/**
	 * Publishes an event after the current transaction commits. Logs errors if any occur during event publication.
	 *
	 * @param event the event to publish
	 */
	public void publishEventAfterCommit(Object event) {
		try {
			if (TransactionSynchronizationManager.isActualTransactionActive()) {
				log.debug("Registering event [{}] to be published after transaction commit.", event.getClass().getSimpleName());
				TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
					@Override
					public void afterCommit() {
						try {
							log.info("Publishing event [{}] after transaction commit.", event.getClass().getSimpleName());
							applicationEventPublisher.publishEvent(event);
						} catch (Exception e) {
							log.error("Failed to publish event [{}] after transaction commit.", event.getClass().getSimpleName(), e);
						}
					}
				});
			} else {
				// If no transaction is active, publish immediately
				log.warn("No active transaction found. Publishing event [{}] immediately.", event.getClass().getSimpleName());
				applicationEventPublisher.publishEvent(event);
			}
		} catch (Exception e) {
			log.error("Failed to register or publish event [{}].", event.getClass().getSimpleName(), e);
		}
	}
}
