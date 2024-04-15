import { Vector3 } from 'three';

export const volumetricLightShader = {
    uniforms: {
        tDiffuse: { value: null },
        lightPosition: { value: new Vector3(0, 0, 0) },
        exposure: { value: 0.005 },
        decay: { value: 0.95 },
        density: { value: 0.95 },
        weight: { value: 0.4 },
        samples: { value: 50 }
    },
    vertexShader: `
    varying vec2 vUv;
    void main() {
      vUv = uv;
      gl_Position = projectionMatrix * modelViewMatrix * vec4(position, 1.0);
    }
  `,
    fragmentShader: `
    varying vec2 vUv;
    uniform sampler2D tDiffuse;
    uniform vec3 lightPosition;
    uniform float exposure;
    uniform float decay;
    uniform float density;
    uniform float weight;
    uniform int samples;
    const int MAX_SAMPLES = 100;
    void main() {
      vec2 uv = vUv;
      vec3 color = vec3(0.0);
      vec2 deltaTextCoord = uv - lightPosition.xy;
      deltaTextCoord *= 1.0 / float(samples) * density;
      vec4 texel = texture2D(tDiffuse, uv);
      for(int i=0; i < MAX_SAMPLES; i++) {
        uv -= deltaTextCoord;
        texel = texture2D(tDiffuse, uv);
        color += texel.rgb * texel.a * weight;
      }
      gl_FragColor = exposure * decay * color;
    }
  `
};
