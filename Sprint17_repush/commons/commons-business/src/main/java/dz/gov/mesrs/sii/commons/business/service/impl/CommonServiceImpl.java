package dz.gov.mesrs.sii.commons.business.service.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import dz.gov.mesrs.sii.commons.business.service.CommonService;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.data.model.Identifiable;

/**
 * 
 * @author Salem
 * @version V1.0
 * @date 12-04-2011
 * @description Classe d'impl�mentation de l'interface commune pour les
 *              Services
 * 
 */
@Service("commonService")
public abstract class CommonServiceImpl<CommonObject extends Identifiable<KeyType>, DtoComm, KeyType extends Serializable>
		implements CommonService<CommonObject, DtoComm, KeyType> {

	protected final Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	public Mapper mapper;

	protected CommonDao<CommonObject, KeyType> commonDao = getDao();

	protected abstract CommonDao<CommonObject, KeyType> getDao();

	@Override
	@Transactional(readOnly = true)
	public DtoComm findById(KeyType id) {

		CommonObject obj = getDao().findById(id);
		Class dtoClass = getDtoCommClass();
		if (obj != null)
			return (DtoComm) mapper.map(obj, dtoClass);
		return null;
	}

	@Override
	public void persist(DtoComm dto) {

		if (dto != null) {
			try {

				CommonObject commonObj = getCmmonObjectClass().newInstance();
				mapper.map(dto, commonObj);

				getDao().persist(commonObj);
			} catch (InstantiationException e) {

				e.printStackTrace();
			} catch (IllegalAccessException e) {

				e.printStackTrace();
			}

		}
	}

	@Override
	public DtoComm merge(DtoComm dto) {
		this.logger.debug("--- edit In class CommonService ---");

		if (dto != null) {
			try {

				CommonObject commonObj = getCmmonObjectClass().newInstance();
				mapper.map(dto, commonObj);

				getDao().merge(commonObj);
			} catch (InstantiationException e) {

				e.printStackTrace();
			} catch (IllegalAccessException e) {

				e.printStackTrace();
			}

		}

		return dto;

	}

	@Override
	@Transactional(readOnly = true)
	public List<DtoComm> findAll() {
		List<CommonObject> resultObjects = getDao().findAll();
		return mapReturn(resultObjects);
	}

	// TODO journalisation !!!
	@Override
	@Transactional
	public void remove(DtoComm dto) {
		Assert.notNull(dto, "can't delete a null dto");
		CommonObject object = mapper.map(dto, getCmmonObjectClass());
		object = getDao().findById(object.getIdenfiant());
		getDao().remove(object);

	}

	@Override
	@Transactional(readOnly = true)
	public List<DtoComm> findAllByExample(DtoComm example) {
		CommonObject exampleObject = mapper.map(example, getCmmonObjectClass());
		List<CommonObject> resultObjects = getDao().findAllByExample(exampleObject);
		return mapReturn(resultObjects);
	}

	@Override
	@Transactional(readOnly = true)
	public List<DtoComm> findAllByExample(DtoComm example, SearchMode searchMode) {
		CommonObject exampleObject = mapper.map(example, getCmmonObjectClass());
		List<CommonObject> resultObjects = getDao().findAllByExample(exampleObject, searchMode);
		return mapReturn(resultObjects);
	}

	@Override
	@Transactional(readOnly = true)
	public int countAllByExample(DtoComm example) {
		CommonObject exampleObject = mapper.map(example, getCmmonObjectClass());
		return getDao().countAllByExample(exampleObject);
	}

	@Override
	@Transactional(readOnly = true)
	public DtoComm findUniqueByExample(DtoComm example) {
		CommonObject exampleObject = mapper.map(example, getCmmonObjectClass());
		CommonObject resultObject = getDao().findUniqueByExample(exampleObject);
		return mapper.map(resultObject, getDtoCommClass());
	}

	@Override
	@Transactional(readOnly = true)
	public DtoComm findUniqueOrNoneByExample(DtoComm example) {
		CommonObject exampleObject = mapper.map(example, getCmmonObjectClass());
		CommonObject resultObject = getDao().findUniqueOrNoneByExample(exampleObject);
		if (resultObject != null)
			return mapper.map(resultObject, getDtoCommClass());
		else
			return null;
	}

	@Transactional(readOnly = true)
	@Override
	public int countByKeyWord(String keyWord) {
		return getDao().countAllByKeyWord(keyWord);
	}

	@Transactional(readOnly = true)
	@Override
	public List<DtoComm> findAllByKeyWord(String keyWord, SearchMode searchMode) {
		List<CommonObject> objects = getDao().findAllByKeyWord(keyWord, searchMode);
		return mapReturn(objects);
	}

	@Transactional
	@Override
	public DtoComm save(DtoComm dto) {
		Assert.notNull(dto, "can't persist a null dto");
		CommonObject object = mapper.map(dto, getCmmonObjectClass());
		object = getDao().save(object);
		return mapper.map(object, getDtoCommClass());
	}

	@Transactional
	@Override
	public void saveAll(List<DtoComm> dtosToSave, List<DtoComm> dtosToDelete) {
		if (dtosToDelete != null) {
			for (DtoComm dtoComm : dtosToDelete) {
				Assert.notNull(dtoComm, "can't delete a null dto");
				CommonObject object = mapper.map(dtoComm, getCmmonObjectClass());
				object = getDao().findById(object.getIdenfiant());
				getDao().remove(object);
			}
		}
		if (dtosToSave != null) {
			for (DtoComm dtoComm : dtosToSave) {
				Assert.notNull(dtoComm, "can't persist a null dto");
				CommonObject object = mapper.map(dtoComm, getCmmonObjectClass());
				object = getDao().save(object);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private Class<CommonObject> getCmmonObjectClass() {
		return (Class<CommonObject>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@SuppressWarnings("unchecked")
	private Class<DtoComm> getDtoCommClass() {
		return (Class<DtoComm>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
	}

	@SuppressWarnings("unchecked")
	private Class<KeyType> getKeyTypeClass() {
		return (Class<KeyType>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[2];
	}

	public void setMapper(Mapper mapper) {
		this.mapper = mapper;
	}

	// @Transactional
	// @Override
	// public List<DtoComm> findAllByExample(DtoComm example, Collection
	// collection, String propretyName, short operator) {
	// CommonObject exampleObject = mapper.map(example, getCmmonObjectClass());
	// List<CommonObject> resultObjects =
	// getDao().findAllByExample(exampleObject, collection, propretyName,
	// operator);
	// return mapReturn(resultObjects);
	// }
	//
	// @Transactional
	// @Override
	// public List<DtoComm> findAllByExample(DtoComm example, SearchMode
	// searchMode, Collection collection,
	// String propretyName, short operator) {
	// CommonObject exampleObject = mapper.map(example, getCmmonObjectClass());
	// List<CommonObject> resultObjects =
	// getDao().findAllByExample(exampleObject, searchMode, collection,
	// propretyName, operator);
	// return mapReturn(resultObjects);
	// }

	@Transactional(readOnly = true)
	@Override
	public int countByKeyWord(String keyWord, SearchMode searchMode) {
		return getDao().countAllByKeyWord(keyWord, searchMode);
	}

	@Override
	@Transactional(readOnly = true)
	public int countAllByExample(DtoComm example, SearchMode searchMode) {
		CommonObject exampleObject = mapper.map(example, getCmmonObjectClass());
		return getDao().countAllByExample(exampleObject, searchMode);
	}

	private List<DtoComm> mapReturn(List<CommonObject> resultObjects) {
		List<DtoComm> dtos = null;
		if (resultObjects != null && resultObjects.size() > 0) {
			dtos = new ArrayList<>();
			for (CommonObject object : resultObjects) {
				dtos.add(mapper.map(object, getDtoCommClass()));
			}
		}
		return dtos;
	}
}
