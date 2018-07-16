package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ContactSectionRepository;
import domain.ContactSection;
import domain.Immigrant;

@Service
@Transactional
public class ContactSectionService {
	
	// Managed repository -----------------------------------------------------

	@Autowired
	private ContactSectionRepository contactSectionRepository;

	// Supporting services ----------------------------------------------------
	
	@Autowired
	private ImmigrantService immigrantService;

	// Constructors -----------------------------------------------------------

	public ContactSectionService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	
	public ContactSection create() {
		final Immigrant immigrant = this.immigrantService.findByPrincipal();
		Assert.notNull(immigrant);
		ContactSection res = new ContactSection();

		return res;
	}

	public Collection<ContactSection> findAll() {
		Collection<ContactSection> res;
		res = contactSectionRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public ContactSection findOne(int contactSectionId) {
		Assert.isTrue(contactSectionId != 0);
		ContactSection res;
		res = contactSectionRepository.findOne(contactSectionId);
		Assert.notNull(res);
		return res;
	}

	public ContactSection save(ContactSection contactSection) {
		ContactSection res;
		res = contactSectionRepository.save(contactSection);
		return res;
	}

	public void delete(ContactSection contactSection) {
		Assert.notNull(contactSection);
		Assert.isTrue(contactSection.getId() != 0);
		Assert.isTrue(contactSectionRepository.exists(contactSection.getId()));
		contactSectionRepository.delete(contactSection);
	}
	
	// Other business methods -------------------------------------------------

}