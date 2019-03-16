package com.jnshu.dreamteam.config.shiro;

import com.auth0.jwt.interfaces.Claim;
import com.jnshu.dreamteam.config.exception.ServiceDaoException;
import com.jnshu.dreamteam.pojo.Module;
import com.jnshu.dreamteam.pojo.ParentModule;
import com.jnshu.dreamteam.pojo.User;
import com.jnshu.dreamteam.service.ModuleService;
import com.jnshu.dreamteam.service.RoleService;
import com.jnshu.dreamteam.service.UserService;
import com.jnshu.dreamteam.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class MyRealm extends AuthorizingRealm {

    @Resource
    private ModuleService moduleService;
    @Resource
    private UserService userService;

    /**
     * 大坑！，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) throws SecurityException{
        //身份认证方法
        Claim claim = JwtUtil.getClaims(principals.toString(),"roleName");
        String roleName = claim.asString();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        try {
            List<ParentModule> parentModuleList = moduleService.selectModuleByRoleName(roleName);
            for (ParentModule parentModule : parentModuleList) {
                List<Module> moduleList = parentModule.getModuleList();
                for (Module module : moduleList) {
                    simpleAuthorizationInfo.addStringPermission(module.getModule());
                }
            }
        }catch (ServiceDaoException e){
            throw new SecurityException("该账号无可查看模块");
        }
        return simpleAuthorizationInfo;
    }

    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();
        // 解密获得username，用于和数据库进行对比
        Claim claim = JwtUtil.getClaims(token,"userId");
        if(claim==null){
            throw new AuthenticationException("token无效");
        }
        Long userId = claim.asLong();
        User user = userService.selectUserById(userId);
        if(user==null){
            throw new AuthenticationException("该用户不存在");
        }
        if(!JwtUtil.verify(token)){
            throw new AuthenticationException("token错误或已过期");
        }
        return new SimpleAuthenticationInfo(token, token, "my_realm");
    }



}
