package eu.jev.springmvcrest.api.v1.mapper;

import eu.jev.springmvcrest.api.v1.model.VendorDTO;
import eu.jev.springmvcrest.domain.Vendor;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VendorMapperTest {

    public static final String NAME = "Vendor 1";

    VendorMapper vendorMapper = Mappers.getMapper(VendorMapper.class);


    @Test
    void vendorListToVendorDTOList() {
        Vendor vendor = Vendor.builder().name(NAME).build();
        List<Vendor> vendorList = Arrays.asList(vendor, vendor);

        List<VendorDTO> vendorDTOList = vendorMapper.vendorListToVendorDTOList(vendorList);

        assertEquals(2, vendorDTOList.size());
    }

    @Test
    void vendorToVendorDTO() {
        Vendor vendor = Vendor.builder().name(NAME).build();

        VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);

        assertEquals(NAME, vendorDTO.getName());
    }

    @Test
    void vendorDTOToVendor() {
        VendorDTO vendorDTO = VendorDTO.builder().name(NAME).build();

        Vendor vendor = vendorMapper.vendorDTOToVendor(vendorDTO);

        assertEquals(NAME, vendor.getName());
    }
}