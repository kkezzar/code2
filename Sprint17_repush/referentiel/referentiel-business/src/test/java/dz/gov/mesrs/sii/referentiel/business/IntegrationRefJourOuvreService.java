package dz.gov.mesrs.sii.referentiel.business;

import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dz.gov.mesrs.sii.commons.util.UtilDate;
import dz.gov.mesrs.sii.referentiel.business.service.RefJourOuvreService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class IntegrationRefJourOuvreService {

	@Autowired
	RefJourOuvreService refJourOuvreService;

	@Test
	public void testBeanSpring() {
		System.out.println("-------test bean Spring-------");
		assertNotNull(refJourOuvreService);
		System.out.println("-------end test bean Spring------");
	}
	

	
	@Test
	public void  isJourOuvre() throws ParseException {
		System.out.println("-------test isJourOuvre-------");
		System.out.println(refJourOuvreService.isJourOuvre(UtilDate.convertToDateOnly("2014-07-05", "GMT+1").getTime()));
		System.out.println("-------end isJourOuvre------");
	}
	
	@Test
	public void  calculateNbJourOuvreBetweenTwoDate() throws ParseException {
		System.out.println("-------test calculateNbJourOuvreBetweenTwoDate-------");
		System.out.println(refJourOuvreService.calculateNbJourOuvreBetweenTwoDate(UtilDate.convertToDateOnly("2014-07-05", "GMT+1").getTime(), new Date()));

		System.out.println("-------end calculateNbJourOuvreBetweenTwoDate------");
	}
	
}
