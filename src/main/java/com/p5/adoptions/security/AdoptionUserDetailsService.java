package com.p5.adoptions.security;

import com.p5.adoptions.repository.users.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class AdoptionUserDetailsService implements UserDetailsService
{
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AdoptionPasswordEncoder adoptionPasswordEncoder;

    public AdoptionUserDetailsService(UserRepository repository, RoleRepository roleRepository, AdoptionPasswordEncoder adoptionPasswordEncoder) {
        this.userRepository = repository;
        this.roleRepository = roleRepository;
        this.adoptionPasswordEncoder = adoptionPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByEmail(username);
        if(!userOptional.isPresent()){
            throw new UsernameNotFoundException(username);
        }

        return new AdoptionUserPrincipal(userOptional.get());
    }

    @Bean
    private CommandLineRunner setUpDefaultUser(){
        return args -> {
            final String defaultEmail ="costinsuhan@mail.io";
            final String defaultPassword="password";

            Role moderatorRole = roleRepository.findByRole(RoleEnum.ROLE_MOD).orElseGet(()->{
                Role role = new Role().setRole(RoleEnum.ROLE_MOD);
                return roleRepository.save(role);
            });

            userRepository.findByEmail(defaultEmail).orElseGet(()->{
               User user = new User().setEmail(defaultEmail)
                        .setPassword(adoptionPasswordEncoder.encode(defaultPassword))
                        .setRoles(Collections.singleton(moderatorRole));
               return userRepository.save(user);
            });
        };
    }
}
