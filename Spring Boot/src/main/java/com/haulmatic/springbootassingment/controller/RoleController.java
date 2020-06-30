package com.haulmatic.springbootassingment.controller;

import com.haulmatic.springbootassingment.entity.Role;
import com.haulmatic.springbootassingment.service.RoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     * get all available roles
     * @return list of Roles
     */
    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(
            value = "Get all roles"
    )
    public @ResponseBody List<Role> getAllRoleList(){
        return roleService.getAllRoles();
    }

    /**
     * get role by given id
     * @param id
     * @return corresponding role
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(
            value = "Get role by id"
    )
    public @ResponseBody Optional<Role> getRoleById(@PathVariable("id") int id){
        return roleService.getRoleById(id);
    }

    /**
     * add new role
     * @param role
     * @return new role with id
     */
    @PostMapping
    @ApiOperation(
            value = "create new roles",
            notes = "Create new role by given information (first name, last name ,organization, nic, role type)"
    )
    public @ResponseBody Role addNewRole(@RequestBody Role role){
        return roleService.addNewRole(role);
    }

    /**
     * update existing role
     * @param role
     * @return updated role
     */
    @PutMapping
    @ApiOperation(
            value = "Update existing role",
            notes = "Need to provide role id and all other info"
    )
    public @ResponseBody Role updateRole(@RequestBody  Role role){
        return roleService.updateRole(role);
    }

    /**
     * delete role of given id
     * @param id
     * @return successful message
     */
    @ApiOperation(
            value = "delete role if exist by id"
    )
    @DeleteMapping(value = "/{id}")
    public @ResponseBody String deleteRoleById(@PathVariable("id") int id){
        return roleService.deletRole(id);
    }

    /**
     * get role by id
     * @param nic
     * @return Iterable List of role
     */
    @ApiOperation(
            value = "get role by nic",
            notes = "Get all roles that are match with given nic"
    )
    @GetMapping(value = "/by_nic/{nic}")
    public @ResponseBody Iterable<Role> getRoleByNic(@PathVariable("nic") String nic){
        return roleService.getRoleByNic(nic);
    }

    /***
     * get list of firstname, last name and nic by given organization
     * @param organization
     * @return list of roles
     */
    @ApiOperation(
            value = "Get role by organization",
            notes = "It will return only first name, last name and nic"
    )
    @GetMapping(value = "/by_organization/{organization}")
    public @ResponseBody Iterable<Object> getRoleByOrganization(@PathVariable("organization") String organization){
        return roleService.getRolesByOrganization(organization);
    }
    /***
     * get list of firstname, last name and nic by given roletype
     * @param roletype
     * @return list of roles
     */
    @ApiOperation(
            value = "Get role by roletype",
            notes = "It will return only first name, last name and nic"
    )
    @GetMapping(value = "/by_roletype/{roletype}")
    public @ResponseBody Iterable<Object> getRoleByRoleType(@PathVariable("roletype") String roletype){
        return roleService.getRolesByRoleType(roletype);
    }

}