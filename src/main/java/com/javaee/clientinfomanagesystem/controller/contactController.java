package com.javaee.clientinfomanagesystem.controller;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.javaee.clientinfomanagesystem.po.contact;
import com.javaee.clientinfomanagesystem.service.clientService;
import com.javaee.clientinfomanagesystem.service.contactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
@RequestMapping("/contact")
public class contactController {
    @Autowired
    private contactService contactService;
    @Autowired
    private clientService clientService;

    @RequestMapping("/contact_list")
    public String contactList(Model model,
                              @RequestParam(defaultValue = "1") int pageNum,
                              @RequestParam(required = false) String key){
        PageHelper.startPage(pageNum, 3);
        List<contact> list;
        if(key != null && !"".equals(key.trim())){
            list = contactService.findContactByKey(key.trim());
        }else{
            list = contactService.findContacts();
        }
        PageInfo<contact> pageInfo = new PageInfo<>(list);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("key", key);
        return "contact/contactlist";
    }

    @RequestMapping("/to_add_contact")
    public String toAddContact(Model model){
        model.addAttribute("clients", clientService.findClients());
        model.addAttribute("contactOper", "0");
        return "contact/add_contact";
    }

    @RequestMapping("/add_contact")
    public String addContact(contact contact){
        contactService.addContact(contact);
        return "redirect:/contact/contact_list";
    }

    @RequestMapping("/to_edit_contact")
    public String toEditContact(int coid, Model model){
        contact contact = contactService.findContact(coid);
        model.addAttribute("contact", contact);
        model.addAttribute("contactOper", "1");
        model.addAttribute("clients", clientService.findClients());
        return "contact/add_contact";
    }

    @RequestMapping("/edit_contact")
    public String editContact(contact contact){
        contactService.updateContact(contact);
        return "redirect:/contact/contact_list";
    }

    @ResponseBody
    @RequestMapping("/delete_contact")
    public String deleteContact(int coid){
        contactService.deleteContact(coid);
        return "SUCCESS";
    }

    @ResponseBody
    @RequestMapping("/batch_delete_contact")
    public String batchDelete(String coids){
        String[] idArr = coids.split(",");
        for(String id : idArr){
            int coid = Integer.parseInt(id);
            contactService.deleteContact(coid);
        }
        return "SUCCESS";
    }
}