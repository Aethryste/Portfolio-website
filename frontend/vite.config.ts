import { defineConfig } from 'vite'
import { templateCompilerOptions } from '@tresjs/core'
import vue from '@vitejs/plugin-vue'
import { viteRequire } from 'vite-require'

export default defineConfig({
    server: {
        port: 80,
    },
    plugins: [
        vue({...templateCompilerOptions}),
        viteRequire()
    ],
    define: {
        BACKEND_URL: JSON.stringify('https://www.srv511389.hstgr.cloud')
    }
})
