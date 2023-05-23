package ams.service.impl;

import ams.model.entity.ClassBudget;
import ams.repository.ClassBudgetRepository;
import ams.service.ClassBudgetService;
import jakarta.annotation.Nullable;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Service
public class ClassBudgetServiceImpl extends BaseServiceImpl<ClassBudget,Long, ClassBudgetRepository> implements ClassBudgetService {
}
