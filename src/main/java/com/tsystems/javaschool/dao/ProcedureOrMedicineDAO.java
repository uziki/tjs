package com.tsystems.javaschool.dao;

import com.tsystems.javaschool.model.ProcedureOrMedicine;

import java.util.List;

public interface ProcedureOrMedicineDAO {
    ProcedureOrMedicine save(ProcedureOrMedicine pom);

    ProcedureOrMedicine getByNameAndType(String name, String type);
}
