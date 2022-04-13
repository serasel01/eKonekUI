module BarangayServices.ui {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    requires org.apache.commons.codec;
    requires java.prefs;
    requires java.sql;
    requires webcam.capture;
    requires java.desktop;
    requires javafx.swing;
    requires spire.doc.free;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires spring.web;
    requires java.logging;
    requires spring.webflux;
    requires reactor.core;
    requires com.google.zxing.javase;
    requires com.google.zxing;

    opens com.example.barangayservicesui to javafx.fxml;
    exports com.example.barangayservicesui;
    exports com.example.barangayservicesui.controllers;
    exports com.example.barangayservicesui.utils;
    opens com.example.barangayservicesui.controllers to javafx.fxml;
    exports com.example.barangayservicesui.certificates;
    opens com.example.barangayservicesui.certificates to javafx.fxml;
    exports com.example.barangayservicesui.models;
    exports com.example.barangayservicesui.enums;
    exports com.example.barangayservicesui.database;
    exports com.example.barangayservicesui.restservices;
}