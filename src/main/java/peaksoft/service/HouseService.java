package peaksoft.service;

import peaksoft.entity.Agency;
import peaksoft.entity.House;

import java.util.List;

public interface HouseService {
    void saveHouse(Long id,House house);
//    void saveHouse(Long agencyId, House house);
    House getHouseById(Long id);
    List<House> getAllHouses(Long id);
    List<House> getAllHouses();
    void updateHouse(Long id, House newHouse);
    void deleteHouseById(Long id);
}
