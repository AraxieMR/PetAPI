package com.manheim.apprenticeship.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manheim.apprenticeship.dao.model.*;
import com.manheim.apprenticeship.service.HumanService;
import com.manheim.apprenticeship.service.PetsService;
import com.manheim.apprenticeship.service.ToyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Controller
public class HumanController {
    private Logger LOG = LoggerFactory.getLogger(HumanController.class);


    @Autowired
    private HumanService humanService;

    @Autowired
    private ToyService toyService;

    @Autowired
    private PetsService petsService;


    @PostMapping("/humans")
    private void createAHuman(@RequestBody Human human) {
        humanService.createAHuman(human);
    }

    @GetMapping("/humans")
    @ModelAttribute("humans")
    public List<HumanResult> getAllHumans() {
        List<Human> humanList = humanService.getAllHumans();
        List<HumanResult> humanResults = new ArrayList<>();
        for (Human hum : humanList) {
            HumanResult result = new HumanResult();
            result.setId(hum.getId());
            result.setName(hum.getName());
            List<Toy> toys = toyService.findToysByHumanId(hum.getId());
            result.setToys(toys);
            humanResults.add(result);
        }
        return humanResults;
    }

    /*Form*/
    @RequestMapping(value = "/newCustomer")
    //@ModelAttribute("humanList")
    public String index(@RequestParam(required = false) boolean refresh, HttpServletResponse response,
                        HttpServletRequest request, Model model) {
        //model.addAttribute("human", aaDTMLink);
        //model.addAttribute("test", "test123");
        //model.addAttribute("humanList");
        return "index";
    }

    /*--------------------------Accepting Form data and sending to the DB--------------------------------*/
    @PostMapping(value = "/customer", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void createNewCustomer(@RequestBody MultiValueMap<String, String> paramMap,
                                  HttpServletResponse response) throws IOException {

        Human newHuman = new Human();
        newHuman.setName(paramMap.getFirst("name"));
        Human human = humanService.createAHuman(newHuman);

        callToyService(paramMap.getFirst("toy1Name"), paramMap.getFirst("toy1Color"), human);
        callToyService(paramMap.getFirst("toy2Name"), paramMap.getFirst("toy2Color"), human);
        callToyService(paramMap.getFirst("toy2Name"), paramMap.getFirst("toy2Color"), human);

        Pets newPet = new Pets();
        newPet.setName(paramMap.getFirst("petName"));
        newPet.setBreed(paramMap.getFirst("petBreed"));
        newPet.setColor(paramMap.getFirst("petColor"));
        newPet.setHuman(human);
        petsService.createAPet(newPet);

        // final ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
        //  final Customer customer = mapper.convertValue(paramMap, Customer.class);
        response.sendRedirect("/humans");
    }

    /*--------------------------accepting Customer in body---------------------------------*/
    @PostMapping("/customer2")
    public void createNewCustomer2(@RequestBody Customer customer, HttpServletResponse response) throws IOException {
        Human newHuman = new Human();
        newHuman.setName(customer.getName());
        Human human = humanService.createAHuman(newHuman);

        // Pets newPet = new Pets();
        // newPet.setName(customer.getPet().getName());
        // newPet.setBreed(customer.getPet().getBreed());
        //  newPet.setColor(customer.getPet().getColor());
        // newPet.setHuman(human);
        // petsService.createAPet(newPet);

        for (int index = 0; index < customer.getToys().size(); index++) {
           /*
            Toy newToy = new Toy();

            newToy.setTitle(customer.getToys().get(index).getTitle());
            newToy.setColor(customer.getToys().get(index).getColor());
            newToy.setHuman(human);
            toyService.createAToy(newToy);
            */
            callToyService(customer.getToys().get(index).getTitle(), customer.getToys().get(index).getColor(), human);
        }
        response.sendRedirect("/humans");
    }

    /*--------------------------------------------------------------------------------------------------------*/
    private void callToyService(String title, String color, Human human) {
        Toy newToy = new Toy();
        newToy.setTitle(title);
        newToy.setColor(color);
        newToy.setHuman(human);
        toyService.createAToy(newToy);
    }

    @PutMapping("/humans")
    private void updateAHuman(@RequestBody Human human,
                              @RequestParam(name = "name", required = false) String name) {
        LOG.info("Update Human");
        humanService.updateAHuman(human, name);
    }
@RequestMapping(value = "/updateCustomer",method = RequestMethod.PUT)
public @ResponseBody void updateCustomerByName(@RequestParam(name="id",required = true)int id,HttpServletResponse response)throws IOException{
        LOG.info("in update method");
       // toyService.updateById(id);
      //  petsService.updateById(id);
       // humanService.updateById(id);
    response.sendRedirect("/humans");
}
//    @PutMapping("/humans/{humanId}/toy")
//    private void assignToyToHuman(@PathVariable(name = "humanId") int humanId,
//                                  @RequestParam(name = "toyTitle", required = true) String toyTitle) {
//        Human human = humanService.findById(humanId);
//        Toy toy = toyService.findByTitle(toyTitle);
//        humanService.assignToyToHuman(human, toy);
//    }

    @RequestMapping(value = "/deleteCustomer", method = RequestMethod.POST)
    public @ResponseBody void deleteHumanByName(@RequestParam(name="id",required = true)int id,HttpServletResponse response)throws IOException {
        LOG.info("In delete customer");
        LOG.info("id"+id);
        toyService.deleteById(id);
        petsService.deleteById(id);
        humanService.deleteById(id);
        //LOG.info("TEST2");
       response.sendRedirect("/humans");
    }

    @RequestMapping(value = "/updateCustomer",method = RequestMethod.GET)
    @ModelAttribute("update")
    public HumanResult getHuman(@RequestParam(name="id",required = true)int id,HttpServletResponse response)throws IOException{
   // public HumanResult getHuman(HttpServletResponse response)throws IOException{

        LOG.info("in get human method");
        LOG.info("id"+id);
        HumanResult humanResult = humanService.getHumanResultById(id);
//        List<Human> humanList = humanService.getAllHumans();
//        List<HumanResult> humanResults = new ArrayList<>();
//        for (Human hum : humanList) {
//            HumanResult result = new HumanResult();
//            result.setId(hum.getId());
//            result.setName(hum.getName());
//            List<Toy> toys = toyService.findToysByHumanId(hum.getId());
//            result.setToys(toys);
//            humanResults.add(result);
//        }
        return humanResult;
    }
}
