package dz.gov.mesrs.sii.gfc.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.dao.gfc.DecisionBudgetDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.DecisionBudget;
import dz.gov.mesrs.sii.gfc.business.model.dto.DecisionBudgetDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.DetailsNotificationDecisionBudgetDto;
import dz.gov.mesrs.sii.gfc.business.service.DecisionBudgetService;

/**
 * Service Implementation for domain model class DecisionBudget.
 * 
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Service("decisionBudgetService")
@Transactional
public class DecisionBudgetServiceImpl extends CommonServiceImpl<DecisionBudget, DecisionBudgetDto, Integer> implements
		DecisionBudgetService {

	@Autowired
	@Qualifier("decisionBudgetDao")
	private DecisionBudgetDao decisionBudgetDao;

	@Autowired
	private Mapper mapper;

	public DecisionBudgetServiceImpl() {

	}

	@Override
	protected CommonDao<DecisionBudget, Integer> getDao() {
		return decisionBudgetDao;
	}

	/**
	 * Divise la liste de details de notification en deux listes list<true>
	 * recettes, list<false> depenses
	 * 
	 * @param decisionBudgetDto
	 * @return
	 */
	@Override
	public ListMultimap<String, DetailsNotificationDecisionBudgetDto> splitDetailsNotificationsByTypeOfChapitre(
			DecisionBudgetDto decisionBudgetDto) {

		ListMultimap<String, DetailsNotificationDecisionBudgetDto> result = ArrayListMultimap.create();
		List<DetailsNotificationDecisionBudgetDto> dtos = decisionBudgetDto.getDetailsNotificationDecisionBudgets();
		if (dtos != null && !dtos.isEmpty()) {
			for (DetailsNotificationDecisionBudgetDto dto : dtos) {
				result.put(dto.getChapitre().isRecetteType().toString(), dto);
			}
		} else {
			result.putAll("true", new ArrayList<DetailsNotificationDecisionBudgetDto>());
			result.putAll("false", new ArrayList<DetailsNotificationDecisionBudgetDto>());
		}

		return result;
	}

	@Override
	public List<Object[]> getTotalOfNotificationsBudget(DecisionBudgetDto decisionBudgetDto) {
		DecisionBudget object = mapper.map(decisionBudgetDto, DecisionBudget.class);
		return decisionBudgetDao.getTotalOfNotificationsBudget(object);
	}
}