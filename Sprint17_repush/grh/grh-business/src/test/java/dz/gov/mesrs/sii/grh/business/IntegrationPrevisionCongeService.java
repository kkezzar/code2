package dz.gov.mesrs.sii.grh.business;

import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dz.gov.mesrs.sii.commons.util.UtilDate;
import dz.gov.mesrs.sii.grh.business.model.dto.AnneeGrhDto;
import dz.gov.mesrs.sii.grh.business.service.conges.PrevisionCongeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class IntegrationPrevisionCongeService {

	@Autowired
	PrevisionCongeService previsionCongeService;

	@Test
	public void testBeanSpring() {
		System.out.println("-------test bean Spring-------");
		assertNotNull(previsionCongeService);

		System.out.println("-------end test bean Spring------");
	}
	
	@Test
	public void findPrevisionConges() throws ParseException{
		System.out.println("-------test findPrevisionConges-------");
		List list = previsionCongeService.findPrevisionConges(Long.valueOf(191), deduceCurrentYear());

		System.out.println("-------end findPrevisionConges------");
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
