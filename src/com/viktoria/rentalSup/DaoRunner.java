package com.viktoria.rentalSup;

import com.viktoria.rentalSup.dao.RoleDao;
import com.viktoria.rentalSup.dao.UserTypeDao;
import com.viktoria.rentalSup.dto.UserTypeFilter;
import com.viktoria.rentalSup.entity.Role;
import com.viktoria.rentalSup.entity.UserType;

import java.util.List;
import java.util.Optional;

public class DaoRunner {

    public static void main(String[] args) {

//        saveRole();
//        updateRole();
//        deleteRole();

//        deleteUserType();
//        saveUserType();
//        updateUserType();

//        filterUserType();


    }


    private static void saveRole() {
        var roleDao = RoleDao.getInstance();
        var role = new Role();
        role.setRoleName("admin");

        var savedRole = roleDao.save(role);
        System.out.println(savedRole);
    }

    private static void deleteRole() {
        var roleDao = RoleDao.getInstance();
        var deleteResult = roleDao.delete(1L);
        System.out.println(deleteResult);
    }

    private static void updateRole() {
        var roleDao = RoleDao.getInstance();
        var maybeRole = roleDao.findById(1L);
        System.out.println(maybeRole);

        maybeRole.ifPresent(role -> {
            role.setRoleName("Admin");
            roleDao.update(role);
        });
        System.out.println(maybeRole);
    }


    private static void deleteUserType() {
        var userTypeDao = UserTypeDao.getInstance();
        var deleteResult = userTypeDao.delete(1L);
        System.out.println(deleteResult);
    }


    private static void saveUserType() {
        var userTypeDao = UserTypeDao.getInstance();
        var userType = new UserType();
        userType.setFirstName("Алексей");
        userType.setLastName("Базанов");
        userType.setLogin("baza");
        userType.setPassword("qwerty1234");
        userType.setNumber("8-920-766-18-10");
        userType.setRole(RoleDao.getInstance().findById(2L).get());

        var savedUserType = userTypeDao.save(userType);
        System.out.println(savedUserType);
    }

    private static void updateUserType() {
        var userTypeDao = UserTypeDao.getInstance();
        var maybeUserType = userTypeDao.findById(1L);
        System.out.println(maybeUserType);

        maybeUserType.ifPresent(userType -> {
            userType.setNumber("8-915-736-99-00");
            userTypeDao.update(userType);
        });
        System.out.println(maybeUserType);
    }


    private static void filterUserType() {
        var userTypeFilter = new UserTypeFilter(2, 0, "Базанов", "8-920");

        var userTypes = UserTypeDao.getInstance().findAll(userTypeFilter);
        System.out.println(userTypes);
    }

}
