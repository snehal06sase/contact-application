package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Contact;

@Repository
public interface ContactRepo extends JpaRepository<Contact, Long> {

}
