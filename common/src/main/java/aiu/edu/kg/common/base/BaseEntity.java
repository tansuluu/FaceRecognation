package aiu.edu.kg.common.base;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * Базовый класс для всех сущностей
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {
    /**
     * Идентификатор
     * @return Long
     */
    public abstract Long getId();

    /**
     * Дата создания элемента
     */
    @Column(name = "created_date")
    public Date createdDate;
}
