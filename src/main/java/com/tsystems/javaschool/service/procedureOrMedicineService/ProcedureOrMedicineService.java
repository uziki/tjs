package com.tsystems.javaschool.service.procedureOrMedicineService;

import com.tsystems.javaschool.model.ProcedureOrMedicine;
import com.tsystems.javaschool.util.exception.NotFoundException;

import java.util.List;

public interface ProcedureOrMedicineService {
    void delete(int id) throws NotFoundException;

    ProcedureOrMedicine get(int id) throws NotFoundException;

    List<ProcedureOrMedicine> getAll();

    ProcedureOrMedicine getByName(String name);

    void update(ProcedureOrMedicine pom) throws NotFoundException;

    ProcedureOrMedicine create(ProcedureOrMedicine pom);
}
