package eu.jev.springmvcrest.bootstrap;

import eu.jev.springmvcrest.domain.Category;
import eu.jev.springmvcrest.domain.Customer;
import eu.jev.springmvcrest.repositories.CategoryRepository;
import eu.jev.springmvcrest.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCategories();
        loadCustomers();
    }

    private void loadCategories() {
        Category fruits = Category.builder().name("Fruits").build();
        Category dried = Category.builder().name("Dried").build();
        Category fresh = Category.builder().name("Fresh").build();
        Category exotic = Category.builder().name("Exotic").build();
        Category nuts = Category.builder().name("Nuts").build();

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        System.out.println("Categories Loaded = " + categoryRepository.count());
    }

    private void loadCustomers() {
        Customer michale = Customer.builder()
                .id(1L)
                .firstname("Michale")
                .lastname("Weston")
                .build();
        Customer sam = Customer.builder()
                .id(2L)
                .firstname("Sam")
                .lastname("Axe")
                .build();

        customerRepository.save(michale);
        customerRepository.save(sam);

        System.out.println("Customers Loaded = " + customerRepository.count());

    }
}
