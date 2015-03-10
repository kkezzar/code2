package dz.gov.mesrs.sii.grh.business.service.poste.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.data.dao.grh.AffectationPosteDao;
import dz.gov.mesrs.sii.commons.data.dao.grh.DossierEmployeDao;
import dz.gov.mesrs.sii.commons.data.dao.grh.PosteEmploiDao;
import dz.gov.mesrs.sii.commons.data.model.grh.AffectationPoste;
import dz.gov.mesrs.sii.commons.data.model.grh.ConnaissanceEmploye;
import dz.gov.mesrs.sii.commons.data.model.grh.ConnaissancePoste;
import dz.gov.mesrs.sii.commons.data.model.grh.DossierEmploye;
import dz.gov.mesrs.sii.commons.data.model.grh.HabileteEmploye;
import dz.gov.mesrs.sii.commons.data.model.grh.HabiletePoste;
import dz.gov.mesrs.sii.commons.data.model.grh.PosteEmploi;
import dz.gov.mesrs.sii.grh.business.model.dto.AffectationPosteDto;
import dz.gov.mesrs.sii.grh.business.model.dto.DossierEmployeDto;
import dz.gov.mesrs.sii.grh.business.service.poste.AffectationPosteService;

@Service("affectationPosteService")
@Transactional
public class AffectationPosteServiceImpl extends CommonServiceImpl<AffectationPoste, AffectationPosteDto, Long>
		implements AffectationPosteService {

	private final static Logger logger = LoggerFactory.getLogger(AffectationPosteServiceImpl.class.getName());

	private AffectationPosteDao affectationPosteDao;
	private PosteEmploiDao posteEmploiDao;
	private DossierEmployeDao dossierEmployeDao;
	private Mapper mapper;

	@Override
	public int countEmployeProposes(Long id) {
		DossierEmploye dossierEmploye = mapEmployeFromPoste(id);
		int result = dossierEmployeDao.countAllByExample(dossierEmploye);
		logger.debug("Found {} proposed employee for posistion {}", new Object[] { result, id });
		return result;
	}

	@Override
	public List<DossierEmployeDto> findEmployeProposes(DossierEmployeDto searchEmployeProposeDto, Long id,
			SearchMode searchMode) {
		DossierEmploye dossierEmploye = mapEmployeFromPoste(id);
		List<DossierEmployeDto> dtos = null;
		List<DossierEmploye> entities = dossierEmployeDao.findAllByExample(dossierEmploye, searchMode);
		if (entities != null && !entities.isEmpty()) {
			dtos = new ArrayList<DossierEmployeDto>();
			for (DossierEmploye entity : entities) {
				dtos.add(mapper.map(entity, DossierEmployeDto.class));
			}
		}
		return dtos;
	}

	private DossierEmploye mapEmployeFromPoste(Long posteId) {
		Assert.notNull(posteId, "can't searcg for null posteId");
		PosteEmploi posteEmploi = posteEmploiDao.findUniqueByExample(new PosteEmploi(posteId));
		DossierEmploye dossierEmploye = new DossierEmploye();
		dossierEmploye.setRefEtablissement(posteEmploi.getEtablissement());
		dossierEmploye.setNiveauCompetence(posteEmploi.getNiveauCompetence());
		for (ConnaissancePoste connaissancePoste : posteEmploi.getConnaissances()) {
			ConnaissanceEmploye connaissanceEmploye = new ConnaissanceEmploye();
			connaissanceEmploye.setType(connaissancePoste.getTypeConnaissance());
			dossierEmploye.addConnaissance(connaissanceEmploye);
		}
		for (HabiletePoste habiletePoste : posteEmploi.getHabiletes()) {
			HabileteEmploye habileteEmploye = new HabileteEmploye();
			habileteEmploye.setType(habiletePoste.getTypeHabilete());
			dossierEmploye.addHabilete(habileteEmploye);
		}
		return dossierEmploye;
	}

	@Override
	protected CommonDao<AffectationPoste, Long> getDao() {
		return affectationPosteDao;
	}

	@Autowired
	public void setAffectationPosteDao(AffectationPosteDao affectationPosteDao) {
		this.affectationPosteDao = affectationPosteDao;
	}

	@Autowired
	public void setDossierEmployeDao(DossierEmployeDao dossierEmployeDao) {
		this.dossierEmployeDao = dossierEmployeDao;
	}

	@Autowired
	public void setMapper(Mapper mapper) {
		this.mapper = mapper;
	}

	public Mapper getMapper() {
		return this.mapper;
	}

	@Autowired
	public void setPosteEmploiDao(PosteEmploiDao posteEmploiDao) {
		this.posteEmploiDao = posteEmploiDao;
	}

}
