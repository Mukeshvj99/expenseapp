package com.mukesh.expenseapp.Service;

import com.mukesh.expenseapp.Exceptions.UserAlreadyExistException;
import com.mukesh.expenseapp.Exceptions.UserNotFoundException;
import com.mukesh.expenseapp.Repository.UserRepository;
import com.mukesh.expenseapp.Repository.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userrepo;
    public Users createUser(Users user){

        if(ExistsByEmail(user.getEmail())){
            throw new UserAlreadyExistException("User Already Exists ...");
        }

          return  userrepo.save(user);

    }

    @Override
    public Boolean ExistsByEmail(String email) {

        return userrepo.existsByEmail(email);
    }

    public Users getUserById(long id){
        Optional<Users> s=userrepo.findById(id);

        if(s.isEmpty()){
            throw new UserNotFoundException("Users is not found ...");
        }
        return s.get();
    }

    public Users getUserByEmail(String email){
        if(ExistsByEmail(email)){

            return userrepo.findByEmail(email);
        }
        throw new UserNotFoundException("Users is not found for given Email");
    }

    public Users updateUsers(long id,Users user){
        Users curruser=getUserById(id);
         curruser.setAge((user.getAge()>0)? user.getAge() : curruser.getAge());
         curruser.setName((user.getName()!=null)?user.getName(): curruser.getName());
         curruser.setEmail((user.getEmail()!=null)?user.getEmail():curruser.getEmail());
         curruser.setPassword((user.getPassword()!=null)?user.getPassword():curruser.getPassword());

         return userrepo.save(curruser);
    }

    @Override
    public Users deleteUsers(long id) {
        Users user=getUserById(id);
         userrepo.deleteById(id);
         return user;
    }
}
