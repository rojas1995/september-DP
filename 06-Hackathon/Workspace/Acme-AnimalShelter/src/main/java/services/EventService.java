package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.EventRepository;
import domain.Event;


@Service
@Transactional
public class EventService {

	// Managed repository

	@Autowired
	private EventRepository eventRepository;

	// Suporting services

	// Constructors

	public EventService() {
		super();
	}

	// Simple CRUD methods

	public Event create() {
		Event res = new Event();

		return res;

	}

	public Collection<Event> findAll() {
		Collection<Event> res;
		res = eventRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Event findOne(int eventId) {
		Assert.isTrue(eventId != 0);
		Event res;
		res = eventRepository.findOne(eventId);
		Assert.notNull(res);
		return res;
	}

	public Event save(Event event) {
		Event res;
		res = eventRepository.save(event);
		return res;
	}

	public void delete(Event event) {
		Assert.notNull(event);
		Assert.isTrue(event.getId() != 0);
		Assert.isTrue(eventRepository.exists(event.getId()));
		eventRepository.delete(event);
	}

	// Other business methods
	
	

}
