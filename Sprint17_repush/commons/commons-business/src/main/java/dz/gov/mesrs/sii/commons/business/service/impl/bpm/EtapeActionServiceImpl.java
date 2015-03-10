/**
 * [dz.gov.mesrs.sii.fve.business.service.impl.habilitation.TacheServiceImpl.java] 
 * @author rlaib on : 29 avr. 2014  16:36:28
 */
package dz.gov.mesrs.sii.commons.business.service.impl.bpm;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.EtapeActionDto;
import dz.gov.mesrs.sii.commons.business.service.bpm.EtapeActionService;
import dz.gov.mesrs.sii.commons.data.dao.bpm.EtapeActionDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.EtapeAction;

/**
 * @author rlaib  on : 29 avr. 2014  16:36:28
 */

@Service("etapeActionService")
public class EtapeActionServiceImpl implements EtapeActionService {

	
	@Autowired
	@Qualifier ("etapeActionDao")
	private EtapeActionDao etapeActionDao;
	
	@Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;
		
	public EtapeActionServiceImpl(){

	}
	
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public EtapeActionDto  insertOrUpdate(EtapeActionDto etapeActionDto) {

		EtapeAction etapeAction = mapper.map(etapeActionDto, EtapeAction.class);
		
		if (etapeAction.getId() == 0) 
			etapeActionDao.persist(etapeAction);
		else
			etapeAction = etapeActionDao.merge(etapeAction);

		mapper.map(etapeAction, etapeActionDto);

		return etapeActionDto;
	}
	
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void remove(EtapeActionDto tapeActionDto) {

		EtapeAction etapeAction = mapper.map(tapeActionDto, EtapeAction.class);
		etapeActionDao.remove(etapeAction);

	}
	
	
	@Override
	public EtapeActionDto findById(int id) {

		EtapeAction etapeAction = etapeActionDao.findById(id);

		if (etapeAction != null)
			return mapper.map(etapeAction, EtapeActionDto.class);

		return null;
	}
	
	@Override
	public List<EtapeActionDto> findAll() {		


		List<EtapeActionDto> etapeActionDtos = new ArrayList<EtapeActionDto>();

		try {
					List<EtapeAction> etapeActions = etapeActionDao.findAll();

					for (EtapeAction etapeAction : etapeActions) {
						etapeActionDtos.add(mapper.map(etapeAction, EtapeActionDto.class));
					}
		}
		catch (Exception e){

		}

		return etapeActionDtos;

	}
	
	
}
