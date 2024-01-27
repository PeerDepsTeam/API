package com.peerdeps.peerdepsapi.service.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.peerdeps.peerdepsapi.model.exception.ApiException;
import java.io.ByteArrayInputStream;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import static com.peerdeps.peerdepsapi.model.exception.ApiException.ExceptionType.SERVER_EXCEPTION;

@Configuration
public class FirebaseService {

  private static final Object lock = new Object();
  private static boolean firebaseAppInitialized = false;

  public final String privateKey;

  public FirebaseService(@Value("${firebase.private.key}") String privateKey) {
    this.privateKey = privateKey;
    initializeFirebaseApp();
  }

  private void initializeFirebaseApp() {
    synchronized (lock) {
      if (!firebaseAppInitialized) {
        FirebaseOptions options =
            new FirebaseOptions.Builder().setCredentials(getCredentials()).build();

        FirebaseApp.initializeApp(options);
        firebaseAppInitialized = true;
      }
    }
  }

  @SneakyThrows
  private FirebaseAuth auth() {
    return FirebaseAuth.getInstance();
  }

  @SneakyThrows
  private GoogleCredentials getCredentials() {
    var stream = new ByteArrayInputStream(privateKey.getBytes());
    return GoogleCredentials.fromStream(stream);
  }

  public FirebaseToken getUserByBearer(String bearer) {
    try {
      return auth().verifyIdToken(bearer);
    } catch (FirebaseAuthException e) {
      throw new ApiException(SERVER_EXCEPTION, e.getMessage());
    }
  }
}

