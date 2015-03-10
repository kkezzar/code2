package dz.gov.mesrs.sii.commons.data.dao.referentiel;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.referentiel.RefGroupe;


public interface RefGroupeDao {

	public abstract void persist(RefGroupe transientInstance);

	public abstract void remove(RefGroupe persistentInstance);

	public abstract RefGroupe merge(RefGroupe detachedInstance);

	public abstract RefGroupe findById(Integer id);
	
	public abstract RefGroupe findByCode(String code);
	
	public abstract List<RefGroupe> findAll();
	
    public  List<RefGroupe> findGeneric(String value);
    
    public  List<RefGroupe> findAdvanced(RefGroupe refGroupe);
    
    public  List<RefGroupe> findGeneric(Integer etabId, String value);

    public  List<RefGroupe> findGeneric(Integer etabId, String value, Integer idTypeGroupe);
    
    public  List<RefGroupe> findAdvanced(Integer etabId, RefGroupe refGroupe);
    
    public  List<RefGroupe> findAll(Integer etabId);
    
    public Integer findLastOrder(String prefix, int orderLength);

    public abstract List<RefGroupe> findByEtablissement(Integer idEtab);

}