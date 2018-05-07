package ghistayumna.com.myapplication.Implement;

import ghistayumna.com.myapplication.Dao.UserDao;
import ghistayumna.com.myapplication.Model.User.ModelUser;

public class IUserDao implements UserDao {
    @Override
    public int login(ModelUser modelUser) {
        return 0;
    }

    @Override
    public String getUserEmail(String email) {

        return null;
    }

    @Override
    public String insertUser(ModelUser modelUser) {
        return null;
    }
}
