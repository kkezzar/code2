package dz.gov.mesrs.sii.fve.ws.service.impl.offreformation;

import java.util.List;

import dz.gov.mesrs.sii.fve.ws.bo.offreformation.UniteEnseignement;
import dz.gov.mesrs.sii.fve.ws.service.offreformation.UniteEnseignementWS;

public class UniteEnseignementWSImpl implements UniteEnseignementWS  {

	@Override
	public UniteEnseignement findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UniteEnseignement> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	/*@Autowired
	private UniteEnseignementService ueService;
	
	public UniteEnseignementService getUeService() {
		return ueService;
	}

	public void setUeService(UniteEnseignementService ueService) {
		this.ueService = ueService;
	}

    @Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;
	
	public DozerBeanMapper getMapper() {
		return mapper;
	}

	public void setMapper(DozerBeanMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public UniteEnseignement findByCode(String code) {

		System.out.print("ok за marche pour ton premier test");

		return null;
	}

	@Override
	public List<UniteEnseignement> findAll() {
		
		List<UniteEnseignementDto> list = ueService.findAll();
		
		List<UniteEnseignement> ueResultList = new ArrayList<UniteEnseignement>();

		for (UniteEnseignementDto ueDto : list) {
			ueResultList.add(mapper.map(ueDto, UniteEnseignement.class));
		}

		return ueResultList;		
		
	}*/


}



