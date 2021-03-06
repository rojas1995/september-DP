package tests;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import security.Authority;
import security.UserAccount;
import services.TutorialService;
import services.UserService;
import utilities.AbstractTest;
import domain.Tutorial;
import domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@Transactional
public class UnauthenticatedTest extends AbstractTest {

	@Autowired
	private UserService userService;
	
	@Autowired
	private TutorialService tutorialService;

	// 5.1 Register to the system as a user
	
	@Test
	public void registerUserTest() {

		final Object testingData[][] = {
			{	// Positivo
				null, "al@mail.com", "perez alarc�n", "http://www.google.com", null
			} , { // Negativo con email incorrecto
				null, "al", "perez alarc�n", "http://www.google.com", DataIntegrityViolationException.class
			} , { // Negativo con apellido vac�o
				null, "al@mail.com", "", "http://www.google.com", DataIntegrityViolationException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateRegisterUser((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (Class<?>) testingData[i][4]);
	}
	
	protected void templateRegisterUser(final String user, final String mail, final String surname, final String pictures, final Class<?> expected) {
		Class<?> caught;
		caught = null;
		try {
			if(user != null){
				this.authenticate(user);
			}
			
			final UserAccount userAccount = new UserAccount();
			userAccount.setUsername("userTest");
			userAccount.setPassword("userTest");
			final List<Authority> authorities = new ArrayList<>();
			final Authority aut = new Authority();
			aut.setAuthority("USER");
			authorities.add(aut);
			userAccount.setAuthorities(authorities);

			final User userTest = this.userService.create();
			
			userTest.setName("user");
			userTest.setSurname(surname);
			userTest.setPictures(pictures);
			userTest.setPostalAddress("41009");
			userTest.setPhoneNumber("666666666");
			userTest.setEmail(mail);
			
			userTest.setUserAccount(userAccount);

			this.userService.save(userTest);
			this.unauthenticate();
			this.userService.flush();
			this.authenticate(userTest.getUserAccount().getUsername());
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}
	
	// 14.1 List the tutorials in the system and display them
	
	@Test
	public void listTutorialTest() {

		final Object testingData[][] = {
				{ // Positivo
					null, null
				}
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateListTutorialTest((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	protected void templateListTutorialTest(final String actor, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			if(actor != null){
				super.authenticate(actor);
			}
			
			final Collection<Tutorial> tutorials = this.tutorialService
					.findAll();

			this.unauthenticate();
			Assert.notNull(tutorials);

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
}
