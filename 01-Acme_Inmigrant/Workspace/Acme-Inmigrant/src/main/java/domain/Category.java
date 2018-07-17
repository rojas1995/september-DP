package domain;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Category extends DomainEntity {

	// Constructors
	
	public Category(){
		super();
	}
	
	// Attributes

	private String name;
	private boolean rootCategory;

	@NotBlank
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getRootCategory() {
		return rootCategory;
	}

	public void setRootCategory(boolean rootCategory) {
		this.rootCategory = rootCategory;
	}
	
	// Relationships
	
	private Category categoryParent;
	private List<Category> categories;

	@Valid
	@ManyToOne(optional = true)
	public Category getCategoryParent() {
		return categoryParent;
	}

	public void setCategoryParent(Category categoryParent) {
		this.categoryParent = categoryParent;
	}

	@Valid
	@OneToMany
	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
	
	
	

}
