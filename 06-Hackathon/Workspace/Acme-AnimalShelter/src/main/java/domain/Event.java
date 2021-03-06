package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = {@Index(columnList = "center_id,endDate") })
public class Event extends DomainEntity{
	
	// Constructors
	
	public Event(){
		super();
	}

	private String title;
	private String description;
	private String nameSite;
	private String address;
	private String placard;
	private Date startDate;
	private Date endDate;
	private Date publicationDate;
//	private Date openTime;
//	private Date closeTime;
	
	
	@NotBlank
	@SafeHtml
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@NotBlank
	@SafeHtml
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@NotBlank
	@SafeHtml
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@NotBlank
	@URL
	@SafeHtml
	public String getNameSite() {
		return nameSite;
	}

	public void setNameSite(String nameSite) {
		this.nameSite = nameSite;
	}

	@NotBlank
	@URL
	@SafeHtml
	public String getPlacard() {
		return placard;
	}

	public void setPlacard(String placard) {
		this.placard = placard;
	}

	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

//	@NotNull
//	@Temporal(TemporalType.TIME)
//	@DateTimeFormat(pattern = "HH:mm")
//	public Date getOpenTime() {
//		return openTime;
//	}
//
//	public void setOpenTime(Date openTime) {
//		this.openTime = openTime;
//	}
//
//	@NotNull
//	@Temporal(TemporalType.TIME)
//	@DateTimeFormat(pattern = "HH:mm")
//	public Date getCloseTime() {
//		return closeTime;
//	}
//
//	public void setCloseTime(Date closeTime) {
//		this.closeTime = closeTime;
//	}

	// Relationships
	
	private Center center;
	private Collection<Donation> donation;
	private Company company;
	
	@Valid
	@ManyToOne(optional=false)
	public Center getCenter() {
		return center;
	}

	public void setCenter(Center center) {
		this.center = center;
	}
	
	@Valid
	@OneToMany(mappedBy = "event",cascade = CascadeType.ALL)
	public Collection<Donation> getDonation() {
		return donation;
	}

	public void setDonation(Collection<Donation> donation) {
		this.donation = donation;
	}

	@Valid
	@OneToOne(optional=true)
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
}
