package com.example.DAR.Repository;

import com.example.DAR.Model.HomeItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeItemRepository extends JpaRepository<HomeItem, Integer> {

    HomeItem findHomeItemById(Integer id);

    List<HomeItem> findHomeItemsByHomeId(Integer homeId);
}
