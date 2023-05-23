package peaksoft.repository;

import peaksoft.entity.Agency;
import peaksoft.entity.House;

import java.util.List;

public interface HouseRepository {
//    void saveHouse(Long agencyId,House house);
    void saveHouse(House house);
    House getHouseById(Long id);
    List<House> getAllHouses(Long id);
    void updateHouse(Long id, House newHouse);
    void deleteHouseById(Long id);
    List<House>getAllHouses();
}
