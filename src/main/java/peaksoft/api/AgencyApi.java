package peaksoft.api;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Agency;
import peaksoft.service.AgencyService;

@Controller
@RequestMapping("/agencies")
public class AgencyApi {
    private final AgencyService agencyService;

    @Autowired
    public AgencyApi(AgencyService agencyService) {
        this.agencyService = agencyService;
    }
    @GetMapping
    public String getAllAgencies(Model model,
                                 @RequestParam(value = "word",required = false)String word){
        model.addAttribute("word",word);
        model.addAttribute("agencies",agencyService.getAllSearch(word));
        return "agency/mainPage";
    }
    @GetMapping("/new")
    public String create(Model model){
        model.addAttribute("newAgency",new Agency());
        return "agency/newAgency";
    }
    @PostMapping("/save")
    public String save(@ModelAttribute("newAgency")@Valid Agency agency,
                       BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "agency/newAgency";
        }
        agencyService.saveAgency(agency);
        return "redirect:/agencies";
    }
    @GetMapping("/{agencyId}")
    public String getById(Model model,@PathVariable("agencyId")Long id){
        model.addAttribute("agency",agencyService.getAgencyById(id));
        model.addAttribute("customers",agencyService.getAgencyById(id).getCustomers().size());
        model.addAttribute("houses",agencyService.getAgencyById(id).getHouses().size());
     return "agency/profile";
    }
    @GetMapping("/{agencyId}/edit")
    public String get(@PathVariable("agencyId")Long id,Model model){
        Agency agency = agencyService.getAgencyById(id);
        model.addAttribute("agency",agency);
        return "agency/edit";
    }
    @PutMapping("/{agencyId}/update")
    public String update(@PathVariable("agencyId")Long id,@ModelAttribute("agency")@Valid Agency agency,
                         BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "agency/edit";
        }
        agencyService.updateAgency(id, agency);
        return "redirect:/agencies";
    }
    @DeleteMapping("{agencyId}/delete")
    public String delete(@PathVariable("agencyId")Long id){
        agencyService.deleteAgencyById(id);
        return "redirect:/agencies";
    }

}
