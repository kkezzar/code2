package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.dossier.congeacademique;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dozer.DozerBeanMapper;
import org.primefaces.component.selectbooleanbutton.SelectBooleanButton;

import dz.gov.mesrs.sii.fve.business.model.dto.bac.DossierBachelierDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.CongeAcademiqueDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierEtudiantDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierInscriptionAdministrativeDto;
import dz.gov.mesrs.sii.fve.business.service.bac.BacService;
import dz.gov.mesrs.sii.fve.business.service.cursus.CongeAcademiqueService;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierEtudiantService;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierInscriptionAdministrativeService;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierService;
import dz.gov.mesrs.sii.fve.business.service.cursus.PieceConstitutiveService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OffreFormationService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OuvertureOffreFormationService;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.dossier.DossierEtudiantBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.CursusConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.ReferentielHelper;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefTypePieceConstitutiveDto;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;
import dz.gov.mesrs.sii.referentiel.business.service.RefIndividuService;
import dz.gov.mesrs.sii.referentiel.business.service.RefTypePieceConstitutiveService;

@ManagedBean(name = "congeAcademiqueBean")
@RequestScoped
public class CongeAcademiqueBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(CongeAcademiqueBean.class);

	private ResourceBundle congeAcademiqueBundle;

	private CongeAcademiqueDto congeAcademiqueDto;

	private DossierDto dossierDto;

	@ManagedProperty("#{param.dossierEtudiantId}")
	private String dossierEtudiantId;

	@ManagedProperty("#{param.dossierId}")
	private String dossierId;

	@ManagedProperty(value = "#{referentielHelper}")
	private ReferentielHelper referentielHelper;

	@ManagedProperty(value = "#{congeAcademiqueService}")
	private CongeAcademiqueService congeAcademiqueService;

	@ManagedProperty(value = "#{dossierService}")
	private DossierService dossierService;

	@ManagedProperty(value = "#{dossierEtudiantService}")
	private DossierEtudiantService dossierEtudiantService;

	private List<SelectItem> listeTypeConge;

	@ManagedProperty("#{dossierEtudiantBean}")
	private DossierEtudiantBean dossierEtudiantBean;

	@ManagedProperty(value = "#{dossierInscriptionAdministrativeService}")
	private DossierInscriptionAdministrativeService dossierInscriptionAdministrativeService;

	private DossierInscriptionAdministrativeDto dossierInscriptionAdministrativeDto;

	private List<DossierInscriptionAdministrativeDto> listDdossierInscriptionAdministrativeDto;

	private DossierEtudiantDto dossierEtudiantDto;

	@ManagedProperty("#{param.demandeAccordee}")
	private String demandeAccordee;

	private boolean accorde;

	@ManagedProperty("#{mapper}")
	private DozerBeanMapper mapper;

	@ManagedProperty("#{bacService}")
	private BacService bacService;

	@ManagedProperty(value = "#{nomenclatureServiceImpl}")
	private NomenclatureService nomenclatureService;
	
	@ManagedProperty(value = "#{refIndividuServiceImpl}")
	private RefIndividuService refIndividuService;
	
	@ManagedProperty(value = "#{refTypePieceConstitutiveServiceImpl}")
	private RefTypePieceConstitutiveService refTypePieceConstitutiveService;
	
	
	/**
	 * [CongeAcademiqueBean.refTypePieceConstitutiveService] Getter 
	 * @author rlaib on : 20 nov. 2014  15:16:13
	 * @return the refTypePieceConstitutiveService
	 */
	public RefTypePieceConstitutiveService getRefTypePieceConstitutiveService() {
		return refTypePieceConstitutiveService;
	}

	/**
	 * [CongeAcademiqueBean.refTypePieceConstitutiveService] Setter 
	 * @author rlaib on : 20 nov. 2014  15:16:13
	 * @param refTypePieceConstitutiveService the refTypePieceConstitutiveService to set
	 */
	public void setRefTypePieceConstitutiveService(
			RefTypePieceConstitutiveService refTypePieceConstitutiveService) {
		this.refTypePieceConstitutiveService = refTypePieceConstitutiveService;
	}

	/**
	 * [CongeAcademiqueBean.nomenclatureService] Getter 
	 * @author rlaib on : 20 nov. 2014  15:14:50
	 * @return the nomenclatureService
	 */
	public NomenclatureService getNomenclatureService() {
		return nomenclatureService;
	}

	/**
	 * [CongeAcademiqueBean.nomenclatureService] Setter 
	 * @author rlaib on : 20 nov. 2014  15:14:50
	 * @param nomenclatureService the nomenclatureService to set
	 */
	public void setNomenclatureService(NomenclatureService nomenclatureService) {
		this.nomenclatureService = nomenclatureService;
	}

	/**
	 * [CongeAcademiqueBean.refIndividuService] Getter 
	 * @author rlaib on : 20 nov. 2014  15:14:50
	 * @return the refIndividuService
	 */
	public RefIndividuService getRefIndividuService() {
		return refIndividuService;
	}

	/**
	 * [CongeAcademiqueBean.refIndividuService] Setter 
	 * @author rlaib on : 20 nov. 2014  15:14:50
	 * @param refIndividuService the refIndividuService to set
	 */
	public void setRefIndividuService(RefIndividuService refIndividuService) {
		this.refIndividuService = refIndividuService;
	}

	@ManagedProperty("#{ouvertureOffreFormationService}")
	private OuvertureOffreFormationService ouvertureOffreFormationService;

	@ManagedProperty("#{offreFormationService}")
	private OffreFormationService offreFormationService;

	@ManagedProperty(value = "#{pieceConstitutiveService}")
	private PieceConstitutiveService pieceConstitutiveService;

	public SessionBean getSessionBean() {
		return sessionBean;
	}

	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	private List<DossierInscriptionAdministrativeDto> listeDossierInscriptionAdministrativeDto;

	@ManagedProperty("#{sessionBeanFve}")
	private SessionBean sessionBean;

	public CongeAcademiqueBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		congeAcademiqueBundle = facesContext.getApplication()
				.getResourceBundle(facesContext,
						CursusConstants.CONGE_ACADEMIQUE_BUNDLE_MSG_NAME);

	}

	@PostConstruct
	public void init() {

		loadParams();

	}

	private void loadParams() {

		try {

			if (dossierEtudiantId != null && !dossierEtudiantId.equals("null")
					&& !dossierEtudiantId.isEmpty()) {

				dossierInscriptionAdministrativeDto = dossierInscriptionAdministrativeService
						.findPresentDossierInscriptionForEtudiant(Integer
								.parseInt(dossierEtudiantId));

				listeDossierInscriptionAdministrativeDto = dossierInscriptionAdministrativeService
						.findDossierInscriptionsBy(Integer
								.parseInt(dossierEtudiantId));

				dossierEtudiantDto = dossierEtudiantService.findById(Integer
						.parseInt(dossierEtudiantId));

				if (dossierInscriptionAdministrativeDto != null) {

					congeAcademiqueDto = congeAcademiqueService
							.findByIdDossier(Integer
									.parseInt(dossierEtudiantId));

					if (congeAcademiqueDto != null) {

						dossierDto = dossierService.findById(congeAcademiqueDto
								.getId());

						congeAcademiqueDto.setDateCreation(dossierDto
								.getDateCreation());

						loadLibellesNomenclature();

						if (demandeAccordee == null
								|| demandeAccordee.equals("null")) {
							accorde = congeAcademiqueDto.getResultat();
						}

						congeAcademiqueDto.setIdDossierEtudiant(Integer
								.parseInt(dossierEtudiantId));

					}

				} else {
					dossierDto = new DossierDto();
					dossierInscriptionAdministrativeDto = new DossierInscriptionAdministrativeDto();
					System.err
							.println("pas de dossier inscription................");
				}

			} else {
				dossierDto = new DossierDto();
			}

			if (congeAcademiqueDto == null) {
				congeAcademiqueDto = new CongeAcademiqueDto();
			}
			if (dossierEtudiantDto == null) {
				dossierEtudiantDto = new DossierEtudiantDto();
			}

			congeAcademiqueDto.setTypeDossier(this.getCodeTypeDossier());

			// charger infos individu et bac
			loadIndividuInfos();

		} catch (Exception e) {

		}

	}

	/**
	 * 
	 * [CongeAcademiqueBean.loadLibelleNomenclature] Method
	 * 
	 * @author yselmane on : 28 mai 2014 17:22:51
	 */
	private void loadLibellesNomenclature() {

		try {
			NomenclatureDto typeconge = nomenclatureService.findByCode(
					CursusConstants.NC_TYPE_CONGE_ACADEMIQUE,
					congeAcademiqueDto.getRefCodeTypeConge());

			congeAcademiqueDto.setTypeCongeLibelleLongFr(typeconge
					.getLibelleLongFr());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * charger les infos de l'inscription actuelle, etduiant et bac
	 * [CongeAcademiqueBean.loadByDossierEtudiantId] Method
	 * 
	 * @author yselmane on : 17 juin 2014 10:40:07
	 * @throws Exception
	 */
	private void loadIndividuInfos() throws Exception {
		if (dossierEtudiantDto.getId() != null) {

			// mapper.map(dossierEtudiantDto, congeAcademiqueDto);

			RefIndividuDto individuDto = refIndividuService
					.findByIdentifiant(dossierEtudiantDto.getNin());

			// IndividuDto individuDto = map(refIndividuDto);

			mapper.map(individuDto, congeAcademiqueDto);

			if (dossierEtudiantDto.getDossierBachelierId() != null) {
				DossierBachelierDto dossierBachelierDto = bacService
						.findBachelorFileById(dossierEtudiantDto
								.getDossierBachelierId().intValue());
				mapper.map(dossierBachelierDto, congeAcademiqueDto);
			}

			congeAcademiqueDto.setIdDossierEtudiant(Integer
					.parseInt(dossierEtudiantId));
		}
	}

	/**
	 * [CongeAcademiqueBean.listeDossierInscriptionAdministrativeDto] Getter
	 * 
	 * @author yselmane on : 17 juin 2014 10:39:02
	 * @return the listeDossierInscriptionAdministrativeDto
	 */
	public List<DossierInscriptionAdministrativeDto> getListeDossierInscriptionAdministrativeDto() {
		return listeDossierInscriptionAdministrativeDto;
	}

	/**
	 * [CongeAcademiqueBean.listeDossierInscriptionAdministrativeDto] Setter
	 * 
	 * @author yselmane on : 17 juin 2014 10:39:02
	 * @param listeDossierInscriptionAdministrativeDto
	 *            the listeDossierInscriptionAdministrativeDto to set
	 */
	public void setListeDossierInscriptionAdministrativeDto(
			List<DossierInscriptionAdministrativeDto> listeDossierInscriptionAdministrativeDto) {
		this.listeDossierInscriptionAdministrativeDto = listeDossierInscriptionAdministrativeDto;
	}

	/**
	 * [CongeAcademiqueBean.congeAcademiqueDto] Getter
	 * 
	 * @author yselmane on : 8 juin 2014 15:42:18
	 * @return the congeAcademiqueDto
	 */
	public CongeAcademiqueDto getCongeAcademiqueDto() {
		return congeAcademiqueDto;
	}

	/**
	 * [CongeAcademiqueBean.congeAcademiqueDto] Setter
	 * 
	 * @author yselmane on : 8 juin 2014 15:42:18
	 * @param congeAcademiqueDto
	 *            the congeAcademiqueDto to set
	 */
	public void setCongeAcademiqueDto(CongeAcademiqueDto congeAcademiqueDto) {
		this.congeAcademiqueDto = congeAcademiqueDto;
	}

	/**
	 * [CongeAcademiqueBean.dossierDto] Getter
	 * 
	 * @author yselmane on : 8 juin 2014 17:03:34
	 * @return the dossierDto
	 */
	public DossierDto getDossierDto() {
		return dossierDto;
	}

	/**
	 * [CongeAcademiqueBean.dossierDto] Setter
	 * 
	 * @author yselmane on : 8 juin 2014 17:03:34
	 * @param dossierDto
	 *            the dossierDto to set
	 */
	public void setDossierDto(DossierDto dossierDto) {
		this.dossierDto = dossierDto;
	}

	/**
	 * [CongeAcademiqueBean.dossierEtudiantDto] Getter
	 * 
	 * @author yselmane on : 11 juin 2014 13:52:52
	 * @return the dossierEtudiantDto
	 */
	public DossierEtudiantDto getDossierEtudiantDto() {
		return dossierEtudiantDto;
	}

	/**
	 * [CongeAcademiqueBean.dossierEtudiantDto] Setter
	 * 
	 * @author yselmane on : 11 juin 2014 13:52:52
	 * @param dossierEtudiantDto
	 *            the dossierEtudiantDto to set
	 */
	public void setDossierEtudiantDto(DossierEtudiantDto dossierEtudiantDto) {
		this.dossierEtudiantDto = dossierEtudiantDto;
	}

	/**
	 * [CongeAcademiqueBean.congeAcademiqueService] Getter
	 * 
	 * @author yselmane on : 8 juin 2014 15:42:18
	 * @return the congeAcademiqueService
	 */
	public CongeAcademiqueService getCongeAcademiqueService() {
		return congeAcademiqueService;
	}

	/**
	 * [CongeAcademiqueBean.congeAcademiqueService] Setter
	 * 
	 * @author yselmane on : 8 juin 2014 15:42:18
	 * @param congeAcademiqueService
	 *            the congeAcademiqueService to set
	 */
	public void setCongeAcademiqueService(
			CongeAcademiqueService congeAcademiqueService) {
		this.congeAcademiqueService = congeAcademiqueService;
	}

	/**
	 * [CongeAcademiqueBean.dossierService] Getter
	 * 
	 * @author yselmane on : 9 juin 2014 10:35:56
	 * @return the dossierService
	 */
	public DossierService getDossierService() {
		return dossierService;
	}

	/**
	 * [CongeAcademiqueBean.dossierService] Setter
	 * 
	 * @author yselmane on : 9 juin 2014 10:35:56
	 * @param dossierService
	 *            the dossierService to set
	 */
	public void setDossierService(DossierService dossierService) {
		this.dossierService = dossierService;
	}

	/**
	 * [CongeAcademiqueBean.dossierEtudiantService] Getter
	 * 
	 * @author yselmane on : 11 juin 2014 13:55:54
	 * @return the dossierEtudiantService
	 */
	public DossierEtudiantService getDossierEtudiantService() {
		return dossierEtudiantService;
	}

	/**
	 * [CongeAcademiqueBean.dossierEtudiantService] Setter
	 * 
	 * @author yselmane on : 11 juin 2014 13:55:54
	 * @param dossierEtudiantService
	 *            the dossierEtudiantService to set
	 */
	public void setDossierEtudiantService(
			DossierEtudiantService dossierEtudiantService) {
		this.dossierEtudiantService = dossierEtudiantService;
	}

	/**
	 * [CongeAcademiqueBean.mapper] Getter
	 * 
	 * @author yselmane on : 9 juin 2014 10:37:03
	 * @return the mapper
	 */
	public DozerBeanMapper getMapper() {
		return mapper;
	}

	/**
	 * [CongeAcademiqueBean.mapper] Setter
	 * 
	 * @author yselmane on : 9 juin 2014 10:37:03
	 * @param mapper
	 *            the mapper to set
	 */
	public void setMapper(DozerBeanMapper mapper) {
		this.mapper = mapper;
	}

	/**
	 * [CongeAcademiqueBean.listeTypeConge] Getter
	 * 
	 * @author yselmane on : 8 juin 2014 15:42:18
	 * @return the listeTypeConge
	 */
	public List<SelectItem> getListeTypeConge() {
		if (listeTypeConge == null || listeTypeConge.isEmpty()) {
			listeTypeConge = new ArrayList<SelectItem>();
			listeTypeConge = referentielHelper
					.getNomenclatureList(CursusConstants.NC_TYPE_CONGE_ACADEMIQUE);
		}
		return listeTypeConge;
	}

	/**
	 * [CongeAcademiqueBean.listeTypeConge] Setter
	 * 
	 * @author yselmane on : 8 juin 2014 15:42:18
	 * @param listeTypeConge
	 *            the listeTypeConge to set
	 */
	public void setListeTypeConge(List<SelectItem> listeTypeConge) {
		this.listeTypeConge = listeTypeConge;
	}

	/**
	 * [CongeAcademiqueBean.idDossierEtudiant] Getter
	 * 
	 * @author yselmane on : 27 mai 2014 09:39:48
	 * @return the idDossierEtudiant
	 */
	public String getDossierEtudiantId() {
		return dossierEtudiantId;
	}

	/**
	 * [CongeAcademiqueBean.idDossierEtudiant] Setter
	 * 
	 * @author yselmane on : 27 mai 2014 09:39:48
	 * @param idDossierEtudiant
	 *            the idDossierEtudiant to set
	 */
	public void setDossierEtudiantId(String dossierEtudiantId) {
		this.dossierEtudiantId = dossierEtudiantId;
	}

	/**
	 * [CongeAcademiqueBean.dossierId] Getter
	 * 
	 * @author yselmane on : 9 juil. 2014 11:00:53
	 * @return the dossierId
	 */
	public String getDossierId() {
		return dossierId;
	}

	/**
	 * [CongeAcademiqueBean.dossierId] Setter
	 * 
	 * @author yselmane on : 9 juil. 2014 11:00:53
	 * @param dossierId
	 *            the dossierId to set
	 */
	public void setDossierId(String dossierId) {

		if (dossierId != null
				&& (dossierId.equals("null") || dossierId.equals("0"))) {
			dossierId = null;
		} else
			this.dossierId = dossierId;
	}

	/**
	 * [CongeAcademiqueBean.dossierInscriptionAdministrativeService] Getter
	 * 
	 * @author yselmane on : 9 juin 2014 16:05:54
	 * @return the dossierInscriptionAdministrativeService
	 */
	public DossierInscriptionAdministrativeService getDossierInscriptionAdministrativeService() {
		return dossierInscriptionAdministrativeService;
	}

	/**
	 * [CongeAcademiqueBean.dossierInscriptionAdministrativeService] Setter
	 * 
	 * @author yselmane on : 9 juin 2014 16:05:54
	 * @param dossierInscriptionAdministrativeService
	 *            the dossierInscriptionAdministrativeService to set
	 */
	public void setDossierInscriptionAdministrativeService(
			DossierInscriptionAdministrativeService dossierInscriptionAdministrativeService) {
		this.dossierInscriptionAdministrativeService = dossierInscriptionAdministrativeService;
	}

	/**
	 * [CongeAcademiqueBean.dossierInscriptionAdministrativeDto] Getter
	 * 
	 * @author yselmane on : 9 juin 2014 16:05:54
	 * @return the dossierInscriptionAdministrativeDto
	 */
	public DossierInscriptionAdministrativeDto getDossierInscriptionAdministrativeDto() {
		return dossierInscriptionAdministrativeDto;
	}

	/**
	 * [CongeAcademiqueBean.dossierInscriptionAdministrativeDto] Setter
	 * 
	 * @author yselmane on : 9 juin 2014 16:05:54
	 * @param dossierInscriptionAdministrativeDto
	 *            the dossierInscriptionAdministrativeDto to set
	 */
	public void setDossierInscriptionAdministrativeDto(
			DossierInscriptionAdministrativeDto dossierInscriptionAdministrativeDto) {
		this.dossierInscriptionAdministrativeDto = dossierInscriptionAdministrativeDto;
	}

	/**
	 * [CongeAcademiqueBean.listDdossierInscriptionAdministrativeDto] Getter
	 * 
	 * @author yselmane on : 11 juin 2014 09:58:26
	 * @return the listDdossierInscriptionAdministrativeDto
	 */
	public List<DossierInscriptionAdministrativeDto> getListDdossierInscriptionAdministrativeDto() {
		return listDdossierInscriptionAdministrativeDto;
	}

	/**
	 * [CongeAcademiqueBean.listDdossierInscriptionAdministrativeDto] Setter
	 * 
	 * @author yselmane on : 11 juin 2014 09:58:26
	 * @param listDdossierInscriptionAdministrativeDto
	 *            the listDdossierInscriptionAdministrativeDto to set
	 */
	public void setListDdossierInscriptionAdministrativeDto(
			List<DossierInscriptionAdministrativeDto> listDdossierInscriptionAdministrativeDto) {
		this.listDdossierInscriptionAdministrativeDto = listDdossierInscriptionAdministrativeDto;
	}

	/**
	 * [CongeAcademiqueBean.ouvertureOffreFormationService] Getter
	 * 
	 * @author yselmane on : 16 juin 2014 18:09:49
	 * @return the ouvertureOffreFormationService
	 */
	public OuvertureOffreFormationService getOuvertureOffreFormationService() {
		return ouvertureOffreFormationService;
	}

	/**
	 * [CongeAcademiqueBean.ouvertureOffreFormationService] Setter
	 * 
	 * @author yselmane on : 16 juin 2014 18:09:49
	 * @param ouvertureOffreFormationService
	 *            the ouvertureOffreFormationService to set
	 */
	public void setOuvertureOffreFormationService(
			OuvertureOffreFormationService ouvertureOffreFormationService) {
		this.ouvertureOffreFormationService = ouvertureOffreFormationService;
	}

	/**
	 * [CongeAcademiqueBean.offreFormationService] Getter
	 * 
	 * @author yselmane on : 16 juin 2014 18:09:49
	 * @return the offreFormationService
	 */
	public OffreFormationService getOffreFormationService() {
		return offreFormationService;
	}

	/**
	 * [CongeAcademiqueBean.offreFormationService] Setter
	 * 
	 * @author yselmane on : 16 juin 2014 18:09:49
	 * @param offreFormationService
	 *            the offreFormationService to set
	 */
	public void setOffreFormationService(
			OffreFormationService offreFormationService) {
		this.offreFormationService = offreFormationService;
	}

	/**
	 * [CongeAcademiqueBean.bacService] Getter
	 * 
	 * @author yselmane on : 16 juin 2014 18:07:28
	 * @return the bacService
	 */
	public BacService getBacService() {
		return bacService;
	}

	/**
	 * [CongeAcademiqueBean.bacService] Setter
	 * 
	 * @author yselmane on : 16 juin 2014 18:07:28
	 * @param bacService
	 *            the bacService to set
	 */
	public void setBacService(BacService bacService) {
		this.bacService = bacService;
	}

	/**
	 * [CongeAcademiqueBean.demandeAccordee] Getter
	 * 
	 * @author yselmane on : 15 juin 2014 10:52:41
	 * @return the demandeAccordee
	 */
	public String getDemandeAccordee() {
		return demandeAccordee;
	}

	/**
	 * [CongeAcademiqueBean.demandeAccordee] Setter
	 * 
	 * @author yselmane on : 15 juin 2014 10:52:41
	 * @param demandeAccordee
	 *            the demandeAccordee to set
	 */
	public void setDemandeAccordee(String demandeAccordee) {
		this.demandeAccordee = demandeAccordee;
		setAccorde(Boolean.parseBoolean(demandeAccordee));
	}

	/**
	 * [CongeAcademiqueBean.pieceConstitutiveService] Getter
	 * 
	 * @author yselmane on : 9 juil. 2014 13:42:52
	 * @return the pieceConstitutiveService
	 */
	public PieceConstitutiveService getPieceConstitutiveService() {
		return pieceConstitutiveService;
	}

	/**
	 * [CongeAcademiqueBean.pieceConstitutiveService] Setter
	 * 
	 * @author yselmane on : 9 juil. 2014 13:42:52
	 * @param pieceConstitutiveService
	 *            the pieceConstitutiveService to set
	 */
	public void setPieceConstitutiveService(
			PieceConstitutiveService pieceConstitutiveService) {
		this.pieceConstitutiveService = pieceConstitutiveService;
	}

	/**
	 * [CongeAcademiqueBean.accorde] Getter
	 * 
	 * @author yselmane on : 16 juin 2014 12:38:07
	 * @return the accorde
	 */
	public boolean isAccorde() {
		return accorde;
	}

	/**
	 * [CongeAcademiqueBean.accorde] Setter
	 * 
	 * @author yselmane on : 16 juin 2014 12:38:07
	 * @param accorde
	 *            the accorde to set
	 */
	public void setAccorde(boolean accorde) {
		this.accorde = accorde;
	}

	public boolean validatePiecesConge() {
		FacesMessage msg = new FacesMessage();

		if (congeAcademiqueDto != null) {
			try {
				List<RefTypePieceConstitutiveDto> list = refTypePieceConstitutiveService
						.findByCodeTypeDossierDate(
								CursusConstants.CODE_TYPE_DOSSIER_CONGE_ACADEMIQUE,
								new Date());
				for (RefTypePieceConstitutiveDto current : list) {
					if (current.getAFournir() && current.getObligatoire()) {
						boolean valid = pieceConstitutiveService
								.isInRequiredValid(current.getIdTypePiece(),
										congeAcademiqueDto.getId());
						if (!valid) {
							msg.setSeverity(FacesMessage.SEVERITY_ERROR);
							msg.setSummary(congeAcademiqueBundle
									.getString("conge_academique_error_piece_obligatoire_non_valide"));
							FacesContext.getCurrentInstance().addMessage(null,
									msg);
							return false;
						}
					}
				}

			} catch (Exception e) {
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(congeAcademiqueBundle
						.getString("conge_academique_error_obligatoire_piece"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return false;
			}

		}
		return true;
	}

	/**
	 * lsitner de selection de la demande conge academique
	 * [CongeAcademiqueBean.selectResultatDemande] Method
	 * 
	 * @author yselmane on : 15 juin 2014 17:52:24
	 * @param event
	 */
	public void selectResultatDemande(AjaxBehaviorEvent event) {
		SelectBooleanButton booleanButton = (SelectBooleanButton) event
				.getComponent();
		boolean checked = (Boolean) booleanButton.getValue();

		setDemandeAccordee(checked + "");

		if (checked) {
			congeAcademiqueDto.setResultat(true);
			// congeAcademiqueDto.setMotifRefus("");
		} else {
			congeAcademiqueDto.setResultat(false);
			// congeAcademiqueDto.setDateDebutAccordee(null);
			// congeAcademiqueDto.setDateFinAccordee(null);
		}
	}

	/**
	 * Save or update titre acces Dto [CongeAcademiqueBean.saveConge] Method
	 * 
	 * @author yselmane on : 26 mai 2014 10:53:43
	 */
	public void saveDemandeConge() {

		FacesMessage msg = new FacesMessage();

		try {

			if (dossierEtudiantId == null || dossierEtudiantId.equals("null")
					|| dossierEtudiantId.isEmpty()) {
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(congeAcademiqueBundle
						.getString("conge_academique_error_persistence_titre"));
				FacesContext.getCurrentInstance().addMessage(null, msg);

				return;
			}

			// la date de demande doit etree superieure a la derniere date
			// d'inscription
			if (dossierInscriptionAdministrativeDto.getId() > 0
					&& congeAcademiqueDto.getDateDemande().before(
							dossierInscriptionAdministrativeDto
									.getDateCreation())) {
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(congeAcademiqueBundle
						.getString("conge_academique_error_date_demande_after_date_inscription"));
				FacesContext.getCurrentInstance().addMessage(null, msg);

				return;
			}

			// la date debut conge doit etre superieure a la date de demande
			if (congeAcademiqueDto.getDateDemande().after(
					congeAcademiqueDto.getDateDebutDemande())) {
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(congeAcademiqueBundle
						.getString("conge_academique_error_date_demande_after_date_conge"));
				FacesContext.getCurrentInstance().addMessage(null, msg);

				return;
			}

			// la date de fin de conge doit etre superieure a la date debut
			if (congeAcademiqueDto.getDateDebutDemande().after(
					congeAcademiqueDto.getDateFinDemande())) {
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(congeAcademiqueBundle
						.getString("conge_academique_error_date_debut_after_date_fin"));
				FacesContext.getCurrentInstance().addMessage(null, msg);

				return;
			}

			if (congeAcademiqueDto.getId() != 0) {

				log.info(" ---------- update conge academique ----");

				// update conge academique
				congeAcademiqueDto.setIdDossierEtudiant(Integer
						.parseInt(dossierEtudiantId));
				congeAcademiqueDto = congeAcademiqueService
						.insertOrUpdate(congeAcademiqueDto);

				loadIndividuInfos();

				setDossierId(congeAcademiqueDto.getId() + "");

				// message succes update conge academique
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(congeAcademiqueBundle
						.getString("conge_academique_success_demande_updated"));

				FacesContext.getCurrentInstance().addMessage(null, msg);

			}

			else {
				log.info(" ---------- save conge academique  ------ ");

				congeAcademiqueDto.setIdDossierEtudiant(Integer
						.parseInt(dossierEtudiantId));

				congeAcademiqueDto
						.setDossierInscriptionId(dossierInscriptionAdministrativeDto
								.getId());
				congeAcademiqueDto
						.setAnneeAcademiqueId(dossierInscriptionAdministrativeDto
								.getAnneeAcademiqueId());

				// save conge academique
				congeAcademiqueDto = congeAcademiqueService
						.insertOrUpdate(congeAcademiqueDto);

				loadIndividuInfos();

				setDossierId(congeAcademiqueDto.getId() + "");

				// message succes enregistrement nouveau conge academique
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(congeAcademiqueBundle
						.getString("conge_academique_success_demande_saved"));
				FacesContext.getCurrentInstance().addMessage(null, msg);

			}

		} catch (Exception e) {

			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(congeAcademiqueBundle
					.getString("conge_academique_error_persistence_titre"));
			FacesContext.getCurrentInstance().addMessage(null, msg);

			log.info(e.getMessage());
		}
	}

	/**
	 * valider la demande de cong� [CongeAcademiqueBean.validerDemandeConge]
	 * Method
	 * 
	 * @author yselmane on : 15 juin 2014 10:09:54
	 */
	public void validerDemandeConge() {
		FacesMessage msg = new FacesMessage();

		try {

			if (dossierEtudiantId == null || dossierEtudiantId.equals("null")
					|| dossierEtudiantId.isEmpty()) {
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(congeAcademiqueBundle
						.getString("conge_academique_error_persistence_titre"));
				FacesContext.getCurrentInstance().addMessage(null, msg);

				return;
			}

			// la date de demande doit etree superieure a la derniere date
			// d'inscription
			if (dossierInscriptionAdministrativeDto.getId() > 0
					&& congeAcademiqueDto.getDateDemande().before(
							dossierInscriptionAdministrativeDto
									.getDateCreation())) {
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(congeAcademiqueBundle
						.getString("conge_academique_error_date_demande_after_date_inscription"));
				FacesContext.getCurrentInstance().addMessage(null, msg);

				return;
			}

			// la date debut conge doit etre superieure a la date de demande
			if (congeAcademiqueDto.getDateDemande().after(
					congeAcademiqueDto.getDateDebutDemande())) {
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(congeAcademiqueBundle
						.getString("conge_academique_error_date_demande_after_date_conge"));
				FacesContext.getCurrentInstance().addMessage(null, msg);

				return;
			}

			// la date de fin de conge doit etre superieure a la date debut
			if (congeAcademiqueDto.getDateDebutDemande().after(
					congeAcademiqueDto.getDateFinDemande())) {
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(congeAcademiqueBundle
						.getString("conge_academique_error_date_debut_conge_after_date_fin"));
				FacesContext.getCurrentInstance().addMessage(null, msg);

				return;
			}

			boolean verif = validatePiecesConge();

			if (!verif) {
				return;
			}

			congeAcademiqueDto.setIdDossierEtudiant(Integer
					.parseInt(dossierEtudiantId));

			congeAcademiqueDto.setDemandeValidee(true);

			congeAcademiqueDto.setDateValidationDemande(new Date());

			congeAcademiqueDto = congeAcademiqueService
					.insertOrUpdate(congeAcademiqueDto);

			loadLibellesNomenclature();

			// recharger infos individu
			loadIndividuInfos();

			// message succes update conge academique
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			msg.setSummary(congeAcademiqueBundle
					.getString("conge_academique_success_demande_validated"));

			FacesContext.getCurrentInstance().addMessage(null, msg);

		} catch (Exception e) {

			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(congeAcademiqueBundle
					.getString("conge_academique_error_persistence_titre"));
			FacesContext.getCurrentInstance().addMessage(null, msg);

			log.info(e.getMessage());
		}

	}

	/**
	 * sauvegarder le resultat de la demande du conge academique
	 * [CongeAcademiqueBean.saveResultat] Method
	 * 
	 * @author yselmane on : 10 juin 2014 16:27:48
	 */
	public void saveResultat() {

		FacesMessage msg = new FacesMessage();
		// la date debut conge doit etre superieure a la date de demande
		if (congeAcademiqueDto.getDateDemande().after(
				congeAcademiqueDto.getDateResultat())) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(congeAcademiqueBundle
					.getString("conge_academique_error_date_demande_after_date_resultat"));
			FacesContext.getCurrentInstance().addMessage(null, msg);

			return;
		}

		if (congeAcademiqueDto.getResultat()) {

			if (congeAcademiqueDto.getDateDebutAccordee() == null
					|| congeAcademiqueDto.getDateFinAccordee() == null) {
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(congeAcademiqueBundle
						.getString("conge_academique_error_dates_obligatoires_required"));
				FacesContext.getCurrentInstance().addMessage(null, msg);

				return;
			}

			if (congeAcademiqueDto.getDateDebutAccordee().after(
					congeAcademiqueDto.getDateFinAccordee())) {
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(congeAcademiqueBundle
						.getString("conge_academique_error_date_debut_accordee_after_date_fin"));
				FacesContext.getCurrentInstance().addMessage(null, msg);

				return;
			}

			congeAcademiqueDto.setMotifRefus(null);

		} else {

			if (congeAcademiqueDto.getMotifRefus() == null) {
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(congeAcademiqueBundle
						.getString("conge_academique_error_motif_required"));
				FacesContext.getCurrentInstance().addMessage(null, msg);

				return;
			}

			congeAcademiqueDto.setDateDebutAccordee(null);
			congeAcademiqueDto.setDateFinAccordee(null);
		}

		try {

			congeAcademiqueDto.setIdDossierEtudiant(Integer
					.parseInt(dossierEtudiantId));

			congeAcademiqueDto = congeAcademiqueService
					.insertOrUpdate(congeAcademiqueDto);

			// message succes update conge academique
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			msg.setSummary(congeAcademiqueBundle
					.getString("conge_academique_success_resultat_demande_saved"));
			FacesContext.getCurrentInstance().addMessage(null, msg);

		} catch (Exception e) {

			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(congeAcademiqueBundle
					.getString("conge_academique_error_persistence_titre"));
			FacesContext.getCurrentInstance().addMessage(null, msg);

			log.info(e.getMessage());
		}

	}

	/**
	 * valider le resultat de la demande de conge
	 * [CongeAcademiqueBean.validerResultat] Method
	 * 
	 * @author yselmane on : 16 juin 2014 17:14:21
	 */
	public void validerResultat() {

		FacesMessage msg = new FacesMessage();
		// la date debut conge doit etre superieure a la date de demande
		if (congeAcademiqueDto.getDateDemande().after(
				congeAcademiqueDto.getDateResultat())) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(congeAcademiqueBundle
					.getString("conge_academique_error_date_demande_after_date_resultat"));
			FacesContext.getCurrentInstance().addMessage(null, msg);

			return;
		}

		if (congeAcademiqueDto.getResultat()) {

			if (congeAcademiqueDto.getDateDebutAccordee() == null
					|| congeAcademiqueDto.getDateFinAccordee() == null) {
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(congeAcademiqueBundle
						.getString("conge_academique_error_dates_obligatoires_required"));
				FacesContext.getCurrentInstance().addMessage(null, msg);

				return;
			}

			if (congeAcademiqueDto.getDateDebutAccordee().after(
					congeAcademiqueDto.getDateFinAccordee())) {
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(congeAcademiqueBundle
						.getString("conge_academique_error_date_debut_accordee_after_date_fin"));
				FacesContext.getCurrentInstance().addMessage(null, msg);

				return;
			}

			congeAcademiqueDto.setMotifRefus(null);

		} else {

			if (congeAcademiqueDto.getMotifRefus() == null) {
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(congeAcademiqueBundle
						.getString("conge_academique_error_motif_required"));
				FacesContext.getCurrentInstance().addMessage(null, msg);

				return;
			}

			congeAcademiqueDto.setDateDebutAccordee(null);
			congeAcademiqueDto.setDateFinAccordee(null);
		}

		try {

			congeAcademiqueDto.setIdDossierEtudiant(Integer
					.parseInt(dossierEtudiantId));

			congeAcademiqueDto.setResultatValide(true);

			congeAcademiqueDto.setDateValidationResultat(new Date());

			congeAcademiqueDto = congeAcademiqueService
					.insertOrUpdate(congeAcademiqueDto);

			// recharger infos individu
			loadIndividuInfos();

			// message succes update conge academique
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			msg.setSummary(congeAcademiqueBundle
					.getString("conge_academique_success_resultat_demande_validated"));
			FacesContext.getCurrentInstance().addMessage(null, msg);

		} catch (Exception e) {

			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(congeAcademiqueBundle
					.getString("conge_academique_error_persistence_titre"));
			FacesContext.getCurrentInstance().addMessage(null, msg);

			log.info(e.getMessage());
		}

	}

	/**
	 * sauvegarder le reintegration de l'etudiant
	 * [CongeAcademiqueBean.saveReintegration] Method
	 * 
	 * @author yselmane on : 10 juin 2014 16:28:15
	 */
	public void saveReintegration() {

		FacesMessage msg = new FacesMessage();

		try {
			if (congeAcademiqueDto.getDateDebutAccordee().after(
					congeAcademiqueDto.getDateReintegration())) {
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(congeAcademiqueBundle
						.getString("conge_academique_error_date_debut_accordee_after_date_integration"));
				FacesContext.getCurrentInstance().addMessage(null, msg);

				return;
			}

			congeAcademiqueDto.setIdDossierEtudiant(Integer
					.parseInt(dossierEtudiantId));

			congeAcademiqueDto = congeAcademiqueService
					.insertOrUpdate(congeAcademiqueDto);

			// message succes update conge academique
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			msg.setSummary(congeAcademiqueBundle
					.getString("conge_academique_success_reintegration_saved"));
			FacesContext.getCurrentInstance().addMessage(null, msg);

		} catch (Exception e) {

			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(congeAcademiqueBundle
					.getString("conge_academique_error_persistence_titre"));
			FacesContext.getCurrentInstance().addMessage(null, msg);

			log.info(e.getMessage());
		}
	}

	public void validerReintegration() {

		FacesMessage msg = new FacesMessage();

		try {
			if (congeAcademiqueDto.getDateDebutAccordee().after(
					congeAcademiqueDto.getDateReintegration())) {
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(congeAcademiqueBundle
						.getString("conge_academique_error_date_debut_accordee_after_date_integration"));
				FacesContext.getCurrentInstance().addMessage(null, msg);

				return;
			}

			congeAcademiqueDto.setIdDossierEtudiant(Integer
					.parseInt(dossierEtudiantId));

			congeAcademiqueDto.setReintegrationValidee(true);

			congeAcademiqueDto.setDateValidationReintegration(new Date());

			// Annee academique en cours
			congeAcademiqueDto.setAnneeAcademiqueReintegrationId(sessionBean
					.getIdAnneeAcademique());

			congeAcademiqueDto = congeAcademiqueService
					.insertOrUpdate(congeAcademiqueDto);

			// recharger infos individu
			loadIndividuInfos();

			// message succes update conge academique
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			msg.setSummary(congeAcademiqueBundle
					.getString("conge_academique_success_reintegration_validated"));
			FacesContext.getCurrentInstance().addMessage(null, msg);

		} catch (Exception e) {

			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(congeAcademiqueBundle
					.getString("conge_academique_error_persistence_titre"));
			FacesContext.getCurrentInstance().addMessage(null, msg);

			log.info(e.getMessage());
		}
	}

	/**
	 * [CongeAcademiqueBean.referentielHelper] Getter
	 * 
	 * @author yselmane on : 26 mai 2014 09:18:03
	 * @return the referentielHelper
	 */
	public ReferentielHelper getReferentielHelper() {
		return referentielHelper;
	}

	/**
	 * [CongeAcademiqueBean.referentielHelper] Setter
	 * 
	 * @author yselmane on : 26 mai 2014 09:18:03
	 * @param referentielHelper
	 *            the referentielHelper to set
	 */
	public void setReferentielHelper(ReferentielHelper referentielHelper) {
		this.referentielHelper = referentielHelper;
	}

	/**
	 * code type dossier pour conge academique
	 * [CongeAcademiqueBean.getCodeTypeDossier] Method
	 * 
	 * @author yselmane on : 9 juin 2014 10:13:00
	 * @return
	 */
	public String getCodeTypeDossier() {
		return CursusConstants.CODE_TYPE_DOSSIER_CONGE_ACADEMIQUE;
	}

	/**
	 * 
	 * [CongeAcademiqueBean.getDossierEtudiantBean] Method
	 * 
	 * @author yselmane on : 5 juin 2014 10:30:24
	 * @return
	 */
	public DossierEtudiantBean getDossierEtudiantBean() {
		return dossierEtudiantBean;
	}

	/**
	 * 
	 * [CongeAcademiqueBean.setDossierEtudiantBean] Method
	 * 
	 * @author yselmane on : 5 juin 2014 10:30:28
	 * @param dossierEtudiantBean
	 */
	public void setDossierEtudiantBean(DossierEtudiantBean dossierEtudiantBean) {
		if (dossierEtudiantBean != null && dossierEtudiantId == null) {
			setDossierEtudiantId(dossierEtudiantBean.getDossierEtudiantId());
		}
		this.dossierEtudiantBean = dossierEtudiantBean;
	}

}
