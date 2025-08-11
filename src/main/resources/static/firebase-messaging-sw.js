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
// Escucha el evento de clic en la notificación
self.addEventListener('notificationclick', (event) => {
    // Cierra la notificación
    event.notification.close();

    // Reemplaza 'index.html' con la URL a la que quieras redirigir.
    const urlToOpen = 'http://localhost:8080/news.html';

    event.waitUntil(
        clients.matchAll({
            type: 'window'
        }).then((clientList) => {
            for (const client of clientList) {
                // Si la página ya está abierta, la enfoca y la redirige.
                if (client.url.includes('localhost:8080')) {
                    return client.focus().then(() => client.navigate(urlToOpen));
                }
            }
            // Si la página no está abierta, abre una nueva pestaña y la redirige.
            if (clients.openWindow) {
                return clients.openWindow(urlToOpen);
            }
        })
    );
});
