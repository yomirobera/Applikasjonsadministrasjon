package com.example.applikasjonsadministrasjon.services.stilling;

import com.example.applikasjonsadministrasjon.models.Stilling;
import com.example.applikasjonsadministrasjon.models.User;
import com.example.applikasjonsadministrasjon.repositories.StillingRepository;
import com.example.applikasjonsadministrasjon.repositories.UserRepository;
import com.example.applikasjonsadministrasjon.utils.exceptions.StillingNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

@Service
public class StillingServiceImpl implements StillingService{

    private final StillingRepository stillingRepository;

    private final UserRepository userRepository;

    public StillingServiceImpl(StillingRepository stillingRepository, UserRepository userRepository) {
        this.stillingRepository = stillingRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Stilling findById(Integer id) {
        return stillingRepository.findById(id).orElseThrow(() -> new StillingNotFoundException(id));
    }

    @Override
    public Collection<Stilling> findAll() {
        return stillingRepository.findAll();
    }


    @Override
    public Stilling add(Stilling entity) {
        return null;
    }

    @Override
    public void update(Stilling entity) {

    }

    @Override
    public void deleteById(Integer integer) {

    }

}
