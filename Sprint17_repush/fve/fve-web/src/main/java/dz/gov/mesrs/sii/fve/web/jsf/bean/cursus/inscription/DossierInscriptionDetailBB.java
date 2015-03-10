/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.inscription.DossierInscriptionDetailBB.java] 
 * @author MAKERRI Sofiane on : 31 juil. 2014  08:43:43
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.inscription;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

import org.jfree.util.Log;

import dz.gov.mesrs.sii.commons.business.service.bpm.SituationService;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierInscriptionAdministrativeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.AnneeAcademiqueDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OffreFormationDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OuvertureOffreFormationDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierInscriptionAdministrativeService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.AnneeAcademiqueService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OffreFormationService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OuvertureOffreFormationService;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.ReferentielHelper;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefDomaineLMDDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefFiliereLmdDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefIndividuService;

/**
 * @author MAKERRI Sofiane on : 31 juil. 2014 08:43:43
 */
@ManagedBean(name = "dossierInscriptionDetailBB")
@RequestScoped
public class DossierInscriptionDetailBB implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author MAKERRI Sofiane on : 31 juil. 2014 08:44:03
	 */
	private static final long serialVersionUID = 1L;
	private List<DossierInscriptionAdministrativeDto> dossierInscriptionList;
	private Integer idAnnee;
	private List<SelectItem> offreFormationList;
	@ManagedProperty(value = "#{ouvertureOffreFormationService}")
	private OuvertureOffreFormationService ouvertureOffreFormationService;
	@ManagedProperty("#{param.anneeId}")
	private String anneeId;
	@ManagedProperty(value = "#{sessionBeanFve}")
	private SessionBean sessionBean;
	@ManagedProperty(value = "#{offreFormationService}")
	private OffreFormationService offreFormationService;
	@ManagedProperty(value = "#{anneeAcademiqueService}")
	private AnneeAcademiqueService anneeAcademiqueService;
	private List<SelectItem> anneeAcademiqueList;
	@ManagedProperty("#{param.oofId}")
	private String oofId;
	private Integer idOof;
	@ManagedProperty(value = "#{dossierInscriptionAdministrativeService}")
	private DossierInscriptionAdministrativeService dossierInscriptionAdministrativeService;
	private List<OuvertureOffreFormationDto> oofList;
	//private List<OffreFormationInscriptionModel> ofInscriptionList;
	@ManagedProperty(value = "#{referentielHelper}")
	private ReferentielHelper referentielHelper;
	private String diaFileName;
	@ManagedProperty("#{param.loadDia}")
	private String loadDia;
	private boolean load;
	@ManagedProperty(value = "#{situationService}")
	private SituationService situationService;
	private String header;
	
	@ManagedProperty(value = "#{refIndividuServiceImpl}")
	private RefIndividuService refIndividuService;

	/**
	 * [DossierInscriptionDetailBB.DossierInscriptionDetailBB()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 31 juil. 2014 08:43:43
	 */
	public DossierInscriptionDetailBB() {
		super();
	}

	@PostConstruct
	public void init() {
		//loadOffreFormationOuverte();
		loadAnneeAcademique();
		if (load) {
			findAdvanced();
		}
	}

	/**
	 * [DossierInscriptionDetailBB.loadAnneeAcademique] Method
	 * 
	 * @author MAKERRI Sofiane on : 31 juil. 2014 08:58:24
	 */
	public void loadAnneeAcademique() {
		try {
			List<AnneeAcademiqueDto> list = anneeAcademiqueService.findAll();
			anneeAcademiqueList = new ArrayList<SelectItem>();
			for (AnneeAcademiqueDto annee : list) {
				SelectItem item = new SelectItem(annee.getId(), annee.getCode());
				anneeAcademiqueList.add(item);
			}
		} catch (Exception e) {

		}
	}

	/**
	 * [DossierInscriptionDetailBB.dossierInscriptionList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 31 juil. 2014 08:47:14
	 * @return the dossierInscriptionList
	 */
	public List<DossierInscriptionAdministrativeDto> getDossierInscriptionList() {
		return dossierInscriptionList;
	}

	/**
	 * [DossierInscriptionDetailBB.dossierInscriptionList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 31 juil. 2014 08:47:14
	 * @param dossierInscriptionList
	 *            the dossierInscriptionList to set
	 */
	public void setDossierInscriptionList(
			List<DossierInscriptionAdministrativeDto> dossierInscriptionList) {
		this.dossierInscriptionList = dossierInscriptionList;
	}

	/**
	 * [DossierInscriptionDetailBB.idAnnee] Getter
	 * 
	 * @author MAKERRI Sofiane on : 31 juil. 2014 08:47:14
	 * @return the idAnnee
	 */
	public Integer getIdAnnee() {
		return idAnnee;
	}

	/**
	 * [DossierInscriptionDetailBB.idAnnee] Setter
	 * 
	 * @author MAKERRI Sofiane on : 31 juil. 2014 08:47:14
	 * @param idAnnee
	 *            the idAnnee to set
	 */
	public void setIdAnnee(Integer idAnnee) {
		this.idAnnee = idAnnee;
	}

	/**
	 * [DossierInscriptionDetailBB.anneeChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 31 juil. 2014 08:48:03
	 */
	public void anneeChanged() {
		//loadOffreFormationOuverte();
		if (idAnnee != null) {
			setAnneeId(idAnnee.toString());
		}
	}

	/**
	 * [DossierInscriptionDetailBB.loadOffreFormationOuverte] Method
	 * 
	 * @author MAKERRI Sofiane on : 31 juil. 2014 08:48:34
	 */
	public void loadOffreFormationOuverte() {
		offreFormationList = new ArrayList<SelectItem>();

		try {
			String etabCode = sessionBean.getCodeEtablissement();
			// Integer idAnnee = sessionBean.getIdAnneeAcademique();
			if (etabCode == null) {
				return;
			}
			if (idAnnee == null) {
				return;
			}

			oofList = ouvertureOffreFormationService.findAdvanced(etabCode,
					null, idAnnee, true);

			for (OuvertureOffreFormationDto item : oofList) {

				if (findOfItem(item.getOffreFormationCode()) == null) {
					SelectItem _of = new SelectItem(item.getId(),
							loadOfforeFormationInfo(item.getOffreFormationId()));
					item.setOffreFormationlibelle(_of.getLabel());
					offreFormationList.add(_of);

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * [DossierInscriptionDetailBB.offreFormationList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 31 juil. 2014 08:49:10
	 * @return the offreFormationList
	 */
	public List<SelectItem> getOffreFormationList() {
		return offreFormationList;
	}

	/**
	 * [DossierInscriptionDetailBB.offreFormationList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 31 juil. 2014 08:49:10
	 * @param offreFormationList
	 *            the offreFormationList to set
	 */
	public void setOffreFormationList(List<SelectItem> offreFormationList) {
		this.offreFormationList = offreFormationList;
	}

	/**
	 * [DossierInscriptionDetailBB.ouvertureOffreFormationService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 31 juil. 2014 08:49:10
	 * @return the ouvertureOffreFormationService
	 */
	public OuvertureOffreFormationService getOuvertureOffreFormationService() {
		return ouvertureOffreFormationService;
	}

	/**
	 * [DossierInscriptionDetailBB.ouvertureOffreFormationService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 31 juil. 2014 08:49:10
	 * @param ouvertureOffreFormationService
	 *            the ouvertureOffreFormationService to set
	 */
	public void setOuvertureOffreFormationService(
			OuvertureOffreFormationService ouvertureOffreFormationService) {
		this.ouvertureOffreFormationService = ouvertureOffreFormationService;
	}

	/**
	 * [DossierInscriptionDetailBB.anneeId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 31 juil. 2014 08:49:56
	 * @return the anneeId
	 */
	public String getAnneeId() {
		return anneeId;
	}

	/**
	 * [DossierInscriptionDetailBB.anneeId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 31 juil. 2014 08:49:56
	 * @param anneeId
	 *            the anneeId to set
	 */
	public void setAnneeId(String anneeId) {
		if (anneeId != null && anneeId.equals("null")) {
			this.anneeId = null;
		} else {
			this.anneeId = anneeId;
			try {
				setIdAnnee(Integer.parseInt(anneeId));
			} catch (Exception e) {

			}
		}
	}

	/**
	 * [DossierInscriptionDetailBB.sessionBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 31 juil. 2014 08:51:12
	 * @return the sessionBean
	 */
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	/**
	 * [DossierInscriptionDetailBB.sessionBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 31 juil. 2014 08:51:12
	 * @param sessionBean
	 *            the sessionBean to set
	 */
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	/**
	 * [DossierInscriptionDetailBB.findOfItem] Method
	 * 
	 * @author MAKERRI Sofiane on : 31 juil. 2014 08:51:32
	 * @param label
	 * @return
	 */
	private SelectItem findOfItem(String label) {
		for (SelectItem _item : offreFormationList) {
			if (_item.getLabel().equals(label)) {
				return _item;
			}
		}
		return null;
	}

	/**
	 * [DossierInscriptionDetailBB.loadOfforeFormationInfo] Method
	 * 
	 * @author MAKERRI Sofiane on : 31 juil. 2014 08:51:52
	 * @param ofId
	 * @return
	 */
	private String loadOfforeFormationInfo(Integer ofId) {
		
		if (ofId != null) {
			OffreFormationDto ofDto = offreFormationService.findById(ofId);
			return ofDto.getLibelleLongFr();

		}
		return null;
	}

	/**
	 * [DossierInscriptionDetailBB.offreFormationService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 31 juil. 2014 08:57:09
	 * @return the offreFormationService
	 */
	public OffreFormationService getOffreFormationService() {
		return offreFormationService;
	}

	/**
	 * [DossierInscriptionDetailBB.offreFormationService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 31 juil. 2014 08:57:09
	 * @param offreFormationService
	 *            the offreFormationService to set
	 */
	public void setOffreFormationService(
			OffreFormationService offreFormationService) {
		this.offreFormationService = offreFormationService;
	}

	/**
	 * [DossierInscriptionDetailBB.anneeAcademiqueService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 31 juil. 2014 08:58:58
	 * @return the anneeAcademiqueService
	 */
	public AnneeAcademiqueService getAnneeAcademiqueService() {
		return anneeAcademiqueService;
	}

	/**
	 * [DossierInscriptionDetailBB.anneeAcademiqueService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 31 juil. 2014 08:58:58
	 * @param anneeAcademiqueService
	 *            the anneeAcademiqueService to set
	 */
	public void setAnneeAcademiqueService(
			AnneeAcademiqueService anneeAcademiqueService) {
		this.anneeAcademiqueService = anneeAcademiqueService;
	}

	/**
	 * [DossierInscriptionDetailBB.anneeAcademiqueList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 31 juil. 2014 08:59:24
	 * @return the anneeAcademiqueList
	 */
	public List<SelectItem> getAnneeAcademiqueList() {
		return anneeAcademiqueList;
	}

	/**
	 * [DossierInscriptionDetailBB.anneeAcademiqueList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 31 juil. 2014 08:59:24
	 * @param anneeAcademiqueList
	 *            the anneeAcademiqueList to set
	 */
	public void setAnneeAcademiqueList(List<SelectItem> anneeAcademiqueList) {
		this.anneeAcademiqueList = anneeAcademiqueList;
	}

	/**
	 * [DossierInscriptionDetailBB.idOof] Getter
	 * 
	 * @author MAKERRI Sofiane on : 31 juil. 2014 09:00:03
	 * @return the idOof
	 */
	public Integer getIdOof() {
		return idOof;
	}

	/**
	 * [DossierInscriptionDetailBB.idOof] Setter
	 * 
	 * @author MAKERRI Sofiane on : 31 juil. 2014 09:00:03
	 * @param idOof
	 *            the idOof to set
	 */
	public void setIdOof(Integer idOof) {
		this.idOof = idOof;
	}

	/**
	 * [DossierInscriptionDetailBB.oofId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 31 juil. 2014 09:00:29
	 * @return the oofId
	 */
	public String getOofId() {
		return oofId;
	}

	/**
	 * [DossierInscriptionDetailBB.oofId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 31 juil. 2014 09:00:29
	 * @param oofId
	 *            the oofId to set
	 */
	public void setOofId(String oofId) {
		if ((oofId != null) && (oofId.equals("null"))) {
			this.oofId = null;
		} else {
			this.oofId = oofId;
			try {
				setIdOof(Integer.parseInt(oofId));
			} catch (Exception e) {

			}
		}
	}

	/**
	 * [DossierInscriptionDetailBB.findAdvanced] Method
	 * 
	 * @author MAKERRI Sofiane on : 31 juil. 2014 09:03:14
	 */
	public void findAdvanced() {
		//ofInscriptionList = new ArrayList<OffreFormationInscriptionModel>();
		DossierInscriptionAdministrativeDto _dto = new DossierInscriptionAdministrativeDto();
		_dto.setRefEtablissementId(sessionBean.getIdEtablissement());
		_dto.setAnneeAcademiqueId(idAnnee);
		_dto.setOuvertureOffreFormationId(idOof);
		dossierInscriptionList = dossierInscriptionAdministrativeService
				.findAdvanced(_dto, false);
		loadDossiersInscriptionInfo();
		
//		if (idOof != null) {
//			this.oofId = idOof.toString();
//		} else {
//			this.oofId = null;
//		}
			
//		for (OuvertureOffreFormationDto _oof : oofList) {
//			OffreFormationInscriptionModel _ofim = new OffreFormationInscriptionModel();
//			if (idOof != null && !_oof.getId().equals(idOof)) {
//				continue;
//			}
//			_ofim.setOffreFormation(_oof.getOffreFormationlibelle());
//			DossierInscriptionAdministrativeDto _dto = new DossierInscriptionAdministrativeDto();
//			_dto.setRefCodeEtablissement(sessionBean.getCodeEtablissement());
//			_dto.setAnneeAcademiqueId(idAnnee);
//			_dto.setOuvertureOffreFormationId((Integer) _oof.getId());
//			dossierInscriptionList = dossierInscriptionAdministrativeService
//					.findAdvanced(_dto, false);
//			_ofim.setDossiersInscription(dossierInscriptionList);
//			ofInscriptionList.add(_ofim);
//		}
//		loadDossiersInscriptionInfo();
	}

	/**
	 * [DossierInscriptionDetailBB.loadDossiersInscriptionInfo] Method
	 * 
	 * @author MAKERRI Sofiane on : 31 juil. 2014 10:36:34
	 */
	public void loadDossiersInscriptionInfo() {
		try {
			if (dossierInscriptionList == null)
				return;
			for (DossierInscriptionAdministrativeDto _dia : dossierInscriptionList) {
				RefIndividuDto _refIndividuDto = refIndividuService.findByIdentifiant(
								_dia.getNin());
				if (_refIndividuDto != null) {
					_dia.setIndividuDateNaissance(_refIndividuDto
							.getDateNaissance());
					_dia.setIndividuNomLatin(_refIndividuDto.getNomLatin());
					_dia.setIndividuPrenomLatin(_refIndividuDto
							.getPrenomLatin());
					_dia.setIndividuNomArabe(_refIndividuDto.getNomArabe());
					_dia.setIndividuPrenomArabe(_refIndividuDto
							.getPrenomArabe());
				}

				if (_dia.getRefLibelleFiliere() == null) {
					RefFiliereLmdDto _filiere = referentielHelper
							.findFiliere(_dia.getRefCodeFiliere());
					if (_filiere != null) {
						_dia.setRefLibelleFiliere(_filiere.getLlFiliere());
						_dia.setRefLibelleFiliereArabe(_filiere
								.getLlFiliereArabe());
					}
				}

				if (_dia.getRefLibelleDomaine() == null) {
					RefDomaineLMDDto _domaine = referentielHelper
							.findDomaine(_dia.getRefCodeDomaine());
					if (_domaine != null) {
						_dia.setRefLibelleDomaine(_domaine.getLlDomaine());
						_dia.setRefLibelleDomaineArabe(_domaine
								.getLlDomaineArabe());
					}
				}

//				if (_dia.getSituationId() != null) {
//					List<SituationI18nDto> situation18n = situationService
//							.findByIdSituationEntite(_dia.getSituationId());
//
//					for (SituationI18nDto s18n : situation18n) {
//						if (s18n.getLangue().equals("fr")) {
//							_dia.setSituationLibelleFr(s18n.getLibelle());
//						} else if (s18n.getLangue().equals("ar")) {
//							_dia.setSituationLibelleAr(s18n.getLibelle());
//						}
//					}
//				}

			}
		} catch (Exception e) {
			Log.error(e.getMessage());
		}
	}

	/**
	 * [DossierInscriptionDetailBB.dossierInscriptionAdministrativeService]
	 * Getter
	 * 
	 * @author MAKERRI Sofiane on : 31 juil. 2014 09:03:10
	 * @return the dossierInscriptionAdministrativeService
	 */
	public DossierInscriptionAdministrativeService getDossierInscriptionAdministrativeService() {
		return dossierInscriptionAdministrativeService;
	}

	/**
	 * [DossierInscriptionDetailBB.dossierInscriptionAdministrativeService]
	 * Setter
	 * 
	 * @author MAKERRI Sofiane on : 31 juil. 2014 09:03:10
	 * @param dossierInscriptionAdministrativeService
	 *            the dossierInscriptionAdministrativeService to set
	 */
	public void setDossierInscriptionAdministrativeService(
			DossierInscriptionAdministrativeService dossierInscriptionAdministrativeService) {
		this.dossierInscriptionAdministrativeService = dossierInscriptionAdministrativeService;
	}

	/**
	 * [DossierInscriptionDetailBB.oofList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 31 juil. 2014 10:10:55
	 * @return the oofList
	 */
	public List<OuvertureOffreFormationDto> getOofList() {
		return oofList;
	}

	/**
	 * [DossierInscriptionDetailBB.oofList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 31 juil. 2014 10:10:55
	 * @param oofList
	 *            the oofList to set
	 */
	public void setOofList(List<OuvertureOffreFormationDto> oofList) {
		this.oofList = oofList;
	}

	/**
	 * [DossierInscriptionDetailBB.ofInscriptionList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 31 juil. 2014 10:14:17
	 * @return the ofInscriptionList
	 */
//	public List<OffreFormationInscriptionModel> getOfInscriptionList() {
//		return ofInscriptionList;
//	}

	/**
	 * [DossierInscriptionDetailBB.ofInscriptionList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 31 juil. 2014 10:14:17
	 * @param ofInscriptionList
	 *            the ofInscriptionList to set
	 */
//	public void setOfInscriptionList(
//			List<OffreFormationInscriptionModel> ofInscriptionList) {
//		this.ofInscriptionList = ofInscriptionList;
//	}

	/**
	 * [DossierInscriptionDetailBB.referentielHelper] Getter
	 * 
	 * @author MAKERRI Sofiane on : 31 juil. 2014 10:36:16
	 * @return the referentielHelper
	 */
	public ReferentielHelper getReferentielHelper() {
		return referentielHelper;
	}

	/**
	 * [DossierInscriptionDetailBB.referentielHelper] Setter
	 * 
	 * @author MAKERRI Sofiane on : 31 juil. 2014 10:36:16
	 * @param referentielHelper
	 *            the referentielHelper to set
	 */
	public void setReferentielHelper(ReferentielHelper referentielHelper) {
		this.referentielHelper = referentielHelper;
	}

	/**
	 * [DossierInscriptionDetailBB.diaFileName] Getter
	 * 
	 * @author MAKERRI Sofiane on : 31 juil. 2014 12:30:22
	 * @return the diaFileName
	 */
	public String getDiaFileName() {
		DateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy");
		diaFileName = sessionBean.getCodeEtablissement() + "_"
				+ sessionBean.getCodeAnneeAcademique() + "_ETAT_INSCRIPTION_"
				+ dateFormat.format(new Date());
		return diaFileName;
	}

	/**
	 * [DossierInscriptionDetailBB.diaFileName] Setter
	 * 
	 * @author MAKERRI Sofiane on : 31 juil. 2014 12:30:22
	 * @param diaFileName
	 *            the diaFileName to set
	 */
	public void setDiaFileName(String diaFileName) {
		this.diaFileName = diaFileName;
	}

	/**
	 * [DossierInscriptionDetailBB.loadDia] Getter
	 * 
	 * @author MAKERRI Sofiane on : 31 juil. 2014 12:32:21
	 * @return the loadDia
	 */
	public String getLoadDia() {
		return loadDia;
	}

	/**
	 * [DossierInscriptionDetailBB.loadDia] Setter
	 * 
	 * @author MAKERRI Sofiane on : 31 juil. 2014 12:32:21
	 * @param loadDia
	 *            the loadDia to set
	 */
	public void setLoadDia(String loadDia) {
		this.loadDia = loadDia;
		if (loadDia != null && loadDia.equals("null")) {
			this.loadDia = null;
		} else {
			this.loadDia = loadDia;
			try {
				setLoad(Boolean.parseBoolean(loadDia));
			} catch (Exception e) {

			}
		}
	}

	/**
	 * [DossierInscriptionDetailBB.load] Getter
	 * 
	 * @author MAKERRI Sofiane on : 31 juil. 2014 12:32:21
	 * @return the load
	 */
	public boolean isLoad() {
		return load;
	}

	/**
	 * [DossierInscriptionDetailBB.load] Setter
	 * 
	 * @author MAKERRI Sofiane on : 31 juil. 2014 12:32:21
	 * @param load
	 *            the load to set
	 */
	public void setLoad(boolean load) {
		this.load = load;
	}

	/**
	 * [DossierInscriptionDetailBB.situationService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 31 juil. 2014 12:54:11
	 * @return the situationService
	 */
	public SituationService getSituationService() {
		return situationService;
	}

	/**
	 * [DossierInscriptionDetailBB.situationService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 31 juil. 2014 12:54:11
	 * @param situationService
	 *            the situationService to set
	 */
	public void setSituationService(SituationService situationService) {
		this.situationService = situationService;
	}

	/**
	 * [DossierInscriptionDetailBB.header] Getter 
	 * @author MAKERRI Sofiane on : 3 ao�t 2014  11:29:35
	 * @return the header
	 */
	public String getHeader() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		header = sessionBean.getLlLatinEtablissement() + " - "
				+ dateFormat.format(new Date());
		return header;
	}

	/**
	 * [DossierInscriptionDetailBB.header] Setter 
	 * @author MAKERRI Sofiane on : 3 ao�t 2014  11:29:35
	 * @param header the header to set
	 */
	public void setHeader(String header) {
		this.header = header;
	}

	/**
	 * [DossierInscriptionDetailBB.refIndividuService] Getter 
	 * @author rlaib on : 20 nov. 2014  15:53:56
	 * @return the refIndividuService
	 */
	public RefIndividuService getRefIndividuService() {
		return refIndividuService;
	}

	/**
	 * [DossierInscriptionDetailBB.refIndividuService] Setter 
	 * @author rlaib on : 20 nov. 2014  15:53:56
	 * @param refIndividuService the refIndividuService to set
	 */
	public void setRefIndividuService(RefIndividuService refIndividuService) {
		this.refIndividuService = refIndividuService;
	}
	
	

}
