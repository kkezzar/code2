package dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.parcours;

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

/**
 * Classe de type Backingbean pour la gestion des repartition dans un parcours
 * 
 * @author kheireddine omrani
 * 
 */

@ManagedBean(name = "parcoursEditBB")
@RequestScoped
public class ParcoursEditBB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1950368111085835320L;

	/**
	 * 
	 */
	public ParcoursEditBB() {

	}

	@PostConstruct
	public void init() {

		if (parcoursId != null && !parcoursId.isEmpty()
				&& !parcoursId.equals("0"))
			parcours = parcoursTypeService
					.findById(Integer.valueOf(parcoursId));

		if (parcours != null)
			repartitionList = repartitionUeService.find(null,
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

	/****************************************************************************/
	/******************************** Services **********************************/
	/****************************************************************************/

	/**
	 * R�f�rence vers le service de gestion des parcours types
	 */
	@ManagedProperty(value = "#{parcoursTypeService}")
	private ParcoursTypeService parcoursTypeService;

	/**
	 * R�f�rence vers le service de gestion des unit�s d'enseignement
	 */
	@ManagedProperty(value = "#{uniteEnseignementService}")
	private UniteEnseignementService uniteEnseignementService;

	/**
	 * R�f�rence vers le service de gestion des r�partitions.
	 */
	@ManagedProperty(value = "#{repartitionUeService}")
	private RepartitionUeService repartitionUeService;

	/****************************************************************************/
	/*********************** propri�t�s & composants ****************************/
	/****************************************************************************/

	private ParcoursTypeDto parcours;

	@ManagedProperty("#{param.parcoursId}")
	private String parcoursId;

	@ManagedProperty("#{param.refCodeSemestre}")
	private String refCodeSemestre;

	private boolean cycleL = true;
	private boolean cycleM;
	private boolean cycleD;

	private String S1 = "S1";
	private String S2 = "S2";
	private String S3 = "S3";
	private String S4 = "S4";
	private String S5 = "S5";
	private String S6 = "S6";

	private String S7 = "S7";
	private String S8 = "S8";
	private String S9 = "S9";
	private String S10 = "S10";
	private String S11 = "S11";
	private String S12 = "S12";
	private String S13 = "S15";
	private String S14 = "S14";
	private String S15 = "S15";
	private String S16 = "S16";

	/****************************************************************************/
	/********************************* getter/setter ***************************/
	/****************************************************************************/

	// ****************** getter/setter des services ***********************/

	public ParcoursTypeService getParcoursTypeService() {
		return parcoursTypeService;
	}

	public void setParcoursTypeService(ParcoursTypeService parcoursTypeService) {
		this.parcoursTypeService = parcoursTypeService;
	}

	public UniteEnseignementService getUniteEnseignementService() {
		return uniteEnseignementService;
	}

	public void setUniteEnseignementService(
			UniteEnseignementService uniteEnseignementService) {
		this.uniteEnseignementService = uniteEnseignementService;
	}

	public RepartitionUeService getRepartitionUeService() {
		return repartitionUeService;
	}

	public void setRepartitionUeService(
			RepartitionUeService repartitionUeService) {
		this.repartitionUeService = repartitionUeService;
	}

	// ************ getter/setter des propri�t� & composants **************/

	public ParcoursTypeDto getParcours() {
		return parcours;
	}

	public String getParcoursId() {
		return parcoursId;
	}

	public void setParcoursId(String parcoursId) {
		this.parcoursId = parcoursId;
	}

	public String getRefCodeSemestre() {
		return refCodeSemestre;
	}

	public void setRefCodeSemestre(String refCodeSemestre) {
		this.refCodeSemestre = refCodeSemestre;
	}

	public boolean isCycleL() {
		return cycleL;
	}

	public boolean isCycleM() {
		return cycleM;
	}

	public boolean isCycleD() {
		return cycleD;
	}

	public String getS1() {
		return S1;
	}

	public String getS2() {
		return S2;
	}

	public String getS3() {
		return S3;
	}

	public String getS4() {
		return S4;
	}

	public String getS5() {
		return S5;
	}

	public String getS6() {
		return S6;
	}

	public String getS7() {
		return S7;
	}

	public String getS8() {
		return S8;
	}

	public String getS9() {
		return S9;
	}

	public String getS10() {
		return S10;
	}

	public String getS11() {
		return S11;
	}

	public String getS12() {
		return S12;
	}

	public String getS13() {
		return S13;
	}

	public String getS14() {
		return S14;
	}

	public String getS15() {
		return S15;
	}

	public String getS16() {
		return S16;
	}

	/****************************************************************************/
	/************************** Persistance & navigation ************************/
	/****************************************************************************/

	public void AddNewRepartition() {

		// Passage des paramètres
		Map<String, List<String>> _params = new HashMap<String, List<String>>();

		List<String> _semestreParamsValues = new ArrayList<String>();
		_semestreParamsValues.add(refCodeSemestre);
		_params.put("refCodeSemestre", _semestreParamsValues);

		List<String> _parcoursParamsValues = new ArrayList<String>();
		_parcoursParamsValues.add(parcoursId);
		_params.put("parcoursId", _parcoursParamsValues);

		// options d'affichage de la dialogue box
		Map<String, Object> _options = new HashMap<String, Object>();
		_options.put("modal", true);
		_options.put("draggable", false);
		_options.put("resizable", false);
		_options.put("contentWidth", 700);
		_options.put("contentHeight", 320);

		// ouverture de la boite de dialogue.
		RequestContext.getCurrentInstance().openDialog("ueSelectPopup",
				_options, _params);
	}

	public void addUeDialogReturn(SelectEvent event) {

		Object o = event.getObject(); 
		if (o == null) // si il n'y avais aucune UE selectionn�e. ne rien faire
			return;
		
		String _slectedUeId = (String) o;

		RepartitionUeDto _repartition = new RepartitionUeDto();
		_repartition.setParcoursTypeId(parcours.getId());
		_repartition.setRefCodeSemestre(refCodeSemestre);
		_repartition.setUniteEnseignementId(Integer.parseInt(_slectedUeId));

		repartitionUeService.insertOrUpdate(_repartition);
	}

	public void removeUe(int ueId) {
		repartitionUeService.remove(parcours.getId(), refCodeSemestre,
				ueId);
	}

}
