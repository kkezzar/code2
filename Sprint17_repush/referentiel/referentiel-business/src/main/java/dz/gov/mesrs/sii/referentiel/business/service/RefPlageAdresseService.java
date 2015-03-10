package dz.gov.mesrs.sii.referentiel.business.service;

import java.util.List;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefPlageAdresseDto;

 

/**
 * @author BELBRIK Oualid  on : 24 f�vr. 2014  10:16:29
 */
public interface RefPlageAdresseService {

	public List<RefPlageAdresseDto> findAll();
	public List<RefPlageAdresseDto> findGeneric(String value);
	public RefPlageAdresseDto save(RefPlageAdresseDto refPlageAdresseDto);
	public void update(RefPlageAdresseDto refPlageAdresseDto);
	public  RefPlageAdresseDto findById(Integer id);
	public void  delete(RefPlageAdresseDto refPlageAdresseDto);
	public void saveOrUpdate(RefPlageAdresseDto refPlageAdresseDto);
	public boolean verifyUserIp(String userAddr, String adresseDebut, String adresseFin);
	public List<RefPlageAdresseDto> findAll(Integer etabId);
	public List<RefPlageAdresseDto> findGeneric(Integer etabId, String value);
	public String  generateIdentify(String prefix, int orderLength);

}
