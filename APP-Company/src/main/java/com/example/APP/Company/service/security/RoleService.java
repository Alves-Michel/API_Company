package com.example.APP.Company.service.security;

import com.example.APP.Company.domain.dto.security.RoleDTO;
import com.example.APP.Company.domain.entity.security.role.Role;
import com.example.APP.Company.repository.users.security.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public ResponseEntity createdRole(@RequestBody RoleDTO body) {
        Optional<Role> role = this.roleRepository.findByName(body.name());

        if (role.isEmpty()) {
            Role newRole = new Role();
            newRole.setName(body.name());
            roleRepository.save(newRole);

            return ResponseEntity.ok().body(newRole);

        }
        return ResponseEntity.badRequest().build();

    }

    public List<RoleDTO> finAllRoles() {
        return roleRepository.findAll().stream()
                .map(role -> new RoleDTO(
                       role.getId(), role.getName()
                )).collect(Collectors.toList());
    }
}
