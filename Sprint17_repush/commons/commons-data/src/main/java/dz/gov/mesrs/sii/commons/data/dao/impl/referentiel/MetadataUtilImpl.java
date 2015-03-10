/**
 * [dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.MetadataUtilImpl.java] 
 * @author BELDI Jamel on : 13 mars 2014  14:48:16
 */
package dz.gov.mesrs.sii.commons.data.dao.impl.referentiel;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import dz.gov.mesrs.sii.commons.data.dao.referentiel.MetadataUtil;

/**
 * @author BELDI Jamel  on : 13 mars 2014  14:48:16
 */
@Repository ("metadataUtilImpl")
public class MetadataUtilImpl implements MetadataUtil {

	private static final Logger log = LoggerFactory.getLogger(MetadataUtilImpl.class.getName());

	@PersistenceContext
	private EntityManager entityManager;
	
	
	

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.MetadataUtil#findTables()
	 */
	@Override
	public List findTables() {
		log.debug("findTables");
		try {
			
			Connection connection = entityManager.unwrap(Session.class).connection();  
			try {
				DatabaseMetaData metaData = connection.getMetaData();
				String[] tablestype= {"TABLE"};
				ResultSet tables = metaData.getTables(connection.getCatalog(),"nc","%",tablestype);
				//affichage des informations
				
				while(tables!=null && tables.next()){
					log.info("###################################");
				   for(int i=0; i<tables.getMetaData().getColumnCount();i++){
				      String nomColonne = tables.getMetaData().getColumnName(i+1);
				      Object valeurColonne = tables.getObject(i+1);
				      log.info(nomColonne+" = "+valeurColonne);
				      if(nomColonne.equals("table_name")){
				    	  findColonnes(valeurColonne.toString()) ; 
				      }
				      
				   }
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
				;
			}
			log.debug("findTables successful");
			return null;
		} catch (RuntimeException re) {
			log.error("findTables failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.MetadataUtil#findColonnes(java.lang.String)
	 */
	@Override
	public List findColonnes(String table) {
		log.debug("findColonnes(String table)");
		try {
			log.info("-----------------------Colonnnes--------------------");
			Connection connection = entityManager.unwrap(Session.class).connection();  
			//on r�cup�re les m�tadonn�es � partir de la Connection
			DatabaseMetaData dmd = connection.getMetaData();
			 
			//r�cup�ration des informations
			ResultSet resultat = dmd.getColumns(connection.getCatalog(),null,table, "%");
			 
			//affichage des informations
			ResultSetMetaData rsmd = resultat.getMetaData(); 
			while(resultat.next()){
			    for(int i=0; i<rsmd.getColumnCount(); i++){
				String col = rsmd.getColumnName(i+1);
				Object val = resultat.getObject(i+1);
			
				System.out.println(col+" = "+val);
			    }
			}
			log.debug("findColonnes(String table) successful");
			return null;
		} catch (RuntimeException re) {
			log.error("findColonnes(String table) failed", re);
			throw re;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}
	}

}
