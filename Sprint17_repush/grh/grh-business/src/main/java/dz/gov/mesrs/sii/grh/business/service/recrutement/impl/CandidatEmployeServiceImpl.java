package dz.gov.mesrs.sii.grh.business.service.recrutement.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.business.util.UtilConstants;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.dao.bpm.SituationEntiteDao;
import dz.gov.mesrs.sii.commons.data.dao.bpm.SituationEntiteOccurrenceDao;
import dz.gov.mesrs.sii.commons.data.dao.grh.CandidatEmployeDao;
import dz.gov.mesrs.sii.commons.data.dao.grh.ChangementPositionDao;
import dz.gov.mesrs.sii.commons.data.dao.grh.DossierEmployeDao;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.NomenclatureDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntiteOccurrence;
import dz.gov.mesrs.sii.commons.data.model.grh.CandidatEmploye;
import dz.gov.mesrs.sii.commons.data.model.grh.ChangementPosition;
import dz.gov.mesrs.sii.commons.data.model.grh.DossierEmploye;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEtablissement;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefIndividu;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefStructure;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;
import dz.gov.mesrs.sii.grh.business.model.dto.CandidatEmployeDto;
import dz.gov.mesrs.sii.grh.business.service.recrutement.CandidatEmployeService;

/**
 * 
 * @author Salem
 * @version V1.0
 * @date 22-10-2014
 * @description Implementation des services
 * 
 */

@Service("candidatEmployeService")
public class CandidatEmployeServiceImpl extends CommonServiceImpl<CandidatEmploye, CandidatEmployeDto, Integer>
		implements CandidatEmployeService {

	private CandidatEmployeDao candidatEmployeDao;
	private DossierEmployeDao dossierEmployeDao;
	private SituationEntiteDao situationEntiteDao;
	private SituationEntiteOccurrenceDao situationEntiteOccurrenceDao;
	private NomenclatureDao nomenclatureDao;
	private ChangementPositionDao changementPositionDao;


	@Override
	@Transactional
	public void validateAll(List<CandidatEmployeDto> listCandidatEmployeDtos) {
		if (listCandidatEmployeDtos != null) {
			Date dateValidation = new Date();
			List<CandidatEmployeDto> _candidats = new ArrayList<CandidatEmployeDto>();
			for (CandidatEmployeDto _candidatEmployeDto : listCandidatEmployeDtos) {
				_candidatEmployeDto.setValide(true);
				_candidatEmployeDto.setDateValidation(dateValidation);
				DossierEmploye example = new DossierEmploye();
				example.setRefIndividu(mapper.map(_candidatEmployeDto.getRefIndividuDto(), RefIndividu.class));
				DossierEmploye dossierEmploye = dossierEmployeDao.findUniqueOrNoneByExample(example);
				if (dossierEmploye == null) {
					example.setMatricule(_candidatEmployeDto.getMatricule());
					example.setRefEtablissement(mapper.map(_candidatEmployeDto.getDetailRecrutementDto()
							.getBesoinRecrutementDto().getRefEtablissementDto(), RefEtablissement.class));
					example.setRefStructure(mapper.map(_candidatEmployeDto.getRefStructureDto(), RefStructure.class));
					example.setDateCreation(dateValidation);
					example.setDateInstallation(_candidatEmployeDto.getDateInstallation());
					example.setCandidatEmploye(mapper.map(_candidatEmployeDto, CandidatEmploye.class));
					// persist Dossier Employé
					SituationEntite situationEntite = situationEntiteDao.findByCodeSituationByCodeEntite(
							UtilConstants.ENTITE_DOSSIER_EMPLOYE_CODE, UtilConstants.SITUATION_CREEE_CODE);
					example.setSituationEntite(situationEntite);
					dossierEmploye = dossierEmployeDao.save(example);
					
					// Enregistrer l'historique de création du dossier Employé
					SituationEntiteOccurrence situationEntiteOccurrence = new SituationEntiteOccurrence();
					situationEntiteOccurrence.setIdOccurrence(dossierEmploye.getId().intValue());
					situationEntiteOccurrence.setSituationEntite(situationEntite);
					situationEntiteOccurrence.setDateSituation(dateValidation);
					situationEntiteOccurrenceDao.persist(situationEntiteOccurrence);
					
					//Activer dossier
					ChangementPosition positionActive = new ChangementPosition();
					positionActive.setDossierEmploye(dossierEmploye);
					positionActive.setDateDebut(dossierEmploye.getDateInstallation());
					positionActive.setSituation(situationEntiteDao.findByCodeSituationByCodeEntite(
							UtilConstant.ENTITE_DEMANDE_CHANGEMEMENT_POSITION, UtilConstant.SITUTAION_VALIDEE_CODE));
					positionActive.setNomenclatureByPosition(nomenclatureDao.findByCode(UtilConstant.NC_GRH_POSITION_EMPLOYE_VALUE,
							UtilConstant.NC_GRH_POSITION_EMPLOYE_ACTIVE_CODE));
					changementPositionDao.save(positionActive);
					dossierEmploye.setCurrentPosition(positionActive);
					dossierEmploye = dossierEmployeDao.save(dossierEmploye);
				}
				// _candidatEmployeDto.setDossierEmployeDto(mapper.map(dossierEmploye,
				// DossierEmployeDto.class));
				_candidats.add(_candidatEmployeDto);

			}

			saveAll(_candidats, null);
		}

	}
	
	

	@Override
	protected CommonDao<CandidatEmploye, Integer> getDao() {
		return candidatEmployeDao;
	}

	/**
	 * @return the candidatEmployeDao
	 */
	public CandidatEmployeDao getCandidatEmployeDao() {
		return candidatEmployeDao;
	}

	/**
	 * @param candidatEmployeDao
	 *            to set
	 */
	@Autowired
	public void setCandidatEmployeDao(CandidatEmployeDao candidatEmployeDao) {
		this.candidatEmployeDao = candidatEmployeDao;
	}

	/**
	 * @return the dossierEmployeDao
	 */
	public DossierEmployeDao getDossierEmployeDao() {
		return dossierEmployeDao;
	}

	/**
	 * @param dossierEmployeDao
	 *            to set
	 */
	@Autowired
	public void setDossierEmployeDao(DossierEmployeDao dossierEmployeDao) {
		this.dossierEmployeDao = dossierEmployeDao;
	}

	public SituationEntiteDao getSituationEntiteDao() {
		return situationEntiteDao;
	}

	@Autowired
	public void setSituationEntiteDao(SituationEntiteDao situationEntiteDao) {
		this.situationEntiteDao = situationEntiteDao;
	}

	public SituationEntiteOccurrenceDao getSituationEntiteOccurrenceDao() {
		return situationEntiteOccurrenceDao;
	}

	@Autowired
	public void setSituationEntiteOccurrenceDao(SituationEntiteOccurrenceDao situationEntiteOccurrenceDao) {
		this.situationEntiteOccurrenceDao = situationEntiteOccurrenceDao;
	}

	
	
	public NomenclatureDao getNomenclatureDao() {
		return nomenclatureDao;
	}
	
	@Autowired
	public void setNomenclatureDao(NomenclatureDao nomenclatureDao) {
		this.nomenclatureDao = nomenclatureDao;
	}

	public ChangementPositionDao getChangementPositionDao() {
		return changementPositionDao;
	}
	
	@Autowired
	public void setChangementPositionDao(ChangementPositionDao changementPositionDao) {
		this.changementPositionDao = changementPositionDao;
	}
}
