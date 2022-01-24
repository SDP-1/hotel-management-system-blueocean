package db;

import module.User;

import java.util.ArrayList;

public class UserDatabase {
    public static ArrayList<User> users = new ArrayList<User>();

    static {
        users.add(new User("Sehan Devinda","102123232","2001/01/04","sehan","1234",1));
        users.add(new User("Sehan Devinda","102123232","2001/01/04","","",1));
        users.add(new User("10212344","2006/01/04","nimal","1234",1));
        users.add(new User("Pasan Sandipa","322524525","2005/01/04","pasan","1234",0));
        users.add(new User("24636354545","2007/01/04","kasun","1234","",0));

    }
}
