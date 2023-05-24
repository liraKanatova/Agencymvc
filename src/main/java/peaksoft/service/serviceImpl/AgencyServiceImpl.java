package peaksoft.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.entity.Agency;
import peaksoft.repository.AgencyRepository;
import peaksoft.service.AgencyService;

import java.util.List;
@Service
public class AgencyServiceImpl implements AgencyService {
    private final AgencyRepository agencyRepository;
    @Autowired
    public AgencyServiceImpl(AgencyRepository agencyRepository) {
        this.agencyRepository = agencyRepository;
    }

    @Override
    public void saveAgency(Agency agency) {
     agencyRepository.saveAgency(agency);
    }

    @Override
    public Agency getAgencyById(Long id) {
        return agencyRepository.getAgencyById(id);
    }

    @Override
    public List<Agency> getAllAgencies() {
        return agencyRepository.getAllAgencies();
    }

    @Override
    public void updateAgency(Long id, Agency newAgency) {
    agencyRepository.updateAgency(id, newAgency);
    }

    @Override
    public void deleteAgencyById(Long id) {
   agencyRepository.deleteAgencyById(id);
    }

    @Override
    public List<Agency> getAllSearch(String word) {
        return agencyRepository.getAllSearch(word) ;
    }



}
