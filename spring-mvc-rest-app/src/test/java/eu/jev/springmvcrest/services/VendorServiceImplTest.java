package eu.jev.springmvcrest.services;

import eu.jev.springmvcrest.api.v1.mapper.VendorMapper;
import eu.jev.springmvcrest.api.v1.model.VendorDTO;
import eu.jev.springmvcrest.domain.Vendor;
import eu.jev.springmvcrest.repositories.VendorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class VendorServiceImplTest {

    VendorServiceImpl vendorService;

    @Mock
    VendorRepository vendorRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        vendorService = new VendorServiceImpl(vendorRepository, Mappers.getMapper(VendorMapper.class));
    }

    @Test
    void getAllVendors() {
        Vendor vendor1 = Vendor.builder().id(1L).name("Vendor 1").build();
        Vendor vendor2 = Vendor.builder().id(2L).name("Vendor 2").build();

        when(vendorRepository.findAll()).thenReturn(Arrays.asList(vendor1, vendor2));

        List<VendorDTO> vendorDTOList = vendorService.getAllVendors();

        assertEquals(2, vendorDTOList.size());
    }

    @Test
    void getVendorById() {
        Vendor vendor = Vendor.builder().id(1L).name("Vendor 1").build();

        when(vendorRepository.findById(anyLong())).thenReturn(Optional.ofNullable(vendor));

        VendorDTO vendorDTO = vendorService.getVendorById(1L);

        assertEquals("Vendor 1", vendorDTO.getName());
        assertEquals("/api/v1/vendors/1", vendorDTO.getVendorUrl());
    }

    @Test
    void createNewVendor() {
        VendorDTO vendorDTO = VendorDTO.builder().name("Vendor 1").build();
        Vendor savedVendor = Vendor.builder().id(1L).name("Vendor 1").build();

        when(vendorRepository.save(any())).thenReturn(savedVendor);

        VendorDTO savedDTO = vendorService.createNewVendor(vendorDTO);

        assertEquals("Vendor 1", savedDTO.getName());
        assertEquals("/api/v1/vendors/1", savedDTO.getVendorUrl());
    }

    @Test
    void saveVendorByDTO() {
        VendorDTO vendorDTO = VendorDTO.builder().name("Vendor 2").build();
        Vendor savedVendor = Vendor.builder().id(1L).name("Vendor 2").build();


        when(vendorRepository.save(any())).thenReturn(savedVendor);

        VendorDTO savedDTO = vendorService.saveVendorByDTO(1L, vendorDTO);

        assertEquals("Vendor 2", savedDTO.getName());
        assertEquals("/api/v1/vendors/1", savedDTO.getVendorUrl());
    }

    @Test
    void patchVendor() {
        VendorDTO vendorDTO = VendorDTO.builder().name("Vend").build();
        Vendor vendor = Vendor.builder().id(1L).name("Vend").build();


        when(vendorRepository.findById(anyLong())).thenReturn(Optional.of(vendor));
        when(vendorRepository.save(any(Vendor.class))).thenReturn(vendor);

        VendorDTO savedDTO = vendorService.patchVendor(1L, vendorDTO);

        assertEquals("Vend", savedDTO.getName());
        assertEquals("/api/v1/vendors/1", savedDTO.getVendorUrl());
    }

    @Test
    void deleteVendorById() {
        vendorService.deleteVendorById(1L);
        verify(vendorRepository, times(1)).deleteById(anyLong());
    }
}