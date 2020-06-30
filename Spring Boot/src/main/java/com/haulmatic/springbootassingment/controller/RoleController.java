package com.haulmatic.springbootassingment.controller;

import com.haulmatic.springbootassingment.entity.Role;
import com.haulmatic.springbootassingment.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<Role> getAllRoleList(){
        return roleService.getAllRoles();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Role> getRoleById(@PathVariable("id") int id){
        return roleService.getRoleById(id);
    }

    @PostMapping
    public @ResponseBody Role addNewRole(@RequestBody Role role){
        return roleService.addNewRole(role);
    }

    @PutMapping
    public @ResponseBody Role updateRole(@RequestBody  Role role){
        return roleService.updateRole(role);
    }
    @DeleteMapping(value = "/{id}")
    public @ResponseBody String deleteRoleById(@PathVariable("id") int id){
        roleService.deletRole(id);
        return "role delete successfully";
    }

    @GetMapping(value = "/by_nic/{nic}")
    public @ResponseBody Iterable<Role> getRoleByNic(@PathVariable("nic") String nic){
        return roleService.getRoleByNic(nic);
    }

    @GetMapping(value = "/by_organization/{organization}")
    public @ResponseBody Iterable<Object> getRoleByOrganization(@PathVariable("organization") String organization){
        return roleService.getRolesByOrganization(organization);
    }

    @GetMapping(value = "/by_roletype/{roletype}")
    public @ResponseBody Iterable<Object> getRoleByRoleType(@PathVariable("roletype") String roletype){
        return roleService.getRolesByRoleType(roletype);
    }

}