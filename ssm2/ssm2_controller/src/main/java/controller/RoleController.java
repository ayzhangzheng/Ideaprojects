package controller;


import domain.Permission;
import domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import service.IRoleService;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @RequestMapping("/findAll")
    //@RolesAllowed("ADMIN")//只有ADMIN权限的有这个方法访问权限,要导入依赖
    //@Secured("ROLE_ADMIN")//不能忽略前缀，不用导入依赖，spring-security本身有的依赖
    @PreAuthorize("hasRole('ROLE_ADMIN')")//表达式操作，只有含有ROLE_ADMIN权限的才能访问
    public ModelAndView findAll() throws Exception{
        List<Role> roleList = roleService.findAll();
        ModelAndView mv=new ModelAndView();
        mv.addObject("roleList",roleList);
        mv.setViewName("role-list");
        return mv;
    }

    @PreAuthorize("authentication.principal.username=='张政'")//只有名字叫张政的用户才可访问
    @RequestMapping("/save")
    public String save(Role role) throws Exception{
        roleService.save(role);
        return "redirect:findAll";
    }

    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(name = "id",required = true)int roleId) throws Exception{
        Role role=roleService.findById(roleId);
        ModelAndView mv=new ModelAndView();
        mv.addObject("role",role);
        mv.setViewName("role-show");
        return mv;
    }

    @RequestMapping("/findRoleByIdAndAllPermission")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id",required = true)int roleId)throws Exception{
        ModelAndView mv=new ModelAndView();
        Role role=roleService.findById(roleId);
        List<Permission> permissionList = roleService.findOtherPermission(roleId);
        mv.addObject("role",role);
        mv.addObject("permissionList",permissionList);
        mv.setViewName("role-permission-add");
        return mv;
    }

    //给角色添加权限
    @RequestMapping("/addPermissionToRole")
    public String addPermissionToRole(@RequestParam(name = "roleId", required = true) int roleId, @RequestParam(name = "ids", required = true) int[] permissionIds) throws Exception {
        roleService.addPermissionToRole(roleId, permissionIds);
        return "redirect:findAll";
    }

}
