package com.deliveryfood.ahmed.controller;


import com.deliveryfood.ahmed.config.jwt.JwtUtils;
import com.deliveryfood.ahmed.config.service.UserDetailsImpl;
import com.deliveryfood.ahmed.dto.JwtResponse;
import com.deliveryfood.ahmed.dto.LoginDto;
import com.deliveryfood.ahmed.dto.PersonDto;
import com.deliveryfood.ahmed.entity.ERole;
import com.deliveryfood.ahmed.entity.Role;
import com.deliveryfood.ahmed.repository.RoleRepository;
import com.deliveryfood.ahmed.service.PersonService;
import com.deliveryfood.ahmed.utils.MessageResponse;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
    public final PersonService personService;

    public final RoleRepository roleRepository;

    public final AuthenticationManager authenticationManager;

    public final JwtUtils jwtUtils;

    public AuthController(PersonService personService, RoleRepository roleRepository, AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.personService = personService;
        this.roleRepository = roleRepository;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> createPerson(@RequestBody PersonDto personDto) throws URISyntaxException {
        if (personService.existsByEmail(personDto.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Email: "+ personDto.getEmail()+" already exists, please try another or sign in"));
        }
        if (personService.existsByUsername(personDto.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Username: "+ personDto.getUsername()+" already exists, please try another or sign in"));
        }
        Set<String> roles = personDto.getRole();
        Set<Role> userRoles = new HashSet<>();
        if (roles == null) {
                userRoles.add(roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException("Error: Role is Not found")));
        } else {
            roles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        userRoles.add(adminRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        userRoles.add(userRole);
                }
            });
        }
        return ResponseEntity.created(new URI("/api/v1/auth/signup")).body(personService.createPerson(personDto, userRoles));
    }
}
