importScripts('https://www.gstatic.com/firebasejs/8.10.1/firebase-app.js');
importScripts('https://www.gstatic.com/firebasejs/8.10.1/firebase-messaging.js');

const firebaseConfig = {
    apiKey: "AIzaSyAu_AY9yvhGoXWA5rXi9H0--n2Qt5IHFUE",
    authDomain: "webpush-af9d0.firebaseapp.com",
    projectId: "webpush-af9d0",
    storageBucket: "webpush-af9d0.firebasestorage.app",
    messagingSenderId: "729290736609",
    appId: "1:729290736609:web:d846bede609acba0a408a0",
    measurementId: "G-D618KM6ZBP"
};


firebase.initializeApp(firebaseConfig);

const messaging = firebase.messaging();

messaging.onBackgroundMessage((payload) => {
    // Si el payload trae notification, dejá que FCM la muestre (no hagas nada)
    if (payload.notification) {
        console.log('[sw] bg message con notification: FCM la muestra solo');
        return;
    }

    // Solo si viene data, la mostrás manual
    const notificationTitle = payload.data?.title ?? 'Notificación';
    const notificationOptions = {
        body: payload.data?.body ?? '',
        icon: '/firebase-logo.png'
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
