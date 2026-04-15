package io.github.leandro12rk.product.service.supplier;

import io.github.leandro12rk.product.exception.ResourceNotFoundException;
import io.github.leandro12rk.product.model.supplier.SupplierModel;
import io.github.leandro12rk.product.projection.supplier.SupplierNameProjection;
import io.github.leandro12rk.product.repository.supplier.SupplierRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class SupplierService {

    SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }


    public List<SupplierModel> getAllSupplierData() {
        return supplierRepository.findAll();
    }

    public SupplierModel getSupplierById(Long supplierId) {
        return supplierRepository.findById(supplierId).orElseThrow(() -> new ResourceNotFoundException("Supplier not found with Id: " + supplierId));
    }

    public SupplierNameProjection getSupplierCompanyName(Long supplierId) {
        return supplierRepository.findCompanyNameProjectionById(supplierId).orElseThrow(() -> new ResourceNotFoundException("Supplier Non Found: " + supplierId));
    }

    // Delete Product By ID
    public void deleteSupplierById(Long productId) {
        if (!supplierRepository.existsById(productId)) {
            throw new ResourceNotFoundException("Could no Delete , Product not Found: " + productId);
        }
        supplierRepository.deleteById(productId);
    }

    // Update Supplier By Id
    public SupplierModel updateSupplierById(Long supplierId, SupplierModel supplierUpdate) {
        return supplierRepository.findById(supplierId).map(existingSupplier -> {

            existingSupplier.setCity(supplierUpdate.getCity());
            existingSupplier.setCountry(supplierUpdate.getCountry());
            existingSupplier.setAddress(supplierUpdate.getAddress());
            existingSupplier.setEmail(supplierUpdate.getEmail());
            existingSupplier.setCompanyName(supplierUpdate.getCompanyName());
            existingSupplier.setPhone(supplierUpdate.getPhone());
            existingSupplier.setContactName(supplierUpdate.getContactName());
            existingSupplier.setStatus(supplierUpdate.isStatus());

            return supplierRepository.save(existingSupplier);
        }).orElseThrow(() -> new ResourceNotFoundException("Could not be updated , Product dosen't exists with Id: " + supplierId));

    }

    public SupplierModel createNewSupplier(SupplierModel supplier) {
        return supplierRepository.save(supplier);
    }

}
