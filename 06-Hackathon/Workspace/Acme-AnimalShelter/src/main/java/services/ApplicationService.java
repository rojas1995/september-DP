package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ApplicationRepository;
import security.Authority;
import security.LoginService;
import domain.Application;
import domain.Center;
import domain.Client;
import domain.Pet;
import domain.Report;

@Service
@Transactional
public class ApplicationService {

	// Managed repository

	@Autowired
	private ApplicationRepository applicationRepository;

	// Suporting services

	@Autowired
	private AdministratorService administratorService;

	@Autowired
	private PetService petService;

	@Autowired
	private ReportService reportService;

	@Autowired
	private ClientService clientService;

	// Constructors

	public ApplicationService() {
		super();
	}

	// Simple CRUD methods

	public Application create(Pet pet) {
		Application res = new Application();

		Client client;
		client = clientService.findByPrincipal();
		res.setClient(client);
		res.setPet(pet);

		String ticker;
		Date date = new Date(System.currentTimeMillis() - 100);

		res.setCreateMoment(date);

		ticker = this.generateTicker(pet);

		res.setClosed(false);

		res.setTicker(ticker);

		return res;

	}

	public Collection<Application> findAll() {
		Collection<Application> res;
		res = applicationRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Application findOne(int applicationId) {
		Assert.isTrue(applicationId != 0);
		Application res;
		res = applicationRepository.findOne(applicationId);
		return res;
	}

	public Application save(Application application) {
		Application res;
		res = applicationRepository.save(application);
		return res;
	}

	public void delete(Application application) {
		Collection<Authority> authority = LoginService.getPrincipal()
				.getAuthorities();
		Assert.notNull(authority);
		Authority employee = new Authority();
		employee.setAuthority("EMPLOYEE");
		Authority admin = new Authority();
		admin.setAuthority("ADMIN");
		Authority boss = new Authority();
		boss.setAuthority("BOSS");
		Assert.isTrue(authority.contains(employee) || authority.contains(admin)|| authority.contains(boss));
		// application.getClient().isBan());
		Assert.notNull(application);
		Assert.isTrue(application.getId() != 0);
		Assert.isTrue(applicationRepository.exists(application.getId()));

		// Borramos sus asociaciones
		Pet pet = application.getPet();
		pet.setApplication(null);
		this.petService.save(pet);

		Report report = application.getReport();
		if (report != null) {
			this.reportService.delete(report);
		}

		applicationRepository.delete(application);
	}

	// Other business methods

	public Collection<Application> findApplicationsPending() {
		Collection<Application> applications = new ArrayList<Application>();
		applications = this.applicationRepository.findApplicationsPending();
		return applications;
	}

	public Collection<Application> findApplicationsClientBan() {
		Collection<Application> applications = new ArrayList<Application>();
		applications = this.applicationRepository.findApplicationsClientBan();
		return applications;
	}

	public String generateTicker(Pet pet) {
		String ticker;
		String identifier = pet.getIdentifier();
		LocalDate date = new LocalDate();

		ticker = String.valueOf(date.getDayOfMonth() < 10 ? "0"
				+ date.getDayOfMonth() : date.getDayOfMonth())
				+ String.valueOf(date.getMonthOfYear() < 10 ? "0"
						+ date.getMonthOfYear() : date.getMonthOfYear())
				+ String.valueOf(date.getYear() % 100 < 10 ? "0"
						+ date.getYear() : date.getYear() % 100);

		ticker = identifier + "-" + ticker;

		return ticker;
	}

	public Collection<Application> findApplicationsPendingPerCentre(Center c) {
		Collection<Application> applications = new ArrayList<Application>();
		applications = this.applicationRepository
				.findApplicationsPendingPerCentre(c);
		return applications;
	}

	public Collection<Application> findApplicationsAprobed() {
		return this.applicationRepository.findApplicationsAprobed();
	}

	public void flush() {
		this.applicationRepository.flush();

	}
}
