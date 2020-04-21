package com.winer.controller;

import com.winer.entity.User;
import com.winer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/findAll")
    public Collection<User> findAll(){
        return userRepository.findAll() ;
    }

    @GetMapping("/findAllPage/{currentPage}/{pageSize}")
    public  Page<User> findAllPage(@PathVariable("pageSize") int pageSize,@PathVariable("currentPage") int currentPage){
        /*Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(currentPage - 1, pageSize, sort);
        User user =new User();
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withMatcher("id",ExampleMatcher.GenericPropertyMatchers.exact());
        Example<User> example = Example.of(user,exampleMatcher);
        Page<User> page = userRepository.findAll(example,pageable);*/
        PageRequest request = PageRequest.of(currentPage,pageSize);
        Page<User> pageuser =  userRepository.findAll(request);
        return pageuser ;

    }

    @PostMapping("/save")
    public String save(@RequestBody User user){
        User result = userRepository.save(user);
        if(result != null){
            return "success";
        }else{
            return "error";
        }
    }

    @GetMapping("/findById/{id}")
    public User findById(@PathVariable("id") Integer id){
        return userRepository.findById(id).get();
    }

    @PutMapping("/update")
    public String update(@RequestBody User user){
        User result = userRepository.save(user);
        if(result != null){
            return "success";
        }else{
            return "error";
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") Integer id){
        userRepository.deleteById(id);
    }
    @Value("${server.port}")
    String port ;

    @GetMapping("/index")
    public String  index(){
        return port ;
    }
}
