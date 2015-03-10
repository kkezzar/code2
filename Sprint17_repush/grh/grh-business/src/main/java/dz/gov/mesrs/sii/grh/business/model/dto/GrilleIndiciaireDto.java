package dz.gov.mesrs.sii.grh.business.model.dto;

import java.io.Serializable;

public class GrilleIndiciaireDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8886448895466356021L;
	private Integer id;
	private CategorieProfessionnelleDto categorieProfessionnelleDto;
	private String echlon;
	private Long indice;

	public GrilleIndiciaireDto() {
	}

	public GrilleIndiciaireDto(Integer id) {
		this.id = id;
	}

	public GrilleIndiciaireDto(Integer id, CategorieProfessionnelleDto categorieProfessionnelle, String echlon,
			Long indice) {
		this.id = id;
		this.categorieProfessionnelleDto = categorieProfessionnelle;
		this.echlon = echlon;
		this.indice = indice;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	public CategorieProfessionnelleDto getCategorieProfessionnelleDto() {
		return categorieProfessionnelleDto;
	}

	public void setCategorieProfessionnelleDto(CategorieProfessionnelleDto categorieProfessionnelleDto) {
		this.categorieProfessionnelleDto = categorieProfessionnelleDto;
	}

	/**
	 * @return the echlon
	 */
	public String getEchlon() {
		return echlon;
	}

	/**
	 * @param echlon
	 *            the echlon to set
	 */
	public void setEchlon(String echlon) {
		this.echlon = echlon;
	}

	/**
	 * @return the indice
	 */
	public Long getIndice() {
		return indice;
	}

	/**
	 * @param indice
	 *            the indice to set
	 */
	public void setIndice(Long indice) {
		this.indice = indice;
	}

}
