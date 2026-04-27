package com.org.invmgm.config;

import com.org.invmgm.exception.DataNotFoundException;
import com.org.invmgm.model.*;
import com.org.invmgm.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Order(1)
@Component
@AllArgsConstructor
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private final UomRepository uomRepo;
    @Autowired
    private final EnumerationTypeRepository enumTypeRepo;
    @Autowired
    private final EnumerationRepository enumRepo;
    @Autowired
    private final StatusItemRepository stsRepo;
    @Autowired
    private final StatusItemTypeRepository stsTypeRepo;
    @Autowired
    private final SecurityGroupRepository secGroRepo;


    @Override
    public void run(String... args) throws Exception {
        seedUom();
        seedEnumerationType();
        seedEnumeration();
        seedStatusItemType();
        seedStatusItem();
        seedSecurityGroup();
    }

    // Seed Uom Data
    private void seedUom () {
        validateAndSeedUom("MCG", BigDecimal.ONE, "Micro Gram");
        validateAndSeedUom("KG", BigDecimal.valueOf(1000000000), "Kilogram");
        validateAndSeedUom("G", BigDecimal.valueOf(1000000), "Gram");
        validateAndSeedUom("MG", BigDecimal.valueOf(1000), "milligram");
        validateAndSeedUom("CT", BigDecimal.ONE, "Count");
    }

    private void validateAndSeedUom (String id, BigDecimal conversion, String description) {
        Uom uom = uomRepo.findByIdEquals(id).orElse(new Uom(id, conversion, description));

        /*uom.setId(id);
        uom.setConversion(conversion);
        uom.setDescription(description);*/
        uomRepo.save(uom);
    }

    // Seed Enumeration Type Data
    private void seedEnumerationType () {
        validateAndSeedEnumerationType("INV_TYPE", "Inventory Received Type");
        validateAndSeedEnumerationType("INV_DTL_TR_TYPE", "Inventory Item Detail Transaction Type");
        validateAndSeedEnumerationType("INV_TRANS_REASON", "Inventory Transfer Reason");
    }

    private void validateAndSeedEnumerationType (String id, String description) {
        EnumerationType enumType = enumTypeRepo.findById(id).orElse(new EnumerationType(id, description));
        enumTypeRepo.save(enumType);
    }

    // Seed Enumeration Data
    private void seedEnumeration () {
        // inventory item enum
        validateAndSeedEnumeration("PURCHASED", "PURCHASED", "Purchased", 1,"INV_TYPE");
        validateAndSeedEnumeration("TRANSFERRED", "TRANSFERRED", "Transferred", 2,"INV_TYPE");

        // inventory item detail enum
        validateAndSeedEnumeration("INV_PURCHASED", "INV_PURCHASED", "Purchased", 1,"INV_DTL_TR_TYPE");
        validateAndSeedEnumeration("INV_TRANSFERRED", "INV_TRANSFERRED", "Transferred", 2,"INV_DTL_TR_TYPE");
        validateAndSeedEnumeration("INV_SALE", "INV_SALE", "Sale", 3,"INV_DTL_TR_TYPE");

        // inventory Transfer enum
        validateAndSeedEnumeration("INV_TRNS_SALE", "INV_TRNS_SALE", "Sale", 1,"INV_TRANS_REASON");
        validateAndSeedEnumeration("INV_TRNS_DESTR", "INV_TRNS_DESTR", "Destruction", 2,"INV_TRANS_REASON");
        validateAndSeedEnumeration("INV_SAMPLE", "INV_SAMPLE", "Sample", 3,"INV_TRANS_REASON");
    }

    // Validate Status Item Type Data
    private void seedStatusItemType () {
        validateAndSeedStatusItemType("INV_ITM_STATUS", "Inventory Item Status Type");
        validateAndSeedStatusItemType("INV_ITM_TRANS_STATUS", "Inventory Item Transfer Status Type");
    }

    // Validate Status Item Data
    private void validateAndSeedStatusItemType (String id, String description) {
        StatusItemType statusItemType = stsTypeRepo.findById(id).orElse(new StatusItemType(id, description));
        stsTypeRepo.save(statusItemType);
    }

    // validate Status Item Data
    private void seedStatusItem () {
        // Inventory Item Status
        validateAndSeedStatusItem("INV_PENDING", "Pending", 1, "INV_ITM_STATUS");
        validateAndSeedStatusItem("INV_VERIFIED", "Verified", 2, "INV_ITM_STATUS");
        validateAndSeedStatusItem("INV_REJECTED", "Rejected", 3, "INV_ITM_STATUS");

        // Inventory Item Transfer Status
        validateAndSeedStatusItem("INV_TRN_TRANSFERRED", "Transferred", 1, "INV_ITM_TRANS_STATUS");
        validateAndSeedStatusItem("INV_TRN_CONFIRMED", "Confirmed", 2, "INV_ITM_TRANS_STATUS");
        validateAndSeedStatusItem("INV_TRN_REJECTED", "Rejected", 3, "INV_ITM_TRANS_STATUS");
    }

    // Seed Status Item Data
    private void validateAndSeedStatusItem (String id, String description, int seqNum, String statusTypeId) {
        StatusItem statusItem = stsRepo.findById(id).orElseGet(() -> {
            StatusItemType statusItemType = stsTypeRepo.findById(statusTypeId).orElseThrow(() -> new DataNotFoundException("Status Item Type not found: " + statusTypeId));
            StatusItem status = new StatusItem();
            status.setId(id);
            status.setType(statusItemType);
            status.setDescription(description);
            status.setSeqNum(seqNum);
            return status;
        });
        stsRepo.save(statusItem);
    }

    private void validateAndSeedEnumeration (String id, String enumCode, String description, int seqNum, String enumTypeId) {
        Enumeration enumeration = enumRepo.findById(id).orElseGet(() -> {
            EnumerationType enumType = enumTypeRepo.findById(enumTypeId).orElseThrow(() -> new DataNotFoundException("EnumType not found: " + enumTypeId));
            Enumeration e = new Enumeration();
            e.setId(id);
            e.setEnumCode(enumCode);
            e.setDescription(description);
            e.setSeqNum(seqNum);
            e.setEnumType(enumType);
            return e;
        });
        enumRepo.save(enumeration);
    }

    // Seed Uom Data
    private void seedSecurityGroup () {
        validateAndSeedSecurityGroup("ADMIN", "Admin");
        validateAndSeedSecurityGroup("USER", "Normal User");
    }

    private void validateAndSeedSecurityGroup (String id, String groupName) {
        SecurityGroup group = secGroRepo.findById(id).orElse(new SecurityGroup(id, groupName));
        secGroRepo.save(group);
    }
}
