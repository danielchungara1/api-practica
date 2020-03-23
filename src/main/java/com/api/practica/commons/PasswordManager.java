package com.api.practica.commons;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordManager extends BCryptPasswordEncoder{

}
