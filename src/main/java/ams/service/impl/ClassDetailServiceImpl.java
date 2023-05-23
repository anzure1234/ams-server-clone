package ams.service.impl;

import ams.model.entity.ClassDetail;
import ams.repository.ClassDetailRepository;
import ams.service.ClassDetailService;
import jakarta.annotation.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ClassDetailServiceImpl extends BaseServiceImpl<ClassDetail,Long, ClassDetailRepository> implements ClassDetailService {

}
