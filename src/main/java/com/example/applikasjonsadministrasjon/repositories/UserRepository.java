package com.example.applikasjonsadministrasjon.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.applikasjonsadministrasjon.models.tables.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
