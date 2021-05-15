package com.tsystems.javaschool.service;


import com.tsystems.javaschool.service.event.EventService;
import com.tsystems.javaschool.service.patient.PatientService;
import com.tsystems.javaschool.service.prescription.PrescriptionService;
import com.tsystems.javaschool.service.procedureOrMedicineService.ProcedureOrMedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PrescriptionEventFacade {
    private PrescriptionService prescriptionService;
    private EventService eventService;
    private ProcedureOrMedicineService pomService;
    private PatientService patientService;

    @Autowired
    public PrescriptionEventFacade(PrescriptionService prescriptionService, EventService eventService,
                                   ProcedureOrMedicineService pomService, PatientService patientService) {
        this.prescriptionService = prescriptionService;
        this.eventService = eventService;
        this.pomService = pomService;
        this.patientService = patientService;
    }

    //TODO при изменении назначения - сохранять врача, изменить назначение может новый врач
    //TODO при создании/изменении назначения - проверять, может такой пом уже есть в базе
    //TODO delete prescriptions
    //TODO prescriptions procedure


}
