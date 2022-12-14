package com.ouyeelf.Repository;

import com.ouyeelf.dbentity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    @Query(value = "select * from t_user where user_name=? and password=?",nativeQuery = true)
    User QueryByUserName(String userName,String password);


}
