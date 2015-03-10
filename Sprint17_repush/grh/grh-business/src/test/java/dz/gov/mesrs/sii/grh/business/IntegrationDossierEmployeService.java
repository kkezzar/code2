package dz.gov.mesrs.sii.grh.business;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.SortField;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.SortOrder;
import dz.gov.mesrs.sii.grh.business.model.dto.DossierEmployeDto;
import dz.gov.mesrs.sii.grh.business.service.dossieremploye.DossierEmployeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class IntegrationDossierEmployeService {

	@Autowired
	DossierEmployeService dossierEmployeService;

	@Test
	public void testBeanSpring() {
		System.out.println("-------test bean Spring-------");
		assertNotNull(dossierEmployeService);

		System.out.println("-------end test bean Spring------");
	}

	@Test
	public void findByExampleEmploye() {
		System.out.println("------ test findByExampleEmploye------");
		System.out.println("------dossierEmployeService.findAllByExample(dto)------------------");
		DossierEmployeDto dto = new DossierEmployeDto();
		dto.setMatricule("1223265412");

		List<DossierEmployeDto> dossiers = dossierEmployeService.findAllByExample(dto);
		for (DossierEmployeDto result : dossiers) {
			System.out.println(result.toString());
		}
		// System.out.println("------dossierEmployeService.findById(1)------------------");
		// dto = dossierEmployeService.findById(1);
		// System.out.println(dto.toString());

		System.out.println("------dossierEmployeService.countAllByExample(dto)------------------");
		System.out.println("count" + dossierEmployeService.countAllByExample(new DossierEmployeDto()));
		SearchMode searchMode = new SearchMode(5, 1);
		System.out
				.println("------dossierEmployeService.findAllByExample(new DossierEmployeDto(), searchMode)------------------");
		System.out.println("firstPage"
				+ dossierEmployeService.findAllByExample(new DossierEmployeDto(), searchMode).toString());
		searchMode = new SearchMode(10, 0);
		SortField sortField = new SearchMode.SortField("matricule", SortOrder.ASC);
		searchMode.addSortField(sortField);
		System.out.println("sort"
				+ dossierEmployeService.findAllByExample(new DossierEmployeDto(), searchMode).toString());

		System.out.println("------end findByExampleEmploye------");

	}

	@Test
	public void saveOrUpdateEmploye() {
		System.out.println("------ test saveOrUpdateEmploye------");
		// DossierEmployeDto dto = new DossierEmployeDto();
		// dto = dossierEmployeService.findById(1);
		// System.out.println(dto.toString());
		// dto = dossierEmployeService.save(dto);
		// System.out.println(dto.toString());
		System.out.println("------end saveOrUpdateEmploye------");

	}

}
