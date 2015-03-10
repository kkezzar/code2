package dz.gov.mesrs.sii.fve.business.service.impl.cursus;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.business.util.Utility;
import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.GroupePedagogiqueDao;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.GroupePedagogique;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.GroupePedagogiqueDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.GroupePedagogiqueService;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */

@Service("groupePedagogiqueService")
public class GroupePedagogiqueServiceImpl implements GroupePedagogiqueService {

	@Autowired
	@Qualifier("groupePedagogiqueDao")
	private GroupePedagogiqueDao groupePedagogiqueDao;
	@Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;

	private static final Log log = LogFactory
			.getLog(GroupePedagogiqueServiceImpl.class);

	public GroupePedagogiqueServiceImpl() {

	}

	@Override
	public GroupePedagogiqueDto insertOrUpdate(
			GroupePedagogiqueDto groupePedagogiqueDto) {
		try {
			GroupePedagogique groupePedagogique = mapper.map(
					groupePedagogiqueDto, GroupePedagogique.class);
			// groupePedagogique.setOf(null);
			if (groupePedagogique.getSection() != null
					&& groupePedagogique.getSection().getId() == 0) {
				groupePedagogique.setSection(null);
			}
			if (groupePedagogique.getId() == 0)
				groupePedagogique = groupePedagogiqueDao
						.persist(groupePedagogique);
			else
				groupePedagogique = groupePedagogiqueDao
						.merge(groupePedagogique);

			mapper.map(groupePedagogique, groupePedagogiqueDto);

			return groupePedagogiqueDto;
		} catch (RuntimeException e) {
			log.error("insertOrUpdate failed", e);
			throw e;

		}
	}

	@Override
	public void remove(GroupePedagogiqueDto groupePedagogiqueDto) {
		try {
			GroupePedagogique groupePedagogique = mapper.map(
					groupePedagogiqueDto, GroupePedagogique.class);
			groupePedagogique = groupePedagogiqueDao.merge(groupePedagogique);
			groupePedagogiqueDao.remove(groupePedagogique);
		} catch (RuntimeException e) {
			log.error("insertOrUpdate failed", e);
			throw e;

		}
	}

	@Override
	public List<GroupePedagogiqueDto> findByQuery(String query) {
		try {
			List<GroupePedagogique> groupePedagogiques = groupePedagogiqueDao
					.findByQuery(query);

			List<GroupePedagogiqueDto> groupePedagogiqueDtos = new ArrayList<GroupePedagogiqueDto>();

			for (GroupePedagogique groupePedagogique : groupePedagogiques) {
				groupePedagogiqueDtos.add(mapper.map(groupePedagogique,
						GroupePedagogiqueDto.class));
			}

			return groupePedagogiqueDtos;
		} catch (RuntimeException e) {
			log.error("insertOrUpdate failed", e);
			throw e;

		}

	}

	@Override
	public GroupePedagogiqueDto findById(int id) {
		try {
			GroupePedagogique groupePedagogique = groupePedagogiqueDao
					.findById(id);

			if (groupePedagogique != null)
				return mapper
						.map(groupePedagogique, GroupePedagogiqueDto.class);

			return null;
		} catch (RuntimeException e) {
			log.error("insertOrUpdate failed", e);
			throw e;

		}
	}

	@Override
	public List<GroupePedagogiqueDto> findAll() {
		try {
			List<GroupePedagogique> groupePedagogiques = groupePedagogiqueDao
					.findAll();

			List<GroupePedagogiqueDto> groupePedagogiqueDtos = new ArrayList<GroupePedagogiqueDto>();

			for (GroupePedagogique groupePedagogique : groupePedagogiques) {
				groupePedagogiqueDtos.add(mapper.map(groupePedagogique,
						GroupePedagogiqueDto.class));
			}

			return groupePedagogiqueDtos;
		} catch (RuntimeException e) {
			log.error("insertOrUpdate failed", e);
			throw e;

		}

	}

	@Override
	public List<GroupePedagogiqueDto> findGeneric(String value,
			String refCodeEtab) {
		try {

			List<GroupePedagogiqueDto> groupePedagogiqueDtoList = new ArrayList<GroupePedagogiqueDto>();
			List<GroupePedagogique> groupePedagogiqueList = groupePedagogiqueDao
					.findGeneric(value, refCodeEtab);
			if (groupePedagogiqueList != null) {
				log.debug("groupe pedagogique list success with size =  "
						+ groupePedagogiqueList.size());

				for (GroupePedagogique currentGroupePedagogique : groupePedagogiqueList) {
					GroupePedagogiqueDto groupePedagogiqueDto = new GroupePedagogiqueDto();
					mapper.map(currentGroupePedagogique, groupePedagogiqueDto);

					groupePedagogiqueDtoList.add(groupePedagogiqueDto);
				}
				return groupePedagogiqueDtoList;
			}

		} catch (RuntimeException e) {
			log.error("findGeneric failed", e);
			throw e;

		}
		return null;
	}

	/**
	 * [GroupePedagogiqueServiceImpl.mapper] Getter
	 * 
	 * @author MAKERRI Sofiane on : 8 juin 2014 11:58:57
	 * @return the mapper
	 */
	public DozerBeanMapper getMapper() {
		return mapper;
	}

	/**
	 * [GroupePedagogiqueServiceImpl.mapper] Setter
	 * 
	 * @author MAKERRI Sofiane on : 8 juin 2014 11:58:57
	 * @param mapper
	 *            the mapper to set
	 */
	public void setMapper(DozerBeanMapper mapper) {
		this.mapper = mapper;
	}

	// @Override
	// public List<GroupePedagogiqueDto> findAllSections(String refCodeEtab,
	// Integer typeGroupe, Integer idPeriode) {
	// try {
	//
	// List<GroupePedagogiqueDto> groupePedagogiqueDtoList = new
	// ArrayList<GroupePedagogiqueDto>();
	// List<GroupePedagogique> groupePedagogiqueList = groupePedagogiqueDao
	// .findAllSections(refCodeEtab, typeGroupe, idPeriode);
	// if (groupePedagogiqueList != null) {
	// log.debug("groupe pedagogique list success with size =  "
	// + groupePedagogiqueList.size());
	//
	// for (GroupePedagogique currentGroupePedagogique : groupePedagogiqueList)
	// {
	// GroupePedagogiqueDto groupePedagogiqueDto = new GroupePedagogiqueDto();
	// mapper.map(currentGroupePedagogique, groupePedagogiqueDto);
	//
	// groupePedagogiqueDtoList.add(groupePedagogiqueDto);
	// }
	// return groupePedagogiqueDtoList;
	// }
	//
	// } catch (RuntimeException e) {
	// log.error("findAllSections failed", e);
	// throw e;
	//
	// }
	// return null;
	// }

	@Override
	public List<GroupePedagogiqueDto> findByOf(Integer oofId, String refCodeEtab) {
		try {

			List<GroupePedagogiqueDto> groupePedagogiqueDtoList = new ArrayList<GroupePedagogiqueDto>();
			List<GroupePedagogique> groupePedagogiqueList = groupePedagogiqueDao
					.findByOf(oofId, refCodeEtab);
			if (groupePedagogiqueList != null) {
				log.debug("findByOf success with size =  "
						+ groupePedagogiqueList.size());

				for (GroupePedagogique currentGroupePedagogique : groupePedagogiqueList) {
					GroupePedagogiqueDto groupePedagogiqueDto = new GroupePedagogiqueDto();
					mapper.map(currentGroupePedagogique, groupePedagogiqueDto);

					groupePedagogiqueDtoList.add(groupePedagogiqueDto);
				}
				return groupePedagogiqueDtoList;
			}

		} catch (RuntimeException e) {
			log.error("findByOf failed", e);
			throw e;

		}
		return null;
	}

	@Override
	public List<GroupePedagogiqueDto> findAdvanced(
			GroupePedagogiqueDto searchDto) {
		try {

			GroupePedagogique search = new GroupePedagogique();
			mapper.map(searchDto, search);
			List<GroupePedagogique> groupePedagogiqueList = groupePedagogiqueDao
					.findAdvanced(search);
			return Utility.map(mapper, groupePedagogiqueList,
					GroupePedagogiqueDto.class);

		} catch (RuntimeException e) {
			log.error("findAdvanced failed", e);
			throw e;

		}
	}

	@Override
	public GroupePedagogiqueDto groupeNameExist(
			GroupePedagogiqueDto groupePedagogiqueDto) {
		try {
			GroupePedagogique groupePedagogique = new GroupePedagogique();
			mapper.map(groupePedagogiqueDto, groupePedagogique);
			groupePedagogique = groupePedagogiqueDao
					.groupeNameExist(groupePedagogique);
			if (groupePedagogique != null) {
				return mapper
						.map(groupePedagogique, GroupePedagogiqueDto.class);
			}
		} catch (RuntimeException e) {
			log.error("groupeNameExist failed", e);
			throw e;
		}
		return null;
	}

	@Override
	public List<GroupePedagogiqueDto> findGroupesOfSection(
			GroupePedagogiqueDto groupePedagogiqueDto) {
		try {
			GroupePedagogique groupePedagogique = new GroupePedagogique();
			mapper.map(groupePedagogiqueDto, groupePedagogique);

			List<GroupePedagogiqueDto> groupePedagogiqueDtoList = new ArrayList<GroupePedagogiqueDto>();
			List<GroupePedagogique> groupePedagogiqueList = groupePedagogiqueDao
					.findGroupesOfSection(groupePedagogique);
			if (groupePedagogiqueList != null) {
				log.debug("findGroupesOfSection list success with size =  "
						+ groupePedagogiqueList.size());

				for (GroupePedagogique currentGroupePedagogique : groupePedagogiqueList) {
					groupePedagogiqueDto = new GroupePedagogiqueDto();
					mapper.map(currentGroupePedagogique, groupePedagogiqueDto);
					groupePedagogiqueDtoList.add(groupePedagogiqueDto);
				}
				return groupePedagogiqueDtoList;
			}

		} catch (RuntimeException e) {
			log.error("findGroupesOfSection failed", e);
			throw e;

		}
		return null;
	}

	@Override
	public List<GroupePedagogiqueDto> findGroupesOfSection(Integer idSection) {
		try {
			if (idSection == null) {
				return null;
			}
			List<GroupePedagogiqueDto> groupePedagogiqueDtoList = new ArrayList<GroupePedagogiqueDto>();
			List<GroupePedagogique> groupePedagogiqueList = groupePedagogiqueDao
					.findGroupesOfSection(idSection);
			if (groupePedagogiqueList != null) {
				log.debug("findGroupesOfSection list success with size =  "
						+ groupePedagogiqueList.size());

				for (GroupePedagogique currentGroupePedagogique : groupePedagogiqueList) {
					GroupePedagogiqueDto groupePedagogiqueDto = new GroupePedagogiqueDto();
					mapper.map(currentGroupePedagogique, groupePedagogiqueDto);
					groupePedagogiqueDtoList.add(groupePedagogiqueDto);
				}
				return groupePedagogiqueDtoList;
			}

		} catch (RuntimeException e) {
			log.error("findGroupesOfSection failed", e);
			throw e;

		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.fve.business.service.cursus.GroupePedagogiqueService
	 * #findSection(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<GroupePedagogiqueDto> findSection(Integer oofId,
			Integer idPeriode) {
		try {
			List<GroupePedagogique> groupePedagogiqueList = groupePedagogiqueDao
					.findSection(oofId, idPeriode);
			if (groupePedagogiqueList != null) {
				log.debug("findSection list success with size =  "
						+ groupePedagogiqueList.size());
				return Utility.map(mapper, groupePedagogiqueList,
						GroupePedagogiqueDto.class);
			}

		} catch (RuntimeException e) {
			log.error("findSection failed", e);
			throw e;

		}
		return null;
	}

}
