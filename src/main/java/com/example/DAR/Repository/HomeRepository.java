package com.example.DAR.Repository;

import com.example.DAR.Model.Home;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeRepository extends JpaRepository<Home, Integer> {

    Home findHomeById(Integer id);

    List<Home> findHomesByUserId(Integer userId);
}
