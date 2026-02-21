package com.org.invmgm.config;

import com.org.invmgm.model.Enumeration;
import com.org.invmgm.model.EnumerationType;
import com.org.invmgm.model.Uom;
import com.org.invmgm.repository.EnumerationRepository;
import com.org.invmgm.repository.EnumerationTypeRepository;
import com.org.invmgm.repository.UomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Order(1)
@Component
public class DataSeeder implements CommandLineRunner {

    private final UomRepository uomRepo;
    @Autowired
    private final EnumerationTypeRepository enumTypeRepo;
    private final EnumerationRepository enumRepo;

    public DataSeeder(UomRepository uomRepo, EnumerationTypeRepository enumTypeRepo, EnumerationRepository enumRepo) {
        this.uomRepo = uomRepo;
        this.enumTypeRepo = enumTypeRepo;
        this.enumRepo = enumRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        seedUom();
        seedEnumerationType();
        seedEnumeration();
    }

    // Seed Uom Data
    private void seedUom () {
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
        validateAndSeedEnumerationType("INV_REC_TYPE", "Inventory Received Type");
    }

    private void validateAndSeedEnumerationType (String id, String description) {
        EnumerationType enumType = enumTypeRepo.findById(id).orElse(new EnumerationType(id, description));
        enumTypeRepo.save(enumType);
    }

    // Seed Enumeration Data
    private void seedEnumeration () {
        validateAndSeedEnumeration("PURCHASED", "PURCHASED", "Purchased", 1,"INV_REC_TYPE");
        validateAndSeedEnumeration("TRANSFERRED", "TRANSFERRED", "Transferred", 2,"INV_REC_TYPE");
    }

    private void validateAndSeedEnumeration (String id, String enumCode, String description, int seqNum, String enumTypeId) {
        Enumeration enumeration = enumRepo.findById(id).orElseGet(() -> {
            EnumerationType enumType = enumTypeRepo.findById(enumTypeId).orElseThrow(() -> new RuntimeException("EnumType not found: " + enumTypeId));
            Enumeration e = new Enumeration();
            e.setId(id);
            e.setEnumCode(id);
            e.setDescription(description);
            e.setSeqNum(seqNum);
            e.setEnumType(enumType);
            return e;
        });
        enumRepo.save(enumeration);
    }
}
