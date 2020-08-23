package com.challenge.repository;

import com.challenge.entity.Acceleration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AccelerationRepository extends JpaRepository<Acceleration, Long> {

    List<Acceleration> findAll();

    @Query(value = "SELECT * FROM  acceleration " +
            "WHERE name like %:name%", nativeQuery = true)
    Optional<Acceleration> findAccelerationByName(@Param("name") String name);

    @Query(value = "SELECT ac.id, ac.created_at, ac.name, ac.slug, ac.challenge_id FROM acceleration as ac\n" +
            "INNER JOIN candidate as ca " +
            "ON (ac.id = ca.acceleration_id) " +
            "INNER JOIN company as co " +
            "ON (ca.company_id = co.id) " +
            "WHERE co.id = :companyId", nativeQuery = true)
    List<Acceleration> findByCompanyId(@Param("companyId") Long companyId);
}
