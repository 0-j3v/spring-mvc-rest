package eu.jev.springmvcrest.api.v1.mapper;

import eu.jev.springmvcrest.api.v1.model.VendorDTO;
import eu.jev.springmvcrest.domain.Vendor;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VendorMapper {

    List<VendorDTO> vendorListToVendorDTOList(List<Vendor> vendorList);

    VendorDTO vendorToVendorDTO(Vendor vendor);

    Vendor vendorDTOToVendor(VendorDTO vendorDTO);
}
