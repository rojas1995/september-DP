package controllers.administrator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CommentService;
import controllers.AbstractController;
import domain.Comment;

@Controller
@RequestMapping("/comment/administrator")
public class CommentAdministratorController extends AbstractController{

	@Autowired
	private CommentService commentService;

	public CommentAdministratorController(){
		super();
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int commentId) {
		ModelAndView result;
		Comment comment;

		comment = this.commentService.findOne(commentId);
		System.out.println("Comment en edit: "+comment);
		result = this.createEditModelAndView(comment);

		return result;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid final Comment comment, final BindingResult binding) {
		System.out.println("Comment en delete: " + comment);
		ModelAndView res;
		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors());
			res = this.createEditModelAndView(comment, "comment.params.error");
		} else {
			try {
				this.commentService.delete(comment);
				res = new ModelAndView("redirect:../../");
			} catch (final Throwable oops) {
				System.out.println(oops.getMessage());
				System.out.println(oops.getStackTrace());
				res = this.createEditModelAndView(comment, "comment.commit.error");
			}
		}
		
		return res;
	}
	
	protected ModelAndView createEditModelAndView(final Comment comment) {
		ModelAndView result;

		result = this.createEditModelAndView(comment, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Comment comment, final String message) {
		ModelAndView result;

		result = new ModelAndView("comment/administrator/edit");
		result.addObject("comment", comment);
		result.addObject("message", message);
		result.addObject("requestUri", "comment/administrator/edit.do");

		return result;
	}
}