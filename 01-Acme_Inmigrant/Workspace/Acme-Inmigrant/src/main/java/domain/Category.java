package domain;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = {@Index(columnList = "name") })
public class Category extends DomainEntity {

	// Constructors
	
	public Category(){
		super();
	}
	
	// Attributes

	private String name;
	private Boolean rootCategory;

	@NotBlank
	@SafeHtml
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotNull
	public Boolean getRootCategory() {
		return rootCategory;
	}

	public void setRootCategory(Boolean rootCategory) {
		this.rootCategory = rootCategory;
	}
	
	// Relationships
	
	private Category categoryParent;
	private List<Category> categories;
	private List<Visa> visas;

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
	@Valid
	@OneToMany(mappedBy="category")
	public List<Visa> getVisas() {
		return visas;
	}
	
	public void setVisas(List<Visa> visas) {
		this.visas = visas;
	}
	
	
	
	

}
