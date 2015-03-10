package dz.gov.mesrs.sii.commons.data.model.referentiel;

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
@Entity
@Table(name = "ref_jour_ouvre", schema = "ppm")
public class RefJourOuvre implements java.io.Serializable {

	
	private static final long serialVersionUID = 1L;
	private Long id;
	private Date date;
	private String description;
	


	public RefJourOuvre() {
	}

	

	
	@Id
	@SequenceGenerator(name="ref_jour_ouvre_id_seq_gen", sequenceName="ppm.ref_jour_ouvre_id_seq")
	@GeneratedValue(generator="ref_jour_ouvre_id_seq_gen")
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	@Temporal(TemporalType.DATE)
	@Column(name = "date", nullable = false, length = 13)
	public Date getDate() {
		return date;
	}




	public void setDate(Date date) {
		this.date = date;
	}



	@Column(name = "description")
	public String getDescription() {
		return description;
	}




	public void setDescription(String description) {
		this.description = description;
	}

	

}
