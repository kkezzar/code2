/**
 * [dz.gov.mesrs.sii.fve.business.service.impl.offreformation.CycleServiceImpl.java] 
 * @author rlaib on : 16 juil. 2014  10:54:44
 */
package dz.gov.mesrs.sii.fve.business.service.impl.offreformation;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.NiveauDao;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.Cycle;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.Niveau;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.NiveauDto;
import dz.gov.mesrs.sii.fve.business.service.offreformation.NiveauService;

/**
 * @author rlaib on : 16 juil. 2014 10:54:44
 */
@Service("niveauService")
public class NiveauServiceImpl implements NiveauService {

	@Autowired
	@Qualifier("niveauDao")
	private NiveauDao niveauDao;

	@Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;

	/**
	 * [NiveauServiceImpl.insertOrUpdate] Method
	 * 
	 * @author rlaib on : 16 juil. 2014 10:54:44
	 * @param cycleDto
	 * @return
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public NiveauDto insertOrUpdate(NiveauDto niveauDto) {

		Niveau niveau = mapper.map(niveauDto, Niveau.class);
		Cycle cycle = new Cycle();
		cycle.setId(niveauDto.getIdCycle());
		niveau.setCycle(cycle);

		if (niveau.getId() == 0)
			niveauDao.persist(niveau);
		else
			niveau = niveauDao.merge(niveau);

		mapper.map(niveau, niveauDto);
		return niveauDto;

	}

	/**
	 * [NiveauServiceImpl.remove] Method
	 * 
	 * @author rlaib on : 16 juil. 2014 10:54:44
	 * @param cycleDto
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void remove(int idNiveau) {
		niveauDao.remove(idNiveau);

	}

	/**
	 * [NiveauServiceImpl.findById] Method
	 * 
	 * @author rlaib on : 16 juil. 2014 10:54:44
	 * @param id
	 * @return
	 */
	@Override
	public NiveauDto findById(int id) {

		Niveau niveau = niveauDao.findById(id);
		if (niveau != null) {
			NiveauDto niveauDto = new NiveauDto();
			niveauDto = mapper.map(niveau, NiveauDto.class);
			return niveauDto;
		}
		return null;
	}

	/**
	 * [NiveauServiceImpl.findAll] Method
	 * 
	 * @author rlaib on : 16 juil. 2014 10:54:44
	 * @return
	 */
	@Override
	public List<NiveauDto> findAll() {

		List<Niveau> niveaux = niveauDao.findAll();
		List<NiveauDto> NiveauxDtos = new ArrayList<NiveauDto>();
		for (Niveau niveau : niveaux) {
			NiveauDto niveauDto = new NiveauDto();
			niveauDto = mapper.map(niveau, NiveauDto.class);
			NiveauxDtos.add(niveauDto);
		}

		return NiveauxDtos;
	}

	/**
	 * [NiveauServiceImpl.findByCycleId] Method
	 * 
	 * @author rlaib on : 16 juil. 2014 11:13:25
	 * @return
	 */
	@Override
	public List<NiveauDto> findByCycleId(int idCycle) {

		List<Niveau> niveaux = niveauDao.findByCycleId(idCycle);
		List<NiveauDto> NiveauxDtos = new ArrayList<NiveauDto>();
		for (Niveau niveau : niveaux) {
			NiveauDto niveauDto = new NiveauDto();
			niveauDto = mapper.map(niveau, NiveauDto.class);
			niveauDto.setIdCycle(idCycle);
			NiveauxDtos.add(niveauDto);
		}

		return NiveauxDtos;
	}

	@Override
	public List<NiveauDto> findByCycleCode(String codeCycle) {
		List<Niveau> niveaux = niveauDao.findByCycleCode(codeCycle);
		List<NiveauDto> niveauxDtos = new ArrayList<NiveauDto>();
		for (Niveau niveau : niveaux) {
			NiveauDto niveauDto = new NiveauDto();
			niveauDto = mapper.map(niveau, NiveauDto.class);
			niveauxDtos.add(niveauDto);
		}

		return niveauxDtos;
	}

	/**
	 * Cette fonction renvoi le prochain niveau du niveau niveauDto
	 * 
	 * @author Mounir.MESSAOUDI on : 26 oct. 2014 15:06:20
	 * @param niveauDto
	 * @return
	 */
	@Override
	public NiveauDto findProchainNiveauOf(NiveauDto niveauDto) {
		Niveau niveauBo = mapper.map(niveauDto, Niveau.class);

		Niveau niveau = niveauDao.findProchainNiveauOf(niveauBo);
		if (niveau != null) {
			NiveauDto nivDto = new NiveauDto();
			nivDto = mapper.map(niveau, NiveauDto.class);
			return nivDto;
		}
		return null;
	}

	/**
	 * Cette fonction renvoi le premier niveau dans un cycle
	 * 
	 * @author Mounir.MESSAOUDI on : 4 nov. 2014 07:59:23
	 * @param cycleId
	 * @return
	 */
	public NiveauDto findPremierNiveauOf(Integer cycleId) {
		Niveau niveau = niveauDao.findPremierNiveauOf(cycleId);
		if (niveau != null) {
			NiveauDto nivDto = new NiveauDto();
			nivDto = mapper.map(niveau, NiveauDto.class);
			return nivDto;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.fve.business.service.offreformation.NiveauService#findLastNiveauOf(java.lang.Integer)
	 */
	@Override
	public NiveauDto findLastNiveauOf(Integer cycleId) {
		Niveau niveau = niveauDao.findLastNiveauOf(cycleId);
		if (niveau != null) {
			NiveauDto nivDto = new NiveauDto();
			nivDto = mapper.map(niveau, NiveauDto.class);
			return nivDto;
		}
		return null;
	}
}
