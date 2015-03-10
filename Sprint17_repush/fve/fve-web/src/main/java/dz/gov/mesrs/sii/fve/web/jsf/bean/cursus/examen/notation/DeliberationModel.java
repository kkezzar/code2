/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.examen.notation.DeliberationModel.java] 
 * @author MAKERRI Sofiane on : 23 oct. 2014  08:22:54
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.examen.notation;

import java.io.Serializable;
import java.util.LinkedHashMap;

import dz.gov.mesrs.sii.fve.business.model.dto.examen.BilanSessionDto;

/**
 * @author MAKERRI Sofiane  on : 23 oct. 2014  08:22:54
 */
public class DeliberationModel implements Serializable {

	/**
	 * serialVersionUID 
	 * @author MAKERRI Sofiane  on : 23 oct. 2014  08:22:59
	 */
	private static final long serialVersionUID = 1L;
	private LinkedHashMap<String, String> line;
	private String matricule;
	private String nomPrenom;
	private String mention;	
	private int diaId;
	private BilanSessionDto  bilanSessionDto;

	/**
	 * [DeliberationModel.DeliberationModel()] Constructor
	 * @author MAKERRI Sofiane  on : 23 oct. 2014  08:22:54	
	 */
	public DeliberationModel() {
		super();
	}

	

	/**
	 * [DeliberationModel.line] Getter 
	 * @author MAKERRI Sofiane on : 12 janv. 2015  10:04:35
	 * @return the line
	 */
	public LinkedHashMap<String, String> getLine() {
		return line;
	}



	/**
	 * [DeliberationModel.line] Setter 
	 * @author MAKERRI Sofiane on : 12 janv. 2015  10:04:35
	 * @param line the line to set
	 */
	public void setLine(LinkedHashMap<String, String> line) {
		this.line = line;
	}



	/**
	 * [DeliberationModel.diaId] Getter 
	 * @author MAKERRI Sofiane on : 12 janv. 2015  10:04:35
	 * @return the diaId
	 */
	public int getDiaId() {
		return diaId;
	}



	/**
	 * [DeliberationModel.diaId] Setter 
	 * @author MAKERRI Sofiane on : 12 janv. 2015  10:04:35
	 * @param diaId the diaId to set
	 */
	public void setDiaId(int diaId) {
		this.diaId = diaId;
	}



	/**
	 * [DeliberationModel.matricule] Getter 
	 * @author MAKERRI Sofiane on : 23 oct. 2014  08:25:04
	 * @return the matricule
	 */
	public String getMatricule() {
		return matricule;
	}


	/**
	 * [DeliberationModel.matricule] Setter 
	 * @author MAKERRI Sofiane on : 23 oct. 2014  08:25:04
	 * @param matricule the matricule to set
	 */
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}


	/**
	 * [DeliberationModel.nomPrenom] Getter 
	 * @author MAKERRI Sofiane on : 23 oct. 2014  08:25:04
	 * @return the nomPrenom
	 */
	public String getNomPrenom() {
		return nomPrenom;
	}


	/**
	 * [DeliberationModel.nomPrenom] Setter 
	 * @author MAKERRI Sofiane on : 23 oct. 2014  08:25:04
	 * @param nomPrenom the nomPrenom to set
	 */
	public void setNomPrenom(String nomPrenom) {
		this.nomPrenom = nomPrenom;
	}


	/**
	 * [DeliberationModel.mention] Getter 
	 * @author MAKERRI Sofiane on : 23 oct. 2014  14:00:46
	 * @return the mention
	 */
	public String getMention() {
		return mention;
	}


	/**
	 * [DeliberationModel.mention] Setter 
	 * @author MAKERRI Sofiane on : 23 oct. 2014  14:00:46
	 * @param mention the mention to set
	 */
	public void setMention(String mention) {
		this.mention = mention;
	}



	/**
	 * [DeliberationModel.bilanSessionDto] Getter 
	 * @author MAKERRI Sofiane on : 12 janv. 2015  10:09:19
	 * @return the bilanSessionDto
	 */
	public BilanSessionDto getBilanSessionDto() {
		return bilanSessionDto;
	}



	/**
	 * [DeliberationModel.bilanSessionDto] Setter 
	 * @author MAKERRI Sofiane on : 12 janv. 2015  10:09:19
	 * @param bilanSessionDto the bilanSessionDto to set
	 */
	public void setBilanSessionDto(BilanSessionDto bilanSessionDto) {
		this.bilanSessionDto = bilanSessionDto;
	}
	
	

}
