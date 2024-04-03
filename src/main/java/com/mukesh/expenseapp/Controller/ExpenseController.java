package com.mukesh.expenseapp.Controller;

import com.mukesh.expenseapp.Service.ExpenseServiceImpl;
import com.mukesh.expenseapp.Repository.Expenses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import org.springframework.web.bind.annotation.*;


import java.sql.Date;
import java.util.List;

@RestController
public class ExpenseController {
    @Autowired

    ExpenseServiceImpl service;

    @GetMapping("/expenses")
    public List<Expenses> getAllExpenses(@RequestParam("userid") long userid, Pageable page){

        return service.getAllExpenses(userid,page);
    }
    @GetMapping("/expenses/{id}")
    public Expenses getExpenses(@RequestParam("userid") long userid,@PathVariable("id") Long id){

         return service.getExpenses(userid,id);
    }

    @GetMapping("/expenses/category")
    public List<Expenses> getAllExpensesByCategory(@RequestParam("userid") long userid,@RequestParam("category") String category, Pageable page){

        return service.filterCategory(userid,category,page);

    }

    @GetMapping("/expenses/name")
    public List<Expenses> getAllExpensesByName(@RequestParam("userid") long userid,@RequestParam("name") String name, Pageable page){

        return service.findByNameContaining(userid,name,page);
    }

    @GetMapping("/expenses/date")
    public List<Expenses> getAllExpensesByDate(@RequestParam("userid") long userid,@RequestParam(value = "startdate" ,required = true) Date start, @RequestParam(value = "enddate",required = false) Date end, Pageable page){

        return service.findByStartDateBetween(userid,start,end,page);
    }
    @DeleteMapping("/expenses/{id}")
    public String deleteExpenses(@RequestParam("userid") long userid,@PathVariable("id") long id){
        return service.deleteById(userid,id);
    }

    @PostMapping("/expenses")

    public Expenses addExpenses(@Valid @RequestBody  Expenses expense,@RequestParam("userid") long userid){

        return service.addExpenses(userid,expense);
    }

    @PutMapping("/expenses/{id}")
    public Expenses UpdateExpenses(@PathVariable("id") long id,@RequestBody Expenses exp,@RequestParam("userid") long userid){
        return service.updateExpenses(userid,id, exp);
    }
}
