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

import dz.gov.mesrs.sii.commons.data.dao.fve.bac.CapaciteEtabDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.AnneeAcademiqueDao;
import dz.gov.mesrs.sii.commons.data.model.fve.bac.CapaciteEtab;
import dz.gov.mesrs.sii.commons.data.model.fve.bac.NoteAccessFiliere;
import dz.gov.mesrs.sii.commons.data.model.fve.bac.PrioriteSerieBac;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.AnneeAcademique;
import dz.gov.mesrs.sii.fve.business.model.dto.bac.CapaciteEtabDto;
import dz.gov.mesrs.sii.fve.business.model.dto.bac.NoteAccessFiliereDto;
import dz.gov.mesrs.sii.fve.business.model.dto.bac.PrioriteSerieBacDto;
import dz.gov.mesrs.sii.fve.business.service.bac.CapaciteEtabService;

/**
 * @author Mounir.MESSAOUDI on : 14 ao�t 2014 12:12:57
 */
@Service("capaciteEtabService")
public class CapaciteEtabServiceImpl implements CapaciteEtabService {

	private static final Log log = LogFactory
			.getLog(CapaciteEtabServiceImpl.class);

	@Autowired
	@Qualifier("capaciteEtabDao")
	private CapaciteEtabDao capaciteEtabDao;

	@Autowired
	@Qualifier("anneeAcademiqueDao")
	private AnneeAcademiqueDao anneeAcademiqueDao;

	@Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;

	public CapaciteEtabServiceImpl() {

	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public CapaciteEtabDto insertOrUpdate(CapaciteEtabDto capaciteEtabDto) {
		try {
			CapaciteEtab capaciteEtab = mapper.map(capaciteEtabDto,
					CapaciteEtab.class);

			List<PrioriteSerieBacDto> prioriteSerieBacDto = capaciteEtabDto
					.getPrioriteSerieBacDto();
			if (prioriteSerieBacDto != null && prioriteSerieBacDto.size() > 0) {
				List<PrioriteSerieBac> prioriteSerieBacBos = new ArrayList<>();
				for (PrioriteSerieBacDto p : prioriteSerieBacDto) {
					PrioriteSerieBac pp = mapper.map(p, PrioriteSerieBac.class);
					pp.setCapaciteEtab(capaciteEtab);

					// notes matieres
					List<NoteAccessFiliere> notesMatieres = new ArrayList<>();
					List<NoteAccessFiliereDto> na = p
							.getNoteAccessFilieresDto();
					if (na != null && na.size() > 0) {
						for (NoteAccessFiliereDto notesBachelierDto : na) {
							NoteAccessFiliere n = mapper.map(notesBachelierDto,
									NoteAccessFiliere.class);
							n.setPrioriteSerieBac(pp);
							notesMatieres.add(n);
						}
					}
					pp.setNoteAccessFilieres(notesMatieres);
					prioriteSerieBacBos.add(pp);

				}
				capaciteEtab.setPrioriteSerieBac(prioriteSerieBacBos);

			}
			AnneeAcademique anneeAcademique = anneeAcademiqueDao
					.findById(capaciteEtabDto.getIdAnneeAcademique());
			capaciteEtab.setAnneeAcademique(anneeAcademique);
			if (capaciteEtab.getId() == 0) {
				capaciteEtabDao.persist(capaciteEtab);
			} else {
				capaciteEtab = capaciteEtabDao.merge(capaciteEtab);
			}
			mapper.map(capaciteEtab, capaciteEtabDto);

			log.error("insertorupdate CapaciteEtab succes..");

			return capaciteEtabDto;

		} catch (RuntimeException e) {
			log.error("insertorupdate CapaciteEtab failed--", e);
			throw e;
		}
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void remove(CapaciteEtabDto capaciteEtabDto) {
		try {

			CapaciteEtab capaciteEtab = mapper.map(capaciteEtabDto,
					CapaciteEtab.class);

			capaciteEtab = capaciteEtabDao.findById(capaciteEtab.getId());
			capaciteEtabDao.remove(capaciteEtab);
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public CapaciteEtabDto findById(int id) {

		CapaciteEtab capaciteEtab = capaciteEtabDao.findById(id);

		if (capaciteEtab != null)
			return mapper.map(capaciteEtab, CapaciteEtabDto.class);

		return null;
	}

	@Override
	public List<CapaciteEtabDto> findAll() {

		List<CapaciteEtab> capaciteEtabs = capaciteEtabDao.findAll();

		List<CapaciteEtabDto> capaciteEtabDtos = new ArrayList<CapaciteEtabDto>();

		for (CapaciteEtab capaciteEtab : capaciteEtabs) {
			capaciteEtabDtos.add(this.map(capaciteEtab));
		}

		return capaciteEtabDtos;

	}

	/**
	 * Renvoi la liste des capcistes par annee academique
	 * 
	 * @author Mounir.MESSAOUDI on : 19 ao�t 2014 15:25:30
	 * @param idAnneeAcademique
	 * @return
	 */
	public List<CapaciteEtabDto> findByIdAnneeAcademique(int idAnneeAcademique) {
		List<CapaciteEtab> capaciteEtabs = capaciteEtabDao
				.findByIdAnneeAcademique(idAnneeAcademique);

		List<CapaciteEtabDto> capaciteEtabDtos = new ArrayList<CapaciteEtabDto>();

		for (CapaciteEtab capaciteEtab : capaciteEtabs) {
			capaciteEtabDtos.add(this.map(capaciteEtab));
		}
		return capaciteEtabDtos;
	}

	/**
	 * Renvoi la liste des capcistes par annee academique
	 * 
	 * @author Mounir.MESSAOUDI on : 19 ao�t 2014 15:25:30
	 * @param searchDto
	 * @return
	 */
	public List<CapaciteEtabDto> findAdvanced(CapaciteEtabDto searchDto) {

		// CapaciteEtab searchBo = new CapaciteEtab();
		// mapper.map(searchBo, searchDto);
		CapaciteEtab searchBo = mapper.map(searchDto, CapaciteEtab.class);

		List<CapaciteEtab> capaciteEtabs = capaciteEtabDao
				.findAdvanced(searchBo);

		List<CapaciteEtabDto> capaciteEtabDtos = new ArrayList<CapaciteEtabDto>();
		for (CapaciteEtab capaciteEtab : capaciteEtabs) {
			capaciteEtabDtos.add(this.map(capaciteEtab));
		}
		return capaciteEtabDtos;
	}

	@Override
	public CapaciteEtabDto map(CapaciteEtab capaciteEtab) {
		CapaciteEtabDto capaciteEtabDto = mapper.map(capaciteEtab,
				CapaciteEtabDto.class);

		// TODO le mapping annnee academique ne capaciteEtab pas
		capaciteEtabDto.setIdAnneeAcademique(capaciteEtab.getAnneeAcademique()
				.getId());
		capaciteEtabDto.setAnneeAcademiquePremiereAnnee(capaciteEtab
				.getAnneeAcademique().getPremiereAnnee());
		capaciteEtabDto.setAnneeAcademiqueDeuxiemeAnnee(capaciteEtab
				.getAnneeAcademique().getDeuxiemeAnnee());
		String anneeAcademiquelibelle = capaciteEtabDto
				.getAnneeAcademiquePremiereAnnee()
				+ "/"
				+ +capaciteEtabDto.getAnneeAcademiqueDeuxiemeAnnee();
		capaciteEtabDto.setAnneeAcademiquelibelle(anneeAcademiquelibelle);

		List<PrioriteSerieBac> l = capaciteEtab.getPrioriteSerieBac();
		List<PrioriteSerieBacDto> ldto = new ArrayList<>();
		if (l != null && l.size() > 0) {
			for (PrioriteSerieBac prioriteSerieBac : l) {
				PrioriteSerieBacDto pp = mapper.map(prioriteSerieBac,
						PrioriteSerieBacDto.class);

				List<NoteAccessFiliere> s = prioriteSerieBac
						.getNoteAccessFilieres();
				List<NoteAccessFiliereDto> ss = new ArrayList<>();
				if (s != null && s.size() > 0) {
					for (NoteAccessFiliere noteAccessFiliere : s) {
						NoteAccessFiliereDto zz = mapper.map(noteAccessFiliere,
								NoteAccessFiliereDto.class);
						ss.add(zz);
					}
				}
				pp.setNoteAccessFilieresDto(ss);
				ldto.add(pp);
			}
		}
		capaciteEtabDto.setPrioriteSerieBacDto(ldto);
		return capaciteEtabDto;
	}
}
