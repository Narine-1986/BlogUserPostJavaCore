package blogPost.Interface;

import blogPost.exception.ModelNotFoundException;
import blogPost.models.User;

public interface UserStorage {

    void add(User user);

    User getUserByEmail(String email) throws ModelNotFoundException;

    User getUserByEmailAndPassword(String email, String password) throws ModelNotFoundException;

    void printAllUsers();


}
