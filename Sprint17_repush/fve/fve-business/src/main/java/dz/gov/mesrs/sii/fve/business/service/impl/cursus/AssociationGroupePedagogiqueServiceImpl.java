/**
 * [dz.gov.mesrs.sii.fve.business.service.impl.cursus.AssociationGroupePedagogiqueServiceImpl.java] 
 * @author MAKERRI Sofiane on : 2 oct. 2014  09:57:32
 */
package dz.gov.mesrs.sii.fve.business.service.impl.cursus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.AssociationGroupePedagogiqueDao;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.AssociationGroupePedagogique;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.GroupePedagogique;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.RattachementMc;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.UniteEnseignement;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.AssociationGroupePedagogiqueDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.GroupePedagogiqueDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.RattachementMcDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.UniteEnseignementDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.AssociationGroupePedagogiqueService;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 20-04-2014 16:44:07
 */

@Service("associationGroupePedagogiqueService")
public class AssociationGroupePedagogiqueServiceImpl implements
		AssociationGroupePedagogiqueService {

	@Autowired
	@Qualifier("associationGroupePedagogiqueDao")
	private AssociationGroupePedagogiqueDao associationGroupePedagogiqueDao;

	private static final Log log = LogFactory
			.getLog(AssociationGroupePedagogiqueServiceImpl.class);

	@Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;

	public AssociationGroupePedagogiqueServiceImpl() {
		// final List<String> mappings = new ArrayList<String>(1);
		// mappings.add(UtilConstants.DOZER_GROUPE_PEDAGOGIQUE_MAPPING_NAME);
		// mapper = new DozerBeanMapper();
		// mapper.setMappingFiles(mappings);

	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public AssociationGroupePedagogiqueDto insertOrUpdate(
			AssociationGroupePedagogiqueDto associationGroupePedagogiqueDto) {
		try {
			AssociationGroupePedagogique associationGroupePedagogique = mapper
					.map(associationGroupePedagogiqueDto,
							AssociationGroupePedagogique.class);
			if (associationGroupePedagogique.getId() == 0) {
				associationGroupePedagogique.setDateAttachement(new Date());
				associationGroupePedagogiqueDao
						.persist(associationGroupePedagogique);
			} else {
				associationGroupePedagogique = associationGroupePedagogiqueDao
						.merge(associationGroupePedagogique);
			}
			mapper.map(associationGroupePedagogique,
					associationGroupePedagogiqueDto);

			log.error("insertorupdate associationGroupePedagogique succes..");

			return associationGroupePedagogiqueDto;

		} catch (RuntimeException e) {
			log.error("insertorupdate associationGroupePedagogique failed--", e);
			throw e;
		}
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void remove(
			AssociationGroupePedagogiqueDto associationGroupePedagogiqueDto) {
		try {

			AssociationGroupePedagogique associationGroupePedagogique = mapper
					.map(associationGroupePedagogiqueDto,
							AssociationGroupePedagogique.class);

			associationGroupePedagogique = associationGroupePedagogiqueDao
					.merge(associationGroupePedagogique);

			associationGroupePedagogiqueDao
					.remove(associationGroupePedagogique);
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public AssociationGroupePedagogiqueDto findById(int id) {

		AssociationGroupePedagogique associationGroupePedagogique = associationGroupePedagogiqueDao
				.findById(id);

		if (associationGroupePedagogique != null)
			return mapper.map(associationGroupePedagogique,
					AssociationGroupePedagogiqueDto.class);

		return null;
	}

	@Override
	public List<AssociationGroupePedagogiqueDto> findAll() {

		List<AssociationGroupePedagogique> associationGroupePedagogiques = associationGroupePedagogiqueDao
				.findAll();

		List<AssociationGroupePedagogiqueDto> associationGroupePedagogiqueDtos = new ArrayList<AssociationGroupePedagogiqueDto>();

		for (AssociationGroupePedagogique associationGroupePedagogique : associationGroupePedagogiques) {
			associationGroupePedagogiqueDtos.add(mapper.map(
					associationGroupePedagogique,
					AssociationGroupePedagogiqueDto.class));
		}

		return associationGroupePedagogiqueDtos;

	}

	@Override
	public List<AssociationGroupePedagogiqueDto> findByGroupePedagogiqueId(
			Integer gpId) {
		try {
			List<AssociationGroupePedagogique> associationGroupePedagogiques = associationGroupePedagogiqueDao
					.findByGroupePedagogiqueId(gpId);

			List<AssociationGroupePedagogiqueDto> associationGroupePedagogiqueDtos = new ArrayList<AssociationGroupePedagogiqueDto>();

			for (AssociationGroupePedagogique associationGroupePedagogique : associationGroupePedagogiques) {
				associationGroupePedagogiqueDtos.add(mapper.map(
						associationGroupePedagogique,
						AssociationGroupePedagogiqueDto.class));
			}

			return associationGroupePedagogiqueDtos;
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public boolean associationExist(Integer id, Integer gpId,
			Integer rattachementMcId, Integer apId) {
		try {
			return associationGroupePedagogiqueDao.associationExist(id, gpId,
					rattachementMcId, apId);
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public boolean groupeNameExist(Integer oofId, Integer periodeId, String nom,
			AssociationGroupePedagogiqueDto associationGroupePedagogiqueDto) {
		try {
			AssociationGroupePedagogique associationGroupePedagogique = mapper
					.map(associationGroupePedagogiqueDto,
							AssociationGroupePedagogique.class);
			return associationGroupePedagogiqueDao.groupeNameExist(oofId, periodeId, nom,
					associationGroupePedagogique);
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public List<UniteEnseignementDto> findUeByGroupePedagogiqueId(Integer gpId) {
		try {
			List<UniteEnseignement> uniteEnseignements = associationGroupePedagogiqueDao
					.findUeByGroupePedagogiqueId(gpId);

			List<UniteEnseignementDto> uniteEnseignementDtos = new ArrayList<UniteEnseignementDto>();

			for (UniteEnseignement uniteEnseignement : uniteEnseignements) {
				uniteEnseignementDtos.add(mapper.map(uniteEnseignement,
						UniteEnseignementDto.class));
			}

			return uniteEnseignementDtos;
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public List<RattachementMcDto> findRattachementMcByGroupePedagogiqueId(
			Integer gpId) {
		try {
			List<RattachementMc> rattachementMcs = associationGroupePedagogiqueDao
					.findRattachementMcByGroupePedagogiqueId(gpId);

			List<RattachementMcDto> rattachementMcDtos = new ArrayList<RattachementMcDto>();

			for (RattachementMc rattachementMc : rattachementMcs) {
				rattachementMcDtos.add(mapper.map(rattachementMc,
						RattachementMcDto.class));
			}

			return rattachementMcDtos;
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public AssociationGroupePedagogiqueDto findUnique(Integer gpId,
			Integer rattachementMc, Integer apId) {
		try {

			AssociationGroupePedagogique associationGroupePedagogique = associationGroupePedagogiqueDao
					.findUnique(gpId, rattachementMc, apId);
			if (associationGroupePedagogique == null) {
				return null;

			}
			return mapper.map(associationGroupePedagogique,
					AssociationGroupePedagogiqueDto.class);
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public List<AssociationGroupePedagogiqueDto> findByRattachementMcAndAp(
			Integer rattachementMcId, Integer apId) {
		try {
			List<AssociationGroupePedagogique> associationGroupePedagogiques = associationGroupePedagogiqueDao
					.findByRattachementMcAndAp(rattachementMcId, apId);

			List<AssociationGroupePedagogiqueDto> associationGroupePedagogiqueDtos = new ArrayList<AssociationGroupePedagogiqueDto>();

			for (AssociationGroupePedagogique associationGroupePedagogique : associationGroupePedagogiques) {
				associationGroupePedagogiqueDtos.add(mapper.map(
						associationGroupePedagogique,
						AssociationGroupePedagogiqueDto.class));
			}

			return associationGroupePedagogiqueDtos;
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public List<GroupePedagogiqueDto> findByRattachementMcId(Integer oofId, Integer periodeId, 
			Integer rattachementMcId) {
		try {
			List<GroupePedagogique> groupePedagogiques = associationGroupePedagogiqueDao
					.findByRattachementMcId(oofId,periodeId, rattachementMcId);

			List<GroupePedagogiqueDto> groupePedagogiqueDtos = new ArrayList<GroupePedagogiqueDto>();

			for (GroupePedagogique groupePedagogique : groupePedagogiques) {
				groupePedagogiqueDtos.add(mapper.map(
						groupePedagogique,
						GroupePedagogiqueDto.class));
			}

			return groupePedagogiqueDtos;
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public List<AssociationGroupePedagogiqueDto> findAdvanced(
			Integer rattachementMcId, Integer apId, Integer oofId,
			Integer periodeId) {
		try {
			List<AssociationGroupePedagogique> associationGroupePedagogiques = associationGroupePedagogiqueDao
					.findAdvanced(rattachementMcId, apId, oofId, periodeId);

			List<AssociationGroupePedagogiqueDto> associationGroupePedagogiqueDtos = new ArrayList<AssociationGroupePedagogiqueDto>();

			for (AssociationGroupePedagogique associationGroupePedagogique : associationGroupePedagogiques) {
				associationGroupePedagogiqueDtos.add(mapper.map(
						associationGroupePedagogique,
						AssociationGroupePedagogiqueDto.class));
			}

			return associationGroupePedagogiqueDtos;
		} catch (RuntimeException e) {
			throw e;
		}
	}

}
