package dz.gov.mesrs.sii.grh.business.service.referentiel.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.dao.grh.StatutDao;
import dz.gov.mesrs.sii.commons.data.model.grh.Statut;
import dz.gov.mesrs.sii.commons.data.util.UtilitiesProgres;
import dz.gov.mesrs.sii.grh.business.model.dto.StatutDto;
import dz.gov.mesrs.sii.grh.business.service.referentiel.StatutService;

/**
 * 
 * @author Salem
 * @version V1.0
 * @date 22-10-2014
 * @description Implementation des services
 * 
 */
@Transactional
@Service("statutService")
public class StatutServiceImpl extends CommonServiceImpl<Statut, StatutDto, Integer> implements StatutService {

	private StatutDao statutDao;

	@Override
	protected CommonDao<Statut, Integer> getDao() {
		return statutDao;
	}

	/**
	 * @return the statutDao
	 */
	public StatutDao getStatutDao() {
		return statutDao;
	}

	/**
	 * @param statutDao
	 *            to set
	 */
	@Autowired
	public void setStatutDao(StatutDao statutDao) {
		this.statutDao = statutDao;
	}

	@Override
	public List<StatutDto> getListValideStatuts() {
		List<StatutDto> statutDtoList = new ArrayList<StatutDto>();

		Date curentDate = UtilitiesProgres.getCurrentDate();
		List<Statut> statutList = statutDao.findAll();
		for (Statut statut : statutList) {
			StatutDto dto = new StatutDto();
			Date dateFinValidation = statut.getDateFinValidation();
			Date dateEffet = statut.getDateEffet();
			if ((dateFinValidation != null && dateFinValidation.before(curentDate))) {
				continue;
			}
			if (dateEffet.after(curentDate)) {
				continue;
			}

			mapper.map(statut, dto);
			statutDtoList.add(dto);

		}

		return statutDtoList;
	}

}
