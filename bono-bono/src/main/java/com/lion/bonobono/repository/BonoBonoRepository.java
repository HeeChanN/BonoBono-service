package com.lion.bonobono.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BonoBonoRepository extends JpaRepository<Member,Long> {
    Optional<Member> findByUrl(String url);
}
