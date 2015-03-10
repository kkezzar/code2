package dz.gov.mesrs.sii.fve.business.service.impl.offreformation;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dz.gov.mesrs.sii.fve.business.service.offreformation.AnneeAcademiqueService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class IntegrationAnneeAcademiqueServiceImpl {

	@Autowired
	AnneeAcademiqueService anneeAcademiqueService;

	@Test
	public void testBeanSpring() {
		System.out.println("-------test bean Spring-------");
		assertNotNull(anneeAcademiqueService);

		System.out.println("-------end test bean Spring------");
	}

	
	@Test
	public void getCurrentAnneeAcademique(){
		System.out.println("------ test getCurrentAnneeAcademique------");
		System.out.println(anneeAcademiqueService.findCurrentAnneeAcademique().getCode());
		System.out.println("------ end getCurrentAnneeAcademique------");
	}
	
	
}
