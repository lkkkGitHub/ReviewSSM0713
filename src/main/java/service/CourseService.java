package service;

import mapper.CourseDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CourseService {

    @Resource
    private CourseDao courseDao;

}
