package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CommentRepository;
import security.Authority;
import security.LoginService;
import domain.Comment;
import domain.Hike;
import domain.Route;
import domain.User;

@Service
@Transactional
public class CommentService {

	// Managed repository
	@Autowired
	private CommentRepository commentRepository;

	// Suporting services
	@Autowired
	private UserService userService;

	@Autowired
	private HikeService hikeService;

	@Autowired
	private AdministratorService administratorService;

	@Autowired
	private ConfigurationService configurationService;

	// Constructors

	public CommentService() {
		super();
	}

	// Simple CRUD methods

	public Comment create() {
		Comment res;
		res = new Comment();

		Date moment = new Date(System.currentTimeMillis() - 1000);
		User user = userService.findByPrincipal();
		res.setUser(user);
		res.setMoment(moment);

		return res;
	}

	public Collection<Comment> findAll() {
		Collection<Comment> res;
		res = this.commentRepository.findAll();
		return res;
	}

	public Comment findOne(final int id) {
		Assert.isTrue(id != 0);
		Comment res;
		res = this.commentRepository.findOne(id);
		return res;
	}

	public Comment save(final Comment comment) {
		Assert.notNull(comment);
		Date moment = new Date(System.currentTimeMillis() - 1000);
		User user = userService.findByPrincipal();
		comment.setUser(user);
		comment.setMoment(moment);

		Comment res;
		Collection<String> tabooWords = new ArrayList<String>();
		tabooWords = configurationService.findTabooWords();
		for (String s : tabooWords) {
			if (comment.getTitle().toLowerCase().contains(s.toLowerCase())
					|| comment.getText().toLowerCase()
							.contains(s.toLowerCase())) {
				comment.setTaboo(true);
			}
		}
		if(comment.getId()==0)
			comment.setUser(this.userService.findByPrincipal());
		res = this.commentRepository.save(comment);
		if (comment.getId() == 0 && comment.getRoute() != null) {
			Route route = comment.getRoute();
			Collection<Comment> comments = new ArrayList<>();
			comments.addAll(route.getComments());
			comments.add(res);
			route.setComments(comments);
			// this.routeService.save(route);
		} else if (comment.getId() == 0 && comment.getHike() != null) {
			Hike hike = comment.getHike();
			Collection<Comment> comments = new ArrayList<Comment>();
			comments.addAll(hike.getComments());
			comments.add(res);
			hike.setComments(comments);
			this.hikeService.save(hike);
		}
		return res;
	}

	public void delete(Comment comment) {
		Assert.notNull(comment);
		Assert.isTrue(comment.getId() != 0);
		Collection<Authority> authority = LoginService.getPrincipal()
				.getAuthorities();
		Assert.notNull(authority);
		Authority user = new Authority();
		user.setAuthority("USER");
		Authority admin = new Authority();
		admin.setAuthority("ADMIN");
		Assert.isTrue(authority.contains(user) || authority.contains(admin));

		Assert.isTrue(this.commentRepository.exists(comment.getId()));
		this.userService.findOne(comment.getUser().getId()).getComments()
				.remove(comment);
		this.commentRepository.delete(comment);
	}

	// Other business methods

	public void flush() {
		this.commentRepository.flush();
	}

	public Collection<Comment> findCommentTaboo() {
		this.administratorService.checkAuthority();
		return commentRepository.findCommentTaboo();
	}

}
