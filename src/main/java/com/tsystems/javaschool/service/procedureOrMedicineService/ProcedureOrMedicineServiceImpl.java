package com.tsystems.javaschool.service.procedureOrMedicineService;

import com.tsystems.javaschool.dao.ProcedureOrMedicineDAO;
import com.tsystems.javaschool.model.ProcedureOrMedicine;
import com.tsystems.javaschool.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(dao.delete(id), id);
    }

    @Override
    public ProcedureOrMedicine get(int id) throws NotFoundException {
        return checkNotFoundWithId(dao.get(id), id);
    }

    @Override
    public List<ProcedureOrMedicine> getAll() {
        return dao.getAll();
    }

    @Override
    public ProcedureOrMedicine getByName(String name) {
        return dao.getByName(name);
    }

    @Override
    public void update(ProcedureOrMedicine pom) throws NotFoundException {
        checkNotFoundWithId(dao.save(pom), pom.getId());
    }

    @Override
    public ProcedureOrMedicine create(ProcedureOrMedicine pom) {
        return dao.save(pom);
    }
}
