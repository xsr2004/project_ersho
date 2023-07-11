package com.ershouserver.ershou_server.controller;

import com.ershouserver.ershou_server.common.Result;
import com.ershouserver.ershou_server.entity.Good;
import com.ershouserver.ershou_server.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/SaleGood")
public class SubmitController {

    @Autowired
    private GoodService GoodService;

    @PostMapping(value = "/submit", consumes = "multipart/*",headers = "content-Type=multipart/form-data")
    public Result HandlerSubmitForm(@RequestParam("uploader") List<MultipartFile> files,Good good){
        Good addFilesGood = GoodService.saveAndTransformFiles(good, files);
        return GoodService.SaveForm(addFilesGood);

    }
}
