/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.examen.notation.ReleveNoteModel.java] 
 * @author MAKERRI Sofiane on : 28 oct. 2014  10:57:16
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.examen.notation;

import java.io.Serializable;
import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.examen.BilanSessionDto;

/**
 * @author MAKERRI Sofiane  on : 28 oct. 2014  10:57:16
 */
public class ReleveNoteModel implements Serializable {

	/**
	 * serialVersionUID 
	 * @author MAKERRI Sofiane  on : 28 oct. 2014  10:57:21
	 */
	private static final long serialVersionUID = 1L;
	private BilanSessionDto bilanFinal;
	private List<BilanSessionDto> bilanPeriode;

	/**
	 * [ReleveNoteModel.ReleveNoteModel()] Constructor
	 * @author MAKERRI Sofiane  on : 28 oct. 2014  10:57:16	
	 */
	public ReleveNoteModel() {
		super();
	}

	/**
	 * [ReleveNoteModel.bilanFinal] Getter 
	 * @author MAKERRI Sofiane on : 28 oct. 2014  11:03:25
	 * @return the bilanFinal
	 */
	public BilanSessionDto getBilanFinal() {
		return bilanFinal;
	}

	/**
	 * [ReleveNoteModel.bilanFinal] Setter 
	 * @author MAKERRI Sofiane on : 28 oct. 2014  11:03:25
	 * @param bilanFinal the bilanFinal to set
	 */
	public void setBilanFinal(BilanSessionDto bilanFinal) {
		this.bilanFinal = bilanFinal;
	}

	/**
	 * [ReleveNoteModel.bilanPeriode] Getter 
	 * @author MAKERRI Sofiane on : 28 oct. 2014  11:03:25
	 * @return the bilanPeriode
	 */
	public List<BilanSessionDto> getBilanPeriode() {
		return bilanPeriode;
	}

	/**
	 * [ReleveNoteModel.bilanPeriode] Setter 
	 * @author MAKERRI Sofiane on : 28 oct. 2014  11:03:25
	 * @param bilanPeriode the bilanPeriode to set
	 */
	public void setBilanPeriode(List<BilanSessionDto> bilanPeriode) {
		this.bilanPeriode = bilanPeriode;
	}
	
	

}
