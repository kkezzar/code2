package dz.gov.mesrs.sii.referentiel.business.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefJourOuvreDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefJourOuvre;
import dz.gov.mesrs.sii.commons.util.UtilDate;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefJourOuvreDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefJourOuvreService;

/**
 * 
 * @author BELDI Jamel
 * 
 */

@Service("refJourOuvreService")
public class RefJourOuvreServiceImpl implements RefJourOuvreService {

	@Autowired
	@Qualifier("refJourOuvreDao")
	private RefJourOuvreDao refJourOuvreDao;

	@Autowired
	@Qualifier("mapper")
	private Mapper mapper;

	public RefJourOuvreServiceImpl() {

	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public RefJourOuvreDto insertOrUpdate(RefJourOuvreDto refJourOuvreDto) {
		Assert.notNull(refJourOuvreDto, "refJourOuvreDto is required");
		RefJourOuvre jour = mapper.map(refJourOuvreDto, RefJourOuvre.class);

		if (jour.getId() == 0) {
			refJourOuvreDao.persist(jour);
		} else {
			jour = refJourOuvreDao.merge(jour);
		}
		mapper.map(jour, refJourOuvreDto);

		return refJourOuvreDto;

	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void remove(RefJourOuvreDto refJourOuvreDto) {
		Assert.notNull(refJourOuvreDto, "refJourOuvreDto is required");

		RefJourOuvre jour = mapper.map(refJourOuvreDto, RefJourOuvre.class);

		jour = refJourOuvreDao.merge(jour);

		refJourOuvreDao.remove(jour);

	}

	@Override
	public RefJourOuvreDto findById(int id) {

		RefJourOuvre jour = refJourOuvreDao.findById(id);

		if (jour != null)
			return mapper.map(jour, RefJourOuvreDto.class);

		return null;
	}

	@Override
	public List<RefJourOuvreDto> findAll() {

		List<RefJourOuvre> jours = refJourOuvreDao.findAll();

		List<RefJourOuvreDto> jourDtos = new ArrayList<RefJourOuvreDto>();

		for (RefJourOuvre jour : jours) {
			jourDtos.add(mapper.map(jour, RefJourOuvreDto.class));
		}

		return jourDtos;

	}

	@Override
	public List<RefJourOuvreDto> findBetweenTwoDate(Date dateDebut, Date dateFin) {
		Assert.notNull(dateDebut, "dateDebut is required");
		Assert.notNull(dateFin, "dateFin is required");
		List<RefJourOuvre> jours = refJourOuvreDao.findBetweenTwoDate(
				dateDebut, dateFin);

		List<RefJourOuvreDto> jourDtos = new ArrayList<RefJourOuvreDto>();

		for (RefJourOuvre jour : jours) {
			jourDtos.add(mapper.map(jour, RefJourOuvreDto.class));
		}

		return jourDtos;

	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public boolean isJourOuvre(Date date) {
		Assert.notNull(date, "date is required");
		RefJourOuvre refJourOuvre = refJourOuvreDao.findByDate(date);
		if (refJourOuvre != null) {
			return true;
		}
		return false;
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public int calculateNbJourOuvreBetweenTwoDate(Date dateDebut, Date dateFin) {
		Assert.notNull(dateDebut, "dateDebut is required");
		Assert.notNull(dateFin, "dateFin is required");
		int result = 0;
		List<RefJourOuvre> jours = refJourOuvreDao.findBetweenTwoDate(
				dateDebut, dateFin);
		if (jours != null) {
			result = jours.size();
		}
		return result;
	}

	@Override
	public RefJourOuvreDto findByDate(Date date) {
		Assert.notNull(date, "date is required");
		RefJourOuvre jour = refJourOuvreDao.findByDate(date);
		if (jour != null)
			return mapper.map(jour, RefJourOuvreDto.class);
		return null;
	}

	@Override
	public Date nextWorkingDay(Date dateDebut, double nbJour) {
		Assert.notNull(dateDebut, "dateDebut is required");
		// Date dateFin = UtilDate.addDays(dateDebut, nbJour) ;
		// //calculer les jour de repos (VENDREDI ET SAMEDI);
		// int nbJourRepos = getNbWeekendDay(dateDebut, dateFin);
		// nbJour = nbJour+nbJourRepos;
		// dateFin = UtilDate.addDays(dateDebut, nbJour) ;
		// int nbJourOuvre = calculateNbJourOuvreBetweenTwoDate(dateDebut,
		// dateFin);
		// dateFin = UtilDate.addDays(dateFin,Double.valueOf(nbJourOuvre));
		// return dateFin;
		Date date = dateDebut;
		for (int i = 0; i < nbJour; i++) {
			if (isJourOuvre(date) || isWeekendDay(date)) {
				nbJour++;
			}
			date = UtilDate.addDays(dateDebut, Double.valueOf(i+1));
		}
		date = UtilDate.addDays(dateDebut, nbJour);
		return date;
	}

	@Override
	public int getNbWeekendDay(Date dateDebut, Date dateFin) {
		int result = 0;
		Date date = dateDebut;
		while (date.equals(dateDebut) || date.after(dateDebut)
				&& (date.equals(dateFin) || date.before(dateFin))) {
			if (isWeekendDay(date)) {
				result = result + 1;
			}
			date = UtilDate.addDays(date, Double.valueOf(1));
		}
		return result;
	}
	
	@Override
	public boolean isWeekendDay(Date date) {
		Assert.notNull(date, "date is required");
		String dayName = UtilDate.getDayName(date);
		if (dayName.equals("vendredi") || dayName.equals("samedi")) {
			return true;
		}
		return false;
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public int calculateWorkingDaysBetweenTwoDate(Date dateDebut, Date dateFin) {
		Assert.notNull(dateDebut, "dateDebut is required");
		Assert.notNull(dateFin, "dateFin is required");
		int result = 0;
		Date date = dateDebut;
		while(date.before(dateFin)){
			if (!isJourOuvre(date) && !isWeekendDay(date)) {
				result++;
			}
			date = UtilDate.addDays(date, Double.valueOf(1));
		}
		return result;
	}
}
