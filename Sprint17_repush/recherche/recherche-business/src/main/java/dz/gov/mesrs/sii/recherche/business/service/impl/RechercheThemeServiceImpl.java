/**
 * [dz.gov.mesrs.sii.recherche.business.service.impl.RechercheStructureServiceImpl.java] 
 * @author rlaib on : 14 dï¿½c. 2014  17:35:31
 */
package dz.gov.mesrs.sii.recherche.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.recherche.ThemeDao;
import dz.gov.mesrs.sii.commons.data.model.recherche.Theme;
import dz.gov.mesrs.sii.recherche.business.model.dto.ThemeDto;
import dz.gov.mesrs.sii.recherche.business.service.RechercheThemeService;

/**
 * @author rlaib  on : 14 dec. 2014  17:35:31
 */
@Service("rechercheThemeService")
public class RechercheThemeServiceImpl implements RechercheThemeService {

	@Autowired
	@Qualifier ("themeDao")
	private ThemeDao themeDao;
	
	private static final Log log = LogFactory.getLog(RechercheThemeServiceImpl.class);
	
    @Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;
	

	/**
	 * [RechercheThemeServiceImpl.insertOrUpdate] Method 
	 * Overridden By : @author rlaib  on : 22 déc. 2014  15:04:35
	 * @param themeDto
	 * @return
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public ThemeDto insertOrUpdate(ThemeDto themeDto) {
		Theme theme = mapper.map(themeDto,
				Theme.class);
		try {
					if (theme.getId() == null){
						themeDao.persist(theme);
					}else{
						if(themeDto.getGroupeId() == null) {
							theme.setGroupe(null);
						}
						theme = themeDao.merge(theme);
					}
					mapper.map(theme, themeDto);
					return themeDto;
		
		} catch (RuntimeException re) {
					log.error("insertOrUpdate ThemeDto failed : ", re);
					throw re;
		}
	}

	/**
	 * [RechercheThemeServiceImpl.remove] Method 
	 * Overridden By : @author rlaib  on : 22 déc. 2014  15:04:53
	 * @param id
	 */
	@Override
	@Transactional(value = "transactionManager", propagation =	Propagation.REQUIRED)
	public void remove(Long id) {

		themeDao.remove(id);
	}


	/**
	 * [RechercheThemeServiceImpl.findStructureRechercheThemes] Method 
	 * Overridden By : @author rlaib  on : 22 déc. 2014  15:06:20
	 * @param idStructure
	 * @return
	 */
	@Override
	public 	List<ThemeDto> findStructureRechercheThemes(Long idStructure) {
	
		try {
			List<Theme> themes = themeDao.findStructureRechercheThemes(idStructure);
			List<ThemeDto> themeDtos = new ArrayList<ThemeDto>();
			for (Theme theme: themes) {
				ThemeDto themeDto = new ThemeDto();
				mapper.map(theme, themeDto);
				themeDtos.add(themeDto);
			}
			return themeDtos;
		} catch (RuntimeException re) {
			log.error("findStructureRechercheThemes ThemeDto", re);
			throw re;
		}
	}

	/**
	 * [RechercheThemeService.findById] Method 
	 * Overridden By : @author rlaib  on : 22 déc. 2014  15:12:38
	 * @param id
	 * @return
	 */
	@Override
	public ThemeDto findById(Long id) {
		try {
			Theme theme = themeDao.findById(id);
			if (theme != null) {
					ThemeDto themeDto = new ThemeDto();
					mapper.map(theme, themeDto);
					return themeDto;
			}
			return null;

		} catch (RuntimeException re) {
			log.error("findById ThemeDto failed : ", re);
			throw re;
		}
	}

	@Override
	public List<ThemeDto> findThemesByStructureGroup(Long structureId,Long groupeId) {
		try {
			List<Theme> themes = themeDao.findThemesByStructureGroup(structureId,groupeId);
			List<ThemeDto> themeDtos = new ArrayList<ThemeDto>();
			for (Theme theme: themes) {
				ThemeDto themeDto = new ThemeDto();
				mapper.map(theme, themeDto);
				themeDtos.add(themeDto);
			}
			return themeDtos;
		} catch (RuntimeException re) {
			log.error("findStructureRechercheThemes ThemeDto", re);
			throw re;
		}
	}

	
}
