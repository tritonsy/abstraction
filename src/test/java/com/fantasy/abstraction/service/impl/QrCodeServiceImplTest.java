package com.fantasy.abstraction.service.impl;


import com.fantasy.abstraction.service.QrCodeService;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class QrCodeServiceImplTest {

    @Autowired
    private QrCodeService qrCodeService;

    private File CORRECT_QR_CODE = new File(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("qr/correct-qr-code.png")).getFile());
    private File INCORRECT_QR_CODE = new File(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("qr/incorrect-qr-code.png")).getFile());

    @Before
    public void setUp() {
    }

    /**
     * На вход подается валидное изображение с QR кодом
     * О.Р. строка с данными о покупке
     * FYI:
     * t=20201115T110600 — дата и время, указанные на чеке;
     * s=1226.00 — стоимость покупки;
     * fn=9289440300714186 — фискальный номер;
     * i=140396 — фискальный документ;
     * fp=766295339 — фискальный признак документа.
     */
    @Test
    public void decodeCorrectQrCode() throws FormatException, ChecksumException, IOException {
        //given
        String qrCodeInfo = qrCodeService.decodeQRCode(CORRECT_QR_CODE);
        //when
        String expectedResult = "t=20201115T110600&s=1226.00&fn=9289440300714186&i=140396&fp=766295339&n=1";
        //then
        assertEquals(expectedResult, qrCodeInfo);
    }

    /**
     * На вход подается невалидное изображение с QR кодом
     * О.Р. после выполнения метода возвращаетя null
     */
    @Test
    public void decodeIncorrectQrCode() throws FormatException, ChecksumException, IOException {
        //given
        String qrCodeInfo = qrCodeService.decodeQRCode(INCORRECT_QR_CODE);
        //when
        String expectedResult = null;
        //then
        assertEquals(expectedResult, qrCodeInfo);
    }
}
