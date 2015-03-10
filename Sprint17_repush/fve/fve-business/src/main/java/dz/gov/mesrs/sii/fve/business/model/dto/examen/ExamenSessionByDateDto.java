package dz.gov.mesrs.sii.fve.business.model.dto.examen;

import java.util.Date;
import java.util.List;

/**
 * 
 * @author BELDI Jamel on : 17 sept. 2014 16:17:13
 */

public class ExamenSessionByDateDto implements java.io.Serializable {

	/**
	 * serialVersionUID 
	 * @author BELDI Jamel  on : 19 oct. 2014  15:02:46
	 */
	private static final long serialVersionUID = 1L;
	private Date dateExamen;
	private List<ExamenSessionDto> examens;
	
/**
 * 
 * [ExamenSessionByDateDto.ExamenSessionByDateDto()] Constructor
 * @author BELDI Jamel  on : 19 oct. 2014  15:03:09
 */
	public ExamenSessionByDateDto(){
		
	}
	
/**
 * [ExamenSessionByDateDto.dateExamen] Getter 
 * @author BELDI Jamel on : 19 oct. 2014  15:03:18
 * @return the dateExamen
 */
public Date getDateExamen() {
	return dateExamen;
}
/**
 * [ExamenSessionByDateDto.dateExamen] Setter 
 * @author BELDI Jamel on : 19 oct. 2014  15:03:18
 * @param dateExamen the dateExamen to set
 */
public void setDateExamen(Date dateExamen) {
	this.dateExamen = dateExamen;
}
/**
 * [ExamenSessionByDateDto.examens] Getter 
 * @author BELDI Jamel on : 19 oct. 2014  15:03:18
 * @return the examens
 */
public List<ExamenSessionDto> getExamens() {
	return examens;
}
/**
 * [ExamenSessionByDateDto.examens] Setter 
 * @author BELDI Jamel on : 19 oct. 2014  15:03:18
 * @param examens the examens to set
 */
public void setExamens(List<ExamenSessionDto> examens) {
	this.examens = examens;
}



}
