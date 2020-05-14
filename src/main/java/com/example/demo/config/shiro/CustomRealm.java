package com.example.demo.config.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashSet;

/**
 * Created by Administrator on 2020/4/18.
 */
public class CustomRealm extends AuthorizingRealm {

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        HashSet<String> set = new HashSet<>();
        set.add("user:show");
        set.add("user:list");
        set.add("user:admin");
        info.setStringPermissions(set);
        return info;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        String userPwd = new String((char[]) authenticationToken.getCredentials());
        String password = "123";
        if (username == null){
            throw new AccountException("用户名不正确");
        }else if (!userPwd.equals(password)){
            throw new AccountException("密码不正确");
        }
        // 密码进行加密过后 下面这行就不适用了
        // return new SimpleAuthenticationInfo(username,password,getName());
        // 加密后要改成这样的
        return new SimpleAuthenticationInfo(username,password, ByteSource.Util.bytes(username+"salt"),getName());
    }
}
