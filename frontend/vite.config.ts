import { defineConfig } from 'vite'
import { templateCompilerOptions } from '@tresjs/core'
import vue from '@vitejs/plugin-vue'
import { viteRequire } from 'vite-require'

export default defineConfig({
    server: {
        port: 8080,
    },
    plugins: [
        vue({...templateCompilerOptions}),
        viteRequire()
    ],
    define: {
        BACKEND_URL: JSON.stringify('http://www.srv511389.hstgr.cloud')
    }
})
