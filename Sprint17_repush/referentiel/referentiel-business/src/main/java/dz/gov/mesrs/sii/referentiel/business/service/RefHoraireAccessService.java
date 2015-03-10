package dz.gov.mesrs.sii.referentiel.business.service;

import java.util.Date;
import java.util.List;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefHoraireAccessDto;

/**
 * @author BELBRIK Oualid  on : 24 f�vr. 2014  12:43:40
 */
public interface RefHoraireAccessService {
	public List<RefHoraireAccessDto> findAll();
	public List<RefHoraireAccessDto> findGeneric(String value);
	public void save(RefHoraireAccessDto refHoraireAccessDto);
	public void update(RefHoraireAccessDto refHoraireAccessDto);
	public  RefHoraireAccessDto findById(Integer id);
	public void  delete(RefHoraireAccessDto refHoraireAccessDto);
	public void saveOrUpdate(RefHoraireAccessDto refHoraireAccessDto);
	public boolean verifyHoraireAcces(Date heureAcces,Date heureDebut, Date heureFin);
	public String  generateIdentify(String prefix, int orderLength);


}
