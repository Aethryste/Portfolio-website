<!--NOTE: VueJS turn static ThreeJs stuff into reactive parts, which break ThreeJs.-->
<!--TODO: Find fix to the above.-->

<template>
  <div class="container">
    <p style="padding: 3em; opacity: 0.5; color: orange;">[ ThreeJS scene here ]</p>
    <canvas class="webgl"></canvas>
  </div>
</template>

<script>
import * as THREE from 'three';

export default {
  name: 'comp_frontpageThreeJs',
// <!--  data() {-->
// <!--    return {-->
// <!--      // fragmentShader: null,-->
// <!--      // vertexShader: null,-->
//
// <!--      // canvas: document.querySelector("canvas.webgl"),-->
// <!--      // scene: null,-->
// <!--      // clock: null,-->
// <!--      // camera: new THREE.PerspectiveCamera(),-->
// <!--      // renderer: null,-->
// <!--      // // mesh: null,-->
// <!--      //-->
// <!--      // // camera: null,-->
// <!--      // // renderer: null,-->
// <!--      // waterGeometry: new THREE.PlaneGeometry(40, 20, 2000, 1000),-->
// <!--      // waterMaterial: new THREE.ShaderMaterial({-->
// <!--      //   vertexShader:-->
// <!--      //     `-->
// <!--      //       #include <fog_pars_vertex>-->
// <!--      //-->
// <!--      //       uniform float uTime;-->
// <!--      //-->
// <!--      //       uniform float uBigWavesElevation;-->
// <!--      //       uniform vec2 uBigWavesFrequency;-->
// <!--      //       uniform float uBigWaveSpeed;-->
// <!--      //-->
// <!--      //       uniform  float uSmallWavesElevation;-->
// <!--      //       uniform  float uSmallWavesFrequency;-->
// <!--      //       uniform  float uSmallWavesSpeed;-->
// <!--      //       uniform float uSmallWavesIterations;-->
// <!--      //-->
// <!--      //       varying float vElevation;-->
// <!--      //-->
// <!--      //       //Classic Perlin 3D Noise-->
// <!--      //       //by Stefan Gustavson-->
// <!--      //       //-->
// <!--      //       vec4 permute(vec4 x){return mod(((x*34.0)+1.0)*x, 289.0);}-->
// <!--      //       vec4 taylorInvSqrt(vec4 r){return 1.79284291400159 - 0.85373472095314 * r;}-->
// <!--      //       vec3 fade(vec3 t) {return t*t*t*(t*(t*6.0-15.0)+10.0);}-->
// <!--      //-->
// <!--      //       float cnoise(vec3 P) {-->
// <!--      //           vec3 Pi0 = floor(P); // Integer part for indexing-->
// <!--      //           vec3 Pi1 = Pi0 + vec3(1.0); // Integer part + 1-->
// <!--      //           Pi0 = mod(Pi0, 289.0);-->
// <!--      //           Pi1 = mod(Pi1, 289.0);-->
// <!--      //           vec3 Pf0 = fract(P); // Fractional part for interpolation-->
// <!--      //           vec3 Pf1 = Pf0 - vec3(1.0); // Fractional part - 1.0-->
// <!--      //           vec4 ix = vec4(Pi0.x, Pi1.x, Pi0.x, Pi1.x);-->
// <!--      //           vec4 iy = vec4(Pi0.yy, Pi1.yy);-->
// <!--      //           vec4 iz0 = Pi0.zzzz;-->
// <!--      //           vec4 iz1 = Pi1.zzzz;-->
// <!--      //-->
// <!--      //           vec4 ixy = permute(permute(ix) + iy);-->
// <!--      //           vec4 ixy0 = permute(ixy + iz0);-->
// <!--      //           vec4 ixy1 = permute(ixy + iz1);-->
// <!--      //-->
// <!--      //           vec4 gx0 = ixy0 / 7.0;-->
// <!--      //           vec4 gy0 = fract(floor(gx0) / 7.0) - 0.5;-->
// <!--      //           gx0 = fract(gx0);-->
// <!--      //           vec4 gz0 = vec4(0.5) - abs(gx0) - abs(gy0);-->
// <!--      //           vec4 sz0 = step(gz0, vec4(0.0));-->
// <!--      //           gx0 -= sz0 * (step(0.0, gx0) - 0.5);-->
// <!--      //           gy0 -= sz0 * (step(0.0, gy0) - 0.5);-->
// <!--      //-->
// <!--      //           vec4 gx1 = ixy1 / 7.0;-->
// <!--      //           vec4 gy1 = fract(floor(gx1) / 7.0) - 0.5;-->
// <!--      //           gx1 = fract(gx1);-->
// <!--      //           vec4 gz1 = vec4(0.5) - abs(gx1) - abs(gy1);-->
// <!--      //           vec4 sz1 = step(gz1, vec4(0.0));-->
// <!--      //           gx1 -= sz1 * (step(0.0, gx1) - 0.5);-->
// <!--      //           gy1 -= sz1 * (step(0.0, gy1) - 0.5);-->
// <!--      //-->
// <!--      //           vec3 g000 = vec3(gx0.x,gy0.x,gz0.x);-->
// <!--      //           vec3 g100 = vec3(gx0.y,gy0.y,gz0.y);-->
// <!--      //           vec3 g010 = vec3(gx0.z,gy0.z,gz0.z);-->
// <!--      //           vec3 g110 = vec3(gx0.w,gy0.w,gz0.w);-->
// <!--      //           vec3 g001 = vec3(gx1.x,gy1.x,gz1.x);-->
// <!--      //           vec3 g101 = vec3(gx1.y,gy1.y,gz1.y);-->
// <!--      //           vec3 g011 = vec3(gx1.z,gy1.z,gz1.z);-->
// <!--      //           vec3 g111 = vec3(gx1.w,gy1.w,gz1.w);-->
// <!--      //-->
// <!--      //           vec4 norm0 = taylorInvSqrt(vec4(dot(g000, g000), dot(g010, g010), dot(g100, g100), dot(g110, g110)));-->
// <!--      //           g000 *= norm0.x;-->
// <!--      //           g010 *= norm0.y;-->
// <!--      //           g100 *= norm0.z;-->
// <!--      //           g110 *= norm0.w;-->
// <!--      //           vec4 norm1 = taylorInvSqrt(vec4(dot(g001, g001), dot(g011, g011), dot(g101, g101), dot(g111, g111)));-->
// <!--      //           g001 *= norm1.x;-->
// <!--      //           g011 *= norm1.y;-->
// <!--      //           g101 *= norm1.z;-->
// <!--      //           g111 *= norm1.w;-->
// <!--      //-->
// <!--      //           float n000 = dot(g000, Pf0);-->
// <!--      //           float n100 = dot(g100, vec3(Pf1.x, Pf0.yz));-->
// <!--      //           float n010 = dot(g010, vec3(Pf0.x, Pf1.y, Pf0.z));-->
// <!--      //           float n110 = dot(g110, vec3(Pf1.xy, Pf0.z));-->
// <!--      //           float n001 = dot(g001, vec3(Pf0.xy, Pf1.z));-->
// <!--      //           float n101 = dot(g101, vec3(Pf1.x, Pf0.y, Pf1.z));-->
// <!--      //           float n011 = dot(g011, vec3(Pf0.x, Pf1.yz));-->
// <!--      //           float n111 = dot(g111, Pf1);-->
// <!--      //-->
// <!--      //           vec3 fade_xyz = fade(Pf0);-->
// <!--      //           vec4 n_z = mix(vec4(n000, n100, n010, n110), vec4(n001, n101, n011, n111), fade_xyz.z);-->
// <!--      //           vec2 n_yz = mix(n_z.xy, n_z.zw, fade_xyz.y);-->
// <!--      //           float n_xyz = mix(n_yz.x, n_yz.y, fade_xyz.x);-->
// <!--      //           return 2.2 * n_xyz;-->
// <!--      //       }-->
// <!--      //-->
// <!--      //       void main() {-->
// <!--      //           #include <begin_vertex>-->
// <!--      //           #include <project_vertex>-->
// <!--      //           #include <fog_vertex>-->
// <!--      //           vec4 modelPosition = modelMatrix * vec4(position, 1.0);-->
// <!--      //           float elevation =-->
// <!--      //           sin(modelPosition.x * uBigWavesFrequency.x + uTime * uBigWaveSpeed)-->
// <!--      //           * sin(modelPosition.z * uBigWavesFrequency.y + uTime * uBigWaveSpeed)-->
// <!--      //           * uBigWavesElevation;-->
// <!--      //-->
// <!--      //           for(float i = 1.0; i <= 10.0; i++) {-->
// <!--      //               elevation -= abs(-->
// <!--      //               cnoise(-->
// <!--      //                   vec3(modelPosition.xz * uSmallWavesFrequency * i, uTime * uSmallWavesSpeed)-->
// <!--      //               )-->
// <!--      //               * uSmallWavesElevation / i-->
// <!--      //               );-->
// <!--      //             if(i >= uSmallWavesIterations ) {-->
// <!--      //               break;-->
// <!--      //             }-->
// <!--      //           }-->
// <!--      //-->
// <!--      //           modelPosition.y += elevation;-->
// <!--      //           vec4 viewPosition = viewMatrix * modelPosition;-->
// <!--      //           vec4 projectedPosition = projectionMatrix * viewPosition;-->
// <!--      //           gl_Position = projectedPosition;-->
// <!--      //-->
// <!--      //           vElevation = elevation;-->
// <!--      //       }-->
// <!--      //   `,-->
// <!--      //   fragmentShader:-->
// <!--      //     `-->
// <!--      //       #include <fog_pars_fragment>-->
// <!--      //       precision mediump float;-->
// <!--      //       uniform vec3 uDepthColor;-->
// <!--      //       uniform vec3 uSurfaceColor;-->
// <!--      //       uniform float uColorOffset;-->
// <!--      //       uniform float uColorMultiplier;-->
// <!--      //       varying float vElevation;-->
// <!--      //       void main() {-->
// <!--      //         float mixStrength = (vElevation + uColorOffset) * uColorMultiplier;-->
// <!--      //         vec3 color = mix(uDepthColor, uSurfaceColor, mixStrength);-->
// <!--      //         gl_FragColor = vec4(color, 1.0);-->
// <!--      //         #include <fog_fragment>-->
// <!--      //       }-->
// <!--      //     `,-->
// <!--      //   transparent: true,-->
// <!--      //   fog: true,-->
// <!--      //   uniforms: {-->
// <!--      //     uTime: { value: 0 },-->
// <!--      //     uBigWavesElevation: { value: 0.1 },-->
// <!--      //     uBigWavesFrequency: { value: new THREE.Vector2(3, 2) },-->
// <!--      //     uBigWaveSpeed: { value: 1.15 },-->
// <!--      //     uSmallWavesElevation: { value: 0.04 },-->
// <!--      //     uSmallWavesFrequency: { value: 4 },-->
// <!--      //     uSmallWavesSpeed: { value: 0.8 },-->
// <!--      //     uSmallWavesIterations: { value: 6 },-->
// <!--      //     uDepthColor: { value: new THREE.Color(0x53868c) },-->
// <!--      //     uSurfaceColor: { value: new THREE.Color(0xd1e7eb) },-->
// <!--      //     uColorOffset: { value: 0.1 },-->
// <!--      //     uColorMultiplier: { value: 3.5 },-->
// <!--      //     ...THREE.UniformsLib["fog"]-->
// <!--      //   }-->
// <!--      // }),-->
// <!--      // waterObject: new THREE.Mesh(this.waterGeometry, this.waterMaterial)-->
// <!--    }-->
// <!--  },-->
// <!--  methods: {-->
// <!--    init() {-->
// <!--      // https://stackoverflow.com/questions/65693108/threejs-component-working-in-vuejs-2-but-not-3#comment116149963_65693108-->
// <!--      const canvas = document.querySelector("canvas.webgl");-->
// <!--      const scene = new THREE.Scene();-->
// <!--      const camera = new THREE.PerspectiveCamera(75, canvas.innerWidth / canvas.innerHeight, 0.1, 100)-->
//
// <!--      const renderer = new THREE.WebGLRenderer({canvas: canvas});-->
//
// <!--      const geometry = new THREE.BoxGeometry( 1, 1, 1 );-->
// <!--      const material = new THREE.MeshBasicMaterial( { color: 0x00ff00 } );-->
// <!--      const cube = new THREE.Mesh( geometry, material );-->
// <!--      scene.add( cube );-->
//
// <!--      function animate() {-->
// <!--        requestAnimationFrame( animate );-->
//
// <!--        cube.rotation.x += 0.01;-->
// <!--        cube.rotation.y += 0.01;-->
//
// <!--        renderer.render( scene, camera );-->
// <!--      }-->
//
// <!--      animate();-->
//
// <!--      // this.renderer.render(this.scene, this.camera);-->
// <!--    },-->
// <!--    // init: function () {-->
// <!--    //   this.createScene();-->
// <!--    //   this.createCamera();-->
// <!--    //   this.createShape();-->
// <!--    //   // this.camera = new THREE.PerspectiveCamera(75, this.canvas.innerWidth / this.canvas.innerHeight, 0.1, 100)-->
// <!--    //   // this.renderer = new THREE.WebGLRenderer({canvas: this.canvas});-->
// <!--    //   // this.scene.background = new THREE.Color(0x000000);-->
// <!--    //   // this.scene.fog = new THREE.Fog(0x000000, 0, 5);-->
// <!--    //   window.addEventListener('resize', this.onResize())-->
// <!--    // },-->
// <!--    // onResize: function () {-->
// <!--    //   const canvas = document.querySelector("canvas.webgl");-->
// <!--    //   this.renderer.setSize(canvas.innerWidth, canvas.innerHeight);-->
// <!--    //   this.camera.aspect = canvas.innerWidth / canvas.innerHeight;-->
// <!--    //   this.renderer.setPixelRatio(Math.min(canvas.devicePixelRatio, 2));-->
// <!--    //   this.camera.updateProjectionMatrix();-->
// <!--    // },-->
// <!--    // createScene: function () {-->
// <!--    //   let clock = new THREE.Clock();-->
// <!--    //   const scene = new THREE.Scene();-->
// <!--    //   this.clock = clock;-->
// <!--    //   this.scene = scene;-->
// <!--    //-->
// <!--    //   const canvas = document.querySelector("canvas.webgl");-->
// <!--    //   const renderer = new THREE.WebGLRenderer({canvas: canvas});-->
// <!--    //   renderer.setSize(canvas.innerWidth, canvas.innerHeight);-->
// <!--    //   renderer.setPixelRatio(Math.min(canvas.devicePixelRatio, 2));-->
// <!--    //   scene.background = new THREE.Color(0x000000);-->
// <!--    //   // this.scene.background.set(0x000000);-->
// <!--    //   scene.fog = new THREE.Fog(0x000000, 0, 5);-->
// <!--    //   // this.canvas.appendChild(this.renderer.domElement);-->
// <!--    //   this.canvas = canvas;-->
// <!--    //   this.scene = scene;-->
// <!--    //   this.renderer = renderer;-->
// <!--    // },-->
// <!--    // createCamera: function () {-->
// <!--    //   const camera = new THREE.PerspectiveCamera(75, this.canvas.innerWidth / this.canvas.innerHeight, 0.1, 100);-->
// <!--    //   camera.position.set(1, 1, 1);-->
// <!--    //   camera.lookAt(0, 0, 0);-->
// <!--    //   // this.camera = camera;-->
// <!--    //   this.scene.add(camera);-->
// <!--    // },-->
// <!--    // createShape: function () {-->
// <!--    //   const geometry = new THREE.PlaneGeometry(40, 20, 2000, 1000);-->
// <!--    //   const material = new THREE.ShaderMaterial()-->
// <!--    //   const mesh = new THREE.Mesh(geometry, material)-->
// <!--    //   mesh.rotation.x = -Math.PI * 0.5;-->
// <!--    //   this.scene.add(mesh);-->
// <!--    // },-->
// <!--    // animate: function () {-->
// <!--    //   this.renderer.render(this.scene, this.camera);-->
// <!--    //   requestAnimationFrame(this.animate);-->
// <!--    //   // this.waterMaterial.uniforms.uTime.value = this.clock.getElapsedTime();-->
// <!--    //   // this.renderer.render(this.scene, this.camera);-->
// <!--    // }-->
// <!--  },-->
// <!--  mounted () {-->
// <!--    this.init()-->
// <!--    // this.animate();-->
// <!--  }-->
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
