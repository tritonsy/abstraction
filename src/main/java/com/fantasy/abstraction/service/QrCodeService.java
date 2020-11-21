package com.fantasy.abstraction.service;

import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;

import java.io.File;
import java.io.IOException;

public interface QrCodeService {
    String decodeQRCode(File file) throws FormatException, ChecksumException, IOException;
}
