package dz.gov.mesrs.sii.grh.business.service.conges.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.Filter;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.FilterMode;
import dz.gov.mesrs.sii.commons.data.dao.grh.PrevisionCongeDao;
import dz.gov.mesrs.sii.commons.data.model.grh.DossierEmploye;
import dz.gov.mesrs.sii.commons.data.model.grh.PrevisionConge;
import dz.gov.mesrs.sii.grh.business.exception.PrevisionCongeChevauchementException;
import dz.gov.mesrs.sii.grh.business.exception.PrevisionCongeDepassementException;
import dz.gov.mesrs.sii.grh.business.exception.PrevisionCongeHorsPeriodeException;
import dz.gov.mesrs.sii.grh.business.model.dto.AnneeGrhDto;
import dz.gov.mesrs.sii.grh.business.model.dto.PrevisionCongeDto;
import dz.gov.mesrs.sii.grh.business.service.conges.PrevisionCongeService;
import dz.gov.mesrs.sii.referentiel.business.service.RefJourOuvreService;

/**
 * 
 * @author BELDI Jamel
 * 
 */

@Service("previsionCongeService")
public class PrevisionCongeServiceImpl extends
		CommonServiceImpl<PrevisionConge, PrevisionCongeDto, Long> implements
		PrevisionCongeService {

	private PrevisionCongeDao previsionCongeDao;
	private RefJourOuvreService refJourOuvreService;
	@Override
	protected CommonDao<PrevisionConge, Long> getDao() {
		return previsionCongeDao;
	}

	
	

	/**
	 * Permet de chercher Les prevsion de congé d'un employé pour une année
	 * 
	 * @param dossierEmployeId
	 * @param year
	 * @return List<PrevisionCongeDto>
	 * @throws ParseException
	 */
	@Override
	public List<PrevisionCongeDto> findPrevisionConges(Long dossierEmployeId,
			AnneeGrhDto anneeGrhDto) {
		PrevisionConge previsionConge = new PrevisionConge();
		previsionConge.setDossierEmploye(new DossierEmploye(dossierEmployeId));
		SearchMode searchMode = new SearchMode();
		searchMode.addFilter(new Filter("dateDebut",
				anneeGrhDto.getDateDebut(), anneeGrhDto.getDateFin(),
				FilterMode.BETWEEN));
		List<PrevisionConge> resultObjects = getDao().findAllByExample(
				previsionConge, searchMode);

		return mapReturn(resultObjects);
	}

	private List<PrevisionCongeDto> mapReturn(List<PrevisionConge> resultObjects) {
		List<PrevisionCongeDto> dtos = null;
		if (resultObjects != null && resultObjects.size() > 0) {
			dtos = new ArrayList<>();
			for (PrevisionConge object : resultObjects) {
				dtos.add(mapper.map(object, PrevisionCongeDto.class));
			}
		}
		return dtos;
	}

	/**
	 * Permet d'enregistrer  les prévisions de congé d'un employé pour une anneeGrh
	 * @param dtosToSave
	 * @param dtosToDelete
	 * @param anneeGrh
	 * @param droitConge
	 * @throws PrevisionCongeChevauchementException 
	 * @throws PrevisionCongeDepassementException 
	 * @throws PrevisionCongeHorsPeriodeException 
	 */
	@Override
	@Transactional
	public void saveEmployePrevisionConges(List<PrevisionCongeDto> dtosToSave,
			List<PrevisionCongeDto> dtosToDelete, AnneeGrhDto anneeGrh,
			Double droitConge) throws PrevisionCongeChevauchementException, PrevisionCongeDepassementException, PrevisionCongeHorsPeriodeException {
		Assert.notNull(dtosToSave, "List PrevisionCongeDtoToSave is required");
		Assert.notNull(dtosToSave, "AnneeGrhDto is required");
		Assert.notNull(droitConge, "DroitConge is required");
		// Ordonner la liste
		Collections.sort(dtosToSave, new Comparator<PrevisionCongeDto>() {
			@Override
			public int compare(PrevisionCongeDto p1, PrevisionCongeDto p2) {
				return p1.getDateDebut().compareTo(p2.getDateDebut());
			}
		});

		// verification
		Double nbjours = new Double(0);
		Date dateFin = null;
		for (PrevisionCongeDto _previsionCongeDto : dtosToSave) {
			Date dateDebut = _previsionCongeDto.getDateDebut();
			if (dateFin != null && dateFin.after(_previsionCongeDto.getDateDebut())) {
				throw new PrevisionCongeChevauchementException("R002:Les dates prévues sont en chevauchement!");
			}
			dateFin = refJourOuvreService.nextWorkingDay(
					dateDebut, _previsionCongeDto.getNbreJours());// UtilDate.addDays(dateDebut,
																	// _previsionCongeDto.getNbreJours());
			if (dateDebut.before(anneeGrh.getDateDebut())
					|| dateDebut.after(anneeGrh.getDateFin())
					|| dateFin.after(anneeGrh.getDateFin())) {
				throw new PrevisionCongeHorsPeriodeException("R001:Vérifier les dates prévues avec l'année de prévision");
			}
			nbjours = nbjours + _previsionCongeDto.getNbreJours();
			if (nbjours > droitConge) {
				throw new PrevisionCongeDepassementException("R003:Vérifier les prévisions de congé avec les droits de congé");
			}
		}
		
		
		
		super.saveAll(dtosToSave, dtosToDelete);

	}

	
	/**
	 * @return the previsionCongeDao
	 */
	public PrevisionCongeDao getPrevisionCongeDao() {
		return previsionCongeDao;
	}

	/**
	 * @param previsionCongeDao
	 *            to set
	 */
	@Autowired
	public void setPrevisionCongeDao(PrevisionCongeDao previsionCongeDao) {
		this.previsionCongeDao = previsionCongeDao;
	}

	public RefJourOuvreService getRefJourOuvreService() {
		return refJourOuvreService;
	}

	@Autowired
	public void setRefJourOuvreService(RefJourOuvreService refJourOuvreService) {
		this.refJourOuvreService = refJourOuvreService;
	}
}
