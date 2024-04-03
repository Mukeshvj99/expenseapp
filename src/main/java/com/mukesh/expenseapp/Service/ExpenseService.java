package com.mukesh.expenseapp.Service;

import com.mukesh.expenseapp.Repository.Expenses;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


public interface ExpenseService {

    List<Expenses> getAllExpenses(long userid,Pageable page);

    Expenses getExpenses(long userid,long id);

    String deleteById(long userid,long id);

    Expenses addExpenses(long userid,Expenses ex);

    Expenses updateExpenses(long userid,long id,Expenses ex);

    List<Expenses> filterCategory(long userid,String category, Pageable page);

    List<Expenses> findByNameContaining(long userid,String name,Pageable page);

    List<Expenses> findByStartDateBetween(long userid,Date start, Date end, Pageable page);
}
