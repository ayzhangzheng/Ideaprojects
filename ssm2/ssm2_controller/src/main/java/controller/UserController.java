package controller;

import domain.Role;
import domain.UserInfo;
import org.apache.ibatis.annotations.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import service.IRoleService;
import service.IUserService;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;


    @RequestMapping("/findAll")
    @PreAuthorize("authentication.principal.username=='张政'")//只有名字叫张政的用户才可访问
    public ModelAndView findAll() throws Exception{
        List<UserInfo> userList = userService.findAll();
        ModelAndView mv=new ModelAndView();
        mv.addObject("userList",userList);
        mv.setViewName("user-list");
        return mv;
    }

    @RequestMapping("/deleteUser")
    public String deleteUser(@RequestParam(name = "id",required = true)int id) throws  Exception{
        userService.deleteUser(id);
        return "redirect:findAll";
    }

    @RequestMapping("/save")
    public String save(UserInfo userInfo) throws Exception{
        userService.save(userInfo);
        return "redirect:findAll";
    }

    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(name = "id" ,required = true)int id) throws Exception{
        UserInfo userInfo = userService.findById(id);
        ModelAndView mv=new ModelAndView();
        mv.addObject("user",userInfo);
        mv.setViewName("user-show");
        return mv;
    }

    @RequestMapping("/findUserByIdAndAllRole")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id",required = true) int userId)throws Exception{
        //根据用户id查询用户
        UserInfo user = userService.findById(userId);
        //根据用户id查询可以添加的角色
        List<Role> roleList = userService.findOtherRoles(userId);
        ModelAndView mv=new ModelAndView();
        mv.addObject("user",user);
        mv.addObject("roleList",roleList);
        mv.setViewName("user-role-add");
        return mv;
    }

    //给用户添加角色
    @RequestMapping("/addRoleToUser")
    public String addRoleToUser(@RequestParam(name = "userId", required = true) int userId, @RequestParam(name = "ids", required = true) int[] roleIds) {
        userService.addRoleToUser(userId, roleIds);
        return "redirect:findAll";
    }
}
