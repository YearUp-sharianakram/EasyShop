package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.data.ProfileDao;
import org.yearup.data.UserDao;
import org.yearup.models.Profile;
import org.yearup.models.User;

import java.security.Principal;

@RestController
@RequestMapping("profile")
@CrossOrigin
public class ProfileController {

    private final ProfileDao profileDao;
    private final UserDao userDao;

    @Autowired
    public ProfileController(ProfileDao profileDao, UserDao userDao) {
        this.profileDao = profileDao;
        this.userDao = userDao;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public Profile getProfile(Principal principal) {
        validatePrincipal(principal);

        String username = principal.getName();
        User user = fetchUserByUsername(username);

        Profile profile = profileDao.getProfileByUserId(user.getId());
        if (profile == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile not found.");
        }

        return profile;
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping
    public Profile updateProfile(@RequestBody Profile profile, Principal principal) {
        validatePrincipal(principal);

        String username = principal.getName();
        User user = fetchUserByUsername(username);

        profile.setUserId(user.getId());
        return profileDao.update(profile);
    }

    private void validatePrincipal(Principal principal) {
        if (principal == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not authenticated.");
        }
    }

    private User fetchUserByUsername(String username) {
        User user = userDao.getByUserName(username);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found.");
        }
        return user;
    }
}