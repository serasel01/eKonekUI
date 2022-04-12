package com.example.barangayservicesui.utils;

import com.example.barangayservicesui.enums.ResidentFilterParameter;
import com.example.barangayservicesui.models.Case;
import com.example.barangayservicesui.models.Resident;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface AdminAccess {
    void addResident(Resident resident) throws JsonProcessingException;
    void editResidentInfo(Resident resident) throws JsonProcessingException;
    void deleteResident(Resident resident) throws JsonProcessingException;
    void addLog(String event) throws JsonProcessingException;
    void addResidentCase(Resident resident, Case aCase) throws JsonProcessingException;
    List<Resident> getResidentList(ResidentFilterParameter filterParameter,
                                   String parameterEntry);
    void logout();
}
