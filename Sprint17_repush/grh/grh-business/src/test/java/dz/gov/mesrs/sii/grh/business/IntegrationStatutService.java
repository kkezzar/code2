package dz.gov.mesrs.sii.grh.business;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.SortField;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.SortOrder;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;
import dz.gov.mesrs.sii.grh.business.model.dto.StatutDto;
import dz.gov.mesrs.sii.grh.business.service.referentiel.CategorieProfessionnelleService;
import dz.gov.mesrs.sii.grh.business.service.referentiel.GradeService;
import dz.gov.mesrs.sii.grh.business.service.referentiel.StatutService;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class IntegrationStatutService {

	@Autowired
	StatutService statutService;
	
	@Autowired
	@Qualifier("nomenclatureServiceImpl")
	NomenclatureService nomenclatureService;
	@Autowired
	GradeService gradeService;
	@Autowired
	CategorieProfessionnelleService categorieProfessionnelleService;

//	@Test
	public void findByExampleStatus() {
		StatutDto dto = new StatutDto();
//		dto.setDecret("2000200");
		List<StatutDto> status = statutService.findAllByExample(dto);
		for (StatutDto result : status) {
			System.out.println(result.toString());
		}
		dto = statutService.findById(1);
		System.out.println(dto.toString());
		dto = new StatutDto();
		dto.setId(1);
		status = statutService.findAllByExample(dto);
		for (StatutDto result : status) {
			System.out.println(result.toString());
		}

		System.out.println("count" + statutService.countAllByExample(new StatutDto()));

		SearchMode searchMode = new SearchMode(1, 1);
		System.out.println("firstPage" + statutService.findAllByExample(new StatutDto(), searchMode).toString());
		searchMode = new SearchMode(10, 0);
		SortField sortField = new SearchMode.SortField("decret", SortOrder.ASC);
		searchMode.addSortField(sortField);
		System.out.println("sort" + statutService.findAllByExample(new StatutDto(), searchMode).toString());

	}

//	@Test
	public void findGeneric() {

		StatutDto dto =new StatutDto();
//		dto.setDecret("20002");
//		dto.setObjet("test");
		System.out.println("genric" + statutService.findAllByKeyWord("200", new SearchMode()));
	}

	// @Test(expected = InvalidDataAccessApiUsageException.class)
	public void findUniqueByExampleKO() {

	}

	// @Test
	public void findUniqueOrNoneByExampleOK() {

	}

	// @Test(expected = InvalidDataAccessApiUsageException.class)
	public void findUniqueOrNoneByExampleKO() {

	}
//	@Transactional
//	@Test
	public void getListNomencalture(){
		nomenclatureService.findByName(UtilConstant.NC_GRH_FILIERE_PRO_NAME);
		int i = 0;
	}
	
	@Test
	public void grade(){
		gradeService.findAll();
	}

}
