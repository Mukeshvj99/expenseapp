package com.mukesh.expenseapp.Service;

import com.mukesh.expenseapp.Repository.Users;

public interface UserService {

    Users createUser(Users user);

    Boolean ExistsByEmail(String email);

    Users getUserByEmail(String email);
    Users getUserById(long id);

    Users updateUsers(long id,Users user);

   Users deleteUsers(long id);
}
