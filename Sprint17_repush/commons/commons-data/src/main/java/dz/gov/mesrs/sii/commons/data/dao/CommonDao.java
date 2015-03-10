package dz.gov.mesrs.sii.commons.data.dao;

import java.io.Serializable;
import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.Identifiable;

/**
 * 
 * @author Salem
 * @version V1.0
 * @date 21-10-2014
 * @description Interface commune pour les DAO
 * 
 */
public interface CommonDao<CommonObject extends Identifiable<KeyType>, KeyType extends Serializable> {

	/**
	 * 
	 * @param obj
	 */
	public void refresh(CommonObject obj);

	/**
	 * for entity manager
	 */
	public void persist(CommonObject obj);

	/**
	 * 
	 * @param obj
	 * @return for entity manager
	 */
	public CommonObject merge(CommonObject obj);

	/**
	 * for entity manager
	 * 
	 * @param id
	 * @return
	 */
	public CommonObject findById(KeyType id);

	/**
	 * for entity manager
	 */
	public List<CommonObject> findAll();

	/**
	 * remove for entity manager
	 * 
	 * @param obj
	 */
	public void remove(CommonObject obj);

	List<CommonObject> findAllByExample(CommonObject example);

	List<CommonObject> findAllByExample(CommonObject example, SearchMode searchMode);

	int countAllByExample(CommonObject example);

	int countAllByExample(CommonObject example, SearchMode searchMode);

	List<CommonObject> findAllByKeyWord(String keyWord, SearchMode searchMode);

	int countAllByKeyWord(String keyWord);

	CommonObject findUniqueByExample(CommonObject example);

	CommonObject findUniqueOrNoneByExample(CommonObject example);

	CommonObject save(CommonObject object);

	int countAllByKeyWord(String keyWord, SearchMode searchMode);

}
