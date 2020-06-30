package com.debug.kill.server.service;

import com.debug.kill.model.entity.User;
import com.debug.kill.model.mapper.UserMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

/**
 * @author User
 * @Title: CustomRealm
 * @ProjectName kill
 * @Description: 用户自定义的realm-用户shiro的认证、授权
 * @date 2020/6/30 18:15
 */
public class CustomRealm extends AuthorizingRealm {

    public static final Logger log = LoggerFactory.getLogger(CustomRealm.class);

    public static final Long sessionKeyTimeOut = 300000L;

    @Autowired
    private UserMapper userMapper;

    /**
     * 授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /**
     * 认证-登录
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername();
        String password = String.valueOf(usernamePasswordToken.getPassword());
        log.info("当前登录的用户名={}，密码={}",username,password);

        User user = userMapper.selectByUserNamePsd(username, password);
        if (user == null) {
            throw new UnknownAccountException("用户名不存在！");
        }
        if (!Objects.equals(1,user.getIsActive().intValue())) {
            throw new DisabledAccountException("当前用户已被禁用！");
        }
        if (!Objects.equals(password,user.getPassword())) {
            throw new IncorrectCredentialsException("用户名密码不匹配！");
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getUserName(),password,getName());


        return info;
    }

    /**
     * 将key与相应的value塞入shiro的session中，最终交给HttpSession进行管理
     */
    private void setSession(String key,Object value){
        Session session = SecurityUtils.getSubject().getSession();
        if (session != null) {
            session.setAttribute(key,value);
            session.setTimeout(sessionKeyTimeOut);
        }
    }
}
