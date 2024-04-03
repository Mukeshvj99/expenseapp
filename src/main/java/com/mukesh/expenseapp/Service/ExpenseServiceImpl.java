package com.mukesh.expenseapp.Service;

import com.mukesh.expenseapp.Exceptions.ResourceNotFoundException;
import com.mukesh.expenseapp.Repository.ExpenseRepository;
import com.mukesh.expenseapp.Repository.Expenses;
import com.mukesh.expenseapp.Repository.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    ExpenseRepository expense;

    @Autowired
    UserServiceImpl userservice;


    public List<Expenses> filterCategory(long userid,String category, Pageable page){
        Users user=userservice.getUserById(userid);
        return expense.findByUserIdAndCategory(userid,category,page).stream().toList();
    }

    @Override
    public List<Expenses> findByNameContaining(long userid,String name, Pageable page) {
        Users user=userservice.getUserById(userid);
        return expense.findByUserIdAndNameContaining(userid,name,page);
    }

    @Override
    public List<Expenses> findByStartDateBetween(long userid,Date start, Date end, Pageable page) {
        Users user=userservice.getUserById(userid);
        if(end==null){
            end=new Date(System.currentTimeMillis());
        }
         return expense.findByUserIdAndDateBetween(userid,start,end ,page);
    }

    @Override
    public List<Expenses> getAllExpenses(long userid,Pageable page) {

        Users user=userservice.getUserById(userid);
        return expense.findByUserId(userid,page).stream().toList();
    }



    public Expenses getExpenses(long userid,long id){

        Users user=userservice.getUserById(userid);
       Expenses exp= expense.findByUserIdAndId(userid,id);
       if(exp==null){
           throw new ResourceNotFoundException("Expense id is Not Found for this User - "+userid);
       }
        return exp;
    }

    public String deleteById(long userid,long id){
        Users user=userservice.getUserById(userid);
        Expenses exp= getExpenses(userid,id);

        System.out.println("Expensess --------"+exp);
       Optional<Expenses> expe=expense.findById(id);
       exp=expe.get();
       System.out.println("Expensess --------"+exp);

         if(exp!=null){

             expense.delete(exp);
             return "success";
         }
        return "not data exist";
    }

    public Expenses addExpenses(long userid,Expenses exp){
        Users user=userservice.getUserById(userid);
        exp.setUser(user);
        return expense.save(exp);
    }

    public Expenses updateExpenses(long userid,long id,Expenses exp){
        Users user=userservice.getUserById(userid);
         Expenses existing= getExpenses(userid,id);
         existing.setName((exp.getName()!=null)?exp.getName():existing.getName());
         existing.setDescription((exp.getDescription()!=null)?exp.getDescription(): existing.getDescription());
         existing.setAmount((exp.getAmount()!=0.0)?exp.getAmount():existing.getAmount());
         existing.setCategory((exp.getCategory()!=null)? exp.getCategory() : existing.getCategory());
         existing.setDate((exp.getDate()!=null)? exp.getDate():existing.getDate());
         existing.setUser(user);
         return expense.save(existing);
    }
}
