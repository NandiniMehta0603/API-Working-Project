package com.finance.transactions.repository;

import com.finance.transactions.kirana.MyKirana;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface KiranaRepository extends JpaRepository<MyKirana,Integer> {
//    @Query("select u FROM User u")
//    public List<User> getAllUser();

//    @Query("select u from Kirana where u.id :=i")
//    List<MyKirana> findById(@Param("i") Integer id);

    Optional<MyKirana> findById(Integer id);
    @Query("SELECT MAX(id) FROM MyKirana")
    int findLastId();

//    @Query(value = "SELECT * FROM transaction where monthname(date)=this_month",nativeQuery = true)
//    List<MyKirana> findByMonth(@Param("this_month") String this_month);

    @Query(value = "SELECT * FROM transaction WHERE DATE_FORMAT(date, '%M') = :month", nativeQuery = true)
    List<MyKirana> findByMonth(@Param("month") String month);

    @Query(value="select * from transaction where type_of_trans =:type", nativeQuery = true)
    List<MyKirana> findByType(@Param("type") String type);

    List<MyKirana> findByDate(LocalDate date);
}
