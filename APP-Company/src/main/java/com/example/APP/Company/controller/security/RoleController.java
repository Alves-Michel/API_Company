package com.example.APP.Company.controller.security;

import com.example.APP.Company.domain.dto.security.RoleDTO;
import com.example.APP.Company.service.security.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/register")
    public ResponseEntity<RoleDTO> registerRole(@RequestBody RoleDTO body){
        return roleService.createdRole(body);
    }

    @GetMapping("/list")
    public ResponseEntity<List<RoleDTO>> findAllRoles(){
        var roles = roleService.finAllRoles();
        return ResponseEntity.ok().body(roles);
    }
}
