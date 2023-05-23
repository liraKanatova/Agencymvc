package peaksoft.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Customer;
import peaksoft.service.AgencyService;
import peaksoft.service.CustomerService;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerApi {
    private final CustomerService customerService;
    private final AgencyService agencyService;

    @Autowired
    public CustomerApi(CustomerService customerService, AgencyService agencyService) {
        this.customerService = customerService;
        this.agencyService = agencyService;
    }

    @GetMapping
    public String getAllCustomers(Model model) {
        model.addAttribute("customers", customerService.getAllCustomers());
        return "customer/mainPage";
    }
    @GetMapping("/getAll/{agencyId}")
    public String getAllCustomersByAgency(@PathVariable("agencyId") Long agencyId, Model model) {
        model.addAttribute("customers", customerService.getAllCustomers(agencyId));
        return "customer/mainPage";
    }

    @GetMapping("/{customerId}/edit")
    public String get(@PathVariable("customerId") Long id,
                      Model model) {
        model.addAttribute("customer", customerService.getCustomerById(id));
        return "customer/edit";
    }

    @PostMapping("{cusId}/update")
    public String update(@ModelAttribute("customer") Customer customer,
                         @PathVariable("cusId") Long id) {
        customerService.updateCustomer(id, customer);
        return "redirect:/customers";
    }

    @DeleteMapping("/{customerId}/delete")
    public String delete(@PathVariable("customerId") Long id) {
        customerService.deleteCustomerById(id);
        return "redirect:/customers";
    }

    @GetMapping("/new")
    public String createCustomer(Model model) {
        model.addAttribute("newCustomer", new Customer());
        return "customer/newCustomer";
    }

    @PostMapping("/save")
    public String saveCustomer(@ModelAttribute("newCustomer") Customer customer) {
        customerService.saveCustomer(customer);
        return "redirect:/customers";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("customer", customerService.getCustomerById(id));
        return "customer/getById";
    }

    @GetMapping("/{customerId}/assignPage")
    public String assignPage(@PathVariable Long customerId,
                             Model model) {
        model.addAttribute("customerId", customerId);
        model.addAttribute("agencyId", agencyService.getAllAgencies());
        return "customer/assignPage";
    }

    @PostMapping("/{customerId}/assign")
    public String assign(@PathVariable Long customerId,
                         @RequestParam List<Long> agencyIdes) {
        customerService.assignCustomerToAgency(customerId, agencyIdes);
        return "redirect:/customers";
    }
}