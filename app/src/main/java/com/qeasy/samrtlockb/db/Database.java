package com.qeasy.samrtlockb.db;

import com.qeasy.samrtlockb.bean.User;

/**
 * Created by fancl.
 * 数据库接口类
 */

public interface Database {



    public void addUser(User user);
    
    public int updateUser(User user);

    public User getUser();

    public void removeUser();

}
