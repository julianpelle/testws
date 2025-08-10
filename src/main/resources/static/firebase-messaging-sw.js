importScripts('https://www.gstatic.com/firebasejs/8.10.1/firebase-app.js');
importScripts('https://www.gstatic.com/firebasejs/8.10.1/firebase-messaging.js');

const firebaseConfig = {
        apiKey: "AIzaSyAI1X_qa4kCPwrLxOC1Z0FbWpeMsyFpevQ",
        authDomain: "webpush-8acca.firebaseapp.com",
        projectId: "webpush-8acca",
        storageBucket: "webpush-8acca.firebasestorage.app",
        messagingSenderId: "759868464205",
        appId: "1:759868464205:web:4f1c6da4d699b55a661004",
        measurementId: "G-7E0NRCH0KR"
        };

firebase.initializeApp(firebaseConfig);

const messaging = firebase.messaging();

messaging.onBackgroundMessage((payload) => {
  console.log('[firebase-messaging-sw.js] Received background message ', payload);
  // Customize notification here
  const notificationTitle = payload.notification.title;
  const notificationOptions = {
    body: payload.notification.body,
    icon: '/firebase-logo.png' // Opcional: añade un icono
  };

  self.registration.showNotification(notificationTitle, notificationOptions);
});