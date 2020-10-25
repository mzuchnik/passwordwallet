package pl.mzuchnik.mypasswordwallet.service;

import pl.mzuchnik.mypasswordwallet.entity.User;



public interface UserService {

    User findByLogin(String login);

    void save(User user);

    void saveUserWithPasswordEncoder(User user, String passwordEncoder);

}
