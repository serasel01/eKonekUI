package com.example.barangayservicesui.controllers;

import com.github.sarxos.webcam.Webcam;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

public class QRScannerController {
    private ObjectProperty<Image> imageProperty = new SimpleObjectProperty<>();
    private Webcam webcam;
    private Dialog<Boolean> dialog;
    private TextField textField;

    @FXML
    private BorderPane bpWebCamPaneHolder;

    @FXML
    private FlowPane fpBottomPane;

    @FXML
    private ImageView imgWebCamCapturedImage;

    @FXML
    private Font x1;

    @FXML
    private Button btnCloseCamera;

    @FXML
    private Text txtMessage;

    @FXML
    void closeCamera(ActionEvent event) {
        dialog.setResult(Boolean.TRUE);
        dialog.close();
    }

    public void start(TextField textField, Dialog<Boolean> dialog) {
        startCameraInput();
        this.textField = textField;
        this.dialog = dialog;
    }

    private void startCameraInput() {
        Task<Void> webCamTask = new Task<Void>() {
            @Override
            protected Void call() {
                webcam = Webcam.getDefault();
                webcam.open();
                startWebCamStream();

                return null;
            }
        };

        Thread webCamThread = new Thread(webCamTask);
        webCamThread.setDaemon(true);
        webCamThread.start();
    }

    private void startWebCamStream() {
        boolean stopCamera = false;

        Task<Void> task = new Task<Void>() {

            @Override
            protected Void call() {
                final AtomicReference<WritableImage> ref = new AtomicReference<>();
                BufferedImage img;

                //noinspection ConstantConditions,LoopConditionNotUpdatedInsideLoop
                while (!stopCamera) {
                    try {
                        if ((img = webcam.getImage()) != null) {
                            ref.set(SwingFXUtils.toFXImage(img, ref.get()));
                            img.flush();
                            Platform.runLater(() -> imageProperty.set(ref.get()));

                            String scanResult = decodeQRCode(img);
                            if (scanResult != null) {
                                textField.setText(scanResult);
                                txtMessage.setText("Scanned ID Number: " + scanResult);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                return null;
            }
        };

        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();

        imgWebCamCapturedImage.imageProperty().bind(imageProperty);
    }

    public String decodeQRCode(BufferedImage image) {
        LuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

        try {
            Result result = new MultiFormatReader().decode(bitmap);
            return result.getText();
        } catch (NotFoundException e) {
//            System.out.println("There is no QR code in the image");
            return null;
        }
    }
}
