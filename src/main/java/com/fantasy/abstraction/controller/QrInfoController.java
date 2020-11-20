package com.fantasy.abstraction.controller;

import com.fantasy.abstraction.entity.QrInfo;
import com.fantasy.abstraction.entity.Views;
import com.fantasy.abstraction.enums.EventType;
import com.fantasy.abstraction.enums.ObjectType;
import com.fantasy.abstraction.repository.QrInfoRepository;
import com.fantasy.abstraction.util.WsSender;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.BiConsumer;

@RestController
@RequestMapping("qr")
public class QrInfoController {
    private final QrInfoRepository qrInfoRepository;
    private final BiConsumer<EventType, QrInfo> wsSender;

    @Autowired
    public QrInfoController(QrInfoRepository messageRepository, WsSender wsSender) {
        this.qrInfoRepository = messageRepository;
        this.wsSender = wsSender.getSender(ObjectType.MESSAGE, Views.IdName.class);
    }

    @GetMapping
    @JsonView(Views.IdName.class)
    public List<QrInfo> list() {
        return qrInfoRepository.findAll();
    }

    @GetMapping("{id}")
    @JsonView(Views.FullMessage.class)
    public QrInfo getById(@PathVariable("id") QrInfo qrInfo) {
        return qrInfo;
    }

    @PostMapping
    public QrInfo create(@RequestBody QrInfo qrInfo) {
        QrInfo updatedQrInfo = qrInfoRepository.save(qrInfo);
        wsSender.accept(EventType.CREATE, updatedQrInfo);
        return updatedQrInfo;
    }

    @PutMapping("{id}")
    public QrInfo update(@PathVariable("id") QrInfo qrInfoFromDb,
                          @RequestBody QrInfo qrInfo) {
        BeanUtils.copyProperties(qrInfo, qrInfoFromDb, "id");
        QrInfo updatedQrInfo = qrInfoRepository.save(qrInfoFromDb);
        wsSender.accept(EventType.UPDATE, updatedQrInfo);
        return updatedQrInfo;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") QrInfo qrInfo) {
        qrInfoRepository.delete(qrInfo);
        wsSender.accept(EventType.REMOVE, qrInfo);
    }
}
