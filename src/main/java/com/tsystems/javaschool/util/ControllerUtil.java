package com.tsystems.javaschool.util;

import com.tsystems.javaschool.model.Prescription;
import com.tsystems.javaschool.model.PrescriptionType;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class ControllerUtil {
    public static int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }

    public static String getType(HttpServletRequest request) {
        String type = Objects.requireNonNull(request.getParameter("type"));
        return type;
    }

    //Parse prescription timePattern and add it to Model
    public static void addTimePatternToModel(Prescription prescription, Model model) {
        //Medicine
        if (prescription.getProcedureOrMedicine().getPrescriptionType() == PrescriptionType.TYPE_MEDICINE) {
            String times[] = prescription.getTimePattern().split(" ");
            for (int i = 0; i < 3; i++) {
                if (times[i].length() > 2) {
                    model.addAttribute("time" + i, times[i].substring(2));
                }
            }
        //Procedure
        } else {
            String days[] = prescription.getTimePattern().split(" ");
            for (int i = 0; i < 7; i++) {
                if (days[i].length() > 2) {
                    model.addAttribute("day" + i, days[i].substring(2));
                }
            }
        }
    }

    //Parse prescription timePattern from request
    public static String timePatternFromRequest(HttpServletRequest request) {
        String timePattern = "";
        //Medicine
        if (request.getParameter("type").equals("TYPE_MEDICINE")) {
            timePattern = "1-" + request.getParameter("morning")
                    + " 2-" + request.getParameter("afternoon")
                    + " 3-" + request.getParameter("evening");
        //Procedure
        } else {
            timePattern = "1-" + request.getParameter("monday")
                    + " 2-" + request.getParameter("tuesday")
                    + " 3-" + request.getParameter("wednesday")
                    + " 4-" + request.getParameter("thursday")
                    + " 5-" + request.getParameter("friday")
                    + " 6-" + request.getParameter("saturday")
                    + " 7-" + request.getParameter("sunday");
        }
        return timePattern;
    }

}
