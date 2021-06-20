package com.tsystems.javaschool.dao;

import com.tsystems.javaschool.model.ProcedureOrMedicine;

/**
 * ProcedureOrMedicine DAO interface.
 */
public interface ProcedureOrMedicineDAO {

    /**
     * Creates or updates ProcedureOrMedicine.
     *
     * @param pom need to be saved
     * @return saved ProcedureOrMedicine
     */
    ProcedureOrMedicine save(ProcedureOrMedicine pom);

    /**
     * Gets ProcedureOrMedicine by name and type.
     *
     * @param name of ProcedureOrMedicine
     * @param type of ProcedureOrMedicine
     * @return ProcedureOrMedicine by name and type
     */
    ProcedureOrMedicine getByNameAndType(String name, String type);
}
