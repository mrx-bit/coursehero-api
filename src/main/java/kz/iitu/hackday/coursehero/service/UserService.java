package kz.iitu.hackday.coursehero.service;


import kz.iitu.hackday.coursehero.entity.User;

public interface UserService {

    //	List<User> getAll();
//	User getByUsername(String username);
	User create(User user);
    User getUserByToken(String token);
    void sessionExpired(String token);
}
