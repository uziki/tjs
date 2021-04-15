package com.tsystems.javaschool.web;

import com.tsystems.javaschool.util.PatientsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PatientServlet extends HttpServlet {
    private final Logger log = LoggerFactory.getLogger(getClass());

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("getAll");
        request.setAttribute("patients", PatientsUtil.getPatients());
        request.getRequestDispatcher("/patients.jsp").forward(request, response);
    }
}
