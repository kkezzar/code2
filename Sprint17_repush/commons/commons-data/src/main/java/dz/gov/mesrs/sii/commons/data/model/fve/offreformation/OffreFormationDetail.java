/**
 * [dz.gov.mesrs.sii.fve.business.model.bo.offreformation.OffreFormationDetail.java] 
 * @author rlaib on : 6 fevr. 2014  12:22:02
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
 * @author rlaib  on : 6 fevr. 2014  12:22:02
 */
@Entity
@Table(name = "offre_formation_detail", schema = "lmd")
public class OffreFormationDetail  implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int typeDetail;
	private String code;
	private String libelleFr;
	private String libelleAr;
	private String descriptionFr;
	private String descriptionAr;
    private OffreFormationDetail offreFormationDetail;
    
	public OffreFormationDetail() {
	}

	public OffreFormationDetail(int id, String code, String libelleFr, String libelleAr) {
		this.id = id;
		this.code = code;
		this.libelleFr = libelleFr;
		this.libelleAr = libelleAr;
	}

	/**
	 * [OffreFormationDetail.id] Getter 
	 * @author rlaib on : 6 fevr. 2014  12:36:52
	 * @return the id
	 */
	
	@SequenceGenerator(name="offre_formation_detail_id_seq_gen", sequenceName="lmd.offre_formation_detail_id_seq")
	@Id @GeneratedValue(generator="offre_formation_detail_id_seq_gen")
	@Column(name = "id")
	public int getId() {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
		return id;
	}

	/**
	 * [OffreFormationDetail.id] Setter 
	 * @author rlaib on : 6 fevr. 2014  12:36:52
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [OffreFormationDetail.typeDetail] Getter 
	 * @author rlaib on : 6 fevr. 2014  12:36:52
	 * @return the typeDetail
	 */
	@Column(name = "detail_type")
	public int getTypeDetail() {
		return typeDetail;
	}

	/**
	 * [OffreFormationDetail.typeDetail] Setter 
	 * @author rlaib on : 6 fevr. 2014  12:36:52
	 * @param typeDetail the typeDetail to set
	 */
	public void setTypeDetail(int typeDetail) {
		this.typeDetail = typeDetail;
	}

	/**
	 * [OffreFormationDetail.code] Getter 
	 * @author rlaib on : 6 fevr. 2014  12:36:52
	 * @return the code
	 */
	@Column(name = "code")
	public String getCode() {
		return code;
	}

	/**
	 * [OffreFormationDetail.code] Setter 
	 * @author rlaib on : 6 fevr. 2014  12:36:52
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * [OffreFormationDetail.libelleFr] Getter 
	 * @author rlaib on : 6 fevr. 2014  12:36:52
	 * @return the libelleFr
	 */
	@Column(name = "libelle_fr", length = 100)
	public String getLibelleFr() {
		return libelleFr;
	}

	/**
	 * [OffreFormationDetail.libelleFr] Setter 
	 * @author rlaib on : 6 fevr. 2014  12:36:52
	 * @param libelleFr the libelleFr to set
	 */
	public void setLibelleFr(String libelleFr) {
		this.libelleFr = libelleFr;
	}

	/**
	 * [OffreFormationDetail.libelleAr] Getter 
	 * @author rlaib on : 6 fevr. 2014  12:36:52
	 * @return the libelleAr
	 */
	@Column(name = "libelle_ar", length = 100)
	public String getLibelleAr() {
		return libelleAr;
	}

	/**
	 * [OffreFormationDetail.libelleAr] Setter 
	 * @author rlaib on : 6 fevr. 2014  12:36:52
	 * @param libelleAr the libelleAr to set
	 */
	public void setLibelleAr(String libelleAr) {
		this.libelleAr = libelleAr;
	}

	/**
	 * [OffreFormationDetail.descriptionFr] Getter 
	 * @author rlaib on : 6 fevr. 2014  12:36:52
	 * @return the descriptionFr
	 */
	@Column(name = "description_fr", length = 150)
	public String getDescriptionFr() {
		return descriptionFr;
	}

	/**
	 * [OffreFormationDetail.descriptionFr] Setter 
	 * @author rlaib on : 6 fevr. 2014  12:36:52
	 * @param descriptionFr the descriptionFr to set
	 */
	public void setDescriptionFr(String descriptionFr) {
		this.descriptionFr = descriptionFr;
	}

	/**
	 * [OffreFormationDetail.descriptionAr] Getter 
	 * @author rlaib on : 6 fevr. 2014  12:36:52
	 * @return the descriptionAr
	 */
	@Column(name = "description_ar", length = 150)
	public String getDescriptionAr() {
		return descriptionAr;
	}

	/**
	 * [OffreFormationDetail.descriptionAr] Setter 
	 * @author rlaib on : 6 fevr. 2014  12:36:52
	 * @param descriptionAr the descriptionAr to set
	 */
	public void setDescriptionAr(String descriptionAr) {
		this.descriptionAr = descriptionAr;
	}

	/**
	 * [OffreFormationDetail.offreFormationDetail] Getter 
	 * @author rlaib on : 6 fevr. 2014  12:36:52
	 * @return the offreFormationDetail
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "parent")
	public OffreFormationDetail getOffreFormationDetail() {
		return offreFormationDetail;
	}

	/**
	 * [OffreFormationDetail.offreFormationDetail] Setter 
	 * @author rlaib on : 6 fevr. 2014  12:36:52
	 * @param offreFormationDetail the offreFormationDetail to set
	 */
	public void setOffreFormationDetail(OffreFormationDetail offreFormationDetail) {
		this.offreFormationDetail = offreFormationDetail;
	}
	
	
}
