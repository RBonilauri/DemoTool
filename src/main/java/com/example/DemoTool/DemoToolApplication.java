package com.example.DemoTool;

import com.example.DemoTool.service.Converter;
import com.example.DemoTool.service.ImportFromAuthenticationService;
import com.example.DemoTool.service.ImportFromTimeService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.ParseException;

@SpringBootApplication
public class DemoToolApplication {

    public static void main(String[] args) throws ParseException {

        ImportFromTimeService importFromTimeService = new ImportFromTimeService();
        ImportFromAuthenticationService importFromAuthenticationService = new ImportFromAuthenticationService();
        Converter converter = new Converter();

        SpringApplication.run(DemoToolApplication.class, args);
        System.out.println("=================================================");
        System.out.println(importFromAuthenticationService.postAuthenticationArguments());
        System.out.println(importFromTimeService.getTimeData().toString());
        System.out.println(converter.convertDateToLong("2006-05-21"));
        System.out.println(converter.convertTimeToLong("02:12"));
        System.out.println(converter.convertLongToStringDate(1148162400000L));
        long d = converter.convertDateToLong("2019-04-17");
        long t = converter.convertTimeToLong("08:50");
        long dt = d + t;
        System.out.println("d = " + d);
        System.out.println("t = " + t);
        System.out.println("d + t = " + dt);
        System.out.println(converter.convertLongToStringDate(dt));
        System.out.println("=================================================");

        // DONE : Relire doc time
        // DONE : Passer le choix de la vitesse en liste déroulante
        // DONE : Mise en forme des données renvoyées par get time : dates lisibles, message explicatif sur les différents temps
        // DONE : Récupération de la date courante du serveur de temps affichée sur la page : ajouter l'heure
        // DONE : Mise à jour du début du temps simulé : permettre également de choisir l'heure
        // TODO : Réorganisation du code
        // DONE : Réorganisation de la page : tout sur une seule page ?
        // DONE : Remettre à zéro la config temps (bloqué par OC-124)
        // TODO : Voir pb internationalisation date
        // TODO : Fast-forward / rewind
    }
}
