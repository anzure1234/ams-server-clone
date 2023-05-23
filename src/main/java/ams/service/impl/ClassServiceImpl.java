package ams.service.impl;

import ams.model.entity.Class;
import ams.repository.ClassRepository;
import ams.service.ClassService;
import jakarta.annotation.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassServiceImpl extends BaseServiceImpl<Class,Long,ClassRepository> implements ClassService  {

}
