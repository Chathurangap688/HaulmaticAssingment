package com.haulmatic.springbootassingment.service;


import com.haulmatic.springbootassingment.dao.RoleDAO;
import com.haulmatic.springbootassingment.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Thilakawansha B. M. C. P
 *
 */
@Service
public class RoleService {
    @Autowired
    private RoleDAO roleDAO;

    /**
     * get all roles
     * @return list of all roles
     */
    public List<Role> getAllRoles(){
        return roleDAO.findAll();
    }

    /**
     * get role by role id
     * @param id
     * @return a role if available
     */
    public Optional<Role> getRoleById(int id){
        return roleDAO.findById(id);
    }

    /**
     * add new role from given inputs
     * @param role
     * @return will return new role with id
     */
    public Role addNewRole(Role role){
        return roleDAO.save(role);
    }

    /**
     * update existing role from given inputs
     * @param role
     * @return will return new role with id
     */
    public Role updateRole(Role role){
        return roleDAO.save(role);
    }

    /**
     * delete given role
     * @param id
     */
    public String deletRole(int id){

        if(roleDAO.existsById(id)){
            Role role = roleDAO.getOne(id);
            roleDAO.delete(role);
            return "role delete successfully";
        }else {
            return "Role not found for given id: "+ id;
        }

    }

    /**
     * get role by given nic no
     * @param nic
     * @return List of roles that are match to given nic
     */
    public List<Role> getRoleByNic(String nic){
        return roleDAO.findByNic(nic);
    }
    /**
     * get list of first name, last name and nic of roles by organization
     * @param organization
     * @return list of roles
     */
    public List<Object> getRolesByOrganization(String organization){
        return roleDAO.findByOrganization(organization);
    }

    /**
     * get list of first name, last name and nic of roles by roleType
     * @param roleType
     * @return list of roles
     */
    public List<Object> getRolesByRoleType(String roleType){
        return roleDAO.findByRoletype(roleType);
    }
}
