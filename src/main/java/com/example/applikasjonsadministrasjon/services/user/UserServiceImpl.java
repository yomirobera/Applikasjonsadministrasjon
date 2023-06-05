package com.example.applikasjonsadministrasjon.services.user;

import com.example.applikasjonsadministrasjon.models.Stilling;
import com.example.applikasjonsadministrasjon.models.User;
import com.example.applikasjonsadministrasjon.repositories.StillingRepository;
import com.example.applikasjonsadministrasjon.repositories.UserRepository;
import com.example.applikasjonsadministrasjon.utils.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final StillingRepository stillingRepository;

    public UserServiceImpl(UserRepository userRepository, StillingRepository stillingRepository) {
        this.userRepository = userRepository;
        this.stillingRepository = stillingRepository;
    }


    @Override
    public User findById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public Collection<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User add(User entity) {
        return userRepository.save(entity);
    }

    @Override
    public void update(User entity) {
        userRepository.save(entity);
    }



    @Override
    public void deleteById(String s) {

    }

    @Override
    public Set<Stilling> findAllStilling(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return user.getStilling();
    }
}
