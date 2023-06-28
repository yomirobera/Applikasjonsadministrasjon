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
        Stilling savedStilling = stillingRepository.save(entity);
        for (User u : entity.getUsers()) {



            u.getStilling().add(savedStilling);
            userRepository.save(u);

            /*Set<Stilling> stilling = u.getStilling();
            stilling.add(entity);
            u.setStilling(stilling);
            userRepository.save(u);*/
        }
        return savedStilling;
    }


    public void update(Stilling entity) {
        //


        stillingRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        Stilling stilling = stillingRepository.findById(id).orElseThrow(() -> new StillingNotFoundException(id));
        for (User u : stilling.getUsers()) {
            u.getStilling().remove(stilling);
        }
        User madeBy=stilling.getMadeByUser();
        madeBy.getMadePositions().remove(stilling);
        stillingRepository.deleteById(id);
    }









}
