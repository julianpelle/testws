package com.academy.demo_ws.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import java.io.IOException;
import java.io.InputStream;

//Clase que se encarga de inicializar la conexión con Firebase (WebPush)
@Configuration
public class FirebaseConfig {

        @Bean // Indica que el método creará un objeto que Spring gestionará
        public FirebaseApp firebaseApp() throws IOException {

            InputStream serviceAccount = null;
            try { //Busca el archivo firebase para autenticarse, si no está lanza error por archivo no encontrado.
                serviceAccount = new ClassPathResource("firebase-adminsdk.json").getInputStream();
                System.out.println("Archivo firebase-adminsdk.json encontrado en el classpath.");
            } catch (IOException e) {
                System.err.println("Error: Archivo firebase-adminsdk.json NO encontrado.");
                throw e;
            }
            FirebaseOptions options = FirebaseOptions.builder() //Se construye el firebase básicamente
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount)) //Carga las credenciales de firebase_adminsdk.json
                    .build();

            return FirebaseApp.initializeApp(options); //Se inicializa Firebase
        }
}
