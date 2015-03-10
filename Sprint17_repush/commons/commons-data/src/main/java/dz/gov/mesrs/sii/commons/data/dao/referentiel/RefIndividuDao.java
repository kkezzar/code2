package dz.gov.mesrs.sii.commons.data.dao.referentiel;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.referentiel.RefIndividu;

public interface RefIndividuDao {

    public abstract void persist(RefIndividu transientInstance);

    public abstract void remove(RefIndividu persistentInstance);

    public abstract RefIndividu merge(RefIndividu detachedInstance);

    public abstract RefIndividu findByIdentifiant(String id);

    public abstract RefIndividu findById(Integer id);

    public abstract List<RefIndividu> findAll();

    public abstract List<RefIndividu> findGeneric(String value);

    public abstract List<RefIndividu> findGeneric(String value, int max);

    public abstract List<RefIndividu> findAdvanced(RefIndividu refIndividu);

    public abstract List<RefIndividu> findAdvanced(RefIndividu refIndividu, int max);

    public abstract RefIndividu findByRefIndividu(RefIndividu refIndividu);

    public abstract List<RefIndividu> findByNames(String name, String surname);

    public abstract List<RefIndividu> findByNames(String name, String surname, int max);

    boolean checkIfExist(RefIndividu refIndividu);

    int countByExample(RefIndividu example);

    List<RefIndividu> findByExample(RefIndividu example, String sortField ,Integer pageSize, Integer first, Boolean descending);
    
    List<RefIndividu> findByNameOrSurname(String query);
}