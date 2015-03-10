package dz.gov.mesrs.sii.fve.business.model.dto.cursus;

import java.util.Date;
import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.examen.BilanSessionDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;

public class DossierEtudiantDto implements java.io.Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author BELDI Jamel on : 21 mai 2014 08:49:54
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;

	private String numeroMatricule;
	private String refCodeEtablissement;
	private Integer refEtablissementId;
	private String etablissementOrigineFr;
	private String etablissementOrigineAr;
	private Date dateCreation;
	private String typeDossier;
	private Integer IdDossier;
	private Integer situationId;

	// private RefIndividu refIndividu;
	private Integer individuId;
	private String nin;
	private String individuNomArabe;
	private String individuNomLatin;
	private String individuPrenomArabe;
	private String individuPrenomLatin;
	private Date individuDateNaissance;

	private RefIndividuDto individu;

	// private DossierBachelierDto dossierBachelier;
	private Integer dossierBachelierId;
	private String dossierBachelierMatricule;
	private String anneeBac;

	// private FormationDto formation;
	// private TitreAccesDto titreAcces;
	// private SituationSportiveDto situationSportive;
	/******* etablissement d'affectation ***************/
	private String refLcEtablissementArabe;
	private String refLcEtablissementLatin;
	private String refLlEtablissementArabe;
	private String refLlEtablissementLatin;
	private String refAncienCodeEtablissement;
	private String wilayaLlAr;
	private String wilayaLlFr;

	private String photo;
	private List<DossierInscriptionAdministrativeDto> inscriptionAdministrativeDtos;
	private List<BilanSessionDto> bilansSessionDtos;

	public DossierEtudiantDto() {
	}

	/**
	 * [DossierEtudiantDto.id] Getter
	 * 
	 * @author BELDI Jamel on : 21 mai 2014 08:50:05
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * [DossierEtudiantDto.id] Setter
	 * 
	 * @author BELDI Jamel on : 21 mai 2014 08:50:05
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * [DossierEtudiantDto.numeroMatricule] Getter
	 * 
	 * @author BELDI Jamel on : 21 mai 2014 08:50:05
	 * @return the numeroMatricule
	 */
	public String getNumeroMatricule() {
		return numeroMatricule;
	}

	/**
	 * [DossierEtudiantDto.numeroMatricule] Setter
	 * 
	 * @author BELDI Jamel on : 21 mai 2014 08:50:05
	 * @param numeroMatricule
	 *            the numeroMatricule to set
	 */
	public void setNumeroMatricule(String numeroMatricule) {
		this.numeroMatricule = numeroMatricule;
	}

	/**
	 * [DossierEtudiantDto.refCodeEtablissement] Getter
	 * 
	 * @author BELDI Jamel on : 21 mai 2014 08:50:05
	 * @return the refCodeEtablissement
	 */
	public String getRefCodeEtablissement() {
		return refCodeEtablissement;
	}

	/**
	 * [DossierEtudiantDto.refCodeEtablissement] Setter
	 * 
	 * @author BELDI Jamel on : 21 mai 2014 08:50:05
	 * @param refCodeEtablissement
	 *            the refCodeEtablissement to set
	 */
	public void setRefCodeEtablissement(String refCodeEtablissement) {
		this.refCodeEtablissement = refCodeEtablissement;
	}

	/**
	 * [DossierEtudiantDto.etablissementOrigineFr] Getter
	 * 
	 * @author BELDI Jamel on : 21 mai 2014 08:50:05
	 * @return the etablissementOrigineFr
	 */
	public String getEtablissementOrigineFr() {
		return etablissementOrigineFr;
	}

	/**
	 * [DossierEtudiantDto.etablissementOrigineFr] Setter
	 * 
	 * @author BELDI Jamel on : 21 mai 2014 08:50:05
	 * @param etablissementOrigineFr
	 *            the etablissementOrigineFr to set
	 */
	public void setEtablissementOrigineFr(String etablissementOrigineFr) {
		this.etablissementOrigineFr = etablissementOrigineFr;
	}

	/**
	 * [DossierEtudiantDto.etablissementOrigineAr] Getter
	 * 
	 * @author BELDI Jamel on : 21 mai 2014 08:50:05
	 * @return the etablissementOrigineAr
	 */
	public String getEtablissementOrigineAr() {
		return etablissementOrigineAr;
	}

	/**
	 * [DossierEtudiantDto.etablissementOrigineAr] Setter
	 * 
	 * @author BELDI Jamel on : 21 mai 2014 08:50:05
	 * @param etablissementOrigineAr
	 *            the etablissementOrigineAr to set
	 */
	public void setEtablissementOrigineAr(String etablissementOrigineAr) {
		this.etablissementOrigineAr = etablissementOrigineAr;
	}

	/**
	 * [DossierEtudiantDto.individuId] Getter
	 * 
	 * @author BELBRIK Oualid on : 22 mai 2014 16:06:38
	 * @return the individuId
	 */
	public Integer getIndividuId() {
		return individuId;
	}

	/**
	 * [DossierEtudiantDto.individuId] Setter
	 * 
	 * @author BELBRIK Oualid on : 22 mai 2014 16:06:38
	 * @param individuId
	 *            the individuId to set
	 */
	public void setIndividuId(Integer individuId) {
		this.individuId = individuId;
	}

	/**
	 * [DossierEtudiantDto.nin] Getter
	 * 
	 * @author BELBRIK Oualid on : 22 mai 2014 16:06:38
	 * @return the nin
	 */
	public String getNin() {
		return nin;
	}

	/**
	 * [DossierEtudiantDto.nin] Setter
	 * 
	 * @author BELBRIK Oualid on : 22 mai 2014 16:06:38
	 * @param nin
	 *            the nin to set
	 */
	public void setNin(String nin) {
		this.nin = nin;
	}

	/**
	 * [DossierEtudiantDto.individuNomArabe] Getter
	 * 
	 * @author BELBRIK Oualid on : 22 mai 2014 16:06:38
	 * @return the individuNomArabe
	 */
	public String getIndividuNomArabe() {
		return individuNomArabe;
	}

	/**
	 * [DossierEtudiantDto.individuNomArabe] Setter
	 * 
	 * @author BELBRIK Oualid on : 22 mai 2014 16:06:38
	 * @param individuNomArabe
	 *            the individuNomArabe to set
	 */
	public void setIndividuNomArabe(String individuNomArabe) {
		this.individuNomArabe = individuNomArabe;
	}

	/**
	 * [DossierEtudiantDto.individuNomLatin] Getter
	 * 
	 * @author BELBRIK Oualid on : 22 mai 2014 16:06:38
	 * @return the individuNomLatin
	 */
	public String getIndividuNomLatin() {
		return individuNomLatin;
	}

	/**
	 * [DossierEtudiantDto.individuNomLatin] Setter
	 * 
	 * @author BELBRIK Oualid on : 22 mai 2014 16:06:38
	 * @param individuNomLatin
	 *            the individuNomLatin to set
	 */
	public void setIndividuNomLatin(String individuNomLatin) {
		this.individuNomLatin = individuNomLatin;
	}

	/**
	 * [DossierEtudiantDto.individuPrenomArabe] Getter
	 * 
	 * @author BELBRIK Oualid on : 22 mai 2014 16:06:38
	 * @return the individuPrenomArabe
	 */
	public String getIndividuPrenomArabe() {
		return individuPrenomArabe;
	}

	/**
	 * [DossierEtudiantDto.individuPrenomArabe] Setter
	 * 
	 * @author BELBRIK Oualid on : 22 mai 2014 16:06:38
	 * @param individuPrenomArabe
	 *            the individuPrenomArabe to set
	 */
	public void setIndividuPrenomArabe(String individuPrenomArabe) {
		this.individuPrenomArabe = individuPrenomArabe;
	}

	/**
	 * [DossierEtudiantDto.individuPrenomLatin] Getter
	 * 
	 * @author BELBRIK Oualid on : 22 mai 2014 16:06:38
	 * @return the individuPrenomLatin
	 */
	public String getIndividuPrenomLatin() {
		return individuPrenomLatin;
	}

	/**
	 * [DossierEtudiantDto.individuPrenomLatin] Setter
	 * 
	 * @author BELBRIK Oualid on : 22 mai 2014 16:06:38
	 * @param individuPrenomLatin
	 *            the individuPrenomLatin to set
	 */
	public void setIndividuPrenomLatin(String individuPrenomLatin) {
		this.individuPrenomLatin = individuPrenomLatin;
	}

	/**
	 * [DossierEtudiantDto.individuDateNaissance] Getter
	 * 
	 * @author BELBRIK Oualid on : 22 mai 2014 16:06:38
	 * @return the individuDateNaissance
	 */
	public Date getIndividuDateNaissance() {
		return individuDateNaissance;
	}

	/**
	 * [DossierEtudiantDto.individuDateNaissance] Setter
	 * 
	 * @author BELBRIK Oualid on : 22 mai 2014 16:06:38
	 * @param individuDateNaissance
	 *            the individuDateNaissance to set
	 */
	public void setIndividuDateNaissance(Date individuDateNaissance) {
		this.individuDateNaissance = individuDateNaissance;
	}

	/**
	 * [DossierEtudiantDto.dossierBachelierMatricule] Getter
	 * 
	 * @author BELBRIK Oualid on : 22 mai 2014 16:06:38
	 * @return the dossierBachelierMatricule
	 */
	public String getDossierBachelierMatricule() {
		return dossierBachelierMatricule;
	}

	/**
	 * [DossierEtudiantDto.dossierBachelierMatricule] Setter
	 * 
	 * @author BELBRIK Oualid on : 22 mai 2014 16:06:38
	 * @param dossierBachelierMatricule
	 *            the dossierBachelierMatricule to set
	 */
	public void setDossierBachelierMatricule(String dossierBachelierMatricule) {
		this.dossierBachelierMatricule = dossierBachelierMatricule;
	}

	/**
	 * [DossierEtudiantDto.dossierBachelierId] Getter
	 * 
	 * @author BELBRIK Oualid on : 26 mai 2014 11:06:30
	 * @return the dossierBachelierId
	 */
	public Integer getDossierBachelierId() {
		return dossierBachelierId;
	}

	/**
	 * [DossierEtudiantDto.dossierBachelierId] Setter
	 * 
	 * @author BELBRIK Oualid on : 26 mai 2014 11:06:30
	 * @param dossierBachelierId
	 *            the dossierBachelierId to set
	 */
	public void setDossierBachelierId(Integer dossierBachelierId) {
		this.dossierBachelierId = dossierBachelierId;
	}

	/**
	 * [DossierEtudiantDto.dateCreation] Getter
	 * 
	 * @author MAKERRI Sofiane on : 26 mai 2014 15:39:08
	 * @return the dateCreation
	 */
	public Date getDateCreation() {
		return dateCreation;
	}

	/**
	 * [DossierEtudiantDto.idDossier] Getter
	 * 
	 * @author rlaib on : 30 oct. 2014 13:51:46
	 * @return the idDossier
	 */
	public Integer getIdDossier() {
		return IdDossier;
	}

	/**
	 * [DossierEtudiantDto.idDossier] Setter
	 * 
	 * @author rlaib on : 30 oct. 2014 13:51:46
	 * @param idDossier
	 *            the idDossier to set
	 */
	public void setIdDossier(Integer idDossier) {
		IdDossier = idDossier;
	}

	/**
	 * [DossierEtudiantDto.dateCreation] Setter
	 * 
	 * @author MAKERRI Sofiane on : 26 mai 2014 15:39:08
	 * @param dateCreation
	 *            the dateCreation to set
	 */
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	/**
	 * [DossierEtudiantDto.typeDossier] Getter
	 * 
	 * @author MAKERRI Sofiane on : 26 mai 2014 16:30:50
	 * @return the typeDossier
	 */
	public String getTypeDossier() {
		return typeDossier;
	}

	/**
	 * [DossierEtudiantDto.typeDossier] Setter
	 * 
	 * @author MAKERRI Sofiane on : 26 mai 2014 16:30:50
	 * @param typeDossier
	 *            the typeDossier to set
	 */
	public void setTypeDossier(String typeDossier) {
		this.typeDossier = typeDossier;
	}

	/**
	 * [DossierEtudiantDto.refLcEtablissementArabe] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 mai 2014 17:09:31
	 * @return the refLcEtablissementArabe
	 */
	public String getRefLcEtablissementArabe() {
		return refLcEtablissementArabe;
	}

	/**
	 * [DossierEtudiantDto.refLcEtablissementArabe] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 mai 2014 17:09:31
	 * @param refLcEtablissementArabe
	 *            the refLcEtablissementArabe to set
	 */
	public void setRefLcEtablissementArabe(String refLcEtablissementArabe) {
		this.refLcEtablissementArabe = refLcEtablissementArabe;
	}

	/**
	 * [DossierEtudiantDto.refLcEtablissementLatin] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 mai 2014 17:09:31
	 * @return the refLcEtablissementLatin
	 */
	public String getRefLcEtablissementLatin() {
		return refLcEtablissementLatin;
	}

	/**
	 * [DossierEtudiantDto.refLcEtablissementLatin] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 mai 2014 17:09:31
	 * @param refLcEtablissementLatin
	 *            the refLcEtablissementLatin to set
	 */
	public void setRefLcEtablissementLatin(String refLcEtablissementLatin) {
		this.refLcEtablissementLatin = refLcEtablissementLatin;
	}

	/**
	 * [DossierEtudiantDto.refLlEtablissementArabe] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 mai 2014 17:09:31
	 * @return the refLlEtablissementArabe
	 */
	public String getRefLlEtablissementArabe() {
		return refLlEtablissementArabe;
	}

	/**
	 * [DossierEtudiantDto.refLlEtablissementArabe] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 mai 2014 17:09:31
	 * @param refLlEtablissementArabe
	 *            the refLlEtablissementArabe to set
	 */
	public void setRefLlEtablissementArabe(String refLlEtablissementArabe) {
		this.refLlEtablissementArabe = refLlEtablissementArabe;
	}

	/**
	 * [DossierEtudiantDto.refLlEtablissementLatin] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 mai 2014 17:09:31
	 * @return the refLlEtablissementLatin
	 */
	public String getRefLlEtablissementLatin() {
		return refLlEtablissementLatin;
	}

	/**
	 * [DossierEtudiantDto.refLlEtablissementLatin] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 mai 2014 17:09:31
	 * @param refLlEtablissementLatin
	 *            the refLlEtablissementLatin to set
	 */
	public void setRefLlEtablissementLatin(String refLlEtablissementLatin) {
		this.refLlEtablissementLatin = refLlEtablissementLatin;
	}

	/**
	 * [DossierEtudiantDto.wilayaLlAr] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 mai 2014 09:52:37
	 * @return the wilayaLlAr
	 */
	public String getWilayaLlAr() {
		return wilayaLlAr;
	}

	/**
	 * [DossierEtudiantDto.wilayaLlAr] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 mai 2014 09:52:37
	 * @param wilayaLlAr
	 *            the wilayaLlAr to set
	 */
	public void setWilayaLlAr(String wilayaLlAr) {
		this.wilayaLlAr = wilayaLlAr;
	}

	/**
	 * [DossierEtudiantDto.wilayaLlFr] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 mai 2014 09:52:37
	 * @return the wilayaLlFr
	 */
	public String getWilayaLlFr() {
		return wilayaLlFr;
	}

	/**
	 * [DossierEtudiantDto.wilayaLlFr] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 mai 2014 09:52:37
	 * @param wilayaLlFr
	 *            the wilayaLlFr to set
	 */
	public void setWilayaLlFr(String wilayaLlFr) {
		this.wilayaLlFr = wilayaLlFr;
	}

	/**
	 * [DossierEtudiantDto.photo] Getter
	 * 
	 * @author yselmane on : 23 juin 2014 16:51:08
	 * @return the photo
	 */
	public String getPhoto() {
		return photo;
	}

	/**
	 * [DossierEtudiantDto.photo] Setter
	 * 
	 * @author yselmane on : 23 juin 2014 16:51:08
	 * @param photo
	 *            the photo to set
	 */
	public void setPhoto(String photo) {
		this.photo = photo;
	}

	/**
	 * [DossierEtudiantDto.anneeBac] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 juin 2014 15:51:03
	 * @return the anneeBac
	 */
	public String getAnneeBac() {
		return anneeBac;
	}

	/**
	 * [DossierEtudiantDto.anneeBac] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 juin 2014 15:51:03
	 * @param anneeBac
	 *            the anneeBac to set
	 */
	public void setAnneeBac(String anneeBac) {
		this.anneeBac = anneeBac;
	}

	/**
	 * [DossierEtudiantDto.refAncienCodeEtablissement] Getter
	 * 
	 * @author MAKERRI Sofiane on : 1 juil. 2014 14:33:37
	 * @return the refAncienCodeEtablissement
	 */
	public String getRefAncienCodeEtablissement() {
		return refAncienCodeEtablissement;
	}

	/**
	 * [DossierEtudiantDto.refAncienCodeEtablissement] Setter
	 * 
	 * @author MAKERRI Sofiane on : 1 juil. 2014 14:33:37
	 * @param refAncienCodeEtablissement
	 *            the refAncienCodeEtablissement to set
	 */
	public void setRefAncienCodeEtablissement(String refAncienCodeEtablissement) {
		this.refAncienCodeEtablissement = refAncienCodeEtablissement;
	}

	/**
	 * [DossierEtudiantDto.situationId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 5 juil. 2014 15:45:36
	 * @return the situationId
	 */
	public Integer getSituationId() {
		return situationId;
	}

	/**
	 * [DossierEtudiantDto.situationId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 5 juil. 2014 15:45:36
	 * @param situationId
	 *            the situationId to set
	 */
	public void setSituationId(Integer situationId) {
		this.situationId = situationId;
	}

	public RefIndividuDto getIndividu() {
		return individu;
	}

	public void setIndividu(RefIndividuDto individu) {
		this.individu = individu;
	}
	
	



	/**
	 * [DossierEtudiantDto.inscriptionAdministrativeDtos] Getter 
	 * @author MAKERRI Sofiane on : 25 dÃ©c. 2014  11:05:53
	 * @return the inscriptionAdministrativeDtos
	 */
	public List<DossierInscriptionAdministrativeDto> getInscriptionAdministrativeDtos() {
		return inscriptionAdministrativeDtos;
	}

	/**
	 * [DossierEtudiantDto.inscriptionAdministrativeDtos] Setter 
	 * @author MAKERRI Sofiane on : 25 dÃ©c. 2014  11:05:53
	 * @param inscriptionAdministrativeDtos the inscriptionAdministrativeDtos to set
	 */
	public void setInscriptionAdministrativeDtos(List<DossierInscriptionAdministrativeDto> inscriptionAdministrativeDtos) {
		this.inscriptionAdministrativeDtos = inscriptionAdministrativeDtos;
	}
	
	
	/**
	 * [DossierEtudiantDto.bilansSessionDtos] Getter 
	 * @author MAKERRI Sofiane on : 30 déc. 2014  10:32:31
	 * @return the bilansSessionDtos
	 */
	public List<BilanSessionDto> getBilansSessionDtos() {
		return bilansSessionDtos;
	}

	/**
	 * [DossierEtudiantDto.bilansSessionDtos] Setter 
	 * @author MAKERRI Sofiane on : 30 déc. 2014  10:32:31
	 * @param bilansSessionDtos the bilansSessionDtos to set
	 */
	public void setBilansSessionDtos(List<BilanSessionDto> bilansSessionDtos) {
		this.bilansSessionDtos = bilansSessionDtos;
	}
	
	

	/**
	 * [DossierEtudiantDto.refEtablissementId] Getter 
	 * @author MAKERRI Sofiane on : 30 déc. 2014  15:26:30
	 * @return the refEtablissementId
	 */
	public Integer getRefEtablissementId() {
		return refEtablissementId;
	}

	/**
	 * [DossierEtudiantDto.refEtablissementId] Setter 
	 * @author MAKERRI Sofiane on : 30 déc. 2014  15:26:30
	 * @param refEtablissementId the refEtablissementId to set
	 */
	public void setRefEtablissementId(Integer refEtablissementId) {
		this.refEtablissementId = refEtablissementId;
	}

	@Override
	public String toString() {
		return "DossierEtudiantDto [id=" + id + ", numeroMatricule="
				+ numeroMatricule + ", refCodeEtablissement="
				+ refCodeEtablissement + ", etablissementOrigineFr="
				+ etablissementOrigineFr + ", etablissementOrigineAr="
				+ etablissementOrigineAr + ", dateCreation=" + dateCreation
				+ ", typeDossier=" + typeDossier + ", situationId="
				+ situationId + ", individuId=" + individuId + ", nin=" + nin
				+ ", individuNomArabe=" + individuNomArabe
				+ ", individuNomLatin=" + individuNomLatin
				+ ", individuPrenomArabe=" + individuPrenomArabe
				+ ", individuPrenomLatin=" + individuPrenomLatin
				+ ", individuDateNaissance=" + individuDateNaissance
				+ ", dossierBachelierId=" + dossierBachelierId
				+ ", dossierBachelierMatricule=" + dossierBachelierMatricule
				+ ", anneeBac=" + anneeBac + ", refLcEtablissementArabe="
				+ refLcEtablissementArabe + ", refLcEtablissementLatin="
				+ refLcEtablissementLatin + ", refLlEtablissementArabe="
				+ refLlEtablissementArabe + ", refLlEtablissementLatin="
				+ refLlEtablissementLatin + ", refAncienCodeEtablissement="
				+ refAncienCodeEtablissement + ", wilayaLlAr=" + wilayaLlAr
				+ ", wilayaLlFr=" + wilayaLlFr + ", photo=" + photo + "]";
	}

}
