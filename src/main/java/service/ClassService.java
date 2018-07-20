package service;

import mapper.ClassDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class ClassService {

    @Resource
    private ClassDao classDao;

}
