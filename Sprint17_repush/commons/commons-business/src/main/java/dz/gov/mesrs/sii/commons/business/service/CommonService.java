package dz.gov.mesrs.sii.commons.business.service;

import java.io.Serializable;
import java.util.List;

import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.data.model.Identifiable;

/**
 * 
 * @author Salem
 * @version V1.0
 * @date 12-04-2011
 * @description Interface commune pour les services
 * 
 */
public interface CommonService<CommonObject extends Identifiable<keyType>, DtoComm, keyType extends Serializable> {

	/***
	 * 
	 * @param id
	 * @return
	 */
	public DtoComm findById(keyType id);

	/**
	 * for entity manager
	 */
	public void persist(DtoComm dto);

	/**
	 * 
	 * @param obj
	 * @return
	 */
	public DtoComm merge(DtoComm dto);

	/**
	 * 
	 * @param dto
	 * @return List dto
	 */
	public List<DtoComm> findAll();

	/**
	 * remove for entity manager
	 * 
	 * @param obj
	 */
	public void remove(DtoComm dto);

	List<DtoComm> findAllByExample(DtoComm example);

	List<DtoComm> findAllByExample(DtoComm example, SearchMode searchMode);

	int countAllByExample(DtoComm example);

	DtoComm findUniqueByExample(DtoComm example);

	DtoComm findUniqueOrNoneByExample(DtoComm example);

	int countByKeyWord(String keyWord);

	List<DtoComm> findAllByKeyWord(String keyWord, SearchMode searchMode);

	DtoComm save(DtoComm dto);

	public void saveAll(List<DtoComm> dtosToSave, List<DtoComm> dtosToDelete);

	// List<DtoComm> findAllByExample(DtoComm example, Collection collection,
	// String propretyName, short operator);
	//
	// List<DtoComm> findAllByExample(DtoComm example, SearchMode searchMode,
	// Collection collection, String propretyName,
	// short operator);

	int countAllByExample(DtoComm example, SearchMode searchMode);

	int countByKeyWord(String keyWord, SearchMode searchMode);

}
