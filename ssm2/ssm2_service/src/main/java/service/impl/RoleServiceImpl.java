package service.impl;

import dao.IRoleDao;
import domain.Permission;
import domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.IRoleService;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;


    public List<Role> findAll() throws Exception {
        return roleDao.findAll();
    }

    public void save(Role role) throws Exception {
        roleDao.save(role);
    }

    public Role findById(int roleId) throws Exception {
        return roleDao.findById(roleId);
    }

    public List<Permission> findOtherPermission(int roleId) throws Exception {
        return roleDao.findOtherPermission(roleId);
    }

    public void addPermissionToRole(int roleId, int[] permissionIds) throws Exception {
        for(int permissionId:permissionIds){
            roleDao.addPermissionToRole(roleId,permissionId);
        }
    }

    public void deleteRoleById(int roleId) throws Exception {
//从user_role表中删除
        roleDao.deleteFromUser_RoleByRoleId(roleId);
        //从role_permission表中删除
        roleDao.deleteFromRole_PermissionByRoleId(roleId);
        //从role表中删除
        roleDao.deleteRoleById(roleId);
    }
}
