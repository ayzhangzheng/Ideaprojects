package service;

import domain.Permission;
import domain.Role;

import java.util.List;

public interface IRoleService {

    public List<Role> findAll() throws Exception;

    void save(Role role) throws Exception;

   Role findById(int roleId)throws Exception;

   List<Permission> findOtherPermission(int roleId) throws Exception;

    void addPermissionToRole(int roleId, int[] permissionIds) throws Exception;

    void deleteRoleById(int roleId) throws Exception;
}
