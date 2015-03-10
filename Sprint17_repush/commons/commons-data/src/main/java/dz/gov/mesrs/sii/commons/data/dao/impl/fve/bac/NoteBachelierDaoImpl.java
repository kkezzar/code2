package dz.gov.mesrs.sii.commons.data.dao.impl.fve.bac;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.fve.bac.NoteBachelierDao;
import dz.gov.mesrs.sii.commons.data.model.fve.bac.NotesBachelier;

/**
 * Dao object for domain model class NoteAccessFiliere;
 *
 * @see dz.gov.mesrs.sii.fve.business.model.bo.cursus.NoteAccessFiliere;
 * @author MESRS CCM
 * @version 1.0
 * @created 20-04-2014 16:44:07
 */
@Repository("noteBachelierDao")
@Transactional
public class NoteBachelierDaoImpl implements NoteBachelierDao {

	private static final Logger log = LoggerFactory.getLogger(NoteBachelierDaoImpl.class.getName());

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    /**
     * @see
     * dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.NoteAccessFiliereDao;#persist(dz.gov.mesrs.sii.lmd.business.model.bo.cursus.NoteAccessFiliere)
     *
     * @param transientInstance
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void persist(NotesBachelier transientInstance) {
        log.debug("persisting NotesBachelier instance");
        try {
            entityManager.persist(transientInstance);
            /*entityManager.flush();
             entityManager.refresh(transientInstance);*/
            log.debug("persist successful");
        } catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }

    /**
     * @see
     * dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.NoteAccessFiliereDao#remove(dz.gov.mesrs.sii.lmd.business.model.bo.cursus.NoteAccessFiliere)
     * @param persistentInstance
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void remove(NotesBachelier persistentInstance) {
        log.debug("removing NotesBachelier instance");
        try {
            entityManager.remove(persistentInstance);
            log.debug("remove successful");
        } catch (RuntimeException re) {
            log.error("remove failed", re);
            throw re;
        }
    }

    /**
     * @see
     * dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.NoteAccessFiliereDao#merge(dz.gov.mesrs.sii.fve.business.model.bo.cursus.NoteAccessFiliere)
     * @param detachedInstance
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public NotesBachelier merge(NotesBachelier detachedInstance) {
        log.debug("merging NotesBachelier instance");
        try {
            log.debug("merge successful");
            return entityManager.merge(detachedInstance);
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    /**
     * @see
     * dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.NoteAccessFiliereDao#findById(int)
     * @param id
     */
    @Override
    @Transactional(readOnly = true)
    public NotesBachelier findById(int id) {
        log.debug("getting NotesBachelier instance with id: " + id);
        try {
            log.debug("get successful");
            return entityManager.find(NotesBachelier.class, id);
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public List<NotesBachelier> findAll() {
        log.debug("getting all NoteAccessFiliere instances");
        try {
            Query query = entityManager.createQuery("from NotesBachelier notesBachelier");
            log.debug("findAll 'NotesBachelier' successful");
            return query.getResultList();
        } catch (RuntimeException re) {
            log.error("findAll 'NoteAccessFiliere' failed", re);
            return new ArrayList<NotesBachelier>();
        }
    }

    @Override
    public List<NotesBachelier> findByIdDossierBachelier(String matriculeBachelier) {
        log.debug("getting all NoteAccessFiliere instances");
        try {
            //String sqlQuery = "FROM NotesBachelier a WHERE a.dossierBachelier.id = :matriculeBachelier ";

        	//String sqlQuery = "FROM NotesBachelier a WHERE a.dossierBachelier.id = :idDossierBachelier AND a.dossierBachelier.matricule = :matriculeBachelier";
        	
        	String sqlQuery = "SELECT a FROM NotesBachelier a INNER JOIN a.dossierBachelier d where d.matricule = :matriculeBachelier ";
        	
            TypedQuery<NotesBachelier> query = entityManager.createQuery(
                    sqlQuery, NotesBachelier.class);
            query.setParameter("matriculeBachelier", matriculeBachelier);
            //query.setParameter("idDossierBachelier", 1);
            log.debug("findAll 'NotesBachelier' successful");
            return query.getResultList();
            
        } catch (RuntimeException re) {
            log.error("findAll 'NotesBachelier' failed", re);
            return new ArrayList<NotesBachelier>();
        }

    }

    @Override
    public List<NotesBachelier> findByIdDossierBachelierRefCodeMatiere(String matriculeBachelier, String refCodeMatiere) {
               log.debug("getting all NoteAccessFiliere instances");
        try {
            String sqlQuery = "SELECT a FROM  from NotesBachelier a WHERE a.dossierBachelier.matricule=:matriculeBachelier and a.refCodeMatiere =:refCodeMatiere";

            TypedQuery<NotesBachelier> query = entityManager.createQuery(
                    sqlQuery, NotesBachelier.class);
            query.setParameter("matriculeBachelier", matriculeBachelier);
            query.setParameter("refCodeMatiere", refCodeMatiere);
            log.debug("findAll 'NotesBachelier' successful");
            return query.getResultList();
        } catch (RuntimeException re) {
            log.error("findAll 'NoteAccessFiliere' failed", re);
            return new ArrayList<NotesBachelier>();
        }
    }
}
