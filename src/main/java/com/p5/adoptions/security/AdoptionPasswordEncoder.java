package com.p5.adoptions.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdoptionPasswordEncoder extends BCryptPasswordEncoder {


}
