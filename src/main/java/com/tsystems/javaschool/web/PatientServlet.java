package com.tsystems.javaschool.web;

import com.tsystems.javaschool.DAO.Patient.jpaDAO;
import com.tsystems.javaschool.model.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class PatientServlet extends HttpServlet {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private ConfigurableApplicationContext springContext;
    private jpaDAO patients;

    @Override
    public void init() {
        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml");
        patients = springContext.getBean(jpaDAO.class);
    }

    @Override
    public void destroy() {
        springContext.close();
        super.destroy();
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");

        Patient patient = new Patient(id.isEmpty() ? null : Integer.valueOf(id),
                request.getParameter("name"),
                request.getParameter("diagnosis"),
                request.getParameter("insurance"),
                true);

        log.info(patient.isNew() ? "Create {}" : "Update {}", patient);
        patients.save(patient);
        response.sendRedirect("patients");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "delete":
                int id = getId(request);
                log.info("Delete {}", id);
                patients.delete(id);
                response.sendRedirect("patients");
                break;
            case "create":
            case "update":
                final Patient patient = "create".equals(action) ?
                        new Patient() : patients.get(getId(request));
                request.setAttribute("patient", patient);
                request.getRequestDispatcher("/patientForm.jsp").forward(request, response);
                break;
            case "all":
            default:
                log.info("getAll");
                request.setAttribute("patients", patients.getAll());
                request.getRequestDispatcher("/patients.jsp").forward(request, response);
                break;
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }

    /*protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("getAll");
        //request.setAttribute("patients", PatientsUtil.getPatients());
        request.setAttribute("patients", new ArrayList<>(patients.getAll()));
        request.getRequestDispatcher("/patients.jsp").forward(request, response);
    }*/
}
