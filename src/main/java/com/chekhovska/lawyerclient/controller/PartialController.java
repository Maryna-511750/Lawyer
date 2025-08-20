package com.chekhovska.lawyerclient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PartialController {

    @GetMapping("/html/index.nav.partial.html")
    public String navPartial() {
        return "html/index.nav.partial";
    }

    @GetMapping("/html/index.header.partial.html")
    public String headerPartial() {
        return "html/index.header.partial";
    }

    @GetMapping("/html/index.about-me.partial.html")
    public String aboutMePartial() {
        return "html/index.about-me.partial";
    }

    @GetMapping("/html/index.services.partial.html")
    public String servicesPartial() {
        return "html/index.services.partial";
    }

    @GetMapping("/html/index.cases.partial.html")
    public String casesPartial() {
        return "html/index.cases.partial";
    }

    @GetMapping("/html/index.contacts.partial.html")
    public String contactsPartial() {
        return "html/index.contacts.partial";
    }
    @GetMapping("/html/index.footer.partial.html")
    public String footerPartial() {
        return "html/index.footer.partial";
    }
}
