package com.mukesh.expenseapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expenses,Long> {

     List<Expenses> findByUserIdAndCategory(long id,String category, Pageable page);

     List<Expenses> findByUserIdAndNameContaining(long id,String name,Pageable page);

     List<Expenses> findByUserIdAndDateBetween(long id,Date start, Date end, Pageable page);

     List<Expenses> findByUserId(long id,Pageable page);

     Expenses findByUserIdAndId(long userid,long id);
}
