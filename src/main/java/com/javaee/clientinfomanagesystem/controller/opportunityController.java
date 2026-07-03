package com.javaee.clientinfomanagesystem.controller;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.javaee.clientinfomanagesystem.po.opportunity;
import com.javaee.clientinfomanagesystem.service.opportunityService;
import com.javaee.clientinfomanagesystem.service.clientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/opportunity")
public class opportunityController {
    @Autowired
    private opportunityService opportunityService;
    @Autowired
    private clientService clientService;

    @RequestMapping("/list")
    public String opportunityList(Model model,
                                  @RequestParam(defaultValue = "1") int pageNum,
                                  @RequestParam(required = false) String key){
        PageHelper.startPage(pageNum, 3);
        List<opportunity> list;
        if(key != null && !"".equals(key.trim())){
            list = opportunityService.findOpportunityByKey(key.trim());
        }else{
            list = opportunityService.findOpportunitys();
        }
        if(list == null){
            list = new ArrayList<>();
        }
        PageInfo<opportunity> pageInfo = new PageInfo<>(list);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("key", key);
        return "opportunity/opportunitylist";
    }

    @RequestMapping("/to_add_opportunity")
    public String toAddOpportunity(Model model){
        model.addAttribute("opportunityOper", "0");
        model.addAttribute("clients", clientService.findClients());
        return "opportunity/add_opportunity";
    }

    @RequestMapping("/add_opportunity")
    public String addOpportunity(opportunity opportunity){

        opportunityService.addOpportunity(opportunity);
        return "redirect:/opportunity/list";
    }

    @RequestMapping("/to_edit_opportunity")
    public String toEditOpportunity(int opid, Model model){
        opportunity opportunity = opportunityService.findOpportunity(opid);
        model.addAttribute("opportunity", opportunity);
        model.addAttribute("opportunityOper", "1");
        model.addAttribute("clients", clientService.findClients());
        return "opportunity/add_opportunity";
    }

    @RequestMapping("/edit_opportunity")
    public String editOpportunity(opportunity opportunity){
        opportunityService.updateOpportunity(opportunity);
        return "redirect:/opportunity/list";
    }

    @ResponseBody
    @RequestMapping("/delete_opportunity")
    public String deleteOpportunity(int opid){
        opportunityService.deleteOpportunity(opid);
        return "SUCCESS";
    }

    @ResponseBody
    @RequestMapping("/batch_delete_opportunity")
    public String batchDelete(String opids){
        String[] idArr = opids.split(",");
        for(String id : idArr){
            int opid = Integer.parseInt(id);
            opportunityService.deleteOpportunity(opid);
        }
        return "SUCCESS";
    }
}