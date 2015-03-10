package dz.gov.mesrs.sii.referentiel.business.model.dto;

// Generated 7 oct. 2014 17:24:06 by Hibernate Tools 3.6.0

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author BELDI Jamel
 * 
 */

public class RefJourOuvreDto implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private Date date;
	private String description;

	public RefJourOuvreDto() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
