package com.fantasy.abstraction.service.impl;

import com.fantasy.abstraction.service.QrCodeService;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.EnumMap;

@Service
public class QrCodeServiceImpl implements QrCodeService {
    private static final Logger logger = LoggerFactory.getLogger(QrCodeServiceImpl.class);

    @Override
    public String decodeQRCode(File qrCodeImage) throws FormatException, ChecksumException, IOException {
        BufferedImage bufferedImage = ImageIO.read(qrCodeImage);
        LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Reader reader = new MultiFormatReader();
        EnumMap<DecodeHintType, Object> hint = new EnumMap<>(DecodeHintType.class);
        hint.put(DecodeHintType.TRY_HARDER, BarcodeFormat.CODE_39);
        try {
            Result result = reader.decode(bitmap, hint);
            logger.info("Barcode was successfully resolved");
            return result.getText();
        } catch (NotFoundException e) {
            logger.error("Barcode doesnt exists on photo/cannot read barcode");
            return null;
        }
    }
}
