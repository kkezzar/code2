/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.etablissement.EtablissementPgiBean.java] 
 * @author MAKERRI Sofiane on : 14 avr. 2014  14:25:09
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.etablissement;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;

/**
 * @author MAKERRI Sofiane on : 14 avr. 2014 14:25:09
 */
@ManagedBean(name = "etablissementPgiBean")
@SessionScoped
public class EtablissementPgiBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author MAKERRI Sofiane on : 14 avr. 2014 14:25:34
	 */
	private static final long serialVersionUID = 1L;
	private RefEtablissementDto refEtablissementDto;
	private Integer id;

	/**
	 * [EtablissementPgiBean.EtablissementPgiBean()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 14 avr. 2014 14:25:10
	 */
	public EtablissementPgiBean() {
		super();
	}

	/**
	 * [EtablissementPgiBean.refEtablissementDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 14 avr. 2014 14:26:18
	 * @return the refEtablissementDto
	 */
	public RefEtablissementDto getRefEtablissementDto() {
		return refEtablissementDto;
	}

	/**
	 * [EtablissementPgiBean.refEtablissementDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 14 avr. 2014 14:26:18
	 * @param refEtablissementDto
	 *            the refEtablissementDto to set
	 */
	public void setRefEtablissementDto(RefEtablissementDto refEtablissementDto) {
		this.refEtablissementDto = refEtablissementDto;
		if (refEtablissementDto != null) {
			setId(refEtablissementDto.getId());
		}
	}

	/**
	 * [EtablissementPgiBean.id] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 juil. 2014 17:50:27
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * [EtablissementPgiBean.id] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 juil. 2014 17:50:27
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

}
