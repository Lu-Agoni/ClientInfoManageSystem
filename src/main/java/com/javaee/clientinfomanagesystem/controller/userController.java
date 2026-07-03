package com.javaee.clientinfomanagesystem.controller;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.javaee.clientinfomanagesystem.po.role;
import com.javaee.clientinfomanagesystem.po.user;
import com.javaee.clientinfomanagesystem.service.roleService;
import com.javaee.clientinfomanagesystem.service.userService;
import com.javaee.clientinfomanagesystem.utils.Md5Util;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/user")
public class userController {
    @Autowired
    private userService userService;
    @Autowired
    private roleService roleService;

    @RequestMapping("/login")
    public String toLogin(Model model) {
        List<role> roles = roleService.findRoles();
        model.addAttribute("roles", roles);
        return "user/login";
    }

    @RequestMapping("/dologin")
    public String doLogin(String username, String password, Integer rid, Model model, HttpSession session) {
        String pwdMd5 = Md5Util.encrypt(password);
        user loginUser = userService.login(username, pwdMd5);
        if (loginUser == null) {
            model.addAttribute("error", "用户名或密码错误");
            model.addAttribute("roles", roleService.findRoles());
            return "user/login";
        }
        if (rid != null && loginUser.getRole() != null && loginUser.getRole().getRid() != rid) {
            model.addAttribute("error", "您没有该身份权限，请选择正确的身份");
            model.addAttribute("roles", roleService.findRoles());
            return "user/login";
        }
        session.setAttribute("user", loginUser);
        return "redirect:/main";
    }

    @RequestMapping("/register")
    public String toReg(Model model){
        List<role> roles = roleService.findRoles();
        model.addAttribute("roles", roles);
        return "user/register";
    }

    @RequestMapping("/doregister")
    public String reg(user user, @RequestParam("rid") int rid, Model model) {
        if (userService.findByUsername(user.getUsername()) != null) {
            model.addAttribute("error", "用户名已存在");
            return "user/register";
        }
        user.setPassword(Md5Util.encrypt(user.getPassword()));
        int res = userService.register(user);
        if (res > 0 && user.getUid() > 0) {
            userService.addUserRole(user.getUid(), rid);
            return "redirect:/user/login";
        } else {
            model.addAttribute("error", "注册失败，请稍后重试");
            return "user/register";
        }
    }
    //退出登录
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/user/login";
    }

    @RequestMapping("/user_list")
    public String userList(Model model,@RequestParam(defaultValue = "1")int pageNum, @RequestParam(required = false) String key){
        PageHelper.startPage(pageNum, 3);
        List<user> users;
        if(key != null && !"".equals(key)){
            users = userService.findUserByKey(key);
        }else{
            users = userService.findUsers();
        }
        PageInfo<user> pageInfo = new PageInfo<>(users);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("key", key);
        return "user/userlist";
    }

    @RequestMapping("/to_add_user")
    public String toAddUser(Model model){
        model.addAttribute("user", new user());
        model.addAttribute("userOper", "0");
        model.addAttribute("roles",roleService.findRoles());
        return "user/add_user";
    }

    @RequestMapping("/add_user")
    public String addUser(user user, Model model){
        user.setPassword(Md5Util.encrypt(user.getPassword()));
        int res = userService.addUser(user);
        if(res > 0 && user.getUid() > 0 && user.getRole() != null && user.getRole().getRid() > 0){
            userService.addUserRole(user.getUid(), user.getRole().getRid());
        }
        return "redirect:/user/user_list";
    }

    @RequestMapping("/to_edit_user")
    public String toEditUser(int uid, Model model){
        user user = userService.findUserByUserId(uid);
        model.addAttribute("user", user);
        model.addAttribute("userOper", "1");
        model.addAttribute("roles",roleService.findRoles());
        return "user/add_user";
    }

    @RequestMapping("/edit_user")
    public String editUser(user user, Model model){
        if(user.getPassword() == null || user.getPassword().trim().isEmpty()){
            user existingUser = userService.findUserByUserId(user.getUid());
            user.setPassword(existingUser.getPassword());
        } else {
            user.setPassword(Md5Util.encrypt(user.getPassword()));
        }
        userService.updateUser(user);
        if(user.getRole() != null && user.getRole().getRid() > 0){
            userService.deleteUserRole(user.getUid());
            userService.addUserRole(user.getUid(), user.getRole().getRid());
        }
        return "redirect:/user/user_list";
    }

    @RequestMapping("/delete_user")
    @ResponseBody
    public String deleteUserByUserId(int uid){
        userService.deleteUserRole(uid);
        int rows = userService.deleteUserByUserId(uid);
        return rows > 0 ? "SUCCESS" : "FAILURE";
    }

    @ResponseBody
    @RequestMapping("/batch_delete_user")
    public String batchDeleteByUserId(String cids){
        String[] idArr = cids.split(",");
        for(String id : idArr){
            int uid = Integer.parseInt(id);
            userService.deleteUserRole(uid);
            userService.deleteUserByUserId(uid);
        }
        return "SUCCESS";
    }

    @RequestMapping("/updatePwd")
    public String updatePwd(String oldPwd, String newPwd, String rePwd, HttpSession session, Model model){
        user loginUser = (user) session.getAttribute("user");
        if(!newPwd.equals(rePwd)){
            model.addAttribute("msg","两次新密码输入不一致");
            return "includes/update_password";
        }
        String oldMd5 = Md5Util.encrypt(oldPwd);
        if(!oldMd5.equals(loginUser.getPassword())){
            model.addAttribute("msg","原密码错误");
            return "includes/update_password";
        }
        String newMd5 = Md5Util.encrypt(newPwd);
        userService.updatePassword(loginUser.getUid(), newMd5);
        session.invalidate();
        return "redirect:/user/login";
    }
}

