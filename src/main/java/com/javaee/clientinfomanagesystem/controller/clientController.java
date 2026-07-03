package com.javaee.clientinfomanagesystem.controller;
import org.springframework.ui.Model;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.javaee.clientinfomanagesystem.po.client;
import com.javaee.clientinfomanagesystem.service.clientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
@RequestMapping("/client")
public class clientController {
    @Autowired
    public clientService clientService;

    @RequestMapping("/client_list")
    public String clientList(Model model,
                             @RequestParam(defaultValue = "1")int pageNum,
                             @RequestParam(required = false) String key){
        PageHelper.startPage(pageNum, 3);
        List<client> clients;
        if(key != null && !"".equals(key)){
            clients = clientService.findClientByKey(key);
        }else{
            clients = clientService.findClients();
        }
        PageInfo<client> pageInfo = new PageInfo<>(clients);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("key", key);
        return "client/clientlist";
    }

    @RequestMapping("/to_add_client")
    public String toAddClient(Model model){
        model.addAttribute("clientOper", "0");
        return "client/add_client";
    }

    @RequestMapping("/add_client")
    public String addClient(client client, Model model){
        clientService.addClient(client);
        return "redirect:/client/client_list";
    }

    @RequestMapping("/to_edit_client")
    public String toEditClient(int cid, Model model){
        client client = clientService.findClient(cid);
        model.addAttribute("client", client);
        model.addAttribute("clientOper", "1");
        return "client/add_client";
    }

    @RequestMapping("/edit_client")
    public String editClient(client client, Model model){
        clientService.updateClient(client);
        return "redirect:/client/client_list";
    }

    @RequestMapping("/delete_client")
    @ResponseBody
    public String deleteClient(int cid){
        clientService.deleteClient(cid);
        return "SUCCESS";
    }

    @ResponseBody
    @RequestMapping("/batch_delete_client")
    public String batchDelete(String cids){
        String[] idArr = cids.split(",");
        for(String id : idArr){
            int cid = Integer.parseInt(id);
            clientService.deleteClient(cid);
        }
        return "SUCCESS";
    }
}