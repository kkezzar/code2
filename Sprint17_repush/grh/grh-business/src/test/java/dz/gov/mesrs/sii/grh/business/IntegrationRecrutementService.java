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
import dz.gov.mesrs.sii.grh.business.service.conges.PrevisionCongeService;
import dz.gov.mesrs.sii.grh.business.service.dossieremploye.DossierEmployeService;
import dz.gov.mesrs.sii.grh.business.service.recrutement.BesoinRecrutementService;
import dz.gov.mesrs.sii.grh.business.service.recrutement.CandidatEmployeService;
import dz.gov.mesrs.sii.grh.business.service.recrutement.DetailRecrutementService;
import dz.gov.mesrs.sii.grh.business.service.recrutement.StageService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class IntegrationRecrutementService {
	@Autowired
	BesoinRecrutementService besoinRecrutementService;
	@Autowired
	DetailRecrutementService detailRecrutementService;
	@Autowired
	CandidatEmployeService candidatEmployeService;
	@Autowired
	StageService stageService;
	
	

	@Test
	public void testBeanSpring() {
		System.out.println("-------test bean Spring-------");
		assertNotNull(besoinRecrutementService);
		assertNotNull(detailRecrutementService);
		assertNotNull(candidatEmployeService);
		assertNotNull(stageService);
		
		System.out.println("-------end test bean Spring------");
	}

	

}
