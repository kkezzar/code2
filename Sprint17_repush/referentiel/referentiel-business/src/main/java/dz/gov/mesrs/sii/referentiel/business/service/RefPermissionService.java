/**
 * [dz.gov.mesrs.sii.referentiel.business.service.RefPermissionService.java] 
 * @author MAKERRI Sofiane on : 3 mars 2014  10:40:18
 */
package dz.gov.mesrs.sii.referentiel.business.service;

import java.util.Date;
import java.util.List;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefAffectationDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefPermissionDto;

/**
 * @author MAKERRI Sofiane  on : 3 mars 2014  10:40:18
 */
public interface RefPermissionService {
	
	public List<RefPermissionDto> findAll();
	
	public List<RefPermissionDto> findGeneric(String value);
	
	public void save(RefPermissionDto refPermissionDto);
	
	public void update(RefPermissionDto refPermissionDto);
	
	public  RefPermissionDto findById(Integer id);
	
	public void  delete(RefPermissionDto refPermissionDto);
	
	public void saveOrUpdate(RefPermissionDto refPermissionDto);
	
	public  RefPermissionDto findByName(String ncName) ;
	
	public List<RefPermissionDto> findByIdfIndividu(String identifiant);
	
	public List<RefPermissionDto> findByIdfIndividu(int _idRole, String identifiant);
	
	public List<RefPermissionDto> findByIdModule(int idRole, int id);
	
	public List<RefPermissionDto> findByIdDomaine(int _idRole, int id);
	
	public List<RefPermissionDto> findByIdfGroupe(String idf);
	
	public List<RefPermissionDto> findByIdfStructure(String idf);
	
	public  RefPermissionDto findByIdFonction(int id);
	
	public  RefPermissionDto findByIdFonction(int idRole, int id);
	
	public  List<RefPermissionDto> findByIdAction(int idRole, int id);
	
	public  List<RefPermissionDto> findActions(int idRole, int id);
	
	public  RefPermissionDto findByIdFonction(String idfIndividu, int id);
	
	public List<RefPermissionDto> findByIdRole(String domaine, int idf);
	
	public List<RefIndividuDto> findModele(String identifiant, int idfAffectation); //identifiant : individu
	
	public RefAffectationDto findAffectationModele(String identifiant, int idfAffectation);
	
	public boolean verifyPeriode(Date startDate, Date endDate, boolean periodique);
	
}
