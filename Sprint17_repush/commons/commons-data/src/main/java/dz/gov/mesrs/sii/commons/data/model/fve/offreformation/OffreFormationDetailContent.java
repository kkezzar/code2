/**
 * [dz.gov.mesrs.sii.fve.business.model.bo.offreformation.OffreFormationDetailContent.java] 
 * @author rlaib on : 6 f�vr. 2014  12:41:42
 */
package dz.gov.mesrs.sii.commons.data.model.fve.offreformation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author rlaib  on : 6 f�vr. 2014  12:41:42
 */
@Entity
@Table(name = "offre_formation_detail_content", schema = "lmd")
public class OffreFormationDetailContent implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String contentFr;
	private String contentAr;
    private OffreFormationDetail offreFormationDetail;
    private OffreFormation offreFormation;
    
    public OffreFormationDetailContent() {
	}

	public OffreFormationDetailContent(int id, String contentFr, String contentAr) {
		this.id = id;
		this.contentFr = contentFr;
		this.contentAr = contentAr;
	}

	/**
	 * [OffreFormationDetailContent.id] Getter 
	 * @author rlaib on : 6 f�vr. 2014  12:47:47
	 * @return the id
	 */
	
	@SequenceGenerator(name="offre_formation_detail_content_id_seq_gen", sequenceName="lmd.offre_formation_detail_content_id_seq")
	@Id @GeneratedValue(generator="offre_formation_detail_content_id_seq_gen")
	@Column(name = "id")
	public int getId() {
		return id;
	}

	/**
	 * [OffreFormationDetailContent.id] Setter 
	 * @author rlaib on : 6 f�vr. 2014  12:47:47
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [OffreFormationDetailContent.contentFr] Getter 
	 * @author rlaib on : 6 f�vr. 2014  12:47:47
	 * @return the contentFr
	 */
	@Column(name = "content_fr", length = 1000)
	public String getContentFr() {
		return contentFr;
	}

	/**
	 * [OffreFormationDetailContent.contentFr] Setter 
	 * @author rlaib on : 6 f�vr. 2014  12:47:47
	 * @param contentFr the contentFr to set
	 */
	public void setContentFr(String contentFr) {
		this.contentFr = contentFr;
	}

	/**
	 * [OffreFormationDetailContent.contentAr] Getter 
	 * @author rlaib on : 6 f�vr. 2014  12:47:47
	 * @return the contentAr
	 */
	@Column(name = "content_ar", length = 1000)
	public String getContentAr() {
		return contentAr;
	}

	/**
	 * [OffreFormationDetailContent.contentAr] Setter 
	 * @author rlaib on : 6 f�vr. 2014  12:47:47
	 * @param contentAr the contentAr to set
	 */
	public void setContentAr(String contentAr) {
		this.contentAr = contentAr;
	}

	/**
	 * [OffreFormationDetailContent.offreFormationDetail] Getter 
	 * @author rlaib on : 6 f�vr. 2014  12:47:47
	 * @return the offreFormationDetail
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "  id_offre_formation_detail")
	public OffreFormationDetail getOffreFormationDetail() {
		return offreFormationDetail;
	}

	/**
	 * [OffreFormationDetailContent.offreFormationDetail] Setter 
	 * @author rlaib on : 6 f�vr. 2014  12:47:47
	 * @param offreFormationDetail the offreFormationDetail to set
	 */
	public void setOffreFormationDetail(OffreFormationDetail offreFormationDetail) {
		this.offreFormationDetail = offreFormationDetail;
	}

	/**
	 * [OffreFormationDetailContent.offreFormation] Getter 
	 * @author rlaib on : 6 fevr. 2014  12:47:47
	 * @return the offreFormation
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "  id_offre_formation")
	public OffreFormation getOffreFormation() {
		return offreFormation;
	}

	/**
	 * [OffreFormationDetailContent.offreFormation] Setter 
	 * @author rlaib on : 6 f�vr. 2014  12:47:47
	 * @param offreFormation the offreFormation to set
	 */
	public void setOffreFormation(OffreFormation offreFormation) {
		this.offreFormation = offreFormation;
	}
	
	
}
