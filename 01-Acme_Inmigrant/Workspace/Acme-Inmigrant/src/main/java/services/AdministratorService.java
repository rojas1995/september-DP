package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.AdministratorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Administrator;
import forms.ActorForm;

@Service
@Transactional
public class AdministratorService {

	// Managed repository
	@Autowired
	private AdministratorRepository administratorRepository;

	// Suporting services
	
	@Autowired
	private Validator		validator;

	// Constructors

	public AdministratorService() {
		super();
	}

	// Simple CRUD methods

	public Administrator create() {
		Administrator res = new Administrator();

		UserAccount userAccount = new UserAccount();
		Authority authority = new Authority();

		authority.setAuthority("ADMIN");
		userAccount.addAuthority(authority);

		res.setUserAccount(userAccount);
		return res;
	}

	public Collection<Administrator> findAll() {
		Collection<Administrator> res;
		res = administratorRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Administrator findOne(int administratorId) {
		Assert.isTrue(administratorId != 0);
		Administrator res;
		res = administratorRepository.findOne(administratorId);
		Assert.notNull(res);
		return res;
	}

	public Administrator save(Administrator administrator) {
		Administrator res;

		String pass = administrator.getUserAccount().getPassword();

		final Md5PasswordEncoder code = new Md5PasswordEncoder();

		pass = code.encodePassword(pass, null);

		administrator.getUserAccount().setPassword(pass);

		res = this.administratorRepository.save(administrator);
		
		return res;

	}

	public void delete(Administrator administrator) {
		Assert.notNull(administrator);
		Assert.isTrue(administrator.getId() != 0);
		Assert.isTrue(administratorRepository.exists(administrator.getId()));
		administratorRepository.delete(administrator);
	}

	// Other business methods

	public Administrator findByPrincipal() {
		Administrator res;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		res = administratorRepository
				.findAdministratorByUserAccountId(userAccount.getId());
		Assert.notNull(res);
		return res;
	}

	public void checkAuthority() {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		Collection<Authority> authority = userAccount.getAuthorities();
		Assert.notNull(authority);
		Authority res = new Authority();
		res.setAuthority("ADMIN");
		Assert.isTrue(authority.contains(res));
	}
	
	public ActorForm construct(Administrator administrator) {
		ActorForm res = new ActorForm();
		
		res.setId(administrator.getId());
		res.setName(administrator.getName());
		res.setSurname(administrator.getSurname());
		res.setPhoneNumber(administrator.getPhoneNumber());
		res.setEmail(administrator.getEmail());
		res.setAddress(administrator.getAddress());
		res.setUsername(administrator.getUserAccount().getUsername());
		
		return res;
	}

	public Administrator reconstruct(final ActorForm administratorForm,
			final BindingResult binding) {
		Assert.notNull(administratorForm);
		Administrator res = new Administrator();
		
		if (administratorForm.getId() != 0)
			res = this.findOne(administratorForm.getId());
		else
			res = this.create();
		
		res.setName(administratorForm.getName());
		res.setSurname(administratorForm.getSurname());
		res.setEmail(administratorForm.getEmail());
		res.setPhoneNumber(administratorForm.getPhoneNumber());
		res.setAddress(administratorForm.getAddress());
		res.getUserAccount().setUsername(administratorForm.getUsername());
		res.getUserAccount().setPassword(administratorForm.getPassword());
		
		this.validator.validate(res, binding);
		
		return res;
	}

	 //methods for dashboard
	public Collection<Double> dataApplicationPerImmigrant() {
		return administratorRepository.dataApplicationPerImmigrant();

	}

	public Collection<Double> dataApplicationsPerOfficer() {
		return administratorRepository.dataApplicationsPerOfficer();
	}

	public Collection<Double> dataPricePerVisa() {
		return administratorRepository.dataPricePerVisa();
	}

	public Collection<Double> dataImmigrantsInvestigated() {
		return administratorRepository.dataImmigrantsInvestigated();
	}

	public Collection<Date> dataTimeToMakeDecision() {
		return administratorRepository.dataTimeToMakeDecision();
	}

	public Collection<Double> dataVisasPerCategory() {
		return administratorRepository.dataVisasPerCategory();
	}

	public Collection<Double> dataLawsPerCountry() {
		return administratorRepository.dataLawsPerCountry();
	}

	public Collection<Double> dataRequirementsPerVisa() {
		return administratorRepository.dataRequirementsPerVisa();
	}
}
