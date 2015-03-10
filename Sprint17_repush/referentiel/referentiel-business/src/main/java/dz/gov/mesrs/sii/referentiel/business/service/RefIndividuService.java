/**
 * 
 */
package dz.gov.mesrs.sii.referentiel.business.service;

import java.util.List;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;

/**
 * @author obelbrik
 * 
 */
public interface RefIndividuService {

    public List<RefIndividuDto> findAll();

    public List<RefIndividuDto> findGeneric(String value);

    public List<RefIndividuDto> findGeneric(String value, int max);

    public List<RefIndividuDto> findAdvanced(RefIndividuDto refIndividuDto);

    public List<RefIndividuDto> findAdvanced(RefIndividuDto refIndividuDto, int max);

    public RefIndividuDto save(RefIndividuDto refIndividuDto);

    public void update(RefIndividuDto refIndividuDto);

    public void saveOrUpdate(RefIndividuDto refIndividuDto);

    public RefIndividuDto findByIdentifiant(String id);

    public RefIndividuDto findById(Integer id);

    public RefIndividuDto findByRefIndividuDto(RefIndividuDto refIndividuDto);

    public void delete(String id);

    public List<RefIndividuDto> findByNames(String name, String surname);

    public List<RefIndividuDto> findByNames(String name, String surname, int max);

    public boolean checkIfExist(RefIndividuDto refIndividuDto);

    public List<RefIndividuDto> saveIndividus(List<RefIndividuDto> refIndividuDtos);

    List<RefIndividuDto> findByExample(RefIndividuDto dto, String sortField ,Integer pageSize, Integer first, Boolean descending);

    int countByExample(RefIndividuDto dto);
    
    public List<RefIndividuDto> findByNameOrSurname(String query);
}
