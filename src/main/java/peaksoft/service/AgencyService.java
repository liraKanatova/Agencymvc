package peaksoft.service;

import peaksoft.entity.Agency;

import java.util.List;

public interface AgencyService {
    void saveAgency(Agency agency);
    Agency getAgencyById(Long id);
    List<Agency> getAllAgencies();
    void updateAgency(Long id,Agency newAgency);
    void deleteAgencyById(Long id);
    List<Agency> getAllSearch(String word);
}
