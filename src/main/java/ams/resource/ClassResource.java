package ams.resource;

import ams.constant.AppConstant;
import ams.model.dto.ClassDisplayDto;
import ams.model.dto.ClassDto;
import ams.model.entity.Class;
import ams.service.BaseService;
import ams.service.ClassService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/class")
public class ClassResource extends BaseResource {
    private final ClassService classService;
    private final BaseService baseService;


    public ClassResource(ClassService classService, @Qualifier("baseServiceImpl") BaseService baseService) {
        this.classService = classService;
        this.baseService = baseService;
    }

    @PostMapping("/create")
    public ResponseEntity<ClassDisplayDto> create(@RequestBody @Valid ClassDto classDto) {
        Class clazz = new Class();
        BeanUtils.copyProperties(classDto, clazz);
        baseService.create(clazz);
        ClassDisplayDto classDisplayDto = new ClassDisplayDto();
        BeanUtils.copyProperties(clazz, classDisplayDto);
        return ResponseEntity.ok(classDisplayDto);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<ClassDisplayDto> update(@PathVariable("id") Long id, @RequestBody @Valid ClassDto classDto) {
        Optional<Class> clazz = baseService.findOneOpt(id);
        if (clazz.isPresent()) {
            BeanUtils.copyProperties(classDto, clazz.get());
            baseService.update(clazz.get());
            ClassDisplayDto classDisplayDto = new ClassDisplayDto();
            BeanUtils.copyProperties(clazz.get(), classDisplayDto);
            return ResponseEntity.ok(classDisplayDto);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        Optional<Class> clazz = baseService.findOneOpt(id);
        if (clazz.isPresent()) {
            baseService.delete(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<Page<ClassDisplayDto>> show(
            @RequestParam(required = false, defaultValue = AppConstant.DEFAULT_PAGE_STR) Integer page,
            @RequestParam(required = false, defaultValue = AppConstant.DEFAULT_PAGE_SIZE_STR) Integer size,
            @RequestParam(required = false, name = "sort") List<String> sorts,
            @RequestParam(required = false, name = "search") Optional<String> keywordOpt) {

        List<Sort.Order> orders = new ArrayList<>();
        for (String sortField : sorts) {
            boolean isDesc = sortField.startsWith("-");
            orders.add(isDesc ? Sort.Order.desc(sortField.substring(1)) : Sort.Order.asc(sortField));
        }


        Specification<Class> spec = (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("deleted"), false);
        if (keywordOpt.isPresent()) {
            Specification<Class> specByKeyWord = (root, query, criteriaBuilder) ->
                    criteriaBuilder.or(
                            criteriaBuilder.like(root.get("classCode"), "%" + keywordOpt.get() + "%"),
                            criteriaBuilder.like(root.get("className"), "%" + keywordOpt.get() + "%")
                    );
            spec = spec.and(specByKeyWord);

        }

        PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by(orders));
        Page<Class> classPage = baseService.findAll(spec, pageRequest);
        List<ClassDisplayDto> classDisplayDtos = classPage.getContent().stream().map(clazz -> {
            ClassDisplayDto classDisplayDto = new ClassDisplayDto();
            BeanUtils.copyProperties(clazz, classDisplayDto);
            return classDisplayDto;
        }).collect(Collectors.toList());

        Page<ClassDisplayDto> result = new PageImpl<>(classDisplayDtos, pageRequest, classPage.getTotalElements());
        return ResponseEntity.ok(result);

    }


}
