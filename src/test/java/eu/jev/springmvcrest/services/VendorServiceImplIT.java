package eu.jev.springmvcrest.services;

import eu.jev.springmvcrest.api.v1.mapper.VendorMapper;
import eu.jev.springmvcrest.api.v1.model.VendorDTO;
import eu.jev.springmvcrest.bootstrap.Bootstrap;
import eu.jev.springmvcrest.domain.Vendor;
import eu.jev.springmvcrest.repositories.CategoryRepository;
import eu.jev.springmvcrest.repositories.CustomerRepository;
import eu.jev.springmvcrest.repositories.VendorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class VendorServiceImplIT {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    VendorRepository vendorRepository;

    VendorService vendorService;

    @BeforeEach
    void setUp() throws Exception {
        System.out.println("Loading Vendors Data");
        System.out.println(vendorRepository.findAll().size());

        Bootstrap bootstrap = new Bootstrap(categoryRepository, customerRepository, vendorRepository);
        bootstrap.run();

        vendorService = new VendorServiceImpl(vendorRepository, Mappers.getMapper(VendorMapper.class));
    }

    @Test
    void patchVendorUpdateName() {
        String updatedName = "UpdatedName";
        Long id = getVendorIdValue();

        Vendor originalVendor = vendorRepository.getOne(id);
        assertNotNull(originalVendor);

        String originalName = originalVendor.getName();

        VendorDTO vendorDTO = VendorDTO.builder().name(updatedName).build();

        vendorService.patchVendor(id, vendorDTO);

        Vendor updatedVendor = vendorRepository.findById(id).get();

        assertNotNull(updatedVendor);
        assertEquals(updatedName, updatedVendor.getName());
        assertThat(originalName, not(equalTo(updatedVendor.getName())));
    }

    private Long getVendorIdValue(){
        List<Vendor> vendors = vendorRepository.findAll();
        System.out.println("Vendors Found: " + vendors.size());
        return vendors.get(0).getId();
    }
}
