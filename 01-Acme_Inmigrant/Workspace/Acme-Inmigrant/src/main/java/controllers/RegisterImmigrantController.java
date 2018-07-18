package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ImmigrantService;
import domain.Immigrant;
import forms.ActorForm;

@Controller
@RequestMapping("/immigrant")
public class RegisterImmigrantController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private ImmigrantService immigrantService;

	// Constructors ---------------------------------------------------------

	public RegisterImmigrantController() {
		super();
	}

	// Registering ----------------------------------------------------------

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		
		Immigrant immigrant = immigrantService.create();

		ActorForm actorForm = new ActorForm();
		actorForm = immigrantService.construct(immigrant);

//		res = new ModelAndView("immigrant/register_Immigrant");
//		res.addObject("actorForm", actorForm);
		
		res = this.createEditModelAndView(actorForm);

		return res;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute("actorForm") ActorForm actorForm,
			final BindingResult binding) {
		ModelAndView res;
		
		if (binding.hasErrors())
			res = this.createEditModelAndView(actorForm, "actor.params.error");
		else if (!actorForm.getRepeatPassword().equals(actorForm.getPassword()))
			res = this.createEditModelAndView(actorForm, "actor.commit.errorPassword");
		else if (actorForm.getTermsAndConditions() == false) {
			res = this.createEditModelAndView(actorForm, "actor.params.errorTerms");
		} else
			try{
				Immigrant immigrant = this.immigrantService.reconstruct(actorForm, binding);
				this.immigrantService.save(immigrant);
				res = new ModelAndView("redirect:/");
			}catch (final Throwable oops) {
				res = this.createEditModelAndView(actorForm, "actor.commit.error");
			}
		
		return res;
	}

	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndView(final ActorForm actorForm) {
		ModelAndView result;

		result = this.createEditModelAndView(actorForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final ActorForm actorForm,
			final String message) {
		ModelAndView result;

		result = new ModelAndView("actor/register");
		result.addObject("actorForm", actorForm);
		result.addObject("message", message);
		result.addObject("requestURI","immigrant/register.do");

		return result;
	}
}
