package com.tsystems.javaschool.service.procedureOrMedicineService;

import com.tsystems.javaschool.dao.ProcedureOrMedicineDAO;
import com.tsystems.javaschool.model.PrescriptionType;
import com.tsystems.javaschool.model.ProcedureOrMedicine;
import com.tsystems.javaschool.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.List;

import static com.tsystems.javaschool.util.ValidationUtil.checkNotFoundWithId;


@Service
@Transactional
public class ProcedureOrMedicineServiceImpl implements ProcedureOrMedicineService {

    private ProcedureOrMedicineDAO dao;

    @Autowired
    public ProcedureOrMedicineServiceImpl(ProcedureOrMedicineDAO dao) {
        this.dao = dao;
    }

    public ProcedureOrMedicine getByNameAndType(String name, String type) {
        return dao.getByNameAndType(name, type);
    }

    @Override
    public ProcedureOrMedicine create(ProcedureOrMedicine pom) {
        return dao.save(pom);
    }

    @Override
    public ProcedureOrMedicine createWithNameAndType(String name, String type) {
        ProcedureOrMedicine pom;
        try {
            pom = getByNameAndType(name, type);
        } catch (NoResultException e) {
            pom = new ProcedureOrMedicine(name, PrescriptionType.valueOf(type));
            return create(pom);
        }
        return pom;
    }
}
