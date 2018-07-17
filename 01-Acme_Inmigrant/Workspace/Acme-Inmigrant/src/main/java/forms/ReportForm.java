package forms;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import domain.Immigrant;

public class ReportForm {
	
	// Constructor 

	public ReportForm(){
		super();
	}

	// Attributes
	private int id;
	private String text;
	private String picture;
	private Immigrant immigrant;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	@Valid
	@NotNull
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public Immigrant getImmigrant() {
		return immigrant;
	}
	public void setImmigrant(Immigrant immigrant) {
		this.immigrant = immigrant;
	}
}
