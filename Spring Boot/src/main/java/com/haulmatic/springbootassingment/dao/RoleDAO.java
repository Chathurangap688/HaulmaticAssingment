package com.haulmatic.springbootassingment.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.haulmatic.springbootassingment.entity.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
//@Repository
public interface RoleDAO extends JpaRepository<Role, Integer> {
    /**
     * Finds Role by using the nic as a search criteria.
     * @param nic
     * @return  A list of Role whose nic is an exact match with the given nic.
     *          If no Role is found, this method returns null.
     */
    public List<Role> findByNic(String nic);

    /**
     * Finds a Role(first name, last name, and NIC no) by using the organization as a search criteria.
     * @param organization
     * @return  A list of Role whose organization is an exact match with the given organization.
     *          If no Role is found, this method returns an empty list.
     */
    @Query("SELECT r.firstName, r.lastName, r.nic FROM Role r WHERE LOWER(r.organization) = LOWER(:organization)")
    public List<Object> findByOrganization(@Param("organization") String organization);

    /**
     * Finds a Role(first name, last name, and NIC no) by using the roletype as a search criteria.
     * @param roletype
     * @return  A list of Role whose roletype is an exact match with the given roletype.
     *          If no Role is found, this method returns an empty list.
     */
    @Query("SELECT r.firstName, r.lastName, r.nic FROM Role r WHERE LOWER(r.roletype) = LOWER(:roletype)")
    public List<Object> findByRoletype(@Param("roletype") String roletype);
}
