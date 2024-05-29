export function backendFetch(url: string, retryCount = 0): Promise<string> {
    return new Promise((resolve, reject) => {
        const ajax = new XMLHttpRequest();
        ajax.open('GET', BACKEND_URL + url);
        ajax.responseType = 'text';

        const handleRetry = () => {
            if (retryCount < 5) {
                console.log(`Retry attempt ${retryCount + 1} for ${url}`);
                setTimeout(() => {
                    backendFetch(url, retryCount + 1)
                        .then(resolve)
                        .catch(reject);
                }, 500 * Math.pow(2, retryCount)); // Exponential backoff starting at 500ms
            } else {
                reject(new Error(`Error fetching: ${url}`));
            }
        };

        ajax.onload = () => {
            if (ajax.status === 200) {
                resolve(ajax.responseText);
            } else {
                handleRetry();
            }
        };
        ajax.onerror = handleRetry;
        ajax.send();
    });
}

export function redirect(path: string, newTab: boolean): void {
    if (newTab) {
        window.open(path, '_blank');
    } else {
        window.open(path);
    }
}

export function isMobileDevice(): boolean {
    const userAgent = navigator.userAgent;
    const mobileKeywords = [
        "Android",
        "webOS",
        "iPhone",
        "iPad",
        "iPod",
        "BlackBerry",
        "Windows Phone"
    ];
    return mobileKeywords.some(keyword => userAgent.includes(keyword));
}

export function onWindowResize(callback: () => void): void {
    window.addEventListener('resize', callback);

    // Optionally, you might want to call the callback immediately to get the initial size
    callback();
}

export function offWindowResize(callback: () => void): void {
    window.removeEventListener('resize', callback);
}
