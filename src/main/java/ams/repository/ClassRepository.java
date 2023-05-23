package ams.repository;


import ams.model.entity.Class;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends BaseRepository<Class, Long> {

}
