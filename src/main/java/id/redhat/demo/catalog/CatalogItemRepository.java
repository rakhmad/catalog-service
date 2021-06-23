package id.redhat.demo.catalog;

import org.springframework.data.repository.CrudRepository;

public interface CatalogItemRepository extends CrudRepository<CatalogItem, Long> {
}
