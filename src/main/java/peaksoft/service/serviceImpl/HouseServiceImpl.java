package peaksoft.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.entity.Agency;
import peaksoft.entity.House;
import peaksoft.repository.AgencyRepository;
import peaksoft.repository.BookingRepository;
import peaksoft.repository.HouseRepository;
import peaksoft.service.HouseService;

import java.util.List;
@Service
public class HouseServiceImpl implements HouseService {
    private final HouseRepository houseRepository;
    private final AgencyRepository agencyRepository;
    private final BookingRepository bookingRepository;
@Autowired
    public HouseServiceImpl(HouseRepository houseRepository, AgencyRepository agencyRepository, BookingRepository bookingRepository) {
        this.houseRepository = houseRepository;
        this.agencyRepository = agencyRepository;
    this.bookingRepository = bookingRepository;
}

    @Override
    public void saveHouse(Long id, House house) {
        Agency agency = agencyRepository.getAgencyById(id);
        house.setAgency(agency);
        houseRepository.saveHouse(house);


    }

    @Override
    public House getHouseById(Long id) {
        return houseRepository.getHouseById(id);
    }

    @Override
    public List<House> getAllHouses(Long id) {
        return houseRepository.getAllHouses(id);
    }

    @Override
    public List<House> getAllHouses() {
        return houseRepository.getAllHouses();
    }

    @Override
    public void updateHouse(Long id, House newHouse) {
    houseRepository.updateHouse(id, newHouse);
    }

    @Override
    public void deleteHouseById(Long id) {
  houseRepository.deleteHouseById(id);

    }
}
