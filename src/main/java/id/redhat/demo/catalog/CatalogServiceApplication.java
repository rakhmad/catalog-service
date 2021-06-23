package id.redhat.demo.catalog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CatalogServiceApplication {

	private static final Logger log = LoggerFactory.getLogger(CatalogServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CatalogServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(CatalogItemRepository repo) {
		return (args) -> {
			log.info("Initialize Catalog Items");
			log.info("=========================================");
			repo.save(new CatalogItem("Logitech", "Mouse Bluetooth", 150000));
			repo.save(new CatalogItem("iPad", "Tablet", 150000000));
			repo.save(new CatalogItem("Kindle", "E-Reader", 3000000));
			repo.save(new CatalogItem("Sidola", "Minyak Kayu Putih", 10000));
			repo.save(new CatalogItem("Blue Yeti", "Microphone", 3000000));
			repo.save(new CatalogItem("Wetties", "Tissue Basah", 10000));
			repo.save(new CatalogItem("BenQ", "Monitor 32 inch", 6000000));
			repo.save(new CatalogItem("AudioTechnica", "Headset", 2500000));
			repo.save(new CatalogItem("Basesus Vacuum", "Small Vacuum Cleaner", 100000));
			repo.save(new CatalogItem("Eclair", "Coklat", 50000));
			log.info("Finish Catalog Items initialization");
		};
	}
}