package peaksoft.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.House;
import peaksoft.service.AgencyService;
import peaksoft.service.HouseService;

@Controller
@RequestMapping("/houses")
public class HouseApi {
    private final HouseService houseService;
    private final AgencyService agencyService;

    @Autowired
    public HouseApi(HouseService houseService, AgencyService agencyService) {
        this.houseService = houseService;
        this.agencyService = agencyService;
    }
    @GetMapping("/{id}")
    public String getAllHouses(Model model,@PathVariable("id")Long id){
        model.addAttribute("houses",houseService.getAllHouses(id));
        model.addAttribute("agencyId",id);
        return "house/mainPage";
    }
    @GetMapping("/{agencyId}/new")
    public String create(Model model,@PathVariable("agencyId")Long id){
        model.addAttribute("newHouse",new House());
        model.addAttribute("agencyId",id);
        return "house/newHouse";
    }
    @PostMapping("/{agencyId}/save")
    public String save(@PathVariable("agencyId") Long id,Model model, @ModelAttribute("newHouse")House house){
        houseService.saveHouse(id,house);
        model.addAttribute("agencyId",id);
        return "redirect:/houses/" + id;
    }
    @DeleteMapping("/{agencyId}/{houseId}/delete")
    public String delete(@PathVariable("agencyId")Long agencyId,@PathVariable("houseId")Long id){
        houseService.deleteHouseById(id);
        return "redirect:/houses/"+agencyId;
    }
    @GetMapping("/{agencyId}/{houseId}/edit")
    public String edit(Model model,@PathVariable("houseId")Long id,@PathVariable("agencyId")Long agencyId){
        model.addAttribute("house",houseService.getHouseById(id));
        model.addAttribute("agencyId",agencyId);
        return "house/edit";
    }
    @PutMapping("/{agencyId}/{houseId}/update")
    public String update(@PathVariable("agencyId")Long agencyId,@PathVariable("houseId")Long id,Model model,@ModelAttribute("house")House house){
        houseService.updateHouse(id,house);
        return "redirect:/houses/" + agencyId;
    }
}
