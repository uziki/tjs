package com.tsystems.javaschool.dao;

import com.tsystems.javaschool.model.ProcedureOrMedicine;

import java.util.List;

public interface ProcedureOrMedicineDAO {
    ProcedureOrMedicine save(ProcedureOrMedicine pom);

    boolean delete(int id);

    ProcedureOrMedicine get(int id);

    List<ProcedureOrMedicine> getAll();

    ProcedureOrMedicine getByNameAndType(String name, String type);
}
