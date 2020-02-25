package aiu.edu.kg.common.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

/**
 * Базовый репозиторий для наследников сущности BaseEntity
 *
 * @param <T> Сущность
 * @param <ID> Идентифиактор
 */
public interface BaseEntityRepository<T extends BaseEntity, ID extends Serializable>
        extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
}
