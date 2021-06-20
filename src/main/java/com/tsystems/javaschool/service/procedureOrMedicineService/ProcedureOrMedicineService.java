package com.tsystems.javaschool.service.procedureOrMedicineService;

import com.tsystems.javaschool.model.ProcedureOrMedicine;

import javax.persistence.NoResultException;

/**
 * ProcedureOrMedicine service interface
 */
public interface ProcedureOrMedicineService {

    /**
     * Gets ProcedureOrMedicine by name and type.
     *
     * @param name of ProcedureOrMedicine
     * @param type of ProcedureOrMedicine
     * @return ProcedureOrMedicine by name and type
     * @throws NoResultException if there is no such ProcedureOrMedicine
     */
    ProcedureOrMedicine getByNameAndType(String name, String type) throws NoResultException;

    /**
     * Checks if ProcedureOrMedicine with this name and this type already exists. If so - returns it,
     * otherwise creates new ProcedureOrMedicine with specified parameters and returns it
     *
     * @param name of ProcedureOrMedicine
     * @param type of ProcedureOrMedicine
     * @return Existed or created ProcedureOrMedicine
     * @throws NoResultException if there is no ProcedureOrMedicine with specified parameters
     */
    ProcedureOrMedicine createWithNameAndType(String name, String type);

    /**
     * Creates ProcedureOrMedicine.
     *
     * @param pom need to be created
     * @return saved ProcedureOrMedicine
     */
    ProcedureOrMedicine create(ProcedureOrMedicine pom);
}
