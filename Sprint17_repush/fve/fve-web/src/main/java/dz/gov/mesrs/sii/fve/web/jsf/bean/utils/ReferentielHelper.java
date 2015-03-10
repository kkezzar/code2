package dz.gov.mesrs.sii.fve.web.jsf.bean.utils;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefDomaineLMDDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefFiliereLmdDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefSpecialiteLmdDto;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;
import dz.gov.mesrs.sii.referentiel.business.service.RefDomaineLMDService;
import dz.gov.mesrs.sii.referentiel.business.service.RefFiliereLmdService;
import dz.gov.mesrs.sii.referentiel.business.service.RefIndividuService;
import dz.gov.mesrs.sii.referentiel.business.service.RefSpecialiteLmdService;

@ManagedBean(name = "referentielHelper")
@RequestScoped
public class ReferentielHelper {

	/**
	 * Service proxy de la gestion des nomenclature.
	 */
	@ManagedProperty(value = "#{nomenclatureServiceImpl}")
	private NomenclatureService nomenclatureService;
		
	@ManagedProperty("#{refDomaineLMDServiceImpl}")
	private RefDomaineLMDService refDomaineLMDService;
	
	@ManagedProperty("#{refFiliereLmdServiceImpl}")
	private RefFiliereLmdService refFiliereLmdService;
	
	@ManagedProperty("#{refSpecialiteLmdServiceImpl}")
	private RefSpecialiteLmdService refSpecialiteLmdService;
	
	@ManagedProperty("#{refIndividuServiceImpl}")
	private RefIndividuService refIndividuService;

	public List<SelectItem> getNomenclatureList(String nomenclatureName) {

		List<SelectItem> _ncList = new ArrayList<SelectItem>();
		
		try {
			List<NomenclatureDto> _typeAps = nomenclatureService
					.findByName(nomenclatureName);

			for (NomenclatureDto item : _typeAps) {
				_ncList.add(new SelectItem(item.getCode(), item
						.getLibelleLongFr()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return _ncList;
	}

	public List<SelectItem> getNomenclatureList(String nomenclatureName,
			String referencedNomenclatureName, String referencedNomenclatureCode) {

		List<SelectItem> _ncList = new ArrayList<SelectItem>();

		try {
			List<NomenclatureDto> _typeAps = nomenclatureService
					.findNcCompositeList(referencedNomenclatureName,
							referencedNomenclatureCode, nomenclatureName);

			for (NomenclatureDto item : _typeAps) {
				_ncList.add(new SelectItem(item.getCode(), item
						.getLibelleLongFr()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return _ncList;
	}
	public NomenclatureDto getNomenclatureItemValue(String nomenclatureName,
			String nomenclatureItemCode) {

		try {
			return nomenclatureService.findByCode(nomenclatureName,
					nomenclatureItemCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Obtient la valeur de l'item nomenclature dont l'identifiant est pass� en
	 * param�tre.
	 * 
	 * @param itemId
	 *            : identifiant de l'item � r�cup�rer
	 * @return l'item de la nomenclature recherch�, null si aucun item n'est
	 *         trouv�.
	 */
	public NomenclatureDto getNomenclatureItemValue(int itemId) {

		try {
			return nomenclatureService.findById(itemId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Recherche tous les domaine de formation ddisponible dans le secteur de
	 * l'ESRS
	 * 
	 * @param nomenclatureName
	 * @return
	 */
	public List<SelectItem> findAllDomaines() {

		List<SelectItem> _dmnList = new ArrayList<SelectItem>();

		try {
			List<RefDomaineLMDDto> _dmnDtos = refDomaineLMDService
					.findGeneric("%");

			for (RefDomaineLMDDto _dmn : _dmnDtos) {
				_dmnList.add(new SelectItem(_dmn.getIdentifiant(), _dmn
						.getLlDomaine()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return _dmnList;
	}

	/**
	 * Recherche les fili�res d'un domaine de formation donn�.
	 * 
	 * @param domaineCode
	 * @return
	 */
	public List<SelectItem> findFilieresOfDomaine(String domaineCode) {

		List<SelectItem> _flrList = new ArrayList<SelectItem>();

		try {
			List<RefFiliereLmdDto> _flrDtos = refFiliereLmdService.findByCodeDomainelmd(domaineCode);

			for (RefFiliereLmdDto _flr : _flrDtos) {
				_flrList.add(new SelectItem(_flr.getIdentifiant(), _flr
						.getLlFiliere()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return _flrList;
	}

	/**
	 * [ReferentielHelper.findFilieresDtoOfDomaine] Method
	 * 
	 * @author MAKERRI Sofiane on : 8 juil. 2014 11:10:37
	 * @param domaineCode
	 * @return
	 */
	public List<RefFiliereLmdDto> findFilieresDtoOfDomaine(String domaineCode) {

		try {
			List<RefFiliereLmdDto> _flrDtos = refFiliereLmdService.findByCodeDomainelmd(domaineCode);

			return _flrDtos;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Recherche les fili�res d'un domaine de formation donn�.
	 * 
	 * @param filiereCode
	 * @return
	 */
	public List<SelectItem> findSpecialitesOfFiliere(String filiereCode) {

		List<SelectItem> _spcltList = new ArrayList<SelectItem>();

		try {
			List<RefSpecialiteLmdDto> _spcltDtos = refSpecialiteLmdService.findByCodeFilierelmd(filiereCode);

			for (RefSpecialiteLmdDto _spclt : _spcltDtos) {
				_spcltList.add(new SelectItem(_spclt.getIdentifiant(), _spclt
						.getLlSpecialite()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return _spcltList;
	}

	/**
	 * [ReferentielHelper.findSpecialitesDtoOfFiliere] Method
	 * 
	 * @author MAKERRI Sofiane on : 8 juil. 2014 15:56:18
	 * @param filiereCode
	 * @return
	 */
	public List<RefSpecialiteLmdDto> findSpecialitesDtoOfFiliere(
			String filiereCode) {
		try {
			return refSpecialiteLmdService.findByCodeFilierelmd(filiereCode);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Obtient le libell� de la sp�cialit� dont le code est pass� en param�tre.
	 * 
	 * @param specialiteCode
	 * @return
	 */
	public String findLibelleSpecialite(String specialiteCode) {
		try {
			RefSpecialiteLmdDto _specialite = refSpecialiteLmdService.findByIdentifiant(specialiteCode);

			if (_specialite != null)
				return _specialite.getLlSpecialite();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Obtient le libell� du fili�re dont le code est pass� en param�tre.
	 * 
	 * @param filiereCode
	 * @return
	 */
	public String findLibelleFiliere(String filiereCode) {
		try {
			RefFiliereLmdDto _filiere = refFiliereLmdService.findByIdentifiant(filiereCode);

			if (_filiere != null)
				return _filiere.getLlFiliere();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Obtient le libell� du domaine dont le code est pass� en param�tre.
	 * 
	 * @param domaineCode
	 * @return
	 */
	public String findLibelleDomaine(String domaineCode) {
		try {
			RefDomaineLMDDto _domaine = refDomaineLMDService.findByIdentifiant(domaineCode);

			if (_domaine != null)
				return _domaine.getLlDomaine();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public RefIndividuDto findIndividuInfos(String nin) {

		try {
			return refIndividuService.findByIdentifiant(nin);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return new RefIndividuDto();
	}

	/**
	 * Obtient le libell� de la sp�cialit� dont le code est pass� en param�tre.
	 * 
	 * @param specialiteCode
	 * @return
	 */
	public RefSpecialiteLmdDto findSpecialite(String specialiteCode) {
		try {
			if (specialiteCode != null) {
				RefSpecialiteLmdDto _specialite = refSpecialiteLmdService.findByIdentifiant(specialiteCode);

				return _specialite;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Obtient le libell� du fili�re dont le code est pass� en param�tre.
	 * 
	 * @param filiereCode
	 * @return
	 */
	public RefFiliereLmdDto findFiliere(String filiereCode) {
		try {
			if (filiereCode != null) {
				RefFiliereLmdDto _filiere = refFiliereLmdService.findByIdentifiant(filiereCode);

				return _filiere;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Obtient le libell� du domaine dont le code est pass� en param�tre.
	 * 
	 * @param domaineCode
	 * @return
	 */
	public RefDomaineLMDDto findDomaine(String domaineCode) {
		try {
			if (domaineCode != null) {
				RefDomaineLMDDto _domaine = refDomaineLMDService.findByIdentifiant(domaineCode);

				return _domaine;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public NomenclatureDto findByCode(String name, String code) {
		try {
			if (code != null) {
				NomenclatureDto nomenclatue = nomenclatureService.findByCode(
						name, code);

				return nomenclatue;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * [ReferentielHelper.findCycleOfPalier] Method
	 * 
	 * @author MAKERRI Sofiane on : 13 juil. 2014 11:15:17
	 * @param code
	 * @return
	 */
	public NomenclatureDto findCycleOfPalier(String code) {
		try {
			if (code != null) {
				NomenclatureDto nomenclatue = nomenclatureService.findReference(code);

				return nomenclatue;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * [ReferentielHelper.refDomaineLMDService] Getter 
	 * @author Rafik on : 27 nov. 2014  11:36:18
	 * @return the refDomaineLMDService
	 */
	public RefDomaineLMDService getRefDomaineLMDService() {
		return refDomaineLMDService;
	}

	/**
	 * [ReferentielHelper.refDomaineLMDService] Setter 
	 * @author Rafik on : 27 nov. 2014  11:36:18
	 * @param refDomaineLMDService the refDomaineLMDService to set
	 */
	public void setRefDomaineLMDService(RefDomaineLMDService refDomaineLMDService) {
		this.refDomaineLMDService = refDomaineLMDService;
	}

	/**
	 * [ReferentielHelper.refFiliereLmdService] Getter 
	 * @author Rafik on : 27 nov. 2014  11:36:18
	 * @return the refFiliereLmdService
	 */
	public RefFiliereLmdService getRefFiliereLmdService() {
		return refFiliereLmdService;
	}

	/**
	 * [ReferentielHelper.refFiliereLmdService] Setter 
	 * @author Rafik on : 27 nov. 2014  11:36:18
	 * @param refFiliereLmdService the refFiliereLmdService to set
	 */
	public void setRefFiliereLmdService(RefFiliereLmdService refFiliereLmdService) {
		this.refFiliereLmdService = refFiliereLmdService;
	}

	/**
	 * [ReferentielHelper.refSpecialiteLmdService] Getter 
	 * @author Rafik on : 27 nov. 2014  11:36:18
	 * @return the refSpecialiteLmdService
	 */
	public RefSpecialiteLmdService getRefSpecialiteLmdService() {
		return refSpecialiteLmdService;
	}

	/**
	 * [ReferentielHelper.refSpecialiteLmdService] Setter 
	 * @author Rafik on : 27 nov. 2014  11:36:18
	 * @param refSpecialiteLmdService the refSpecialiteLmdService to set
	 */
	public void setRefSpecialiteLmdService(
			RefSpecialiteLmdService refSpecialiteLmdService) {
		this.refSpecialiteLmdService = refSpecialiteLmdService;
	}

	/**
	 * [ReferentielHelper.refIndividuService] Getter 
	 * @author Rafik on : 27 nov. 2014  11:36:18
	 * @return the refIndividuService
	 */
	public RefIndividuService getRefIndividuService() {
		return refIndividuService;
	}

	/**
	 * [ReferentielHelper.refIndividuService] Setter 
	 * @author Rafik on : 27 nov. 2014  11:36:18
	 * @param refIndividuService the refIndividuService to set
	 */
	public void setRefIndividuService(RefIndividuService refIndividuService) {
		this.refIndividuService = refIndividuService;
	}

	/**
	 * [ReferentielHelper.nomenclatureService] Getter 
	 * @author Rafik on : 27 nov. 2014  11:57:39
	 * @return the nomenclatureService
	 */
	public NomenclatureService getNomenclatureService() {
		return nomenclatureService;
	}

	/**
	 * [ReferentielHelper.nomenclatureService] Setter 
	 * @author Rafik on : 27 nov. 2014  11:57:39
	 * @param nomenclatureService the nomenclatureService to set
	 */
	public void setNomenclatureService(NomenclatureService nomenclatureService) {
		this.nomenclatureService = nomenclatureService;
	}
	
	
}
