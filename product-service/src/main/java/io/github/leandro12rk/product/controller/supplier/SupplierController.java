package io.github.leandro12rk.product.controller.supplier;


import io.github.leandro12rk.product.model.supplier.SupplierModel;
import io.github.leandro12rk.product.projection.supplier.SupplierNameProjection;
import io.github.leandro12rk.product.service.supplier.SupplierService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }
    @GetMapping
    public ResponseEntity<List<SupplierModel>> getAllSupplierData() {
        return ResponseEntity.ok(supplierService.getAllSupplierData());
    }

    @GetMapping("/{supplierId}")
    public ResponseEntity<SupplierModel> getSupplierById(@PathVariable("supplierId") Long supplierId) {
        return ResponseEntity.ok(supplierService.getSupplierById(supplierId));
    }

    @GetMapping("/{supplierId}/name")
    public ResponseEntity<SupplierNameProjection> getSupplierCompanyName(@PathVariable Long supplierId) {
        return ResponseEntity.ok(supplierService.getSupplierCompanyName(supplierId));
    }
    // Delete Product By ID
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteSupplierById(@PathVariable Long productId) {
        supplierService.deleteSupplierById(productId);
        return ResponseEntity.noContent().build();
    }


    @PostMapping
    public ResponseEntity<SupplierModel> createNewSupplier(@RequestBody SupplierModel supplier) {
        SupplierModel created = supplierService.createNewSupplier(supplier);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    @PutMapping("/{supplierId}")
    public ResponseEntity<SupplierModel> updateSupplierById(@PathVariable Long supplierId, @RequestBody SupplierModel supplier) {
        return ResponseEntity.ok(supplierService.updateSupplierById(supplierId, supplier));
    }


}
