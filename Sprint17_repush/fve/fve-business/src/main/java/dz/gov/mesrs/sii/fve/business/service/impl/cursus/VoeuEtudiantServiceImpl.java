package dz.gov.mesrs.sii.fve.business.service.impl.cursus;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.commons.data.dao.bpm.SituationDao;
import dz.gov.mesrs.sii.commons.data.dao.bpm.SituationEntiteOccurrenceDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.VoeuEtudiantDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntiteOccurrence;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.VoeuEtudiant;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.VoeuEtudiantChoix;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.VoeuEtudiantChoixDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.VoeuEtudiantDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.VoeuEtudiantService;

/**
 * @author Mounir.MESSAOUDI on : 14 ao�t 2014 12:12:37
 */
@Service("voeuEtudiantService")
public class VoeuEtudiantServiceImpl implements VoeuEtudiantService {

	private static final Log log = LogFactory
			.getLog(VoeuEtudiantServiceImpl.class);

	@Autowired
	@Qualifier("voeuEtudiantDao")
	private VoeuEtudiantDao voeuEtudiantDao;

	@Autowired
	@Qualifier("situationDao")
	SituationDao situationDao;

	@Autowired
	@Qualifier("situationEntiteOccurrenceDao")
	SituationEntiteOccurrenceDao situationEntiteOccurrenceDao;

	@Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;

	public VoeuEtudiantServiceImpl() {

	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public VoeuEtudiantDto insertOrUpdate(VoeuEtudiantDto voeuEtudiantDto) {
		try {
			VoeuEtudiant voeuEtudiant = mapper.map(voeuEtudiantDto,
					VoeuEtudiant.class);

			voeuEtudiant
					.setVoeuxEtudiantsChoix(new ArrayList<VoeuEtudiantChoix>());

			// attacher les choix
			List<VoeuEtudiantChoixDto> ld = voeuEtudiantDto
					.getVoeuxEtudiantsChoixDto();
			if (ld != null)
				for (VoeuEtudiantChoixDto voeuEtudiantChoixDto : ld) {
					VoeuEtudiantChoix voeuEtudiantChoix = mapper.map(
							voeuEtudiantChoixDto, VoeuEtudiantChoix.class);
					voeuEtudiantChoix.setVoeuEtudiant(voeuEtudiant);
					voeuEtudiant.addVoeuxEtudiantsChoix(voeuEtudiantChoix);
				}

			if (voeuEtudiantDto.getId() == null) {
				voeuEtudiantDao.persist(voeuEtudiant);
			} else {
				voeuEtudiant = voeuEtudiantDao.merge(voeuEtudiant);
			}

			// Enregistrer l'historique de la situation
			SituationEntiteOccurrence ss = new SituationEntiteOccurrence();
			ss.setSituationEntite(voeuEtudiant.getSituationEntite());
			ss.setIdOccurrence(voeuEtudiant.getId());
			ss.setDateSituation(new java.util.Date());
			situationEntiteOccurrenceDao.persist(ss);

			mapper.map(voeuEtudiant, voeuEtudiantDto);

			log.error("insertorupdate VoeuEtudiant succes..");

			return voeuEtudiantDto;

		} catch (RuntimeException e) {
			log.error("insertorupdate VoeuEtudiant failed--", e);
			throw e;
		}
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void remove(VoeuEtudiantDto voeuEtudiantDto) {
		try {

			VoeuEtudiant voeuEtudiant = mapper.map(voeuEtudiantDto,
					VoeuEtudiant.class);

			voeuEtudiant = voeuEtudiantDao.findById(voeuEtudiant.getId());

			voeuEtudiantDao.remove(voeuEtudiant);
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public VoeuEtudiantDto findById(int id) {

		VoeuEtudiant voeuEtudiant = voeuEtudiantDao.findById(id);

		if (voeuEtudiant != null) {
			VoeuEtudiantDto voeuEtudiantDto = mapper.map(voeuEtudiant,
					VoeuEtudiantDto.class);

			// mappping des choix
			List<VoeuEtudiantChoixDto> listVoeuEtudiantChoixDtos = new ArrayList<VoeuEtudiantChoixDto>();
			List<VoeuEtudiantChoix> listVoeuxEtudiantsChoix = voeuEtudiant
					.getVoeuxEtudiantsChoix();
			if (listVoeuxEtudiantsChoix != null) {
				for (VoeuEtudiantChoix voeuxEtudiantChoix : listVoeuxEtudiantsChoix) {
					VoeuEtudiantChoixDto voeuEtudiantChoixDto = mapper.map(
							voeuxEtudiantChoix, VoeuEtudiantChoixDto.class);
					listVoeuEtudiantChoixDtos.add(voeuEtudiantChoixDto);
				}
			}
			voeuEtudiantDto
					.setVoeuxEtudiantsChoixDto(listVoeuEtudiantChoixDtos);

			return voeuEtudiantDto;

		} else {
			return null;
		}
	}

	@Override
	public List<VoeuEtudiantDto> findAll() {

		List<VoeuEtudiant> voeuEtudiants = voeuEtudiantDao.findAll();
		List<VoeuEtudiantDto> VoeuEtudiantsDto = new ArrayList<VoeuEtudiantDto>();

		for (VoeuEtudiant voeuEtudiant : voeuEtudiants) {
			VoeuEtudiantDto voeuEtudiantDto = mapper.map(voeuEtudiant,
					VoeuEtudiantDto.class);

			// mappping des choix
			List<VoeuEtudiantChoixDto> listVoeuEtudiantChoixDtos = new ArrayList<VoeuEtudiantChoixDto>();
			List<VoeuEtudiantChoix> listVoeuxEtudiantsChoix = voeuEtudiant
					.getVoeuxEtudiantsChoix();
			if (listVoeuxEtudiantsChoix != null) {
				for (VoeuEtudiantChoix voeuxEtudiantChoix : listVoeuxEtudiantsChoix) {
					VoeuEtudiantChoixDto voeuEtudiantChoixDto = mapper.map(
							voeuxEtudiantChoix, VoeuEtudiantChoixDto.class);
					listVoeuEtudiantChoixDtos.add(voeuEtudiantChoixDto);
				}
			}
			voeuEtudiantDto
					.setVoeuxEtudiantsChoixDto(listVoeuEtudiantChoixDtos);

			// map
			VoeuEtudiantsDto.add(voeuEtudiantDto);

		}

		return VoeuEtudiantsDto;

	}

	/**
	 * Recherche avancee
	 * 
	 * @author Mounir.MESSAOUDI on : 19 ao�t 2014 15:25:30
	 * @param searchDto
	 * @return
	 */
	@Override
	public List<VoeuEtudiantDto> findAdvanced(VoeuEtudiantDto searchDto,
			List<SituationEntiteDto> searchSitutationEntiteDtos) {

		VoeuEtudiant searchBo = new VoeuEtudiant();
		mapper.map(searchDto, searchBo);

		List<SituationEntite> searchSituationEntiteBos = new ArrayList<SituationEntite>();
		if (searchSitutationEntiteDtos != null
				&& !searchSitutationEntiteDtos.isEmpty()) {
			for (SituationEntiteDto situationEntiteDto : searchSitutationEntiteDtos) {
				SituationEntite situationEntite = new SituationEntite();
				mapper.map(situationEntiteDto, situationEntite);
				searchSituationEntiteBos.add(situationEntite);
			}
		}
		List<VoeuEtudiant> voeuEtudiants = voeuEtudiantDao.findAdvanced(
				searchBo, searchSituationEntiteBos);
		List<VoeuEtudiantDto> voeuEtudiantDtos = new ArrayList<VoeuEtudiantDto>();

		for (VoeuEtudiant voeuEtudiant : voeuEtudiants) {
			VoeuEtudiantDto voeuEtudiantDto = mapper.map(voeuEtudiant,
					VoeuEtudiantDto.class);

			// mappping des choix
			List<VoeuEtudiantChoixDto> listVoeuEtudiantChoixDtos = new ArrayList<VoeuEtudiantChoixDto>();
			List<VoeuEtudiantChoix> listVoeuxEtudiantsChoix = voeuEtudiant
					.getVoeuxEtudiantsChoix();
			if (listVoeuxEtudiantsChoix != null) {
				for (VoeuEtudiantChoix voeuxEtudiantChoix : listVoeuxEtudiantsChoix) {
					VoeuEtudiantChoixDto voeuEtudiantChoixDto = mapper.map(
							voeuxEtudiantChoix, VoeuEtudiantChoixDto.class);
					listVoeuEtudiantChoixDtos.add(voeuEtudiantChoixDto);
				}
			}
			voeuEtudiantDto
					.setVoeuxEtudiantsChoixDto(listVoeuEtudiantChoixDtos);

			voeuEtudiantDtos.add(voeuEtudiantDto);
		}

		return voeuEtudiantDtos;
	}

	/**
	 * rechercher une fiche de voeux par annee academqiue et dossier etudiant
	 * 
	 * @author Mounir.MESSAOUDI on : 2 oct. 2014 10:39:53
	 * @param searchBo
	 * @return
	 */
	public VoeuEtudiantDto findByIdDosEtudIdAnnAcad(Integer idDossierEtudiant,
			Integer idAnneeAcademique) {

		VoeuEtudiant voeuEtudiant = voeuEtudiantDao.findByIdDosEtudIdAnnAcad(
				idDossierEtudiant, idAnneeAcademique);

		if (voeuEtudiant != null) {
			VoeuEtudiantDto voeuEtudiantDto = mapper.map(voeuEtudiant,
					VoeuEtudiantDto.class);

			// mappping des choix
			List<VoeuEtudiantChoixDto> listVoeuEtudiantChoixDtos = new ArrayList<VoeuEtudiantChoixDto>();
			List<VoeuEtudiantChoix> listVoeuxEtudiantsChoix = voeuEtudiant
					.getVoeuxEtudiantsChoix();
			if (listVoeuxEtudiantsChoix != null) {
				for (VoeuEtudiantChoix voeuxEtudiantChoix : listVoeuxEtudiantsChoix) {
					VoeuEtudiantChoixDto voeuEtudiantChoixDto = mapper.map(
							voeuxEtudiantChoix, VoeuEtudiantChoixDto.class);
					listVoeuEtudiantChoixDtos.add(voeuEtudiantChoixDto);
				}
			}
			voeuEtudiantDto
					.setVoeuxEtudiantsChoixDto(listVoeuEtudiantChoixDtos);

			return voeuEtudiantDto;

		} else {
			return null;
		}
	}

}
