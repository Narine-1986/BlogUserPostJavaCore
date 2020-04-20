package blogPost.blogStorageImpl;

import blogPost.Interface.UserStorage;
import blogPost.exception.ModelNotFoundException;
import blogPost.models.User;

public class UserStorageImpl implements UserStorage {
    private User[] users = new User[15];
    private int size = 0;

    @Override
    public void add(User user) {
        if (size == users.length) {
            extend();
        }
        users[size++] = user;

    }

    private void extend() {
        User[] tmp = new User[users.length + 10];
        System.arraycopy(users, 0, tmp, 0, users.length);
        users = tmp;

    }

    @Override
    public User getUserByEmail(String email) throws ModelNotFoundException {

        for (int i = 0; i < size; i++) {
            if (users[i].getEmail().equals(email)) {
                return users[i];
            }
        }
        throw new ModelNotFoundException("Don't found that user");

    }


    @Override
    public User getUserByEmailAndPassword(String email, String password) throws ModelNotFoundException {
        for (int i = 0; i < size; i++) {
            if (users[i].getEmail().equals(email) && users[i].getPassword().equals(password)) {
                return users[i];
            }
        }
        throw new ModelNotFoundException("Don't found that user");


    }

    public boolean isEmpty() {
        return size == 0;
    }


    @Override
    public void printAllUsers() {
        for (int i = 0; i < size; i++) {
            System.out.println(users[i]);

        }

    }


}
