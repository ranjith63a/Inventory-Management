package com.org.invmgm.config;

import com.org.invmgm.model.Uom;
import com.org.invmgm.repository.UomRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Order(1)
@Component
public class DataSeeder implements CommandLineRunner {

    private final UomRepository uomRepo;

    public DataSeeder(UomRepository uomRepo) {
        this.uomRepo = uomRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        seedUom();
    }

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
}
