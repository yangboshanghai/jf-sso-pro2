package com.ouyeelf.Repository;

import com.ouyeelf.dbentity.SdUser;
import com.ouyeelf.dbentity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SdUserRepository extends JpaRepository<SdUser,String> {

    @Query(value = "select * from tuser where username=? and password=?",nativeQuery = true)
    User QueryByUserName(String userName,String password);


}
