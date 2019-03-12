package com.jnshu.dreamteam.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jnshu.dreamteam.pojo.Response;
import com.jnshu.dreamteam.pojo.User;
import com.jnshu.dreamteam.service.UserService;
import com.jnshu.dreamteam.utils.EmptyUtil;
import com.jnshu.dreamteam.utils.JwtUtil;
import com.jnshu.dreamteam.utils.ValidatedUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


@Log4j2
@RestController
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 添加后台账号，目前还没做权限
     * @param user 对user参数校验
     * @return
     */
    @PostMapping("/a/u/user")
    public Response registerUser(@Validated @RequestBody User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ValidatedUtil.errorInfo(bindingResult);
        }
        Long id = userService.insertUserOne(user);
        return id!=null?Response.ok():Response.error();
    }

    /**
     * 更新后台用户信息
     * @param user 对user对象参数校验
     * @param bindingResult 校验结果集
     * @return
     */
    @PutMapping("/a/u/user")
    public Response updateUser(@Validated @RequestBody User user, BindingResult bindingResult){
        log.info("入参为"+user);
        if(bindingResult.hasErrors()){
            return ValidatedUtil.errorInfo(bindingResult);
        }
        return userService.updateUserOne(user)!=null?Response.ok():Response.error();
    }

    /**
     * 根据用户ID删除
     * @param id 用户ID
     * @return
     */
    @DeleteMapping("/a/u/user/{id}")
    public Response deleteUser(@PathVariable("id") Long id){
         return userService.deleteUserById(id)>0?Response.ok():Response.error();
    }

    /**
     * 根据前端传入的参数动态进行查询，如果account和roleName都传了，则按照account内容查询
     *
     * @param page 查询第几页 可不填
     * @param size 每页显示多少数据 可不填
     * @param roleName 根据角色名查询后台用户 可不填
     * @param account 根据用户名查询用户 可不填
     * @return
     */
    @GetMapping("/a/u/user")
    public Response selectUserByParam(@RequestParam(value = "page",required = false) Integer page
                                     ,@RequestParam(value = "size",required = false) Integer size
                                     ,@RequestParam(value = "roleName",required = false) String roleName
                                     ,@RequestParam(value = "account",required = false) String account){
        IPage<User> data = userService.selectAll(page,size,roleName,account);
        return data==null?Response.error():new Response<>(200,"查询成功",data);
    }


    /**
     * 前端传入用户名和密码，验证密码是否正确，若正确则生成token放入响应头中返回，若密码不对，则返回错误
     *
     * @param account 账号
     * @param password 密码
     * @param httpServletResponse 响应
     * @return
     */
    @PostMapping("/a/login")
    public Response login(@RequestParam("account") String account, @RequestParam("password") String password
                         ,HttpServletResponse httpServletResponse){
        Map<String,Object> map = userService.validatePassword(account,password);
        if(!EmptyUtil.isEmpty(map)){
            String token = JwtUtil.createToken(map);
            httpServletResponse.setHeader("token",token);
            return new Response(200,"登录成功");
        }
        return new Response(100,"账号不存在或密码错误");
    }



}
