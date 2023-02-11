package com.gelecex.signerx.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author obetron
 * @date 11.02.2023 - 22:27
 */
@RestController
@RequestMapping("signerx")
public class SignerxRestController {

    @GetMapping("/version")
    public String getVersion() {
        return "v0.0.2";
    }
}
