package com.tsystems.javaschool.service.procedureOrMedicineService;

import com.tsystems.javaschool.model.ProcedureOrMedicine;
import com.tsystems.javaschool.util.exception.NotFoundException;

import javax.persistence.NoResultException;
import java.util.List;

public interface ProcedureOrMedicineService {
    ProcedureOrMedicine getByNameAndType(String name, String type);

    ProcedureOrMedicine createWithNameAndType(String name, String type) throws NoResultException;

    ProcedureOrMedicine create(ProcedureOrMedicine pom);
}
