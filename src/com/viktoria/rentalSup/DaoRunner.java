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

//        new DaoRunner().saveRole();
//        new DaoRunner().updateRole();
//        new DaoRunner().deleteRole();

//        new DaoRunner().deleteUserType();
//        new DaoRunner().saveUserType();
//        new DaoRunner().updateUserType();

//        new DaoRunner().filterUserType();


    }


    private void saveRole() {
        var roleDao = RoleDao.getInstance();
        var role = new Role();
        role.setRoleName("Client");

        var savedRole = roleDao.save(role);
        System.out.println(savedRole);
    }

    private void deleteRole() {
        var roleDao = RoleDao.getInstance();
        var deleteResult = roleDao.delete(1);
        System.out.println(deleteResult);
    }

    private void updateRole() {
        var roleDao = RoleDao.getInstance();
        var maybeRole = roleDao.findById(1);
        System.out.println(maybeRole);

        maybeRole.ifPresent(role -> {
            role.setRoleName("Admin");
            roleDao.update(role);
        });
        System.out.println(maybeRole);
    }


    private void deleteUserType() {
        var userTypeDao = UserTypeDao.getInstance();
        var deleteResult = userTypeDao.delete(1L);
        System.out.println(deleteResult);
    }


    private void saveUserType() {
        var userTypeDao = UserTypeDao.getInstance();
        var userType = new UserType();
        userType.setFirstName("Антон");
        userType.setLastName("Богданов");
        userType.setLogin("antokha");
        userType.setPassword("123qwey");
        userType.setNumber("8-966-776-75-32");
        userType.setRole(RoleDao.getInstance().findById(2).get());

        var savedUserType = userTypeDao.save(userType);
        System.out.println(savedUserType);
    }

    private void updateUserType() {
        var userTypeDao = UserTypeDao.getInstance();
        var maybeUserType = userTypeDao.findById(2L);
        System.out.println(maybeUserType);

        maybeUserType.ifPresent(userType -> {

            userType.setRole(RoleDao.getInstance().findById(1).get());
//            userType.setNumber("8-915-736-99-00");
            userTypeDao.update(userType);
        });
        System.out.println(maybeUserType);
    }


    private void filterUserType() {
        var userTypeFilter = new UserTypeFilter(2, 0, "Зотов", "8-");

        var userTypes = UserTypeDao.getInstance().findAll(userTypeFilter);
        System.out.println(userTypes);
    }
}
