package com.postgreDemo.Controller;

import com.postgreDemo.Entity.SpringTable;
import com.postgreDemo.Repository.SpringTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
public class SpringTableController {

    @Autowired
    private SpringTableRepository springTableRepository;

    @GetMapping("/findall")
    public List<SpringTable> findalldata(){
        return  springTableRepository.findAll();
    }

    @PostMapping("/insert")
    public String insertdata(@RequestBody SpringTable body){
        springTableRepository.save(body);
        return "新增成功";
    }

}
