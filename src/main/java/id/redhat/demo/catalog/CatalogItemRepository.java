package id.redhat.demo.catalog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("catalogItemRepository")
public interface CatalogItemRepository extends JpaRepository<CatalogItem, Long> {
}
