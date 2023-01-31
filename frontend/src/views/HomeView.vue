<template>
  <div ref="container"></div>
</template>

<script>
import * as THREE from 'three';

export default {
  name: 'ParticlePlane',
  mounted() {
    const container = this.$refs.container;
    const scene = new THREE.Scene();
    const camera = new THREE.PerspectiveCamera(45, 1, 0.1, 1000);
    camera.position.set(10, 10, 10);
    camera.lookAt(0, 0, 0);
    const renderer = new THREE.WebGLRenderer({ alpha: true });
    renderer.setClearColor(0x000000, 0);
    renderer.setSize(container.offsetWidth, container.offsetHeight);
    container.appendChild(renderer.domElement);

    const geometry = new THREE.BufferGeometry();
    const material = new THREE.PointsMaterial({ color: 0xffffff });

    const positions = [];
    const sizes = [];

    for (let i = 0; i < 10; i++) {
      for (let j = 0; j < 10; j++) {
        positions.push(i - 4.5, 0, j - 4.5);
        sizes.push(0.1);
      }
    }

    geometry.setAttribute('position', new THREE.Float32BufferAttribute(positions, 3));
    geometry.setAttribute('size', new THREE.Float32BufferAttribute(sizes, 1));

    const points = new THREE.Points(geometry, material);
    scene.add(points);

    function animate() {
      requestAnimationFrame(animate);
      renderer.render(scene, camera);
    }

    animate(); // start the animation loop
  }
};

</script>
