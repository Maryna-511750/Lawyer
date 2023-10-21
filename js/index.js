document.addEventListener('partialsLoaded', async () => {
    await import('./nav.js');
    await import('./services.js');
});
