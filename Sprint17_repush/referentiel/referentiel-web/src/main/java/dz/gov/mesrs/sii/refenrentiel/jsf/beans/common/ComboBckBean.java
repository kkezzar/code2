/**
 * 
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import dz.gov.mesrs.sii.commons.data.util.UtilConstant;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NcNamesDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefContratDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefDomaineDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefDomaineLMDDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEquipementDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefFiliereLmdDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefGroupeDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefHoraireAccessDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefLieuDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefPeriodeDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefPlageAdresseDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefStructureDto;
import dz.gov.mesrs.sii.referentiel.business.service.NcNamesService;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;
import dz.gov.mesrs.sii.referentiel.business.service.RefContratService;
import dz.gov.mesrs.sii.referentiel.business.service.RefDomaineLMDService;
import dz.gov.mesrs.sii.referentiel.business.service.RefDomaineService;
import dz.gov.mesrs.sii.referentiel.business.service.RefEquipementService;
import dz.gov.mesrs.sii.referentiel.business.service.RefEtablissementService;
import dz.gov.mesrs.sii.referentiel.business.service.RefFiliereLmdService;
import dz.gov.mesrs.sii.referentiel.business.service.RefGroupeService;
import dz.gov.mesrs.sii.referentiel.business.service.RefHoraireAccessService;
import dz.gov.mesrs.sii.referentiel.business.service.RefIndividuService;
import dz.gov.mesrs.sii.referentiel.business.service.RefLieuService;
import dz.gov.mesrs.sii.referentiel.business.service.RefPeriodeService;
import dz.gov.mesrs.sii.referentiel.business.service.RefPlageAdresseService;
import dz.gov.mesrs.sii.referentiel.business.service.RefStructureService;

/**
 * @author jbeldi
 * 
 */
@ManagedBean(name = "comboBckBean")
@ViewScoped
public class ComboBckBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Nomenclatures
	 */
	private List<SelectItem> listeGroupeSanguin;
	private List<SelectItem> listeSitSceNat;
	private List<SelectItem> listeSituationFamiliale;
	private List<SelectItem> listeNationalite;
	private List<SelectItem> listeFonction;
	private List<SelectItem> listeCivilite;
	private List<SelectItem> listeNatureContrat;
	private List<SelectItem> listeUniteMonetaire;
	private List<SelectItem> listeDescipline;
	private List<SelectItem> listRole;
	private List<SelectItem> listTypeAdresse;
	private List<SelectItem> listPays;
	private List<SelectItem> listWilaya;
	private List<SelectItem> listDaira;
	private List<SelectItem> listCommune;
	private List<SelectItem> listNatTel;
	private List<SelectItem> listTypeTel;
	private List<SelectItem> listTypeMail;
	private List<SelectItem> listNatureAdresseElectronique;
	private List<SelectItem> listNc;
	private List<SelectItem> listTypeStructure;
	private List<SelectItem> listTypeEtablissement;
	private List<SelectItem> listFormeJuridique;
	private List<SelectItem> listStatut;
	private List<SelectItem> listTypeAvenant;
	private List<SelectItem> listeTypeLieu;
	private List<SelectItem> listeTypeImplan;
	private List<SelectItem> listTypeEvenement;
	private List<SelectItem> listRepetition;
	private List<SelectItem> listTypeTache;
	private List<SelectItem> listeTypeAcces;
	private List<SelectItem> listeTypeSalle;
	private List<SelectItem> listTypeOccupation;
	private List<SelectItem> listePlageAdresse;
	private List<SelectItem> listeQuestionSecrete;
	private List<SelectItem> listeHoraireAcces;
	private List<SelectItem> listTypeIndividu;
	private List<SelectItem> listLangue;
	private List<SelectItem> listTypeDocument;
	private List<SelectItem> listNatureDocument;
	private List<SelectItem> listMotCle;
	private List<SelectItem> listClassement;
	private List<SelectItem> listFamillequipement;
	private List<SelectItem> listSousFamillequipement;
	private List<SelectItem> listItemDomaines;
	private List<SelectItem> listEtatEquipement;
	private List<SelectItem> listRefEquipementDto;
	private List<SelectItem> listePeriode;
	private List<SelectItem> listTypeDossier;
	private List<SelectItem> listeNiveauRecrutement;
	private List<SelectItem> listTypeGroupe;

	/**
	 * PPM
	 */
	private List<SelectItem> listRefGroupeDto;
	private List<SelectItem> listRefIndividuDto;
	private List<SelectItem> listRefStructureDto;
	private List<SelectItem> listRefContratDto;
	private List<SelectItem> listRefLieuDto;
	private List<SelectItem> listRefEtablissementDto;
	private List<RefEtablissementDto> listEtablissements;
	private List<SelectItem> listRefFiliereLmdDto;

	/***
	 * 
	 * Default values
	 * 
	 */

	private Integer defaultGroupeSanguin;
	private Integer defaultSitSceNat;
	private Integer defaultSituationFamiliale;
	private Integer defaultNationalite;
	private Integer defaultFonction;
	private Integer defaultCivilite;
	private Integer defaultNatureContrat;
	private Integer defaultUniteMonetaire;
	private Integer defaultDiscipline;
	private Integer defaultRole;
	private Integer defaultTypeAdresse;
	private Integer defaultPays;
	private Integer defaultWilaya;
	private Integer defaultDaira;
	private Integer defaultCommune;
	private Integer defaultNatTel;
	private Integer defaultTypeTel;
	private Integer defaultTypeMail;
	private Integer defaultNatureAdresseElectronique;
	private Integer defaultTypeStructure;
	private Integer defaultTypeEtablissement;
	private Integer defaultFormeJuridique;
	private Integer defaultStatut;
	private Integer defaultTypeAvenant;
	private Integer defaultTypeLieu;
	private Integer defaultTypeImplan;
	private Integer defaultTypeEvenement;
	private Integer defaultRepetition;
	private Integer defaultTypeTache;
	private Integer defaultTypeAcces;
	private Integer defaultTypeSalle;
	private Integer defaultTypeOccupation;
	private Integer defaultTypeIndividu;
	private Integer defaultLangue;
	private Integer defaultTypeDocument;
	private Integer defaultNatureDocument;
	private Integer defaultMotCle;
	private Integer defaultClassement;
	private Integer defaultFamillequipement;
	private Integer defaultSousFamillequipement;
	private Integer defaultEtatEquipement;

	/**
	 * NC Services
	 */
	@ManagedProperty(value = "#{nomenclatureServiceImpl}")
	private NomenclatureService nomenclatureServiceImpl;
	@ManagedProperty(value = "#{ncNamesServiceImpl}")
	private NcNamesService ncNamesServiceImpl;

	/**
	 * PPM Services
	 */
	@ManagedProperty(value = "#{refGroupeServiceImpl}")
	private RefGroupeService refGroupeServiceImpl;
	@ManagedProperty(value = "#{refIndividuServiceImpl}")
	private RefIndividuService refIndividuServiceImpl;
	@ManagedProperty(value = "#{refStructureServiceImpl}")
	private RefStructureService refStructureServiceImpl;
	@ManagedProperty(value = "#{refContratServiceImpl}")
	private RefContratService refContratServiceImpl;
	@ManagedProperty(value = "#{refLieuServiceImpl}")
	private RefLieuService refLieuServiceImpl;
	@ManagedProperty(value = "#{refPlageAdresseServiceImpl}")
	private RefPlageAdresseService refPlageAdresseServiceImpl;
	@ManagedProperty(value = "#{refHoraireAccessServiceImpl}")
	private RefHoraireAccessService refHoraireAccessServiceImpl;
	@ManagedProperty(value = "#{refDomaineServiceImpl}")
	private RefDomaineService refDomaineServiceImpl;
	@ManagedProperty(value = "#{refEquipementServiceImpl}")
	private RefEquipementService refEquipementServiceImpl;
	@ManagedProperty(value = "#{refPeriodeServiceImpl}")
	private RefPeriodeService refPeriodeServiceImpl;
	@ManagedProperty(value = "#{refEtablissementServiceImpl}")
	private RefEtablissementService refEtablissementServiceImpl;
	@ManagedProperty(value = "#{refDomaineLMDServiceImpl}")
	private RefDomaineLMDService refDomaineLMDServiceImpl;
	private List<SelectItem> listRefDomaineLMDDto;
	@ManagedProperty(value = "#{refFiliereLmdServiceImpl}")
	private RefFiliereLmdService refFiliereLmdServiceImpl;

	/**
	 * [ComboBckBean.ListEtatEquipement] Getter
	 * 
	 * @author BELBRIK Oualid on : 20 mars 2014 18:08:45
	 * @return the ListEtatEquipement
	 */

	public List<SelectItem> getListEtatEquipement() {
		if (listEtatEquipement == null || listEtatEquipement.isEmpty()) {
			listEtatEquipement = new ArrayList<SelectItem>();
			listEtatEquipement = getNcGenericList(UtilConstant.NC_ETAT_EQUIPEMENT_NAME);
		}
		return listEtatEquipement;
	}

	public void setListEtatEquipement(List<SelectItem> listEtatEquipement) {
		this.listEtatEquipement = listEtatEquipement;
	}

	/**
	 * [ComboBckBean.listItemDomaines] Getter
	 * 
	 * @author BELDI Jamel on : 20 mars 2014 18:08:45
	 * @return the listItemDomaines
	 */
	public List<SelectItem> getListItemDomaines() {
		if (listItemDomaines == null || listItemDomaines.isEmpty()) {
			listItemDomaines = new ArrayList<SelectItem>();
			List<RefDomaineDto> listDomaines = refDomaineServiceImpl
					.findDomaines();
			for (RefDomaineDto refDomaineDto : listDomaines) {
				SelectItem selectItem = new SelectItem(refDomaineDto.getId(),
						refDomaineDto.getNom());
				listItemDomaines.add(selectItem);
			}

		}

		return listItemDomaines;
	}

	/**
	 * [ComboBckBean.listItemDomaines] Setter
	 * 
	 * @author BELDI Jamel on : 20 mars 2014 18:08:45
	 * @param listItemDomaines
	 *            the listItemDomaines to set
	 */
	public void setListItemDomaines(List<SelectItem> listItemDomaines) {
		this.listItemDomaines = listItemDomaines;
	}

	public List<SelectItem> getListFamillequipement() {
		if (listFamillequipement == null || listFamillequipement.isEmpty()) {
			listFamillequipement = new ArrayList<SelectItem>();
			listFamillequipement = getNcGenericList(UtilConstant.NC_FAMILLE_EQUIPEMENT_NAME);
		}
		return listFamillequipement;
	}

	public void setListFamillequipement(List<SelectItem> listFamillequipement) {
		this.listFamillequipement = listFamillequipement;
	}

	public List<SelectItem> getListSousFamillequipement() {
		if (listSousFamillequipement == null
				|| listSousFamillequipement.isEmpty()) {
			listSousFamillequipement = new ArrayList<SelectItem>();
			listSousFamillequipement = getNcGenericList(UtilConstant.NC_SOUS_FAMILLE_EQUIPEMENT_NAME);
		}
		return listSousFamillequipement;
	}

	public void setListSousFamillequipement(
			List<SelectItem> listSousFamillequipement) {
		this.listSousFamillequipement = listSousFamillequipement;
	}

	public List<SelectItem> getListTypeOccupation() {
		if (listTypeOccupation == null || listTypeOccupation.isEmpty()) {
			listTypeOccupation = new ArrayList<SelectItem>();
			listTypeOccupation = getNcGenericList(UtilConstant.NC_TYPE_OCCUPATION_NAME);
		}
		return listTypeOccupation;
	}

	public void setListTypeOccupation(List<SelectItem> listTypeOccupation) {
		this.listTypeOccupation = listTypeOccupation;
	}

	public List<SelectItem> getListeTypeAcces() {
		if (listeTypeAcces == null || listeTypeAcces.isEmpty()) {
			listeTypeAcces = new ArrayList<SelectItem>();
			listeTypeAcces = getNcGenericList(UtilConstant.NC_TYPE_ACCES_NAME);
		}
		return listeTypeAcces;
	}

	public void setListeTypeAcces(List<SelectItem> listeTypeAcces) {
		this.listeTypeAcces = listeTypeAcces;
	}

	public List<SelectItem> getListeTypeSalle() {
		if (listeTypeSalle == null || listeTypeSalle.isEmpty()) {
			listeTypeSalle = new ArrayList<SelectItem>();
			listeTypeSalle = getNcGenericList(UtilConstant.NC_TYPE_SALLE_NAME);
		}
		return listeTypeSalle;
	}

	public void setListeTypeSalle(List<SelectItem> listeTypeSalle) {
		this.listeTypeSalle = listeTypeSalle;
	}

	/**
	 * [ComboBean.listRefEquipementDto] Getter
	 * 
	 * @author BELBRIK Oualid on : 24 mars. 2014 13:54:32
	 * @return the listRefEquipementDto
	 */
	public List<SelectItem> getListRefEquipementDto() {
		if (listRefEquipementDto == null || listRefEquipementDto.isEmpty()) {
			listRefEquipementDto = new ArrayList<SelectItem>();
			List<RefEquipementDto> list = refEquipementServiceImpl
					.findAll(SessionValues.getIdEtablissement());
			for (RefEquipementDto refEquipementDto : list) {
				SelectItem selectItem = new SelectItem(
						refEquipementDto.getId(),
						refEquipementDto.getDesignation());
				listRefEquipementDto.add(selectItem);
			}

		}

		return listRefEquipementDto;
	}

	/**
	 * [ComboBean.listRefEquipementDto] Setter
	 * 
	 * @author BELBRIK Oualid on : 24 janv. 2014 13:54:32
	 * @param listRefEquipementDto
	 *            the listRefEquipementDto to set
	 */
	public void setListRefEquipementDto(List<SelectItem> listRefEquipementDto) {
		this.listRefEquipementDto = listRefEquipementDto;
	}

	/**
	 * [ComboBckBean.listTypeAvenant] Getter
	 * 
	 * @author BELDI Jamel on : 6 f�vr. 2014 14:47:00
	 * @return the listTypeAvenant
	 */
	public List<SelectItem> getListTypeAvenant() {
		if (listTypeAvenant == null || listTypeAvenant.isEmpty()) {
			listTypeAvenant = new ArrayList<SelectItem>();
			listTypeAvenant = getNcGenericList(UtilConstant.NC_TYPE_AVENANT_NAME);
		}
		return listTypeAvenant;
	}

	/**
	 * [ComboBckBean.listTypeAvenant] Setter
	 * 
	 * @author BELDI Jamel on : 6 f�vr. 2014 14:47:00
	 * @param listTypeAvenant
	 *            the listTypeAvenant to set
	 */
	public void setListTypeAvenant(List<SelectItem> listTypeAvenant) {
		this.listTypeAvenant = listTypeAvenant;
	}

	/**
	 * [ComboBckBean.listeTypeLieu] Getter
	 * 
	 * @author BELBRIK Oualid on : 12 f�vr. 2014 18:47:00
	 * @return the listTypeAvenant
	 */
	public List<SelectItem> getListeTypeLieu() {
		if (listeTypeLieu == null || listeTypeLieu.isEmpty()) {
			listeTypeLieu = new ArrayList<SelectItem>();
			listeTypeLieu = getNcGenericList(UtilConstant.NC_TYPE_LIEU_NAME);
		}
		return listeTypeLieu;
	}

	/**
	 * [ComboBckBean.listeTypeLieu] Setter
	 * 
	 * @author BELDI Jamel on : 12 f�vr. 2014 18:47:00
	 * @param listeTypeLieu
	 *            the listeTypeLieu to set
	 */
	public void setListeTypeLieu(List<SelectItem> listeTypeLieu) {
		this.listeTypeLieu = listeTypeLieu;
	}

	/**
	 * [ComboBckBean.listeTypeImplan] Getter
	 * 
	 * @author BELBRIK Oualid on : 12 f�vr. 2014 19:47:00
	 * @return the listeTypeImplan
	 */
	public List<SelectItem> getListeTypeImplan() {
		if (listeTypeImplan == null || listeTypeImplan.isEmpty()) {
			listeTypeImplan = new ArrayList<SelectItem>();
			listeTypeImplan = getNcGenericList(UtilConstant.NC_TYPE_IMPLANTATION_NAME);
		}
		return listeTypeImplan;
	}

	/**
	 * [ComboBckBean.listeTypeImplan] Setter
	 * 
	 * @author BELDI Jamel on : 12 f�vr. 2014 19:47:00
	 * @param listeTypeImplan
	 *            the listeTypeImplan to set
	 */
	public void setListeTypeImplan(List<SelectItem> listeTypeImplan) {
		this.listeTypeImplan = listeTypeImplan;
	}

	/**
	 * [ComboBckBean.refEquipementServiceImpl] Getter
	 * 
	 * @author BELBRIK Oualid on : 24 mars 2014 15:24:02
	 * @return the refEquipementServiceImpl
	 */
	public RefEquipementService getRefEquipementServiceImpl() {
		return refEquipementServiceImpl;
	}

	/**
	 * [ComboBckBean.refEquipementServiceImpl] Setter
	 * 
	 * @author BELBRIK Oualid on : 24 mars 2014 15:24:03
	 * @param refEquipementServiceImpl
	 *            the refEquipementServiceImpl to set
	 */
	public void setRefEquipementServiceImpl(
			RefEquipementService refEquipementServiceImpl) {
		this.refEquipementServiceImpl = refEquipementServiceImpl;
	}

	/**
	 * [ComboBckBean.refHoraireAccessServiceImpl] Getter
	 * 
	 * @author BELDI Jamel on : 4 mars 2014 12:17:24
	 * @return the refHoraireAccessServiceImpl
	 */
	public RefHoraireAccessService getRefHoraireAccessServiceImpl() {
		return refHoraireAccessServiceImpl;
	}

	/**
	 * [ComboBckBean.refHoraireAccessServiceImpl] Setter
	 * 
	 * @author BELDI Jamel on : 4 mars 2014 12:17:24
	 * @param refHoraireAccessServiceImpl
	 *            the refHoraireAccessServiceImpl to set
	 */
	public void setRefHoraireAccessServiceImpl(
			RefHoraireAccessService refHoraireAccessServiceImpl) {
		this.refHoraireAccessServiceImpl = refHoraireAccessServiceImpl;
	}

	/**
	 * @return the listeDescipline
	 */
	public List<SelectItem> getListeDescipline() {
		if (listeDescipline == null || listeDescipline.isEmpty()) {
			listeDescipline = new ArrayList<SelectItem>();
			listeDescipline = getNcGenericList(UtilConstant.NC_DISCIPLINE_NAME);
		}
		return listeDescipline;
	}

	/**
	 * @param listeDescipline
	 *            the listeDescipline to set
	 */
	public void setListeDescipline(List<SelectItem> listeDescipline) {
		this.listeDescipline = listeDescipline;
	}

	/**
	 * 
	 */
	public ComboBckBean() {

	}

	/**
	 * @return the listeNatureContrat
	 */
	public List<SelectItem> getListeNatureContrat() {
		if (listeNatureContrat == null || listeNatureContrat.isEmpty()) {
			listeNatureContrat = new ArrayList<SelectItem>();
			listeNatureContrat = getNcGenericList(UtilConstant.NC_NAT_CONTRAT_NAME);
		}
		return listeNatureContrat;
	}

	/**
	 * @param listeNatureContrat
	 *            the listeNatureContrat to set
	 */
	public void setListeNatureContrat(List<SelectItem> listeNatureContrat) {
		this.listeNatureContrat = listeNatureContrat;
	}

	public List<SelectItem> getListeCivilite() {
		if (listeCivilite == null || listeCivilite.isEmpty()) {
			listeCivilite = new ArrayList<SelectItem>();
			listeCivilite = getNcGenericList(UtilConstant.NC_CIVILITE_NAME);

		}
		return listeCivilite;
	}

	public void setListeCivilite(List<SelectItem> listeCivilite) {
		this.listeCivilite = listeCivilite;
	}

	/**
	 * @return the listeFonction
	 */
	public List<SelectItem> getListeFonction() {
		if (listeFonction == null || listeFonction.isEmpty()) {
			listeFonction = new ArrayList<SelectItem>();
			listeFonction = getNcGenericList(UtilConstant.NC_FONCTION_NAME);
		}
		return listeFonction;
	}

	/**
	 * @param listeFonction
	 *            the listeFonction to set
	 */
	public void setListeFonction(List<SelectItem> listeFonction) {
		this.listeFonction = listeFonction;
	}

	/**
	 * @return the listeNationalite
	 */
	public List<SelectItem> getListeNationalite() {
		if (listeNationalite == null || listeNationalite.isEmpty()) {
			listeNationalite = new ArrayList<SelectItem>();
			listeNationalite = getNcGenericList(UtilConstant.NC_NATIONALITE_NAME);
		}
		return listeNationalite;
	}

	/**
	 * @param listeNationalite
	 *            the listeNationalite to set
	 */
	public void setListeNationalite(List<SelectItem> listeNationalite) {
		this.listeNationalite = listeNationalite;
	}

	/**
	 * @return the listeSituationFamiliale
	 */
	public List<SelectItem> getListeSituationFamiliale() {
		if (listeSituationFamiliale == null
				|| listeSituationFamiliale.isEmpty()) {
			listeSituationFamiliale = new ArrayList<SelectItem>();
			listeSituationFamiliale = getNcGenericList(UtilConstant.NC_SIT_FAMILLIALE_NAME);
		}
		return listeSituationFamiliale;
	}

	/**
	 * @param listeSituationFamiliale
	 *            the listeSituationFamiliale to set
	 */
	public void setListeSituationFamiliale(
			List<SelectItem> listeSituationFamiliale) {
		this.listeSituationFamiliale = listeSituationFamiliale;
	}

	/**
	 * @return the listeSitSceNat
	 */
	public List<SelectItem> getListeSitSceNat() {
		if (listeSitSceNat == null || listeSitSceNat.isEmpty()) {
			listeSitSceNat = new ArrayList<SelectItem>();
			listeSitSceNat = getNcGenericList(UtilConstant.NC_SIT_SERVICE_NAT_NAME);
		}
		return listeSitSceNat;
	}

	/**
	 * @param listeSitSceNat
	 *            the listeSitSceNat to set
	 */
	public void setListeSitSceNat(List<SelectItem> listeSitSceNat) {
		this.listeSitSceNat = listeSitSceNat;
	}

	/**
	 * @return the GroupeSanguin
	 */
	public List<SelectItem> getListeGroupeSanguin() {
		if (listeGroupeSanguin == null || listeGroupeSanguin.isEmpty()) {
			listeGroupeSanguin = new ArrayList<SelectItem>();
			listeGroupeSanguin = getNcGenericList(UtilConstant.NC_GROUPE_SANGUIN_NAME);
		}
		return listeGroupeSanguin;
	}

	/**
	 * @param listeGroupeSanguin
	 *            the listeGroupeSanguin to set
	 */
	public void setListeGroupeSanguin(List<SelectItem> listeGroupeSanguin) {
		this.listeGroupeSanguin = listeGroupeSanguin;
	}

	/**
	 * @return the listeUniteMonetaire
	 */
	public List<SelectItem> getListeUniteMonetaire() {
		if (listeUniteMonetaire == null || listeUniteMonetaire.isEmpty()) {
			//
			// listeUniteMonetaire = new ArrayList<SelectItem>();
			// List<NomenclatureDto> list =
			// nomenclatureServiceImpl.findByName(ConverterService.NC_UNITE_MONITAIRE_NAME);
			// for (NomenclatureDto nomenclatureDto : list) {
			// SelectItem selectItem = new SelectItem(nomenclatureDto,
			// nomenclatureDto.getLibelleLongFr());
			// listeUniteMonetaire.add(selectItem);
			// }
			listeUniteMonetaire = getNcGenericList(UtilConstant.NC_UNITE_MONITAIRE_NAME);
		}
		return listeUniteMonetaire;
	}

	/**
	 * @param listeUniteMonetaire
	 *            the listeUniteMonetaire to set
	 */
	public void setListeUniteMonetaire(List<SelectItem> listeUniteMonetaire) {
		this.listeUniteMonetaire = listeUniteMonetaire;
	}

	/**
	 * [ComboBean.listRole] Getter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2014 15:58:21
	 * @return the listRole
	 */
	public List<SelectItem> getListRole() {
		if (listRole == null || listRole.isEmpty()) {
			listRole = new ArrayList<SelectItem>();
			listRole = getNcGenericList(UtilConstant.NC_ROLE_NAME);
		}
		return listRole;
	}

	/**
	 * [ComboBean.listRole] Setter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2014 15:58:21
	 * @param listRole
	 *            the listRole to set
	 */
	public void setListRole(List<SelectItem> listRole) {
		this.listRole = listRole;
	}

	/**
	 * [ComboBean.listRefGroupeDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 6 janv. 2014 16:55:44
	 * @return the listRefGroupeDto
	 */
	public List<SelectItem> getListRefGroupeDto() {
		if (listRefGroupeDto == null || listRefGroupeDto.isEmpty()) {
			listRefGroupeDto = new ArrayList<SelectItem>();
			List<RefGroupeDto> list = refGroupeServiceImpl
					.findAll(SessionValues.getIdEtablissement());
			for (RefGroupeDto refGroupeDto : list) {
				SelectItem selectItem = new SelectItem(refGroupeDto.getId(),
						refGroupeDto.getLlGroupe());
				listRefGroupeDto.add(selectItem);
			}

		}

		return listRefGroupeDto;
	}

	/**
	 * [ComboBean.listRefGroupeDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 6 janv. 2014 16:55:44
	 * @param listRefGroupeDto
	 *            the listRefGroupeDto to set
	 */
	public void setListRefGroupeDto(List<SelectItem> listRefGroupeDto) {
		this.listRefGroupeDto = listRefGroupeDto;
	}

	/**
	 * [ComboBean.listRefLieuDto] Getter
	 * 
	 * @author BELBRIK Oualid on : 17 02. 2014 09:25:44
	 * @return the listRefLieuDto
	 */
	public List<SelectItem> getListRefLieuDto() {
		if (listRefLieuDto == null || listRefLieuDto.isEmpty()) {
			listRefLieuDto = new ArrayList<SelectItem>();
			List<RefLieuDto> list = refLieuServiceImpl.findAll(SessionValues
					.getIdEtablissement());
			for (RefLieuDto refLieuDto : list) {
				SelectItem selectItem = new SelectItem(refLieuDto.getId(),
						refLieuDto.getDesignation());
				listRefLieuDto.add(selectItem);
			}

		}

		return listRefLieuDto;
	}

	/**
	 * [ComboBean.listRefLieuDto] Getter
	 * 
	 * @author BELBRIK Oualid on : 17 02. 2014 09:25:44
	 * @return the listRefLieuDto
	 */
	public void setListRefLieuDto(List<SelectItem> listRefLieuDto) {
		this.listRefLieuDto = listRefLieuDto;
	}

	/**
	 * [ComboBean.listRefIndividuDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 6 janv. 2014 16:55:44
	 * @return the listRefIndividuDto
	 */
	public List<SelectItem> getListRefIndividuDto() {
		if (listRefIndividuDto == null || listRefIndividuDto.isEmpty()) {
			listRefIndividuDto = new ArrayList<SelectItem>();
			List<RefIndividuDto> list = refIndividuServiceImpl.findAll();
			for (RefIndividuDto refIndividuDto : list) {
				SelectItem selectItem = new SelectItem(refIndividuDto.getId(),
						refIndividuDto.getNomLatin() + " "
								+ refIndividuDto.getPrenomLatin());
				listRefIndividuDto.add(selectItem);
			}

		}

		return listRefIndividuDto;
	}

	/**
	 * [ComboBean.listRefIndividuDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 6 janv. 2014 16:55:44
	 * @param listRefIndividuDto
	 *            the listRefIndividuDto to set
	 */
	public void setListRefIndividuDto(List<SelectItem> listRefIndividuDto) {
		this.listRefIndividuDto = listRefIndividuDto;
	}

	/**
	 * [ComboBean.refGroupeServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 6 janv. 2014 16:58:17
	 * @return the refGroupeServiceImpl
	 */
	public RefGroupeService getRefGroupeServiceImpl() {
		return refGroupeServiceImpl;
	}

	/**
	 * [ComboBean.refGroupeServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 6 janv. 2014 16:58:17
	 * @param refGroupeServiceImpl
	 *            the refGroupeServiceImpl to set
	 */
	public void setRefGroupeServiceImpl(RefGroupeService refGroupeServiceImpl) {
		this.refGroupeServiceImpl = refGroupeServiceImpl;
	}

	/**
	 * [ComboBean.refIndividuServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 6 janv. 2014 17:28:42
	 * @return the refIndividuServiceImpl
	 */
	public RefIndividuService getRefIndividuServiceImpl() {
		return refIndividuServiceImpl;
	}

	/**
	 * [ComboBean.refIndividuServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 6 janv. 2014 17:28:42
	 * @param refIndividuServiceImpl
	 *            the refIndividuServiceImpl to set
	 */
	public void setRefIndividuServiceImpl(
			RefIndividuService refIndividuServiceImpl) {
		this.refIndividuServiceImpl = refIndividuServiceImpl;
	}

	/**
	 * [ComboBean.listRefStructureDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 8 janv. 2014 13:54:32
	 * @return the listRefStructureDto
	 */
	public List<SelectItem> getListRefStructureDto() {
		if (listRefStructureDto == null || listRefStructureDto.isEmpty()) {
			listRefStructureDto = new ArrayList<SelectItem>();
			List<RefStructureDto> list = refStructureServiceImpl
					.findAll(SessionValues.getIdEtablissement());
			for (RefStructureDto refStructureDto : list) {
				SelectItem selectItem = new SelectItem(refStructureDto.getId(),
						refStructureDto.getLlStructureLatin());
				listRefStructureDto.add(selectItem);
			}

		}

		return listRefStructureDto;
	}

	/**
	 * [ComboBean.listRefStructureDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 8 janv. 2014 13:54:32
	 * @param listRefStructureDto
	 *            the listRefStructureDto to set
	 */
	public void setListRefStructureDto(List<SelectItem> listRefStructureDto) {
		this.listRefStructureDto = listRefStructureDto;
	}

	public List<SelectItem> getListRefDomaineLMDDto() {
		if (listRefDomaineLMDDto == null || listRefDomaineLMDDto.isEmpty()) {
			listRefDomaineLMDDto = new ArrayList<SelectItem>();
			List<RefDomaineLMDDto> list = refDomaineLMDServiceImpl.findAll();
			for (RefDomaineLMDDto refDomaineLMDDto : list) {
				SelectItem selectItem = new SelectItem(
						refDomaineLMDDto.getId(),
						refDomaineLMDDto.getLlDomaine(),
						refDomaineLMDDto.getLcDomaine());
				listRefDomaineLMDDto.add(selectItem);
			}

		}

		return listRefDomaineLMDDto;
	}

	public void setListRefDomaineLMDDto(List<SelectItem> listRefDomaineLMDDto) {
		this.listRefDomaineLMDDto = listRefDomaineLMDDto;
	}

	public void setListRefFiliereLmdDto(List<SelectItem> listRefFiliereLmdDto) {
		this.listRefFiliereLmdDto = listRefFiliereLmdDto;
	}

	public List<SelectItem> getListRefFiliereLmdDto() {
		if (listRefFiliereLmdDto == null || listRefFiliereLmdDto.isEmpty()) {
			listRefFiliereLmdDto = new ArrayList<SelectItem>();
			List<RefFiliereLmdDto> list = refFiliereLmdServiceImpl.findAll();
			for (RefFiliereLmdDto refFiliereLmdDto : list) {
				SelectItem selectItem = new SelectItem(
						refFiliereLmdDto.getId(),
						refFiliereLmdDto.getLcFiliere());
				listRefFiliereLmdDto.add(selectItem);
			}

		}

		return listRefFiliereLmdDto;
	}

	/**
	 * [ComboBckBean.setListRefEtablissementDto] Method
	 * 
	 * @author MAKERRI Sofiane on : 16 juil. 2014 15:38:07
	 * @param listRefEtablissementDto
	 */
	public void setListRefEtablissementDto(
			List<SelectItem> listRefEtablissementDto) {
		this.listRefEtablissementDto = listRefEtablissementDto;
	}

	/**
	 * [ComboBckBean.getListRefEtablissementDto] Method
	 * 
	 * @author MAKERRI Sofiane on : 16 juil. 2014 15:38:17
	 * @return
	 */
	public List<SelectItem> getListRefEtablissementDto() {
		if (listRefEtablissementDto == null
				|| listRefEtablissementDto.isEmpty()) {
			listRefEtablissementDto = new ArrayList<SelectItem>();
			List<RefEtablissementDto> list = refEtablissementServiceImpl
					.findAll();
			for (RefEtablissementDto refEtablissementDto : list) {
				SelectItem selectItem = new SelectItem(
						refEtablissementDto.getId(),
						refEtablissementDto.getLlEtablissementLatin());
				listRefEtablissementDto.add(selectItem);
			}

		}

		return listRefEtablissementDto;
	}

	/**
	 * [ComboBckBean.getRefEtablissementServiceImpl] Method
	 * 
	 * @author MAKERRI Sofiane on : 16 juil. 2014 15:38:23
	 * @return
	 */
	public RefEtablissementService getRefEtablissementServiceImpl() {
		return refEtablissementServiceImpl;
	}

	/**
	 * [ComboBckBean.setRefEtablissementServiceImpl] Method
	 * 
	 * @author MAKERRI Sofiane on : 16 juil. 2014 15:38:26
	 * @param refEtablissementServiceImpl
	 */
	public void setRefEtablissementServiceImpl(
			RefEtablissementService refEtablissementServiceImpl) {
		this.refEtablissementServiceImpl = refEtablissementServiceImpl;
	}

	/**
	 * [ComboBean.refStructureServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 8 janv. 2014 13:57:05
	 * @return the refStructureServiceImpl
	 */
	public RefStructureService getRefStructureServiceImpl() {
		return refStructureServiceImpl;
	}

	/**
	 * [ComboBean.refStructureServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 8 janv. 2014 13:57:05
	 * @param refStructureServiceImpl
	 *            the refStructureServiceImpl to set
	 */
	public void setRefStructureServiceImpl(
			RefStructureService refStructureServiceImpl) {
		this.refStructureServiceImpl = refStructureServiceImpl;
	}

	public RefDomaineLMDService getRefDomaineLMDServiceImpl() {
		return refDomaineLMDServiceImpl;
	}

	public void setRefDomaineLMDServiceImpl(
			RefDomaineLMDService refDomaineLMDServiceImpl) {
		this.refDomaineLMDServiceImpl = refDomaineLMDServiceImpl;
	}

	public RefFiliereLmdService getRefFiliereLmdServiceImpl() {
		return refFiliereLmdServiceImpl;
	}

	public void setRefFiliereLmdServiceImpl(
			RefFiliereLmdService refFiliereLmdServiceImpl) {
		this.refFiliereLmdServiceImpl = refFiliereLmdServiceImpl;
	}

	/**
	 * [ComboBckBean.listTypeAdresse] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 janv. 2014 08:47:11
	 * @return the listTypeAdresse
	 */
	public List<SelectItem> getListTypeAdresse() {
		if (listTypeAdresse == null || listTypeAdresse.isEmpty()) {
			listTypeAdresse = new ArrayList<SelectItem>();
			listTypeAdresse = getNcGenericList(UtilConstant.NC_TYPE_ADRESSE_NAME);
		}
		return listTypeAdresse;
	}

	/**
	 * [ComboBckBean.listTypeAdresse] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 janv. 2014 08:47:11
	 * @param listTypeAdresse
	 *            the listTypeAdresse to set
	 */
	public void setListTypeAdresse(List<SelectItem> listTypeAdresse) {
		this.listTypeAdresse = listTypeAdresse;
	}

	/**
	 * [ComboBckBean.listPays] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 janv. 2014 08:47:11
	 * @return the listPays
	 */
	public List<SelectItem> getListPays() {
		if (listPays == null || listPays.isEmpty()) {
			listPays = new ArrayList<SelectItem>();
			listPays = getNcGenericList(UtilConstant.NC_PAYS_NAME);
		}
		return listPays;
	}

	/**
	 * [ComboBckBean.listPays] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 janv. 2014 08:47:11
	 * @param listPays
	 *            the listPays to set
	 */
	public void setListPays(List<SelectItem> listPays) {
		this.listPays = listPays;
	}

	/**
	 * [ComboBckBean.listWilaya] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 janv. 2014 08:47:11
	 * @return the listWilaya
	 */
	public List<SelectItem> getListWilaya() {
		if (listWilaya == null || listWilaya.isEmpty()) {
			listWilaya = new ArrayList<SelectItem>();
			listWilaya = getNcGenericList(UtilConstant.NC_WILAYA_NAME);
		}
		return listWilaya;
	}

	/**
	 * [ComboBckBean.listWilaya] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 janv. 2014 08:47:11
	 * @param listWilaya
	 *            the listWilaya to set
	 */
	public void setListWilaya(List<SelectItem> listWilaya) {
		this.listWilaya = listWilaya;
	}

	/**
	 * [ComboBckBean.listDaira] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 janv. 2014 08:47:11
	 * @return the listDaira
	 */
	public List<SelectItem> getListDaira() {
		if (listDaira == null || listDaira.isEmpty()) {
			listDaira = new ArrayList<SelectItem>();
			listDaira = getNcGenericList(UtilConstant.NC_DAIRA_NAME);
		}
		return listDaira;
	}

	/**
	 * [ComboBckBean.getListDaira] Method
	 * 
	 * @author MAKERRI Sofiane on : 16 avr. 2014 09:51:27
	 * @param idWilaya
	 * @return
	 */
	public List<SelectItem> getListDaira(Integer idWilaya) {
		List<SelectItem> listDaira = new ArrayList<SelectItem>();
		List<NomenclatureDto> list = nomenclatureServiceImpl
				.findNcCompositeList(idWilaya, UtilConstant.NC_DAIRA_NAME);
		for (NomenclatureDto nomenclatureDto : list) {
			SelectItem selectItem = new SelectItem(nomenclatureDto.getId(),
					nomenclatureDto.getLibelleLongFr());
			listDaira.add(selectItem);
		}
		return listDaira;
	}

	/**
	 * [ComboBckBean.listDaira] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 janv. 2014 08:47:11
	 * @param listDaira
	 *            the listDaira to set
	 */
	public void setListDaira(List<SelectItem> listDaira) {
		this.listDaira = listDaira;
	}

	/**
	 * [ComboBckBean.getNcGenericList] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 janv. 2014 15:25:49
	 * @param nc_name
	 * @return
	 */
	private List<SelectItem> getNcGenericList(String nc_name) {

		List<SelectItem> nomenclatureDtoList = new ArrayList<SelectItem>();
		List<NomenclatureDto> list = nomenclatureServiceImpl
				.findByName(nc_name);
		for (NomenclatureDto nomenclatureDto : list) {
			SelectItem selectItem = new SelectItem(nomenclatureDto.getId(),
					nomenclatureDto.getLibelleLongFr());
			nomenclatureDtoList.add(selectItem);
		}
		return nomenclatureDtoList;
	}

	/**
	 * [ComboBckBean.findNcGenericList] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 janv. 2014 15:25:53
	 * @param nc_name
	 * @param orderby
	 * @return
	 */
	private List<SelectItem> findNcGenericList(String nc_name, String orderby) {

		List<SelectItem> nomenclatureDtoList = new ArrayList<SelectItem>();
		List<NomenclatureDto> list = nomenclatureServiceImpl.findByName(
				nc_name, orderby);
		for (NomenclatureDto nomenclatureDto : list) {
			SelectItem selectItem = new SelectItem(nomenclatureDto.getId(),
					nomenclatureDto.getLibelleLongFr());
			nomenclatureDtoList.add(selectItem);

		}
		return nomenclatureDtoList;
	}

	/**
	 * [ComboBckBean.findNcCompositeList] Method
	 * 
	 * @author MAKERRI Sofiane on : 26 janv. 2014 12:13:41
	 * @param ncName
	 * @param ncValue
	 * @param ncTarget
	 * @return
	 */
	private List<SelectItem> findNcCompositeList(String ncName, String ncValue,
			String ncTarget) {

		List<SelectItem> nomenclatureDtoList = new ArrayList<SelectItem>();
		List<NomenclatureDto> list = nomenclatureServiceImpl
				.findNcCompositeList(ncName, ncValue, ncTarget);
		for (NomenclatureDto nomenclatureDto : list) {
			SelectItem selectItem = new SelectItem(nomenclatureDto.getId(),
					nomenclatureDto.getLibelleLongFr());
			nomenclatureDtoList.add(selectItem);
		}
		return nomenclatureDtoList;
	}

	/**
	 * [ComboBckBean.listCommune] Getter
	 * 
	 * @author MAKERRI Sofiane on : 21 janv. 2014 10:00:21
	 * @return the listCommune
	 */
	public List<SelectItem> getListCommune() {
		if (listCommune == null || listCommune.isEmpty()) {
			listCommune = new ArrayList<SelectItem>();
			listCommune = getNcGenericList(UtilConstant.NC_COMMUNE_NAME);

		}
		return listCommune;
	}

	/**
	 * [ComboBckBean.listCommune] Setter
	 * 
	 * @author MAKERRI Sofiane on : 21 janv. 2014 10:00:21
	 * @param listCommune
	 *            the listCommune to set
	 */
	public void setListCommune(List<SelectItem> listCommune) {
		this.listCommune = listCommune;
	}

	/**
	 * [ComboBckBean.listNatTel] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 janv. 2014 17:16:06
	 * @return the listNatTel
	 */
	public List<SelectItem> getListNatTel() {
		if (listNatTel == null || listNatTel.isEmpty()) {
			listNatTel = new ArrayList<SelectItem>();
			listNatTel = getNcGenericList(UtilConstant.NC_NAT_TEL_NAME);
		}
		return listNatTel;
	}

	/**
	 * [ComboBckBean.listNatTel] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 janv. 2014 17:16:06
	 * @param listNatTel
	 *            the listNatTel to set
	 */
	public void setListNatTel(List<SelectItem> listNatTel) {
		this.listNatTel = listNatTel;
	}

	/**
	 * [ComboBckBean.listTypeTel] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 janv. 2014 17:16:06
	 * @return the listTypeTel
	 */
	public List<SelectItem> getListTypeTel() {
		if (listTypeTel == null || listTypeTel.isEmpty()) {
			listTypeTel = new ArrayList<SelectItem>();
			listTypeTel = getNcGenericList(UtilConstant.NC_TYPE_TEL_NAME);
		}
		return listTypeTel;
	}

	/**
	 * [ComboBckBean.listTypeTel] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 janv. 2014 17:16:06
	 * @param listTypeTel
	 *            the listTypeTel to set
	 */
	public void setListTypeTel(List<SelectItem> listTypeTel) {
		this.listTypeTel = listTypeTel;
	}

	/**
	 * [ComboBckBean.listTypeMail] Getter
	 * 
	 * @author MAKERRI Sofiane on : 19 janv. 2014 11:17:33
	 * @return the listTypeMail
	 */
	public List<SelectItem> getListTypeMail() {
		if (listTypeMail == null || listTypeMail.isEmpty()) {
			listTypeMail = new ArrayList<SelectItem>();
			listTypeMail = getNcGenericList(UtilConstant.NC_TYPE_MAIL_NAME);
		}
		return listTypeMail;
	}

	/**
	 * [ComboBckBean.listTypeMail] Setter
	 * 
	 * @author MAKERRI Sofiane on : 19 janv. 2014 11:17:33
	 * @param listTypeMail
	 *            the listTypeMail to set
	 */
	public void setListTypeMail(List<SelectItem> listTypeMail) {
		this.listTypeMail = listTypeMail;
	}

	/**
	 * [ComboBckBean.listNatureAdresseElectronique] Getter
	 * 
	 * @author MAKERRI Sofiane on : 19 janv. 2014 11:17:33
	 * @return the listNatureAdresseElectronique
	 */
	public List<SelectItem> getListNatureAdresseElectronique() {
		if (listNatureAdresseElectronique == null
				|| listNatureAdresseElectronique.isEmpty()) {
			listNatureAdresseElectronique = new ArrayList<SelectItem>();
			listNatureAdresseElectronique = getNcGenericList(UtilConstant.NC_NAT_EMAIL_NAME);
		}
		return listNatureAdresseElectronique;
	}

	/**
	 * [ComboBckBean.listNatureAdresseElectronique] Setter
	 * 
	 * @author MAKERRI Sofiane on : 19 janv. 2014 11:17:33
	 * @param listNatureAdresseElectronique
	 *            the listNatureAdresseElectronique to set
	 */
	public void setListNatureAdresseElectronique(
			List<SelectItem> listNatureAdresseElectronique) {
		this.listNatureAdresseElectronique = listNatureAdresseElectronique;
	}

	public NomenclatureService getNomenclatureServiceImpl() {
		return nomenclatureServiceImpl;
	}

	public void setNomenclatureServiceImpl(
			NomenclatureService nomenclatureServiceImpl) {
		this.nomenclatureServiceImpl = nomenclatureServiceImpl;
	}

	/**
	 * [ComboBckBean.listNc] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 janv. 2014 12:16:39
	 * @return the listNc
	 */
	public List<SelectItem> getListNc() {
		if (listNc == null || listNc.isEmpty()) {
			listNc = new ArrayList<SelectItem>();
			List<NcNamesDto> list = ncNamesServiceImpl.findAll();
			for (NcNamesDto ncNamesDto : list) {
				SelectItem selectItem = new SelectItem(ncNamesDto.getId(),
						ncNamesDto.getNomNomenclature());
				listNc.add(selectItem);
			}
		}
		return listNc;
	}

	/**
	 * [ComboBckBean.listNc] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 janv. 2014 12:16:39
	 * @param listNc
	 *            the listNc to set
	 */
	public void setListNc(List<SelectItem> listNc) {
		this.listNc = listNc;
	}

	/**
	 * [ComboBckBean.listTypeStructure] Getter
	 * 
	 * @author BELDI Jamel on : 29 janv. 2014 11:24:29
	 * @return the listTypeStructure
	 */
	public List<SelectItem> getListTypeStructure() {
		if (listTypeStructure == null || listTypeStructure.isEmpty()) {
			listTypeStructure = new ArrayList<SelectItem>();
			listTypeStructure = getNcGenericList(UtilConstant.NC_TYPE_STUCTURE_NAME);
		}
		return listTypeStructure;
	}

	/**
	 * [ComboBckBean.listTypeStructure] Setter
	 * 
	 * @author BELDI Jamel on : 29 janv. 2014 11:24:29
	 * @param listTypeStructure
	 *            the listTypeStructure to set
	 */
	public void setListTypeStructure(List<SelectItem> listTypeStructure) {
		this.listTypeStructure = listTypeStructure;
	}

	/**
	 * [ComboBckBean.listFormeJuridique] Getter
	 * 
	 * @author BELDI Jamel on : 29 janv. 2014 11:25:44
	 * @return the listFormeJuridique
	 */
	public List<SelectItem> getListFormeJuridique() {
		if (listFormeJuridique == null || listFormeJuridique.isEmpty()) {
			listFormeJuridique = new ArrayList<SelectItem>();
			listFormeJuridique = getNcGenericList(UtilConstant.NC_FORME_JURIDIQUE_NAME);
		}
		return listFormeJuridique;
	}

	/**
	 * [ComboBckBean.listFormeJuridique] Setter
	 * 
	 * @author BELDI Jamel on : 29 janv. 2014 11:25:44
	 * @param listFormeJuridique
	 *            the listFormeJuridique to set
	 */
	public void setListFormeJuridique(List<SelectItem> listFormeJuridique) {
		this.listFormeJuridique = listFormeJuridique;
	}

	/**
	 * [ComboBckBean.listStatut] Getter
	 * 
	 * @author BELDI Jamel on : 29 janv. 2014 11:25:44
	 * @return the listStatut
	 */
	public List<SelectItem> getListStatut() {
		if (listStatut == null || listStatut.isEmpty()) {
			listStatut = new ArrayList<SelectItem>();
			listStatut = getNcGenericList(UtilConstant.NC_STATUS_STRUCTURE_NAME);
		}
		return listStatut;
	}

	/**
	 * [ComboBckBean.listStatut] Setter
	 * 
	 * @author BELDI Jamel on : 29 janv. 2014 11:25:44
	 * @param listStatut
	 *            the listStatut to set
	 */
	public void setListStatut(List<SelectItem> listStatut) {
		this.listStatut = listStatut;
	}

	/**
	 * [ComboBckBean.ncNamesServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 janv. 2014 12:17:31
	 * @return the ncNamesServiceImpl
	 */
	public NcNamesService getNcNamesServiceImpl() {
		return ncNamesServiceImpl;
	}

	/**
	 * [ComboBckBean.ncNamesServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 janv. 2014 12:17:31
	 * @param ncNamesServiceImpl
	 *            the ncNamesServiceImpl to set
	 */
	public void setNcNamesServiceImpl(NcNamesService ncNamesServiceImpl) {
		this.ncNamesServiceImpl = ncNamesServiceImpl;
	}

	/**
	 * [ComboBckBean.listRefContratDto] Getter
	 * 
	 * @author BELDI Jamel on : 6 f�vr. 2014 17:08:56
	 * @return the listRefContratDto
	 */
	public List<SelectItem> getListRefContratDto() {
		if (listRefContratDto == null || listRefContratDto.isEmpty()) {
			listRefContratDto = new ArrayList<SelectItem>();
			List<RefContratDto> list = refContratServiceImpl.findGeneric("",
					false);
			for (RefContratDto refContratDto : list) {
				SelectItem selectItem = new SelectItem(refContratDto.getId(),
						refContratDto.getIdentifiant());
				listRefContratDto.add(selectItem);
			}
		}
		return listRefContratDto;
	}

	/**
	 * [ComboBckBean.listRefContratDto] Setter
	 * 
	 * @author BELDI Jamel on : 6 f�vr. 2014 17:08:56
	 * @param listRefContratDto
	 *            the listRefContratDto to set
	 */
	public void setListRefContratDto(List<SelectItem> listRefContratDto) {
		this.listRefContratDto = listRefContratDto;
	}

	/**
	 * [ComboBckBean.refContratServiceImpl] Getter
	 * 
	 * @author BELDI Jamel on : 6 f�vr. 2014 17:08:56
	 * @return the refContratServiceImpl
	 */
	public RefContratService getRefContratServiceImpl() {
		return refContratServiceImpl;
	}

	/**
	 * [ComboBckBean.refContratServiceImpl] Setter
	 * 
	 * @author BELDI Jamel on : 6 f�vr. 2014 17:08:56
	 * @param refContratServiceImpl
	 *            the refContratServiceImpl to set
	 */
	public void setRefContratServiceImpl(RefContratService refContratServiceImpl) {
		this.refContratServiceImpl = refContratServiceImpl;
	}

	/**
	 * [ComboBckBean.refLieuServiceImpl] Getter
	 * 
	 * @author BELBRIK Oualid on : 17 f�vr. 2014 09:31:11
	 * @return the refLieuServiceImpl
	 */
	public RefLieuService getRefLieuServiceImpl() {
		return refLieuServiceImpl;
	}

	/**
	 * [ComboBckBean.refLieuServiceImpl] Setter
	 * 
	 * @author BELBRIK Oualid on : 17 f�vr. 2014 09:31:11
	 * @param refLieuServiceImpl
	 *            the refLieuServiceImpl to set
	 */
	public void setRefLieuServiceImpl(RefLieuService refLieuServiceImpl) {
		this.refLieuServiceImpl = refLieuServiceImpl;
	}

	/**
	 * [ComboBckBean.listTypeEvenement] Getter
	 * 
	 * @author BELDI Jamel on :16 fevrier. 2014 10:30:29
	 * @return the listTypeEvenement
	 */
	public List<SelectItem> getListTypeEvenement() {
		if (listTypeEvenement == null || listTypeEvenement.isEmpty()) {
			listTypeEvenement = new ArrayList<SelectItem>();
			listTypeEvenement = getNcGenericList(UtilConstant.NC_TYPE_EVENEMENT_NAME);
		}
		return listTypeEvenement;
	}

	/**
	 * [ComboBckBean.listTypeTache] Getter
	 * 
	 * @author BELDI Jamel on :16 fevrier. 2014 10:30:29
	 * @return the listTypeTache
	 */
	public List<SelectItem> getListTypeTache() {
		if (listTypeTache == null || listTypeTache.isEmpty()) {
			listTypeTache = new ArrayList<SelectItem>();
			listTypeTache = getNcGenericList(UtilConstant.NC_TYPE_TACHE_NAME);
		}
		return listTypeTache;
	}

	/**
	 * [ComboBckBean.listRepetition] Getter
	 * 
	 * @author BELDI Jamel on :16 fevrier. 2014 10:30:29
	 * @return the listTypeTache
	 */
	public List<SelectItem> getListRepetition() {
		if (listRepetition == null || listRepetition.isEmpty()) {
			listRepetition = new ArrayList<SelectItem>();
			listRepetition = getNcGenericList(UtilConstant.NC_REPETITION_NAME);
		}
		return listRepetition;
	}

	/**
	 * [ComboBckBean.refPlageAdresseServiceImpl] Getter
	 * 
	 * @author BELDI Jamel on : 24 f�vr. 2014 17:04:15
	 * @return the refPlageAdresseServiceImpl
	 */
	public RefPlageAdresseService getRefPlageAdresseServiceImpl() {
		return refPlageAdresseServiceImpl;
	}

	/**
	 * [ComboBckBean.refPlageAdresseServiceImpl] Setter
	 * 
	 * @author BELDI Jamel on : 24 f�vr. 2014 17:04:15
	 * @param refPlageAdresseServiceImpl
	 *            the refPlageAdresseServiceImpl to set
	 */
	public void setRefPlageAdresseServiceImpl(
			RefPlageAdresseService refPlageAdresseServiceImpl) {
		this.refPlageAdresseServiceImpl = refPlageAdresseServiceImpl;
	}

	/**
	 * [ComboBckBean.refDomaineServiceImpl] Getter
	 * 
	 * @author BELDI Jamel on : 20 mars 2014 17:52:23
	 * @return the refDomaineServiceImpl
	 */
	public RefDomaineService getRefDomaineServiceImpl() {
		return refDomaineServiceImpl;
	}

	/**
	 * [ComboBckBean.refDomaineServiceImpl] Setter
	 * 
	 * @author BELDI Jamel on : 20 mars 2014 17:52:23
	 * @param refDomaineServiceImpl
	 *            the refDomaineServiceImpl to set
	 */
	public void setRefDomaineServiceImpl(RefDomaineService refDomaineServiceImpl) {
		this.refDomaineServiceImpl = refDomaineServiceImpl;
	}

	/**
	 * [ComboBckBean.listePlageAdresse] Getter
	 * 
	 * @author BELDI Jamel on : 24 f�vr. 2014 17:04:15
	 * @return the listePlageAdresse
	 */
	public List<SelectItem> getListePlageAdresse() {
		if (listePlageAdresse == null || listePlageAdresse.isEmpty()) {
			listePlageAdresse = new ArrayList<SelectItem>();
			List<RefPlageAdresseDto> list = refPlageAdresseServiceImpl
					.findAll(SessionValues.getIdEtablissement());
			for (RefPlageAdresseDto refPlageAdresseDto : list) {
				SelectItem selectItem = new SelectItem(
						refPlageAdresseDto.getId(), refPlageAdresseDto.getNom());
				listePlageAdresse.add(selectItem);
			}
		}
		return listePlageAdresse;
	}

	/**
	 * [ComboBckBean.listePlageAdresse] Getter
	 * 
	 * @author BELDI Jamel on : 24 f�vr. 2014 17:04:15
	 * @return the listePlageAdresse
	 */
	public List<SelectItem> getListeHoraireAcces() {
		if (listeHoraireAcces == null || listeHoraireAcces.isEmpty()) {
			listeHoraireAcces = new ArrayList<SelectItem>();
			List<RefHoraireAccessDto> list = refHoraireAccessServiceImpl
					.findAll();
			for (RefHoraireAccessDto refHoraireAccessDto : list) {
				SelectItem selectItem = new SelectItem(
						refHoraireAccessDto.getId(),
						refHoraireAccessDto.getNom());
				listeHoraireAcces.add(selectItem);
			}
		}
		return listeHoraireAcces;
	}

	public List<SelectItem> getListeQuestionSecrete() {
		if (listeQuestionSecrete == null || listeQuestionSecrete.isEmpty()) {
			listeQuestionSecrete = new ArrayList<SelectItem>();
			listeQuestionSecrete = getNcGenericList(UtilConstant.NC_QUESTION_SECRETE_NAME);
		}
		return listeQuestionSecrete;
	}

	public void setListeQuestionSecrete(List<SelectItem> listeQuestionSecrete) {
		this.listeQuestionSecrete = listeQuestionSecrete;
	}

	public List<SelectItem> getListLangue() {

		if (listLangue == null || listLangue.isEmpty()) {
			listLangue = new ArrayList<SelectItem>();
			listLangue = getNcGenericList(UtilConstant.NC_LANGUE_NAME);
		}
		return listLangue;
	}

	public List<SelectItem> getListTypeDocument() {
		if (listTypeDocument == null || listTypeDocument.isEmpty()) {
			listTypeDocument = new ArrayList<SelectItem>();
			listTypeDocument = getNcGenericList(UtilConstant.NC_TYPE_DOCUMENT_NAME);
		}
		return listTypeDocument;
	}

	public List<SelectItem> getListNatureDocument() {
		if (listNatureDocument == null || listNatureDocument.isEmpty()) {
			listNatureDocument = new ArrayList<SelectItem>();
			listNatureDocument = getNcGenericList(UtilConstant.NC_NATURE_DOCUMENT_NAME);
		}
		return listNatureDocument;
	}

	public List<SelectItem> getListMotCle() {
		if (listMotCle == null || listMotCle.isEmpty()) {
			listMotCle = new ArrayList<SelectItem>();
			listMotCle = getNcGenericList(UtilConstant.NC_MOT_CLE_NAME);
		}
		return listMotCle;
	}

	public List<SelectItem> getListClassement() {
		if (listClassement == null || listClassement.isEmpty()) {
			listClassement = new ArrayList<SelectItem>();
			listClassement = getNcGenericList(UtilConstant.NC_CLASSEMENT_NAME);
		}
		return listClassement;
	}

	public List<SelectItem> getListTypeIndividu() {
		if (listTypeIndividu == null || listTypeIndividu.isEmpty()) {
			listTypeIndividu = new ArrayList<SelectItem>();
			listTypeIndividu = getNcGenericList(UtilConstant.NC_TYPE_INDIVIDU_NAME);
		}
		return listTypeIndividu;
	}

	public void setListTypeEvenement(List<SelectItem> listTypeEvenement) {
		this.listTypeEvenement = listTypeEvenement;
	}

	public void setListRepetition(List<SelectItem> listRepetition) {
		this.listRepetition = listRepetition;
	}

	public void setListTypeTache(List<SelectItem> listTypeTache) {
		this.listTypeTache = listTypeTache;
	}

	public void setListTypeIndividu(List<SelectItem> listTypeIndividu) {
		this.listTypeIndividu = listTypeIndividu;
	}

	public void setListLangue(List<SelectItem> listLangue) {
		this.listLangue = listLangue;
	}

	public void setListTypeDocument(List<SelectItem> listTypeDocument) {
		this.listTypeDocument = listTypeDocument;
	}

	public void setListNatureDocument(List<SelectItem> listNatureDocument) {
		this.listNatureDocument = listNatureDocument;
	}

	public void setListMotCle(List<SelectItem> listMotCle) {
		if (listMotCle == null || listMotCle.isEmpty()) {
			listMotCle = new ArrayList<SelectItem>();
			listMotCle = getNcGenericList(UtilConstant.NC_MOT_CLE_NAME);
		}
		this.listMotCle = listMotCle;
	}

	public void setListClassement(List<SelectItem> listClassement) {
		this.listClassement = listClassement;
	}

	/**
	 * [ComboBckBean.listePlageAdresse] Setter
	 * 
	 * @author BELBRIK Oualid on : 26 mars 2014 15:36:08
	 * @param listePlageAdresse
	 *            the listePlageAdresse to set
	 */
	public void setListePlageAdresse(List<SelectItem> listePlageAdresse) {
		this.listePlageAdresse = listePlageAdresse;
	}

	/**
	 * [ComboBckBean.listeHoraireAcces] Setter
	 * 
	 * @author BELBRIK Oualid on : 26 mars 2014 15:36:08
	 * @param listeHoraireAcces
	 *            the listeHoraireAcces to set
	 */
	public void setListeHoraireAcces(List<SelectItem> listeHoraireAcces) {
		this.listeHoraireAcces = listeHoraireAcces;
	}

	/**
	 * [ComboBckBean.refPeriodeServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 3 avr. 2014 11:52:00
	 * @return the refPeriodeServiceImpl
	 */
	public RefPeriodeService getRefPeriodeServiceImpl() {
		return refPeriodeServiceImpl;
	}

	/**
	 * [ComboBckBean.refPeriodeServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 3 avr. 2014 11:52:00
	 * @param refPeriodeServiceImpl
	 *            the refPeriodeServiceImpl to set
	 */
	public void setRefPeriodeServiceImpl(RefPeriodeService refPeriodeServiceImpl) {
		this.refPeriodeServiceImpl = refPeriodeServiceImpl;
	}

	/**
	 * [ComboBckBean.listePeriode] Getter
	 * 
	 * @author MAKERRI Sofiane on : 3 avr. 2014 11:48:05
	 * @return the listePeriode
	 */
	public List<SelectItem> getListePeriode() {
		if (listePeriode == null || listePeriode.isEmpty()) {
			listePeriode = new ArrayList<SelectItem>();
			List<RefPeriodeDto> list = refPeriodeServiceImpl.findAll();
			for (RefPeriodeDto refPeriodeDto : list) {
				SelectItem selectItem = new SelectItem(refPeriodeDto.getId(),
						refPeriodeDto.getNom());
				listePeriode.add(selectItem);
			}
		}

		return listePeriode;
	}

	/**
	 * [ComboBckBean.listePeriode] Setter
	 * 
	 * @author MAKERRI Sofiane on : 3 avr. 2014 11:48:05
	 * @param listePeriode
	 *            the listePeriode to set
	 */
	public void setListePeriode(List<SelectItem> listePeriode) {
		this.listePeriode = listePeriode;
	}

	/**
	 * [ComboBckBean.listTypeEtablissement] Getter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 14:59:05
	 * @return the listTypeEtablissement
	 */
	public List<SelectItem> getListTypeEtablissement() {
		if (listTypeEtablissement == null || listTypeEtablissement.isEmpty()) {
			listTypeEtablissement = new ArrayList<SelectItem>();
			listTypeEtablissement = getNcGenericList(UtilConstant.NC_TYPE_ETABLISSEMENT_NAME);
		}
		return listTypeEtablissement;
	}

	/**
	 * [ComboBckBean.listTypeEtablissement] Setter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 14:59:05
	 * @param listTypeEtablissement
	 *            the listTypeEtablissement to set
	 */
	public void setListTypeEtablissement(List<SelectItem> listTypeEtablissement) {
		this.listTypeEtablissement = listTypeEtablissement;
	}

	/**
	 * [ComboBckBean.listEtablissements] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 avr. 2014 15:38:14
	 * @return the listEtablissements
	 */
	public List<RefEtablissementDto> getListEtablissements() {
		listEtablissements = refEtablissementServiceImpl.findAll();
		return listEtablissements;
	}

	/**
	 * [ComboBckBean.listEtablissements] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 avr. 2014 15:38:14
	 * @param listEtablissements
	 *            the listEtablissements to set
	 */
	public void setListEtablissements(
			List<RefEtablissementDto> listEtablissements) {
		this.listEtablissements = listEtablissements;
	}

	/**
	 * [ComboBckBean.defaultGroupeSanguin] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @return the defaultGroupeSanguin
	 */
	public Integer getDefaultGroupeSanguin() {
		NcNamesDto ncNamesDto = ncNamesServiceImpl
				.findDefaultValue(UtilConstant.NC_GROUPE_SANGUIN_NAME);
		if (ncNamesDto == null) {
			defaultGroupeSanguin = null;
		} else {
			defaultGroupeSanguin = ncNamesDto.getIdDefaultValue();
		}
		return defaultGroupeSanguin;
	}

	/**
	 * [ComboBckBean.defaultGroupeSanguin] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @param defaultGroupeSanguin
	 *            the defaultGroupeSanguin to set
	 */
	public void setDefaultGroupeSanguin(Integer defaultGroupeSanguin) {
		this.defaultGroupeSanguin = defaultGroupeSanguin;
	}

	/**
	 * [ComboBckBean.defaultSitSceNat] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @return the defaultSitSceNat
	 */
	public Integer getDefaultSitSceNat() {
		NcNamesDto ncNamesDto = ncNamesServiceImpl
				.findDefaultValue(UtilConstant.NC_SIT_SERVICE_NAT_NAME);
		if (ncNamesDto == null) {
			defaultSitSceNat = null;
		} else {
			defaultSitSceNat = ncNamesDto.getIdDefaultValue();
		}
		return defaultSitSceNat;
	}

	/**
	 * [ComboBckBean.defaultSitSceNat] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @param defaultSitSceNat
	 *            the defaultSitSceNat to set
	 */
	public void setDefaultSitSceNat(Integer defaultSitSceNat) {
		this.defaultSitSceNat = defaultSitSceNat;
	}

	/**
	 * [ComboBckBean.defaultSituationFamiliale] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @return the defaultSituationFamiliale
	 */
	public Integer getDefaultSituationFamiliale() {
		NcNamesDto ncNamesDto = ncNamesServiceImpl
				.findDefaultValue(UtilConstant.NC_SIT_FAMILLIALE_NAME);
		if (ncNamesDto == null) {
			defaultSituationFamiliale = null;
		} else {
			defaultSituationFamiliale = ncNamesDto.getIdDefaultValue();
		}
		return defaultSituationFamiliale;
	}

	/**
	 * [ComboBckBean.defaultSituationFamiliale] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @param defaultSituationFamiliale
	 *            the defaultSituationFamiliale to set
	 */
	public void setDefaultSituationFamiliale(Integer defaultSituationFamiliale) {
		this.defaultSituationFamiliale = defaultSituationFamiliale;
	}

	/**
	 * [ComboBckBean.defaultNationalite] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @return the defaultNationalite
	 */
	public Integer getDefaultNationalite() {
		NcNamesDto ncNamesDto = ncNamesServiceImpl
				.findDefaultValue(UtilConstant.NC_NATIONALITE_NAME);
		if (ncNamesDto == null) {
			defaultNationalite = null;
		} else {
			defaultNationalite = ncNamesDto.getIdDefaultValue();
		}
		return defaultNationalite;
	}

	/**
	 * [ComboBckBean.defaultNationalite] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @param defaultNationalite
	 *            the defaultNationalite to set
	 */
	public void setDefaultNationalite(Integer defaultNationalite) {
		this.defaultNationalite = defaultNationalite;
	}

	/**
	 * [ComboBckBean.defaultFonction] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @return the defaultFonction
	 */
	public Integer getDefaultFonction() {
		NcNamesDto ncNamesDto = ncNamesServiceImpl
				.findDefaultValue(UtilConstant.NC_FONCTION_NAME);
		if (ncNamesDto == null) {
			defaultFonction = null;
		} else {
			defaultFonction = ncNamesDto.getIdDefaultValue();
		}
		return defaultFonction;
	}

	/**
	 * [ComboBckBean.defaultFonction] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @param defaultFonction
	 *            the defaultFonction to set
	 */
	public void setDefaultFonction(Integer defaultFonction) {
		this.defaultFonction = defaultFonction;
	}

	/**
	 * [ComboBckBean.defaultCivilite] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @return the defaultCivilite
	 */
	public Integer getDefaultCivilite() {
		NcNamesDto ncNamesDto = ncNamesServiceImpl
				.findDefaultValue(UtilConstant.NC_CIVILITE_NAME);
		if (ncNamesDto == null) {
			defaultCivilite = null;
		} else {
			defaultCivilite = ncNamesDto.getIdDefaultValue();
		}
		return defaultCivilite;
	}

	/**
	 * [ComboBckBean.defaultCivilite] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @param defaultCivilite
	 *            the defaultCivilite to set
	 */
	public void setDefaultCivilite(Integer defaultCivilite) {
		this.defaultCivilite = defaultCivilite;
	}

	/**
	 * [ComboBckBean.defaultNatureContrat] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @return the defaultNatureContrat
	 */
	public Integer getDefaultNatureContrat() {
		NcNamesDto ncNamesDto = ncNamesServiceImpl
				.findDefaultValue(UtilConstant.NC_NAT_CONTRAT_NAME);
		if (ncNamesDto == null) {
			defaultNatureContrat = null;
		} else {
			defaultNatureContrat = ncNamesDto.getIdDefaultValue();
		}
		return defaultNatureContrat;
	}

	/**
	 * [ComboBckBean.defaultNatureContrat] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @param defaultNatureContrat
	 *            the defaultNatureContrat to set
	 */
	public void setDefaultNatureContrat(Integer defaultNatureContrat) {
		this.defaultNatureContrat = defaultNatureContrat;
	}

	/**
	 * [ComboBckBean.defaultUniteMonetaire] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @return the defaultUniteMonetaire
	 */
	public Integer getDefaultUniteMonetaire() {
		NcNamesDto ncNamesDto = ncNamesServiceImpl
				.findDefaultValue(UtilConstant.NC_UNITE_MONITAIRE_NAME);
		if (ncNamesDto == null) {
			defaultUniteMonetaire = null;
		} else {
			defaultUniteMonetaire = ncNamesDto.getIdDefaultValue();
		}
		return defaultUniteMonetaire;
	}

	/**
	 * [ComboBckBean.defaultUniteMonetaire] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @param defaultUniteMonetaire
	 *            the defaultUniteMonetaire to set
	 */
	public void setDefaultUniteMonetaire(Integer defaultUniteMonetaire) {
		this.defaultUniteMonetaire = defaultUniteMonetaire;
	}

	/**
	 * [ComboBckBean.defaultDescipline] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @return the defaultDescipline
	 */
	public Integer getDefaultDiscipline() {
		NcNamesDto ncNamesDto = ncNamesServiceImpl
				.findDefaultValue(UtilConstant.NC_DISCIPLINE_NAME);
		if (ncNamesDto == null) {
			defaultDiscipline = null;
		} else {
			defaultDiscipline = ncNamesDto.getIdDefaultValue();
		}
		return defaultDiscipline;
	}

	/**
	 * [ComboBckBean.defaultDescipline] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @param defaultDescipline
	 *            the defaultDescipline to set
	 */
	public void setDefaultDiscipline(Integer defaultDiscipline) {
		this.defaultDiscipline = defaultDiscipline;
	}

	/**
	 * [ComboBckBean.defaultRole] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @return the defaultRole
	 */
	public Integer getDefaultRole() {
		NcNamesDto ncNamesDto = ncNamesServiceImpl
				.findDefaultValue(UtilConstant.NC_ROLE_NAME);
		if (ncNamesDto == null) {
			defaultRole = null;
		} else {
			defaultRole = ncNamesDto.getIdDefaultValue();
		}
		return defaultRole;
	}

	/**
	 * [ComboBckBean.defaultRole] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @param defaultRole
	 *            the defaultRole to set
	 */
	public void setDefaultRole(Integer defaultRole) {
		this.defaultRole = defaultRole;
	}

	/**
	 * [ComboBckBean.defaultTypeAdresse] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @return the defaultTypeAdresse
	 */
	public Integer getDefaultTypeAdresse() {
		NcNamesDto ncNamesDto = ncNamesServiceImpl
				.findDefaultValue(UtilConstant.NC_TYPE_ADRESSE_NAME);
		if (ncNamesDto == null) {
			defaultTypeAdresse = null;
		} else {
			defaultTypeAdresse = ncNamesDto.getIdDefaultValue();
		}
		return defaultTypeAdresse;
	}

	/**
	 * [ComboBckBean.defaultTypeAdresse] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @param defaultTypeAdresse
	 *            the defaultTypeAdresse to set
	 */
	public void setDefaultTypeAdresse(Integer defaultTypeAdresse) {
		this.defaultTypeAdresse = defaultTypeAdresse;
	}

	/**
	 * [ComboBckBean.defaultPays] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @return the defaultPays
	 */
	public Integer getDefaultPays() {
		NcNamesDto ncNamesDto = ncNamesServiceImpl
				.findDefaultValue(UtilConstant.NC_PAYS_NAME);
		if (ncNamesDto == null) {
			defaultPays = null;
		} else {
			defaultPays = ncNamesDto.getIdDefaultValue();
		}
		return defaultPays;
	}

	/**
	 * [ComboBckBean.defaultPays] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @param defaultPays
	 *            the defaultPays to set
	 */
	public void setDefaultPays(Integer defaultPays) {
		this.defaultPays = defaultPays;
	}

	/**
	 * [ComboBckBean.defaultWilaya] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @return the defaultWilaya
	 */
	public Integer getDefaultWilaya() {
		NcNamesDto ncNamesDto = ncNamesServiceImpl
				.findDefaultValue(UtilConstant.NC_WILAYA_NAME);
		if (ncNamesDto == null) {
			defaultWilaya = null;
		} else {
			defaultWilaya = ncNamesDto.getIdDefaultValue();
		}
		return defaultWilaya;
	}

	/**
	 * [ComboBckBean.defaultWilaya] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @param defaultWilaya
	 *            the defaultWilaya to set
	 */
	public void setDefaultWilaya(Integer defaultWilaya) {
		this.defaultWilaya = defaultWilaya;
	}

	/**
	 * [ComboBckBean.defaultDaira] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @return the defaultDaira
	 */
	public Integer getDefaultDaira() {
		NcNamesDto ncNamesDto = ncNamesServiceImpl
				.findDefaultValue(UtilConstant.NC_DAIRA_NAME);
		if (ncNamesDto == null) {
			defaultDaira = null;
		} else {
			defaultDaira = ncNamesDto.getIdDefaultValue();
		}
		return defaultDaira;
	}

	/**
	 * [ComboBckBean.defaultDaira] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @param defaultDaira
	 *            the defaultDaira to set
	 */
	public void setDefaultDaira(Integer defaultDaira) {
		this.defaultDaira = defaultDaira;
	}

	/**
	 * [ComboBckBean.defaultCommune] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @return the defaultCommune
	 */
	public Integer getDefaultCommune() {
		NcNamesDto ncNamesDto = ncNamesServiceImpl
				.findDefaultValue(UtilConstant.NC_DAIRA_NAME);
		if (ncNamesDto == null) {
			defaultCommune = null;
		} else {
			defaultCommune = ncNamesDto.getIdDefaultValue();
		}
		return defaultCommune;
	}

	/**
	 * [ComboBckBean.defaultCommune] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @param defaultCommune
	 *            the defaultCommune to set
	 */
	public void setDefaultCommune(Integer defaultCommune) {
		this.defaultCommune = defaultCommune;
	}

	/**
	 * [ComboBckBean.defaultNatTel] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @return the defaultNatTel
	 */
	public Integer getDefaultNatTel() {
		NcNamesDto ncNamesDto = ncNamesServiceImpl
				.findDefaultValue(UtilConstant.NC_NAT_TEL_NAME);
		if (ncNamesDto == null) {
			defaultNatTel = null;
		} else {
			defaultNatTel = ncNamesDto.getIdDefaultValue();
		}
		return defaultNatTel;
	}

	/**
	 * [ComboBckBean.defaultNatTel] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @param defaultNatTel
	 *            the defaultNatTel to set
	 */
	public void setDefaultNatTel(Integer defaultNatTel) {
		this.defaultNatTel = defaultNatTel;
	}

	/**
	 * [ComboBckBean.defaultTypeTel] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @return the defaultTypeTel
	 */
	public Integer getDefaultTypeTel() {
		NcNamesDto ncNamesDto = ncNamesServiceImpl
				.findDefaultValue(UtilConstant.NC_TYPE_TEL_NAME);
		if (ncNamesDto == null) {
			defaultTypeTel = null;
		} else {
			defaultTypeTel = ncNamesDto.getIdDefaultValue();
		}
		return defaultTypeTel;
	}

	/**
	 * [ComboBckBean.defaultTypeTel] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @param defaultTypeTel
	 *            the defaultTypeTel to set
	 */
	public void setDefaultTypeTel(Integer defaultTypeTel) {
		this.defaultTypeTel = defaultTypeTel;
	}

	/**
	 * [ComboBckBean.defaultTypeMail] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @return the defaultTypeMail
	 */
	public Integer getDefaultTypeMail() {
		NcNamesDto ncNamesDto = ncNamesServiceImpl
				.findDefaultValue(UtilConstant.NC_TYPE_MAIL_NAME);
		if (ncNamesDto == null) {
			defaultTypeMail = null;
		} else {
			defaultTypeMail = ncNamesDto.getIdDefaultValue();
		}
		return defaultTypeMail;
	}

	/**
	 * [ComboBckBean.defaultTypeMail] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @param defaultTypeMail
	 *            the defaultTypeMail to set
	 */
	public void setDefaultTypeMail(Integer defaultTypeMail) {
		this.defaultTypeMail = defaultTypeMail;
	}

	/**
	 * [ComboBckBean.defaultNatureAdresseElectronique] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @return the defaultNatureAdresseElectronique
	 */
	public Integer getDefaultNatureAdresseElectronique() {
		NcNamesDto ncNamesDto = ncNamesServiceImpl
				.findDefaultValue(UtilConstant.NC_TYPE_MAIL_NAME);
		if (ncNamesDto == null) {
			defaultNatureAdresseElectronique = null;
		} else {
			defaultNatureAdresseElectronique = ncNamesDto.getIdDefaultValue();
		}
		return defaultNatureAdresseElectronique;
	}

	/**
	 * [ComboBckBean.defaultNatureAdresseElectronique] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @param defaultNatureAdresseElectronique
	 *            the defaultNatureAdresseElectronique to set
	 */
	public void setDefaultNatureAdresseElectronique(
			Integer defaultNatureAdresseElectronique) {
		this.defaultNatureAdresseElectronique = defaultNatureAdresseElectronique;
	}

	/**
	 * [ComboBckBean.defaultTypeStructure] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @return the defaultTypeStructure
	 */
	public Integer getDefaultTypeStructure() {
		NcNamesDto ncNamesDto = ncNamesServiceImpl
				.findDefaultValue(UtilConstant.NC_TYPE_STUCTURE_NAME);
		if (ncNamesDto == null) {
			defaultTypeStructure = null;
		} else {
			defaultTypeStructure = ncNamesDto.getIdDefaultValue();
		}
		return defaultTypeStructure;
	}

	/**
	 * [ComboBckBean.defaultTypeStructure] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @param defaultTypeStructure
	 *            the defaultTypeStructure to set
	 */
	public void setDefaultTypeStructure(Integer defaultTypeStructure) {
		this.defaultTypeStructure = defaultTypeStructure;
	}

	/**
	 * [ComboBckBean.defaultTypeEtablissement] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @return the defaultTypeEtablissement
	 */
	public Integer getDefaultTypeEtablissement() {
		NcNamesDto ncNamesDto = ncNamesServiceImpl
				.findDefaultValue(UtilConstant.NC_TYPE_ETABLISSEMENT_NAME);
		if (ncNamesDto == null) {
			defaultTypeEtablissement = null;
		} else {
			defaultTypeEtablissement = ncNamesDto.getIdDefaultValue();
		}
		return defaultTypeEtablissement;
	}

	/**
	 * [ComboBckBean.defaultTypeEtablissement] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @param defaultTypeEtablissement
	 *            the defaultTypeEtablissement to set
	 */
	public void setDefaultTypeEtablissement(Integer defaultTypeEtablissement) {
		this.defaultTypeEtablissement = defaultTypeEtablissement;
	}

	/**
	 * [ComboBckBean.defaultFormeJuridique] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @return the defaultFormeJuridique
	 */
	public Integer getDefaultFormeJuridique() {
		NcNamesDto ncNamesDto = ncNamesServiceImpl
				.findDefaultValue(UtilConstant.NC_FORME_JURIDIQUE_NAME);
		if (ncNamesDto == null) {
			defaultFormeJuridique = null;
		} else {
			defaultFormeJuridique = ncNamesDto.getIdDefaultValue();
		}
		return defaultFormeJuridique;
	}

	/**
	 * [ComboBckBean.defaultFormeJuridique] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @param defaultFormeJuridique
	 *            the defaultFormeJuridique to set
	 */
	public void setDefaultFormeJuridique(Integer defaultFormeJuridique) {
		this.defaultFormeJuridique = defaultFormeJuridique;
	}

	/**
	 * [ComboBckBean.defaultStatut] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @return the defaultStatut
	 */
	public Integer getDefaultStatut() {
		NcNamesDto ncNamesDto = ncNamesServiceImpl
				.findDefaultValue(UtilConstant.NC_STATUS_STRUCTURE_NAME);
		if (ncNamesDto == null) {
			defaultStatut = null;
		} else {
			defaultStatut = ncNamesDto.getIdDefaultValue();
		}
		return defaultStatut;
	}

	/**
	 * [ComboBckBean.defaultStatut] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @param defaultStatut
	 *            the defaultStatut to set
	 */
	public void setDefaultStatut(Integer defaultStatut) {
		this.defaultStatut = defaultStatut;
	}

	/**
	 * [ComboBckBean.defaultTypeAvenant] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @return the defaultTypeAvenant
	 */
	public Integer getDefaultTypeAvenant() {
		NcNamesDto ncNamesDto = ncNamesServiceImpl
				.findDefaultValue(UtilConstant.NC_TYPE_AVENANT_NAME);
		if (ncNamesDto == null) {
			defaultTypeAvenant = null;
		} else {
			defaultTypeAvenant = ncNamesDto.getIdDefaultValue();
		}
		return defaultTypeAvenant;
	}

	/**
	 * [ComboBckBean.defaultTypeAvenant] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @param defaultTypeAvenant
	 *            the defaultTypeAvenant to set
	 */
	public void setDefaultTypeAvenant(Integer defaultTypeAvenant) {
		this.defaultTypeAvenant = defaultTypeAvenant;
	}

	/**
	 * [ComboBckBean.defaultTypeLieu] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @return the defaultTypeLieu
	 */
	public Integer getDefaultTypeLieu() {
		NcNamesDto ncNamesDto = ncNamesServiceImpl
				.findDefaultValue(UtilConstant.NC_TYPE_LIEU_NAME);
		if (ncNamesDto == null) {
			defaultTypeLieu = null;
		} else {
			defaultTypeLieu = ncNamesDto.getIdDefaultValue();
		}
		return defaultTypeLieu;
	}

	/**
	 * [ComboBckBean.defaultTypeLieu] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @param defaultTypeLieu
	 *            the defaultTypeLieu to set
	 */
	public void setDefaultTypeLieu(Integer defaultTypeLieu) {
		this.defaultTypeLieu = defaultTypeLieu;
	}

	/**
	 * [ComboBckBean.defaultTypeImplan] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @return the defaultTypeImplan
	 */
	public Integer getDefaultTypeImplan() {
		NcNamesDto ncNamesDto = ncNamesServiceImpl
				.findDefaultValue(UtilConstant.NC_TYPE_IMPLANTATION_NAME);
		if (ncNamesDto == null) {
			defaultTypeImplan = null;
		} else {
			defaultTypeImplan = ncNamesDto.getIdDefaultValue();
		}
		return defaultTypeImplan;
	}

	/**
	 * [ComboBckBean.defaultTypeImplan] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @param defaultTypeImplan
	 *            the defaultTypeImplan to set
	 */
	public void setDefaultTypeImplan(Integer defaultTypeImplan) {
		this.defaultTypeImplan = defaultTypeImplan;
	}

	/**
	 * [ComboBckBean.defaultTypeEvenement] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @return the defaultTypeEvenement
	 */
	public Integer getDefaultTypeEvenement() {
		NcNamesDto ncNamesDto = ncNamesServiceImpl
				.findDefaultValue(UtilConstant.NC_TYPE_EVENEMENT_NAME);
		if (ncNamesDto == null) {
			defaultTypeEvenement = null;
		} else {
			defaultTypeEvenement = ncNamesDto.getIdDefaultValue();
		}
		return defaultTypeEvenement;
	}

	/**
	 * [ComboBckBean.defaultTypeEvenement] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @param defaultTypeEvenement
	 *            the defaultTypeEvenement to set
	 */
	public void setDefaultTypeEvenement(Integer defaultTypeEvenement) {
		this.defaultTypeEvenement = defaultTypeEvenement;
	}

	/**
	 * [ComboBckBean.defaultRepetition] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @return the defaultRepetition
	 */
	public Integer getDefaultRepetition() {
		NcNamesDto ncNamesDto = ncNamesServiceImpl
				.findDefaultValue(UtilConstant.NC_REPETITION_NAME);
		if (ncNamesDto == null) {
			defaultRepetition = null;
		} else {
			defaultRepetition = ncNamesDto.getIdDefaultValue();
		}
		return defaultRepetition;
	}

	/**
	 * [ComboBckBean.defaultRepetition] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @param defaultRepetition
	 *            the defaultRepetition to set
	 */
	public void setDefaultRepetition(Integer defaultRepetition) {
		this.defaultRepetition = defaultRepetition;
	}

	/**
	 * [ComboBckBean.defaultTypeTache] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @return the defaultTypeTache
	 */
	public Integer getDefaultTypeTache() {
		NcNamesDto ncNamesDto = ncNamesServiceImpl
				.findDefaultValue(UtilConstant.NC_TYPE_TACHE_NAME);
		if (ncNamesDto == null) {
			defaultTypeTache = null;
		} else {
			defaultTypeTache = ncNamesDto.getIdDefaultValue();
		}
		return defaultTypeTache;
	}

	/**
	 * [ComboBckBean.defaultTypeTache] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @param defaultTypeTache
	 *            the defaultTypeTache to set
	 */
	public void setDefaultTypeTache(Integer defaultTypeTache) {
		this.defaultTypeTache = defaultTypeTache;
	}

	/**
	 * [ComboBckBean.defaultTypeAcces] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @return the defaultTypeAcces
	 */
	public Integer getDefaultTypeAcces() {
		NcNamesDto ncNamesDto = ncNamesServiceImpl
				.findDefaultValue(UtilConstant.NC_TYPE_ACCES_NAME);
		if (ncNamesDto == null) {
			defaultTypeAcces = null;
		} else {
			defaultTypeAcces = ncNamesDto.getIdDefaultValue();
		}
		return defaultTypeAcces;
	}

	/**
	 * [ComboBckBean.defaultTypeAcces] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @param defaultTypeAcces
	 *            the defaultTypeAcces to set
	 */
	public void setDefaultTypeAcces(Integer defaultTypeAcces) {
		this.defaultTypeAcces = defaultTypeAcces;
	}

	/**
	 * [ComboBckBean.defaultTypeSalle] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @return the defaultTypeSalle
	 */
	public Integer getDefaultTypeSalle() {
		NcNamesDto ncNamesDto = ncNamesServiceImpl
				.findDefaultValue(UtilConstant.NC_TYPE_SALLE_NAME);
		if (ncNamesDto == null) {
			defaultTypeSalle = null;
		} else {
			defaultTypeSalle = ncNamesDto.getIdDefaultValue();
		}
		return defaultTypeSalle;
	}

	/**
	 * [ComboBckBean.defaultTypeSalle] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @param defaultTypeSalle
	 *            the defaultTypeSalle to set
	 */
	public void setDefaultTypeSalle(Integer defaultTypeSalle) {
		this.defaultTypeSalle = defaultTypeSalle;
	}

	/**
	 * [ComboBckBean.defaultTypeOccupation] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @return the defaultTypeOccupation
	 */
	public Integer getDefaultTypeOccupation() {
		NcNamesDto ncNamesDto = ncNamesServiceImpl
				.findDefaultValue(UtilConstant.NC_TYPE_OCCUPATION_NAME);
		if (ncNamesDto == null) {
			defaultTypeOccupation = null;
		} else {
			defaultTypeOccupation = ncNamesDto.getIdDefaultValue();
		}
		return defaultTypeOccupation;
	}

	/**
	 * [ComboBckBean.defaultTypeOccupation] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @param defaultTypeOccupation
	 *            the defaultTypeOccupation to set
	 */
	public void setDefaultTypeOccupation(Integer defaultTypeOccupation) {
		this.defaultTypeOccupation = defaultTypeOccupation;
	}

	/**
	 * [ComboBckBean.defaultTypeIndividu] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @return the defaultTypeIndividu
	 */
	public Integer getDefaultTypeIndividu() {
		NcNamesDto ncNamesDto = ncNamesServiceImpl
				.findDefaultValue(UtilConstant.NC_TYPE_INDIVIDU_NAME);
		if (ncNamesDto == null) {
			defaultTypeIndividu = null;
		} else {
			defaultTypeIndividu = ncNamesDto.getIdDefaultValue();
		}
		return defaultTypeIndividu;
	}

	/**
	 * [ComboBckBean.defaultTypeIndividu] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @param defaultTypeIndividu
	 *            the defaultTypeIndividu to set
	 */
	public void setDefaultTypeIndividu(Integer defaultTypeIndividu) {
		this.defaultTypeIndividu = defaultTypeIndividu;
	}

	/**
	 * [ComboBckBean.defaultLangue] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @return the defaultLangue
	 */
	public Integer getDefaultLangue() {
		NcNamesDto ncNamesDto = ncNamesServiceImpl
				.findDefaultValue(UtilConstant.NC_LANGUE_NAME);
		if (ncNamesDto == null) {
			defaultLangue = null;
		} else {
			defaultLangue = ncNamesDto.getIdDefaultValue();
		}
		return defaultLangue;
	}

	/**
	 * [ComboBckBean.defaultLangue] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @param defaultLangue
	 *            the defaultLangue to set
	 */
	public void setDefaultLangue(Integer defaultLangue) {
		this.defaultLangue = defaultLangue;
	}

	/**
	 * [ComboBckBean.defaultTypeDocument] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @return the defaultTypeDocument
	 */
	public Integer getDefaultTypeDocument() {
		NcNamesDto ncNamesDto = ncNamesServiceImpl
				.findDefaultValue(UtilConstant.NC_TYPE_DOCUMENT_NAME);
		if (ncNamesDto == null) {
			defaultTypeDocument = null;
		} else {
			defaultTypeDocument = ncNamesDto.getIdDefaultValue();
		}
		return defaultTypeDocument;
	}

	/**
	 * [ComboBckBean.defaultTypeDocument] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @param defaultTypeDocument
	 *            the defaultTypeDocument to set
	 */
	public void setDefaultTypeDocument(Integer defaultTypeDocument) {
		this.defaultTypeDocument = defaultTypeDocument;
	}

	/**
	 * [ComboBckBean.defaultNatureDocument] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @return the defaultNatureDocument
	 */
	public Integer getDefaultNatureDocument() {
		NcNamesDto ncNamesDto = ncNamesServiceImpl
				.findDefaultValue(UtilConstant.NC_NATURE_DOCUMENT_NAME);
		if (ncNamesDto == null) {
			defaultNatureDocument = null;
		} else {
			defaultNatureDocument = ncNamesDto.getIdDefaultValue();
		}
		return defaultNatureDocument;
	}

	/**
	 * [ComboBckBean.defaultNatureDocument] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @param defaultNatureDocument
	 *            the defaultNatureDocument to set
	 */
	public void setDefaultNatureDocument(Integer defaultNatureDocument) {
		this.defaultNatureDocument = defaultNatureDocument;
	}

	/**
	 * [ComboBckBean.defaultMotCle] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @return the defaultMotCle
	 */
	public Integer getDefaultMotCle() {
		NcNamesDto ncNamesDto = ncNamesServiceImpl
				.findDefaultValue(UtilConstant.NC_MOT_CLE_NAME);
		if (ncNamesDto == null) {
			defaultMotCle = null;
		} else {
			defaultMotCle = ncNamesDto.getIdDefaultValue();
		}
		return defaultMotCle;
	}

	/**
	 * [ComboBckBean.defaultMotCle] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @param defaultMotCle
	 *            the defaultMotCle to set
	 */
	public void setDefaultMotCle(Integer defaultMotCle) {
		this.defaultMotCle = defaultMotCle;
	}

	/**
	 * [ComboBckBean.defaultClassement] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @return the defaultClassement
	 */
	public Integer getDefaultClassement() {
		NcNamesDto ncNamesDto = ncNamesServiceImpl
				.findDefaultValue(UtilConstant.NC_CLASSEMENT_NAME);
		if (ncNamesDto == null) {
			defaultClassement = null;
		} else {
			defaultClassement = ncNamesDto.getIdDefaultValue();
		}
		return defaultClassement;
	}

	/**
	 * [ComboBckBean.defaultClassement] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @param defaultClassement
	 *            the defaultClassement to set
	 */
	public void setDefaultClassement(Integer defaultClassement) {
		this.defaultClassement = defaultClassement;
	}

	/**
	 * [ComboBckBean.defaultFamillequipement] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @return the defaultFamillequipement
	 */
	public Integer getDefaultFamillequipement() {
		NcNamesDto ncNamesDto = ncNamesServiceImpl
				.findDefaultValue(UtilConstant.NC_FAMILLE_EQUIPEMENT_NAME);
		if (ncNamesDto == null) {
			defaultFamillequipement = null;
		} else {
			defaultFamillequipement = ncNamesDto.getIdDefaultValue();
		}
		return defaultFamillequipement;
	}

	/**
	 * [ComboBckBean.defaultFamillequipement] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @param defaultFamillequipement
	 *            the defaultFamillequipement to set
	 */
	public void setDefaultFamillequipement(Integer defaultFamillequipement) {
		this.defaultFamillequipement = defaultFamillequipement;
	}

	/**
	 * [ComboBckBean.defaultSousFamillequipement] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @return the defaultSousFamillequipement
	 */
	public Integer getDefaultSousFamillequipement() {
		NcNamesDto ncNamesDto = ncNamesServiceImpl
				.findDefaultValue(UtilConstant.NC_SOUS_FAMILLE_EQUIPEMENT_NAME);
		if (ncNamesDto == null) {
			defaultSousFamillequipement = null;
		} else {
			defaultSousFamillequipement = ncNamesDto.getIdDefaultValue();
		}
		return defaultSousFamillequipement;
	}

	/**
	 * [ComboBckBean.defaultSousFamillequipement] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @param defaultSousFamillequipement
	 *            the defaultSousFamillequipement to set
	 */
	public void setDefaultSousFamillequipement(
			Integer defaultSousFamillequipement) {
		this.defaultSousFamillequipement = defaultSousFamillequipement;
	}

	/**
	 * [ComboBckBean.defaulttatEquipement] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @return the defaulttatEquipement
	 */
	public Integer getDefaultEtatEquipement() {
		NcNamesDto ncNamesDto = ncNamesServiceImpl
				.findDefaultValue(UtilConstant.NC_ETAT_EQUIPEMENT_NAME);
		if (ncNamesDto == null) {
			defaultEtatEquipement = null;
		} else {
			defaultEtatEquipement = ncNamesDto.getIdDefaultValue();
		}
		return defaultEtatEquipement;
	}

	/**
	 * [ComboBckBean.defaulttatEquipement] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 16:59:48
	 * @param defaulttatEquipement
	 *            the defaulttatEquipement to set
	 */
	public void setDefaultEtatEquipement(Integer defaultEtatEquipement) {
		this.defaultEtatEquipement = defaultEtatEquipement;
	}

	/**
	 * [ComboBckBean.listTypeDossier] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 mai 2014 09:49:46
	 * @return the listTypeDossier
	 */
	public List<SelectItem> getListTypeDossier() {
		if (listTypeDossier == null || listTypeDossier.isEmpty()) {
			listTypeDossier = new ArrayList<SelectItem>();
			listTypeDossier = getNcGenericList(UtilConstant.NC_TYPE_DOSSIER);

		}
		return listTypeDossier;
	}

	/**
	 * [ComboBckBean.listTypeDossier] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 mai 2014 09:49:46
	 * @param listTypeDossier
	 *            the listTypeDossier to set
	 */
	public void setListTypeDossier(List<SelectItem> listTypeDossier) {
		this.listTypeDossier = listTypeDossier;
	}

	/**
	 * [ComboBckBean.listeNiveauRecrutement] Getter
	 * 
	 * @author BELBRIK Oualid on : 25 mai 2014 09:49:46
	 * @return the listeNiveauRecrutement
	 */
	public List<SelectItem> getListeNiveauRecrutement() {
		if (listeNiveauRecrutement == null || listeNiveauRecrutement.isEmpty()) {
			listeNiveauRecrutement = new ArrayList<SelectItem>();
			listeNiveauRecrutement = getNcGenericList(UtilConstant.NC_NIVEAU_RECRUTEMENT);

		}
		return listeNiveauRecrutement;
	}

	/**
	 * [ComboBckBean.listTypeDossier] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 mai 2014 09:49:46
	 * @param listTypeDossier
	 *            the listTypeDossier to set
	 */
	public void setListeNiveauRecrutement(
			List<SelectItem> listeNiveauRecrutement) {
		this.listeNiveauRecrutement = listeNiveauRecrutement;
	}

	/**
	 * [ComboBckBean.listTypeGroupe] Getter
	 * 
	 * @author MAKERRI Sofiane on : 12 janv. 2015 16:28:06
	 * @return the listTypeGroupe
	 */
	public List<SelectItem> getListTypeGroupe() {
		if (listTypeGroupe == null || listTypeGroupe.isEmpty()) {
			listTypeGroupe = new ArrayList<SelectItem>();
			listTypeGroupe = getNcGenericList(UtilConstant.NC_TYPE_GROUPE);
		}
		return listTypeGroupe;
	}

	/**
	 * [ComboBckBean.listTypeGroupe] Setter
	 * 
	 * @author MAKERRI Sofiane on : 12 janv. 2015 16:28:06
	 * @param listTypeGroupe
	 *            the listTypeGroupe to set
	 */
	public void setListTypeGroupe(List<SelectItem> listTypeGroupe) {
		this.listTypeGroupe = listTypeGroupe;
	}

}
