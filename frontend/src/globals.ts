// import { backendFetch, handleResize, redirect } from '@/globals.ts';

export function backendFetch(url: string): Promise<string> {
    return new Promise((resolve, reject) => {
        const ajax = new XMLHttpRequest();
        ajax.open('GET', BACKEND_URL + url);
        ajax.responseType = 'text';
        ajax.onload = () => {
            if (ajax.status === 200) {
                resolve(ajax.responseText);
            } else {
                reject(new Error(`Error fetching: ${url}`));
            }
        };
        ajax.onerror = () => {
            reject(new Error(`Error fetching: ${url}`));
        };
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
