package com.ctn.web.controller;

import com.ctn.util.qrcode.MatrixToImageWriter;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Barry on 2015/3/5.
 */

@RestController
@RequestMapping("/qr")
public class QrCodeController {


    @Value("${qrcode.server.address}")
    private String basePath;

    @RequestMapping(value = "/code", method = RequestMethod.GET)
    public Object genQRCode(HttpServletRequest request,HttpServletResponse response,@RequestParam("content")String content){
        try {

            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            Map hints = new HashMap();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            BitMatrix bitMatrix = multiFormatWriter.encode(basePath+"?id="+content, BarcodeFormat.QR_CODE, 400, 400,hints);
            MatrixToImageWriter.writeToStream(bitMatrix, "jpg", response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }




}
