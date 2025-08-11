package com.academy.demo_ws.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {

        @Bean
        public FirebaseApp firebaseApp() throws IOException {

            InputStream serviceAccount = null;
            try {
                serviceAccount = new ClassPathResource("firebase-adminsdk.json").getInputStream();
                System.out.println("Archivo firebase-adminsdk.json encontrado en el classpath.");
            } catch (IOException e) {
                System.err.println("Error: Archivo firebase-adminsdk.json NO encontrado. Revisa la ubicación.");
                throw e;
            }
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            return FirebaseApp.initializeApp(options);
        }
}
