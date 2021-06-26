package nl.cc.task.service;

import nl.cc.task.model.UserDetailsDTO;
import nl.cc.task.repository.CustomUserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    public final CustomUserRepository customUserRepository;

    public CustomUserDetailsService(CustomUserRepository customUserRepository) {
        this.customUserRepository = customUserRepository;
    }

    @Override
    public UserDetailsDTO loadUserByUsername(String userName) throws UsernameNotFoundException {
        return customUserRepository.findByUserName(userName).map(UserDetailsDTO::new).orElseThrow();
    }
}
