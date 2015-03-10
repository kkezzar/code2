/**
 * [dz.gov.mesrs.sii.referentiel.business.service.RefFonctionService.java] 
 * @author MAKERRI Sofiane on : 27 f�vr. 2014  14:02:42
 */
package dz.gov.mesrs.sii.referentiel.business.service;

import java.util.List;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefFonctionDto;

/**
 * @author MAKERRI Sofiane  on : 27 f�vr. 2014  14:02:42
 */
public interface RefFonctionService {
	
	    public  RefFonctionDto insertOrUpdate(RefFonctionDto refFonctionDto);
		
		public  void remove(RefFonctionDto refFonctionDto);	
		
		public  RefFonctionDto findById(int id);
		
		public  List<RefFonctionDto> findByQuery(String query) ;
		
		public  List<RefFonctionDto> findAll();
		
		public  List<RefFonctionDto> findGeneric(String value);
		
		public  List<RefFonctionDto> findActionGeneric(String value);
		
		public RefFonctionDto findByIdentifiant(String identifiant);
		
		public  RefFonctionDto findByName(Integer id, String name);
		
		public  RefFonctionDto findByNameArabe(Integer id, String name);
		
	    public void save(RefFonctionDto refFonctionDto);
		
		public void update(RefFonctionDto refFonctionDto);
		
		public int findFonctionLastRang(int id);
		
		public int findActionLastRang(int id);
		
		public List<RefFonctionDto> findActions(int id);
		
		public  List<RefFonctionDto>findFonctions(int id); // module id
		
		public  List<RefFonctionDto>findFonctionsOfDomaine(int idDomaine);
		
		public  List<RefFonctionDto>findAllFonctions(String domaine);
		
		public  List<RefFonctionDto>findAllActions();
		
		public  RefFonctionDto findPeriode(int idPeriode);
		
		public String  generateIdentify(String prefix, int orderLength);
		

}
