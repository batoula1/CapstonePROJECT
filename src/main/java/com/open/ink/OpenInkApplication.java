package com.open.ink;

import com.open.ink.entities.Role;
import com.open.ink.repositories.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class OpenInkApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(OpenInkApplication.class, args);
    }

    @Autowired
    private RoleRepo roleRepo;

    @Override
    public void run(String... args) throws Exception {

        Role roleNormal = new Role();
        roleNormal.setId(1001);
        roleNormal.setRole("ROLE_NORMAL");

        Role roleAdmin = new Role();
        roleAdmin.setId(1002);
        roleAdmin.setRole("ROLE_ADMIN");

        List<Role> roles = List.of(roleNormal, roleAdmin);

        roleRepo.saveAll(roles);
    }

}
