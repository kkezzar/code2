package dz.gov.mesrs.sii.fve.business.model.dto.cursus;





public class ReleveDeNotesDto implements java.io.Serializable {

	/**
	 * serialVersionUID 
	 * @author BELDI Jamel  on : 21 mai 2014  08:54:42
	 */
	private static final long serialVersionUID = 1L;
	private int id;

//	private Set<LigneReleveDeNotesDto> ligneReleveDeNoteses = new HashSet<LigneReleveDeNotesDto>(
//			0);

	public ReleveDeNotesDto() {
	}

	public ReleveDeNotesDto(int id) {
		this.id = id;
	}

	/**
	 * [ReleveDeNotesDto.id] Getter 
	 * @author BELDI Jamel on : 21 mai 2014  08:55:19
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * [ReleveDeNotesDto.id] Setter 
	 * @author BELDI Jamel on : 21 mai 2014  08:55:19
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	

}
