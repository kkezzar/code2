package dz.gov.mesrs.sii.gfc.web.util;

import java.util.List;

import dz.gov.mesrs.sii.gfc.business.model.dto.DetailsProjetBudgetDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.SectionDto;

public class DetailBudgetPojo{
	
	private SectionDto section;
	private List<DetailsProjetBudgetDto> listDetails;
	private double montantFinalTotal;
	private double montantbesoinsTotal;
	
	public DetailBudgetPojo(){
	
	}
	/**
	 * @return the section
	 */
	public SectionDto getSection() {
		return section;
	}
	/**
	 * @param section the section to set
	 */
	public void setSection(SectionDto section) {
		this.section = section;
	}
	/**
	 * @return the listDetails
	 */
	public List<DetailsProjetBudgetDto> getListDetails() {
		return listDetails;
	}
	/**
	 * @param listDetails the listDetails to set
	 */
	public void setListDetails(List<DetailsProjetBudgetDto> listDetails) {
		this.listDetails = listDetails;
	}
	/**
	 * @return the montantFinalTotal
	 */
	public double getMontantFinalTotal() {
		return montantFinalTotal;
	}
	/**
	 * @param montantFinalTotal the montantFinalTotal to set
	 */
	public void setMontantFinalTotal(double montantFinalTotal) {
		this.montantFinalTotal = montantFinalTotal;
	}
	/**
	 * @return the montantbesoinsTotal
	 */
	public double getMontantbesoinsTotal() {
		return montantbesoinsTotal;
	}
	/**
	 * @param montantbesoinsTotal the montantbesoinsTotal to set
	 */
	public void setMontantbesoinsTotal(double montantbesoinsTotal) {
		this.montantbesoinsTotal = montantbesoinsTotal;
	}
	
	
	
	
}