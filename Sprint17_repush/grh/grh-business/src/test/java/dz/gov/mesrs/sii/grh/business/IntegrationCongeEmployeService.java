package dz.gov.mesrs.sii.grh.business;

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
import dz.gov.mesrs.sii.grh.business.model.dto.AnneeGrhDto;
import dz.gov.mesrs.sii.grh.business.service.conges.CongeEmployeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class IntegrationCongeEmployeService {

	@Autowired
	CongeEmployeService congeEmployeService;

	@Test
	public void testBeanSpring() {
		System.out.println("-------test bean Spring-------");
		assertNotNull(congeEmployeService);

		System.out.println("-------end test bean Spring------");
	}

	
	@Test
	public void calculateDroitConge() throws ParseException{
		System.out.println("------ test calculateDroitConge------");
		AnneeGrhDto anneeGrhDto = deduceCurrentYear();
		System.out.println(congeEmployeService.calculateDroitConge(anneeGrhDto, Long.valueOf(191), new Date()));
		System.out.println("------ end calculateDroitConge------");
	}
	
	@Test
	public void  isEmployeConges(){
		System.out.println("------ test isEmployeConges------");
		System.out.println(congeEmployeService.isEmployeConges(Long.valueOf(191), new Date(), new Date()));
		System.out.println("------ end isEmployeConges------");
		
	}
	
	
	private  AnneeGrhDto deduceCurrentYear() throws ParseException{
		
		int month= Calendar.getInstance().get(Calendar.MONTH);
		int year;
		if(month<6){
			year = Calendar.getInstance().get(Calendar.YEAR)-1;
		}else {
			year = Calendar.getInstance().get(Calendar.YEAR);
		}
		Calendar debut = UtilDate.convertToDateOnly(year+"-07-01", "GMT+1");
		Calendar fin = UtilDate.convertToDateOnly((year+1)+"-06-30","GMT+1");
		String libelle ="Juillet "+ year + " / Août " +(year+1);
		
		 return new AnneeGrhDto(year, libelle , debut.getTime(), fin.getTime());
		
	}
}
