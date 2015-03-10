package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.dossier.parcours;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.ParcoursTypeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.RepartitionUeDto;
import dz.gov.mesrs.sii.fve.business.service.offreformation.ParcoursTypeService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.RepartitionUeService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.UniteEnseignementService;


@ManagedBean(name = "parcoursIndividualiseManagerBean")
@RequestScoped
public class ParcoursIndividualiseManagerBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1950368111085835320L;

	/**
	 * 
	 */
	public ParcoursIndividualiseManagerBean() {

	}

	@PostConstruct
	public void init() {

		if (parcoursId != null && !parcoursId.isEmpty()
				&& !parcoursId.equals("0"))
			
		if (parcours != null)
			repartitionList = repartitionUeParcoursService.find(null,
					parcours.getId(), null);

		if (repartitionList == null)
			repartitionList = new ArrayList<RepartitionUeDto>(0);
	}

	public void initParcours(String parcoursId) {

		this.parcoursId = parcoursId;

		init();
	}

	public List<RepartitionUeDto> getRepartition(String refCodeSemestre) {

		List<RepartitionUeDto> _repartitions = new ArrayList<RepartitionUeDto>();

		for (RepartitionUeDto _rep : repartitionList) {
			if (_rep.getRefCodeSemestre().equals(refCodeSemestre))
				_repartitions.add(_rep);
		}
		return _repartitions;
	}

	private List<RepartitionUeDto> repartitionList;

	@ManagedProperty(value = "#{uniteEnseignementService}")
	private UniteEnseignementService uniteEnseignementService;

	@ManagedProperty(value = "#{repartitionUeParcoursService}")
	private RepartitionUeService repartitionUeParcoursService;


	private ParcoursTypeDto parcours;

	@ManagedProperty("#{param.parcoursId}")
	private String parcoursId;

	@ManagedProperty("#{param.refCodeSemestre}")
	private String refCodeSemestre;



	public UniteEnseignementService getUniteEnseignementService() {
		return uniteEnseignementService;
	}

	public void setUniteEnseignementService(
			UniteEnseignementService uniteEnseignementService) {
		this.uniteEnseignementService = uniteEnseignementService;
	}

	public RepartitionUeService getRepartitionUeParcoursService() {
		return repartitionUeParcoursService;
	}

	public void setRepartitionUeParcoursService(
			RepartitionUeService repartitionUeParcoursService) {
		this.repartitionUeParcoursService = repartitionUeParcoursService;
	}



	

	
}
