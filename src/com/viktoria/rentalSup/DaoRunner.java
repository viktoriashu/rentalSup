package com.viktoria.rentalSup;

import com.viktoria.rentalSup.dao.impl.*;
import com.viktoria.rentalSup.dto.UserTypeFilter;
import com.viktoria.rentalSup.entity.*;
import com.viktoria.rentalSup.dao.impl.ClaimDao;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DaoRunner {

    public static void main(String[] args) {

//        new DaoRunner().saveRole();
//        new DaoRunner().updateRole();
//        new DaoRunner().deleteRole();

//        new DaoRunner().deleteUserType();
//        new DaoRunner().saveUserType();
//        new DaoRunner().updateUserType();

//        new DaoRunner().filterUserType();

//        new DaoRunner().saveStatusSup();
//        new DaoRunner().deleteStatusSup();
//        new DaoRunner().updateStatusSup();

//        new DaoRunner().saveSup();
//        new DaoRunner().deleteSup();
        //проверить выдает ошибку
//        new DaoRunner().updateSup();

//        new DaoRunner().saveStatusClaim();
//        new DaoRunner().deleteStatusClaim();
//        new DaoRunner().updateStatusClaim();

//        new DaoRunner().saveClaim();
//        new DaoRunner().deleteClaim();
        //проверить выдает ошибку
//        new DaoRunner().updateClaim();


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


    private void saveStatusSup() {
        var statusSupDao = StatusSupDao.getInstance();
        var statusSup = new StatusSup();
        statusSup.setStatus("reserve");

        var savedStatusSup = statusSupDao.save(statusSup);
        System.out.println(savedStatusSup);
    }

    private void deleteStatusSup() {
        var statusSupDao = StatusSupDao.getInstance();
        var deleteResult = statusSupDao.delete(4);
        System.out.println(deleteResult);
    }

    private void updateStatusSup() {
        var statusSupDao = StatusSupDao.getInstance();
        var maybeStatusSup = statusSupDao.findById(3);
        System.out.println(maybeStatusSup);

        maybeStatusSup.ifPresent(statusSup -> {
            statusSup.setStatus("busy");
            statusSupDao.update(statusSup);
        });
        System.out.println(maybeStatusSup);
    }


    private void deleteSup() {
        var supDao = SupDao.getInstance();
        var deleteResult = supDao.delete(7L);
        System.out.println(deleteResult);
    }


    private void saveSup() {
        var supDao = SupDao.getInstance();
        var sup = new Sup();
        sup.setModel("Red Paddle 13'2×30 Voyager Plus");
        sup.setStatusSup(StatusSupDao.getInstance().findById(1).get());

        var savedUserType = supDao.save(sup);
        System.out.println(savedUserType);
    }

    private void updateSup() {
        var supDao = SupDao.getInstance();
        var maybeSup = supDao.findById(8L);
        System.out.println(maybeSup);

        maybeSup.ifPresent(sup -> {
            sup.setModel("Molokai Touring Finder Air 11'6");
//            sup.setStatusSup(StatusSupDao.getInstance().findById(1).get());
            supDao.update(sup);
        });
        System.out.println(maybeSup);
    }


    private void saveStatusClaim() {
        var statusClaimDao = StatusClaimDao.getInstance();
        var statusClaim = new StatusClaim();
        statusClaim.setStatus("close");

        var savedStatusClaim = statusClaimDao.save(statusClaim);
        System.out.println(savedStatusClaim);
    }

    private void deleteStatusClaim() {
        var statusClaimDao = StatusClaimDao.getInstance();
        var deleteResult = statusClaimDao.delete(5);
        System.out.println(deleteResult);
    }

    private void updateStatusClaim() {
        var statusClaimDao = StatusClaimDao.getInstance();
        var maybeStatusClaim = statusClaimDao.findById(3);
        System.out.println(maybeStatusClaim);

        maybeStatusClaim.ifPresent(statusClaim -> {
            statusClaim.setStatus("paid");
            statusClaimDao.update(statusClaim);
        });
        System.out.println(maybeStatusClaim);
    }


    private void saveClaim() {
        var claimDao = ClaimDao.getInstance();
        var claim = new Claim();
        claim.setClient(UserTypeDao.getInstance().findById(3L).get());
        claim.setAdmin(UserTypeDao.getInstance().findById(2L).get());
        claim.setSup(SupDao.getInstance().findById(1L).get());
        claim.setStatusClaim(StatusClaimDao.getInstance().findById(1).get());
        claim.setDataStartRent(LocalDate.of(2024, 6, 13));
        claim.setDurationRent(1);
        claim.setPrice(BigDecimal.valueOf(1100));

        var savedClaim = claimDao.save(claim);
        System.out.println(savedClaim);
    }


    private void deleteClaim() {
        var claimDao = ClaimDao.getInstance();
        var deleteResult = claimDao.delete(5L);
        System.out.println(deleteResult);
    }

    private void updateClaim() {
        var claimDao = ClaimDao.getInstance();
        var maybeClaim = claimDao.findById(4L);
        System.out.println(maybeClaim);

        maybeClaim.ifPresent(claim -> {

            claim.setDataStartRent(LocalDate.of(2024, 6, 15));

            claimDao.update(claim);
        });
        System.out.println(maybeClaim);
    }
}
