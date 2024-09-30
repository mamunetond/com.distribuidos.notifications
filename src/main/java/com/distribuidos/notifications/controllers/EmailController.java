package com.distribuidos.notifications.controllers;

import com.distribuidos.notifications.services.NotifService;


@RestController
@RequestMapping("/api/notifications")
public class EmailController {

   @Autowired
   private NotifService notifService;

   @PostMapping("/send-email")
   public String sendEmail(
   @RequestParam String to,
   @RequestParam String subject,
   @RequestParam String body) {
   notifService.sendEmail(to, subject, body);
   return "Email successfully sent to" + to;
   }
}
    
