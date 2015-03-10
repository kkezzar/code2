package dz.gov.mesrs.sii.grh.business;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dz.gov.mesrs.sii.grh.business.model.dto.GroupeDto;
import dz.gov.mesrs.sii.grh.business.service.referentiel.GroupeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class IntegrationGroupeService {

	@Autowired
	private GroupeService groupeService;

	@Test
	public void findAll() {
		List<GroupeDto> dtos = groupeService.findAll();
		System.out.println(dtos.toString());
	}

}
