package dz.gov.mesrs.sii.fve.business.service.impl.cursus;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.TransfertDao;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.Transfert;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.TransfertExterne;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.TransfertExterneBachelier;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.TransfertExterneSortanteBachelier;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.TransfertInterne;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.TransfertInterneBachelier;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.TransfertStatistiques;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.TransfertStatistiquesTypeTransfert;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.TransfertTypeTransfert;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.TransfertDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.TransfertStatistiquesDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.TransfertStatistiquesTypeTransfertDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.TransfertService;

/**
 * 
 * @author Mounir.MESSAOUDI
 */
@Service("transfertService")
public class TransfertServiceImpl implements TransfertService {

	private static final Log log = LogFactory
			.getLog(TitreAccesServiceImpl.class);

	@Autowired
	private TransfertDao transfertDao;

	@Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;

	@Override
	public TransfertDto findById(int id) {
		Transfert transfert = transfertDao.findById(id);

		if (transfert != null) {
			return mapper.map(transfert, TransfertDto.class);
		}

		return null;
	}

	@Override
	public List<TransfertDto> findByQuery(String query) {
		List<Transfert> transferts = transfertDao.findByQuery(query);

		List<TransfertDto> transfertDtos = new ArrayList<TransfertDto>();

		for (Transfert transfert : transferts) {
			transfertDtos.add(mapper.map(transfert, TransfertDto.class));
		}

		return transfertDtos;
	}

	@Override
	public List<TransfertDto> findByRefCodeEtablissement(String refCodeEtab) {
		List<Transfert> transferts = transfertDao
				.findByRefCodeEtablissement(refCodeEtab);

		List<TransfertDto> transfertDtos = new ArrayList<TransfertDto>();

		for (Transfert transfert : transferts) {
			transfertDtos.add(mapper.map(transfert, TransfertDto.class));
		}

		return transfertDtos;
	}

	/**
	 * Recherche par annnee academique et code etablissement
	 * 
	 * @author Mounir.MESSAOUDI on : 31 ao�t 2014 12:54:57
	 * @param idAnneeAcademique
	 * @param refCodeEtab
	 * @return
	 */
	@Override
	public List<TransfertDto> findByIdAnneeAcademiqueRefCodeEtablissement(
			Integer idAnneeAcademique, String refCodeEtab) {

		List<Transfert> transferts = transfertDao
				.findByIdAnneeAcademiqueRefCodeEtablissement(idAnneeAcademique,
						refCodeEtab);

		List<TransfertDto> transfertDtos = new ArrayList<TransfertDto>();

		for (Transfert transfert : transferts) {
			transfertDtos.add(mapper.map(transfert, TransfertDto.class));
		}

		return transfertDtos;

	}

	@Override
	public List<TransfertDto> findAll() {

		List<Transfert> transferts = transfertDao.findAll();

		List<TransfertDto> transfertDtos = new ArrayList<TransfertDto>();

		for (Transfert transfert : transferts) {
			transfertDtos.add(mapper.map(transfert, TransfertDto.class));
		}

		return transfertDtos;
	}

	@Override
	public List<TransfertStatistiquesDto> findStatsByIdAnneeAcademiqueRefCodeEtablissement(
			Integer idAnneeAcademique, String refCodeEtab) {
		List<TransfertStatistiques> transferts = transfertDao
				.findStatsByIdAnneeAcademiqueRefCodeEtablissement(
						idAnneeAcademique, refCodeEtab);

		List<TransfertStatistiquesDto> transfertDtos = new ArrayList<TransfertStatistiquesDto>();

		for (TransfertStatistiques transfert : transferts) {
			transfertDtos.add(mapper.map(transfert,
					TransfertStatistiquesDto.class));
		}

		return transfertDtos;
	}

	@Override
	public List<TransfertStatistiquesTypeTransfertDto> findStatsByIdAnneeAcademiqueRefCodeEtablissementTypeTransfert(
			Integer idAnneeAcademique, String refCodeEtab,
			String refCodeTypeTransfert) {
		List<TransfertStatistiquesTypeTransfert> transferts = transfertDao
				.findStatsByIdAnneeAcademiqueRefCodeEtablissementTypeTransfert(
						idAnneeAcademique, refCodeEtab, refCodeTypeTransfert);

		List<TransfertStatistiquesTypeTransfertDto> transfertDtos = new ArrayList<TransfertStatistiquesTypeTransfertDto>();

		for (TransfertStatistiquesTypeTransfert transfert : transferts) {
			transfertDtos.add(mapper.map(transfert,
					TransfertStatistiquesTypeTransfertDto.class));
		}

		return transfertDtos;
	}

	@Override
	public List<TransfertDto> findTransfertInterneByQuery(String query) {
		List<TransfertInterne> transferts = transfertDao
				.findTransfertInterneByQuery(query);

		List<TransfertDto> transfertDtos = new ArrayList<TransfertDto>();

		for (TransfertInterne transfert : transferts) {
			transfertDtos.add(mapper.map(transfert, TransfertDto.class));
		}

		return transfertDtos;
	}

	@Override
	public List<TransfertDto> findTransfertInterneByRefCodeEtab(
			String refCodeEtab) {
		List<TransfertInterne> transferts = transfertDao
				.findTransfertInterneByRefCodeEtab(refCodeEtab);

		List<TransfertDto> transfertDtos = new ArrayList<TransfertDto>();

		for (TransfertInterne transfert : transferts) {
			transfertDtos.add(mapper.map(transfert, TransfertDto.class));
		}

		return transfertDtos;
	}

	@Override
	public List<TransfertDto> findTransfertInterneByIdAnnAcadRefCodeEtab(
			Integer idAnneeAcademique, String refCodeEtab) {
		List<TransfertInterne> transferts = transfertDao
				.findTransfertInterneByIdAnnAcadRefCodeEtab(idAnneeAcademique,
						refCodeEtab);

		List<TransfertDto> transfertDtos = new ArrayList<TransfertDto>();

		for (TransfertInterne transfert : transferts) {
			transfertDtos.add(mapper.map(transfert, TransfertDto.class));
		}

		return transfertDtos;
	}

	@Override
	public List<TransfertDto> findTransfertExterneByQuery(String query) {
		List<TransfertExterne> transferts = transfertDao
				.findTransfertExterneByQuery(query);

		List<TransfertDto> transfertDtos = new ArrayList<TransfertDto>();

		for (TransfertExterne transfert : transferts) {
			transfertDtos.add(mapper.map(transfert, TransfertDto.class));
		}

		return transfertDtos;
	}

	@Override
	public List<TransfertDto> findTransfertExterneByRefCodeEtab(
			String refCodeEtab) {
		List<TransfertExterne> transferts = transfertDao
				.findTransfertExterneByRefCodeEtab(refCodeEtab);

		List<TransfertDto> transfertDtos = new ArrayList<TransfertDto>();

		for (TransfertExterne transfert : transferts) {
			transfertDtos.add(mapper.map(transfert, TransfertDto.class));
		}

		return transfertDtos;
	}

	@Override
	public List<TransfertDto> findTransfertExterneByIdAnnAcadRefCodeEtab(
			Integer idAnneeAcademique, String refCodeEtab) {
		List<TransfertExterne> transferts = transfertDao
				.findTransfertExterneByIdAnnAcadRefCodeEtab(idAnneeAcademique,
						refCodeEtab);

		List<TransfertDto> transfertDtos = new ArrayList<TransfertDto>();

		for (TransfertExterne transfert : transferts) {
			transfertDtos.add(mapper.map(transfert, TransfertDto.class));
		}

		return transfertDtos;
	}

	@Override
	public List<TransfertDto> findTransfertInterneBachelierByQuery(String query) {
		List<TransfertInterneBachelier> transferts = transfertDao
				.findTransfertInterneBachelierByQuery(query);

		List<TransfertDto> transfertDtos = new ArrayList<TransfertDto>();

		for (TransfertInterneBachelier transfert : transferts) {
			transfertDtos.add(mapper.map(transfert, TransfertDto.class));
		}

		return transfertDtos;
	}

	@Override
	public List<TransfertDto> findTransfertInterneBachelierByRefCodeEtab(
			String refCodeEtab) {
		List<TransfertInterneBachelier> transferts = transfertDao
				.findTransfertInterneBachelierByRefCodeEtab(refCodeEtab);

		List<TransfertDto> transfertDtos = new ArrayList<TransfertDto>();

		for (TransfertInterneBachelier transfert : transferts) {
			transfertDtos.add(mapper.map(transfert, TransfertDto.class));
		}

		return transfertDtos;
	}

	@Override
	public List<TransfertDto> findTransfertInterneBachelierByIdAnnAcadRefCodeEtab(
			Integer idAnneeAcademique, String refCodeEtab) {
		List<TransfertInterneBachelier> transferts = transfertDao
				.findTransfertInterneBachelierByIdAnnAcadRefCodeEtab(
						idAnneeAcademique, refCodeEtab);

		List<TransfertDto> transfertDtos = new ArrayList<TransfertDto>();

		for (TransfertInterneBachelier transfert : transferts) {
			transfertDtos.add(mapper.map(transfert, TransfertDto.class));
		}

		return transfertDtos;
	}

	@Override
	public List<TransfertDto> findTransfertExterneBachelierByQuery(String query) {
		List<TransfertExterneBachelier> transferts = transfertDao
				.findTransfertExterneBachelierByQuery(query);

		List<TransfertDto> transfertDtos = new ArrayList<TransfertDto>();

		for (TransfertExterneBachelier transfert : transferts) {
			transfertDtos.add(mapper.map(transfert, TransfertDto.class));
		}

		return transfertDtos;
	}

	@Override
	public List<TransfertDto> findTransfertExterneBachelierByRefCodeEtab(
			String refCodeEtab) {
		List<TransfertExterneBachelier> transferts = transfertDao
				.findTransfertExterneBachelierByRefCodeEtab(refCodeEtab);

		List<TransfertDto> transfertDtos = new ArrayList<TransfertDto>();

		for (TransfertExterneBachelier transfert : transferts) {
			transfertDtos.add(mapper.map(transfert, TransfertDto.class));
		}

		return transfertDtos;
	}

	@Override
	public List<TransfertDto> findTransfertExterneBachelierByIdAnnAcadRefCodeEtab(
			Integer idAnneeAcademique, String refCodeEtab) {
		List<TransfertExterneBachelier> transferts = transfertDao
				.findTransfertExterneBachelierByIdAnnAcadRefCodeEtab(
						idAnneeAcademique, refCodeEtab);

		List<TransfertDto> transfertDtos = new ArrayList<TransfertDto>();

		for (TransfertExterneBachelier transfert : transferts) {
			transfertDtos.add(mapper.map(transfert, TransfertDto.class));
		}

		return transfertDtos;
	}

	@Override
	public List<TransfertDto> findTransfertTypeTransfertByQuery(String query) {
		List<TransfertTypeTransfert> transferts = transfertDao
				.findTransfertTypeTransfertByQuery(query);

		List<TransfertDto> transfertDtos = new ArrayList<TransfertDto>();

		for (TransfertTypeTransfert transfert : transferts) {
			transfertDtos.add(mapper.map(transfert, TransfertDto.class));
		}

		return transfertDtos;
	}

	@Override
	public List<TransfertDto> findTransfertTypeTransfertyRefCodeEtab(
			String refCodeEtab) {
		List<TransfertTypeTransfert> transferts = transfertDao
				.findTransfertTypeTransfertyRefCodeEtab(refCodeEtab);

		List<TransfertDto> transfertDtos = new ArrayList<TransfertDto>();

		for (TransfertTypeTransfert transfert : transferts) {
			transfertDtos.add(mapper.map(transfert, TransfertDto.class));
		}

		return transfertDtos;
	}

	@Override
	public List<TransfertDto> findTransfertTypeTransfertByIdAnnAcadRefCodeEtab(
			Integer idAnneeAcademique, String refCodeEtab) {
		List<TransfertTypeTransfert> transferts = transfertDao
				.findTransfertTypeTransfertByIdAnnAcadRefCodeEtab(
						idAnneeAcademique, refCodeEtab);

		List<TransfertDto> transfertDtos = new ArrayList<TransfertDto>();

		for (TransfertTypeTransfert transfert : transferts) {
			transfertDtos.add(mapper.map(transfert, TransfertDto.class));
		}
		return transfertDtos;
	}

	@Override
	public List<TransfertDto> findTransfertTypeTransfertByIdAnnAcadRefCodeEtabRefCodeTypeTransfert(
			Integer idAnneeAcademique, String refCodeEtab,
			String refCodeTypeTransdfert) {
		List<TransfertTypeTransfert> transferts = transfertDao
				.findTransfertTypeTransfertByIdAnnAcadRefCodeEtabRefCodeTypeTransfert(
						idAnneeAcademique, refCodeEtab, refCodeTypeTransdfert);

		List<TransfertDto> transfertDtos = new ArrayList<TransfertDto>();

		for (TransfertTypeTransfert transfert : transferts) {
			transfertDtos.add(mapper.map(transfert, TransfertDto.class));
		}

		return transfertDtos;
	}

	// transferts sortantes par type bachelier

	@Override
	public List<TransfertDto> findTransfertExterneSortanteByQuery(String query) {
		List<TransfertExterneSortanteBachelier> transferts = transfertDao
				.findTransfertExterneSortanteByQuery(query);

		List<TransfertDto> transfertDtos = new ArrayList<TransfertDto>();

		for (TransfertExterneSortanteBachelier transfert : transferts) {
			transfertDtos.add(mapper.map(transfert, TransfertDto.class));
		}

		return transfertDtos;
	}

	@Override
	public List<TransfertDto> findTransfertExterneSortanteByRefCodeEtab(
			String refCodeEtab) {
		List<TransfertExterneSortanteBachelier> transferts = transfertDao
				.findTransfertExterneSortanteByRefCodeEtab(refCodeEtab);

		List<TransfertDto> transfertDtos = new ArrayList<TransfertDto>();

		for (TransfertExterneSortanteBachelier transfert : transferts) {
			transfertDtos.add(mapper.map(transfert, TransfertDto.class));
		}

		return transfertDtos;

	}

	@Override
	public List<TransfertDto> findTransfertExterneSortanteByIdAnnAcadRefCodeEtab(
			Integer idAnneeAcademique, String refCodeEtab) {
		List<TransfertExterneSortanteBachelier> transferts = transfertDao
				.findTransfertExterneSortanteByIdAnnAcadRefCodeEtab(
						idAnneeAcademique, refCodeEtab);

		List<TransfertDto> transfertDtos = new ArrayList<TransfertDto>();

		for (TransfertExterneSortanteBachelier transfert : transferts) {
			transfertDtos.add(mapper.map(transfert, TransfertDto.class));
		}

		return transfertDtos;
	}

}
