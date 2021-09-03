package lms.co.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import lms.co.model.User;

@Component
public class UsersRepository {
    HashMap<String, User> users = new HashMap<>();

    public void addUser(User user){

        users.put(user.getUserid(), user);
    }

    public User getUser(String id){
        return users.get(id);
    }

    public List<User> getAllUsers(){
        return new ArrayList<>(users.values());
    }

    public Boolean isUserAvailable(String userId) {
        return users.containsKey(userId);
    }

    public Boolean removeUser(String userId) {
        if (users.containsKey(userId)){
            users.remove(userId);
            return true;
        }else{
            return false;
        }
    }
}
