import { onWindowResize, offWindowResize } from './generalUtils';
import { defineComponent } from 'vue';

export default defineComponent({
    data() {
        return {
            windowWidth: window.innerWidth,
            handleResize: null as null | (() => void),
        };
    },
    created() {
        this.handleResize = () => {
            this.windowWidth = window.innerWidth;
        };
        onWindowResize(this.handleResize);
    },
    beforeUnmount() {
        if (this.handleResize) {
            offWindowResize(this.handleResize);
        }
    },
});
