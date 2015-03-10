package dz.gov.mesrs.sii.fve.business.service.impl.bac;

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

import dz.gov.mesrs.sii.commons.data.dao.fve.bac.AccessWilayaDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.AnneeAcademiqueDao;
import dz.gov.mesrs.sii.commons.data.model.fve.bac.AccessWilaya;
import dz.gov.mesrs.sii.commons.data.model.fve.bac.DroitAccessWilaya;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.AnneeAcademique;
import dz.gov.mesrs.sii.fve.business.model.dto.bac.AccessWilayaDto;
import dz.gov.mesrs.sii.fve.business.model.dto.bac.DroitAccessWilayaDto;
import dz.gov.mesrs.sii.fve.business.service.bac.AccessWilayaService;

/**
 * @author Mounir.MESSAOUDI on : 14 ao�t 2014 12:12:37
 */
@Service("accessWilayaService")
public class AccessWilayaServiceImpl implements AccessWilayaService {

	@Autowired
	@Qualifier("accessWilayaDao")
	private AccessWilayaDao accessWilayaDao;

	@Autowired
	@Qualifier("anneeAcademiqueDao")
	private AnneeAcademiqueDao anneeAcademiqueDao;

	private static final Log log = LogFactory.getLog(AccessWilayaServiceImpl.class);

	@Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;

	public AccessWilayaServiceImpl() {

	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public AccessWilayaDto insertOrUpdate(AccessWilayaDto accessWilayaDto) {
		try {
			AccessWilaya accessWilaya = mapper.map(accessWilayaDto, AccessWilaya.class);
			AnneeAcademique anneeAcademique = anneeAcademiqueDao.findById(accessWilayaDto.getIdAnneeAcademique());
			accessWilaya.setAnneeAcademique(anneeAcademique);

			// attacher accessWilaya au droitAccessWilaya
			List<DroitAccessWilayaDto> ld = accessWilayaDto.getDroitAccessWilayaDto();

			accessWilaya.setDroitAccessWilaya(new ArrayList<DroitAccessWilaya>());

			for (DroitAccessWilayaDto droitAccessWilayaDto : ld) {
				DroitAccessWilaya droitAccessWilaya = mapper.map(droitAccessWilayaDto, DroitAccessWilaya.class);
				droitAccessWilaya.setAccessWilaya(accessWilaya);
				accessWilaya.addDroitAccessWilaya(droitAccessWilaya);
			}

			if (accessWilaya.getId() == 0) {
				accessWilayaDao.persist(accessWilaya);
			} else {
				accessWilaya = accessWilayaDao.merge(accessWilaya);
			}
			mapper.map(accessWilaya, accessWilayaDto);

			log.error("insertorupdate AccessWilaya succes..");

			return accessWilayaDto;

		} catch (RuntimeException e) {
			log.error("insertorupdate AccessWilaya failed--", e);
			throw e;
		}
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void remove(AccessWilayaDto accessWilayaDto) {
		try {

			AccessWilaya accessWilaya = mapper.map(accessWilayaDto, AccessWilaya.class);

			accessWilaya = accessWilayaDao.findById(accessWilaya.getId());

			accessWilayaDao.remove(accessWilaya);
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public AccessWilayaDto findById(int id) {

		AccessWilaya accessWilaya = accessWilayaDao.findById(id);

		if (accessWilaya != null) {
			AccessWilayaDto accessWilayaDto = mapper.map(accessWilaya, AccessWilayaDto.class);

			// TODO le mapping annnee academique ne marche pas
			accessWilayaDto.setIdAnneeAcademique(accessWilaya.getAnneeAcademique().getId());
			accessWilayaDto.setAnneeAcademiquePremiereAnnee(accessWilaya.getAnneeAcademique().getPremiereAnnee());
			accessWilayaDto.setAnneeAcademiqueDeuxiemeAnnee(accessWilaya.getAnneeAcademique().getDeuxiemeAnnee());
			String anneeAcademiquelibelle = accessWilayaDto.getAnneeAcademiquePremiereAnnee() + "/"
					+ +accessWilayaDto.getAnneeAcademiqueDeuxiemeAnnee();
			accessWilayaDto.setAnneeAcademiquelibelle(anneeAcademiquelibelle);

			// mappping des droits d'access wilayas
			List<DroitAccessWilayaDto> listDroitAccessWilayasDto = new ArrayList<DroitAccessWilayaDto>();
			List<DroitAccessWilaya> listDroitAccessWilayas = accessWilaya.getDroitAccessWilaya();
			if (listDroitAccessWilayas != null) {
				for (DroitAccessWilaya droitAccessWilaya : listDroitAccessWilayas) {
					DroitAccessWilayaDto droitAccessWilayaDto = mapper.map(droitAccessWilaya,
							DroitAccessWilayaDto.class);
					listDroitAccessWilayasDto.add(droitAccessWilayaDto);
				}
			}
			accessWilayaDto.setDroitAccessWilayaDto(listDroitAccessWilayasDto);

			return accessWilayaDto;

		} else {
			return null;
		}
	}

	@Override
	public List<AccessWilayaDto> findAll() {

		List<AccessWilaya> accessWilayas = accessWilayaDao.findAll();
		List<AccessWilayaDto> accessWilayaDtos = new ArrayList<AccessWilayaDto>();

		for (AccessWilaya accessWilaya : accessWilayas) {
			AccessWilayaDto accessWilayaDto = mapper.map(accessWilaya, AccessWilayaDto.class);

			// TODO le mapping annnee academique ne marche pas
			accessWilayaDto.setIdAnneeAcademique(accessWilaya.getAnneeAcademique().getId());
			accessWilayaDto.setAnneeAcademiquePremiereAnnee(accessWilaya.getAnneeAcademique().getPremiereAnnee());
			accessWilayaDto.setAnneeAcademiqueDeuxiemeAnnee(accessWilaya.getAnneeAcademique().getDeuxiemeAnnee());
			String anneeAcademiquelibelle = accessWilayaDto.getAnneeAcademiquePremiereAnnee() + "/"
					+ +accessWilayaDto.getAnneeAcademiqueDeuxiemeAnnee();
			accessWilayaDto.setAnneeAcademiquelibelle(anneeAcademiquelibelle);

			// mappping des droits d'access wilayas
			List<DroitAccessWilayaDto> listDroitAccessWilayasDto = new ArrayList<DroitAccessWilayaDto>();
			List<DroitAccessWilaya> listDroitAccessWilayas = accessWilaya.getDroitAccessWilaya();
			if (listDroitAccessWilayas != null) {
				for (DroitAccessWilaya droitAccessWilaya : listDroitAccessWilayas) {
					DroitAccessWilayaDto droitAccessWilayaDto = mapper.map(droitAccessWilaya,
							DroitAccessWilayaDto.class);
					listDroitAccessWilayasDto.add(droitAccessWilayaDto);
				}
			}
			accessWilayaDto.setDroitAccessWilayaDto(listDroitAccessWilayasDto);

			// map
			accessWilayaDtos.add(accessWilayaDto);

		}

		return accessWilayaDtos;

	}

	/**
	 * Renvoi la liste des access wilayas par annee academique
	 * 
	 * @author Mounir.MESSAOUDI on : 19 ao�t 2014 15:25:30
	 * @param idAnneeAcademique
	 * @return
	 */
	@Override
	public List<AccessWilayaDto> findByIdAnneeAcademique(int idAnneeAcademique) {

		List<AccessWilaya> accessWilayas = accessWilayaDao.findByIdAnneeAcademique(idAnneeAcademique);
		List<AccessWilayaDto> accessWilayaDtos = new ArrayList<AccessWilayaDto>();

		for (AccessWilaya accessWilaya : accessWilayas) {
			AccessWilayaDto accessWilayaDto = mapper.map(accessWilaya, AccessWilayaDto.class);

			// TODO le mapping annnee academique ne marche pas
			accessWilayaDto.setIdAnneeAcademique(accessWilaya.getAnneeAcademique().getId());
			accessWilayaDto.setAnneeAcademiquePremiereAnnee(accessWilaya.getAnneeAcademique().getPremiereAnnee());
			accessWilayaDto.setAnneeAcademiqueDeuxiemeAnnee(accessWilaya.getAnneeAcademique().getDeuxiemeAnnee());
			String anneeAcademiquelibelle = accessWilayaDto.getAnneeAcademiquePremiereAnnee() + "/"
					+ +accessWilayaDto.getAnneeAcademiqueDeuxiemeAnnee();
			accessWilayaDto.setAnneeAcademiquelibelle(anneeAcademiquelibelle);

			// mappping des droits d'access wilayas
			List<DroitAccessWilayaDto> listDroitAccessWilayasDto = new ArrayList<DroitAccessWilayaDto>();
			List<DroitAccessWilaya> listDroitAccessWilayas = accessWilaya.getDroitAccessWilaya();
			if (listDroitAccessWilayas != null) {
				for (DroitAccessWilaya droitAccessWilaya : listDroitAccessWilayas) {
					DroitAccessWilayaDto droitAccessWilayaDto = mapper.map(droitAccessWilaya,
							DroitAccessWilayaDto.class);
					listDroitAccessWilayasDto.add(droitAccessWilayaDto);
				}
			}
			accessWilayaDto.setDroitAccessWilayaDto(listDroitAccessWilayasDto);

			accessWilayaDtos.add(accessWilayaDto);
		}

		return accessWilayaDtos;
	}

	/**
	 * Recherche avancee
	 * 
	 * @author Mounir.MESSAOUDI on : 19 ao�t 2014 15:25:30
	 * @param searchDto
	 * @return
	 */
	@Override
	public List<AccessWilayaDto> findAdvanced(AccessWilayaDto searchDto) {

		AccessWilaya searchBo = new AccessWilaya();
		mapper.map(searchDto, searchBo);
		List<AccessWilaya> accessWilayas = accessWilayaDao.findAdvanced(searchBo);
		List<AccessWilayaDto> accessWilayaDtos = new ArrayList<AccessWilayaDto>();

		for (AccessWilaya accessWilaya : accessWilayas) {
			AccessWilayaDto accessWilayaDto = mapper.map(accessWilaya, AccessWilayaDto.class);

			// TODO le mapping annnee academique ne marche pas
			accessWilayaDto.setIdAnneeAcademique(accessWilaya.getAnneeAcademique().getId());
			accessWilayaDto.setAnneeAcademiquePremiereAnnee(accessWilaya.getAnneeAcademique().getPremiereAnnee());
			accessWilayaDto.setAnneeAcademiqueDeuxiemeAnnee(accessWilaya.getAnneeAcademique().getDeuxiemeAnnee());
			String anneeAcademiquelibelle = accessWilayaDto.getAnneeAcademiquePremiereAnnee() + "/"
					+ +accessWilayaDto.getAnneeAcademiqueDeuxiemeAnnee();
			accessWilayaDto.setAnneeAcademiquelibelle(anneeAcademiquelibelle);

			// mappping des droits d'access wilayas
			List<DroitAccessWilayaDto> listDroitAccessWilayasDto = new ArrayList<DroitAccessWilayaDto>();
			List<DroitAccessWilaya> listDroitAccessWilayas = accessWilaya.getDroitAccessWilaya();
			if (listDroitAccessWilayas != null) {
				for (DroitAccessWilaya droitAccessWilaya : listDroitAccessWilayas) {
					DroitAccessWilayaDto droitAccessWilayaDto = mapper.map(droitAccessWilaya,
							DroitAccessWilayaDto.class);
					listDroitAccessWilayasDto.add(droitAccessWilayaDto);
				}
			}
			accessWilayaDto.setDroitAccessWilayaDto(listDroitAccessWilayasDto);

			accessWilayaDtos.add(accessWilayaDto);
		}

		return accessWilayaDtos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.fve.business.service.bac.AccessWilayaService#remove(
	 * dz.gov.mesrs.sii.fve.business.model.dto.bac.DroitAccessWilayaDto)
	 */
	@Override
	public void remove(DroitAccessWilayaDto droitAccessWilayaDto) {
		try {

			DroitAccessWilaya droitAccessWilaya = mapper.map(droitAccessWilayaDto, DroitAccessWilaya.class);

			// droitAccessWilaya =
			// accessWilayaDao.findById(droitAccessWilaya.getId());

			accessWilayaDao.remove(droitAccessWilaya);
		} catch (RuntimeException e) {
			throw e;
		}

	}

}
