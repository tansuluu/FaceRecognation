package aiu.edu.kg.common.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * Базовый сервис для работы с наследниками BaseEntity
 *
 * @param <T> Сущность
 */
public abstract class BaseEntityService<T extends BaseEntity> {

    /**
     * Репозиторий
     */
    protected BaseEntityRepository<T, Long> repository;

    /**
     * Конструктор
     *
     * @param repository Репозиторий
     */
    public BaseEntityService(BaseEntityRepository repository) {
        this.repository = repository;
    }

    /**
     * Получить все записи
     *
     * @return Список T
     */
    public List<T> getAll() {
        return repository.findAll();
    }

    /**
     * Получить все записи по конкретным спецификациям
     *
     * @param specification Спецификации
     * @return Список T
     */
    public List<T> get(Specification specification){
        return repository.findAll(specification);
    }

    /**
     * Получить страницу с искомыми элементами
     *
     * @param pageable Параметры пагинации
     * @return Страница с элментами
     */
    public Page<T> get(Pageable pageable) {
        return repository.findAll(pageable);
    }

    /**
     * Получить страницу с искомыми элементами по конкретным спецификациям
     *
     * @param specification Спецификации
     * @param pageable Параметры пагинации
     * @return Страница с элментами
     */
    public Page<T> get(Specification specification, Pageable pageable){
        return repository.findAll(specification, pageable);
    }

    /**
     * Получить запись
     *
     * @param id Идентификатор записи
     * @return Запись T
     */
    public T get(Long id) {
        return repository.getOne(id);
    }

    /**
     * Сохранить запись
     *
     * @param entity Сущность
     * @return Сохраненная запись T
     */
    public T save(T entity) {
        return repository.save(entity);
    }

    /**
     * Сохранить запись и записать в базу данных
     *
     * @param entity Сущность
     * @return Сохраненная запись T
     */
    public T saveWithFlush(T entity) {
        T saved = repository.save(entity);
        repository.flush();

        return saved;
    }

    /**
     * Удалить запись
     *
     * @param id Идентификатор
     */
    public void delete(Long id) {
        repository.deleteById(id);
    }

    /**
     * Записать в базу данных
     */
    public void flush(){
        repository.flush();
    }
}
