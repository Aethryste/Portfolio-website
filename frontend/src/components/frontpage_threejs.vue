<template>
  <div class="container">
    <canvas class="webgl"></canvas>
  </div>
</template>

<script>
import * as THREE from 'three';

export default {
  name: 'section_landingPage',
  data() {
    return {
      shaderFetched: false,
      fragmentShader: null,
      vertexShader: null,
      canvas: null,
      clock: new THREE.Clock(),
      scene: new THREE.Scene(),
      camera: null,
      renderer: new THREE.WebGLRenderer({canvas: this.canvas}),
      waterGeometry: new THREE.PlaneGeometry(40, 20, 2000, 1000),
      waterMaterial: new THREE.ShaderMaterial({}),
      // waterMaterial: new THREE.ShaderMaterial({
      //   // check https://stackoverflow.com/questions/48186368/how-to-load-shader-from-external-file-three-fileloader
      //   vertexShader: this.vertexShader,
      //   fragmentShader: this.fragmentShader,
      //   transparent: true,
      //   fog: true,
      //   uniforms: {
      //     uTime: { value: 0 },
      //     uBigWavesElevation: { value: 0.1 },
      //     uBigWavesFrequency: { value: new THREE.Vector2(3, 2) },
      //     uBigWaveSpeed: { value: 1.15 },
      //     uSmallWavesElevation: { value: 0.04 },
      //     uSmallWavesFrequency: { value: 4 },
      //     uSmallWavesSpeed: { value: 0.8 },
      //     uSmallWavesIterations: { value: 6 },
      //     uDepthColor: { value: new THREE.Color(0x53868c) },
      //     uSurfaceColor: { value: new THREE.Color(0xd1e7eb) },
      //     uColorOffset: { value: 0.1 },
      //     uColorMultiplier: { value: 3.5 },
      //     ...THREE.UniformsLib["fog"]
      //   }
      // }),
      waterObject: new THREE.Mesh(this.waterGeometry, this.waterMaterial)
    }
  },
  // created () {
  //   //TODO: I need vue to wait for the fetching to complete here.
  //   if (!(this.shaderFetched)) {
  //     const loader = new THREE.FileLoader();
  //     // console.log(process.env.BASE_URL);
  //     loader.load('./src/shaders/fragmentShader.glsl',function ( data ) {this.fragmentShader =  data;},);
  //     loader.load(process.env.BASE_URL+'../shaders/Vertex.glsl',function ( data ) {this.vertexShader =  data;},);
  //   }
  // },
  mounted () {
    // Properties
    this.canvas = document.querySelector("canvas.webgl");
    this.camera = new THREE.PerspectiveCamera(75, this.canvas.innerWidth / this.canvas.innerHeight, 0.1, 100)
    this.scene.background = new THREE.Color(0x000000);
    this.scene.fog = new THREE.Fog(0x000000, 0, 5);

    this.camera.position.set(1, 1, 1);
    this.camera.lookAt(0, 0, 0);
    this.scene.add(this.camera);

    this.waterObject.rotation.x = -Math.PI * 0.5;
    this.scene.add(this.waterObject);

    this.renderer.setSize(this.canvas.innerWidth, this.canvas.innerHeight);
    this.renderer.setPixelRatio(Math.min(this.canvas.devicePixelRatio, 2));

    // this.waterMaterial.uniforms.fogColor.value.set(0x000000);
    this.scene.background.set(0x000000);

    const tick = () => {
      // this.waterMaterial.uniforms.uTime.value = this.clock.getElapsedTime();
      // this.renderer.render(this.scene, this.camera);
      // this.canvas.requestAnimationFrame(tick);
    };

    tick();

    //TODO: Move event listener to vue watch.
    window.addEventListener("resize", () => {
      this.camera.aspect = window.innerWidth / window.innerHeight;
      this.camera.updateProjectionMatrix();
      this.renderer.setSize(window.innerWidth, window.innerHeight);
      this.renderer.setPixelRatio(Math.min(window.devicePixelRatio, 2));
    });
  }
}

</script>

<style scoped lang="scss">
.container {
  width: calc(100% - 2px);
  height: calc(100% - 2px);
  margin: 0;
  border: 1px solid red;
  overflow: hidden;
  .webgl {
    position: absolute;
    top: 0;
    left: 0;
    outline: none;
  }
}
</style>
