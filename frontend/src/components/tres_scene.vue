<script setup lang="ts">
import { TresCanvas } from '@tresjs/core';
import { ref, onMounted, reactive, defineProps, watch } from 'vue';
import { Noise } from 'noisejs';

// Databind from parent
const parent_props = defineProps({
  isActive: Boolean,
})

// Variables
let gridSize = isMobileDevice() ? 15 : 50;
let fps = isMobileDevice() ? 40 : 60;
let now, then = Date.now(), interval = 1000/fps, delta;

let columnGap = 0.55;
let rowGap = 0.2;
let time = ref(0);
let noiseSeed = Math.random();
let noise = new Noise(noiseSeed);
let noiseScale = 5;
let pillars = reactive({ value: [] as Array<{ x: number; y: number; z: number; }[]> });
let animationId:number|null = null;

// Methods
function isMobileDevice(): boolean {
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

function calculateGroupCenter() {
  let totalX = 0;
  let totalY = 0;
  let totalZ = 0;
  let pillarCount = 0;
  for (let rowIndex = 0; rowIndex < gridSize; rowIndex++) {
    for (let columnIndex = 0; columnIndex < gridSize; columnIndex++) {
      let positionX, positionZ;
      if (rowIndex % 2 === 0) {
        positionX = columnIndex * (0.75 + rowGap);
        positionZ = rowIndex * 1.5 * columnGap;
      } else {
        positionX = (columnIndex + 0.5) * (0.75 + rowGap);
        positionZ = rowIndex * 1.5 * columnGap;
      }
      totalX += positionX;
      totalY += 0;
      totalZ += positionZ;
      pillarCount++;
    }
  }
  return { x: totalX / pillarCount, y: totalY / pillarCount, z: totalZ / pillarCount };
}

function generatePillars() {
  for (let rowIndex = 0; rowIndex < gridSize; rowIndex++) {
    let row: { x: number; y: number; z: number; }[] = [];
    for (let columnIndex = 0; columnIndex < gridSize; columnIndex++) {
      const position = calculatePillarPosition(rowIndex, columnIndex);
      row.push(position);
    }
    pillars.value.push(row);
  }
}

function calculatePillarPosition(rowIndex: number, columnIndex: number) {
  let positionX = columnIndex * (0.75 + rowGap);
  let positionZ = rowIndex * 1.5 * columnGap;
  if (rowIndex % 2 !== 0) {
    positionX += 0.5 * (0.75 + rowGap);
  }
  return { x: positionX, y: 0, z: positionZ };
}

function animateWaves() {
  time.value += 0.01;
  for (let rowIndex = 0; rowIndex < gridSize; rowIndex++) {
    for (let columnIndex = 0; columnIndex < gridSize; columnIndex++) {
      const noiseValue = noise.simplex3(
          rowIndex * 0.1,
          columnIndex * 0.1,
          time.value * 0.1
      );
      const targetHeightOffset = noiseValue * noiseScale;
      const currentHeightOffset = pillars.value[rowIndex][columnIndex].y;
      pillars.value[rowIndex][columnIndex].y = lerp(currentHeightOffset, targetHeightOffset, 0.1);
    }
  }
}

function lerp(a: number, b: number, t: number) {
  return a * (1 - t) + b * t;
}

// function animateLoop() {
//   console.log(parent_props.isActive, 'test');
//   now = Date.now();
//   delta = now - then;
//   console.log(delta, interval)
//   if (delta > interval) {
//     then = now - (delta % interval);
//     animateWaves();
//     animationId = requestAnimationFrame(animateLoop);
//   }
// }

let lastFrameTime = 0;
const maxFPS = isMobileDevice() ? 20 : 60;
let fpsInterval = 1000 / maxFPS;
let lastFPSTime = 0;
let frameCount = 0;

function animateLoop() {
  const currentTime = Date.now();
  const elapsedTime = currentTime - lastFrameTime;

  // Log FPS every second
  if (currentTime > lastFPSTime + 1000) {
    const fps = frameCount;
    console.log(`FPS: ${fps}`);

    lastFPSTime = currentTime;
    frameCount = 0;
  }

  // Limit the frame rate by checking if enough time has passed since the last frame
  if (elapsedTime > fpsInterval) {
    lastFrameTime = currentTime;

    animateWaves();
    frameCount++;

    animationId = requestAnimationFrame(animateLoop);
  } else {
    // If not enough time has passed, wait until the next frame
    animationId = requestAnimationFrame(animateLoop);
  }
}

watch(() => parent_props.isActive, (newVal, oldVal) => {
  if (newVal) {
    if (!animationId) {
      setTimeout(() => {
        animateLoop();
      }, 1000);
    }
  } else {
    if (animationId) {
      cancelAnimationFrame(animationId);
      animationId = null;
    }
  }
})

onMounted(() => {
  generatePillars();
  if (parent_props.isActive) {
    animateLoop();
  }
});

let rawObjGridCenter = calculateGroupCenter();
let correctedGridCenter: [number, number, number] = [(0 - rawObjGridCenter.x), (0 - rawObjGridCenter.y), (0 - rawObjGridCenter.z)];
</script>

<template>
  <TresCanvas preset="realistic" window-size>
    <TresPerspectiveCamera v-if="isMobileDevice()" ref="camera" :position="[8, 8, 5]" :look-at="[0, -2, 0]" :zoom="1.2"/>
    <TresPerspectiveCamera v-else ref="camera" :position="[10, 10, 5]" :look-at="[2, -2, 0]" />
    <TresDirectionalLight :intensity="0.6" :color="0xe3eeff" :position="[-5, 10, 1]" />
    <TresPointLight :intensity="80" :color="0xf03800" :position="[0,-4,0]" />
    <TresPointLight :intensity="20" :color="0xff0000" :position="[5,-4,5]" />
    <TresPointLight :intensity="20" :color="0xff0000" :position="[-5,-4,-5]" />
    <TresGroup ref="object-grid" :position="correctedGridCenter">
      <template v-for="(row, rowIndex) in pillars.value" :key="rowIndex">
        <template v-for="(pillar, columnIndex) in row" :key="columnIndex">
          <TresMesh :position="[pillar.x, pillar.y, pillar.z]" :receive-shadow="true">
            <TresCylinderGeometry :args="[0.5, 0.5, 3, 6]" />
            <TresMeshStandardMaterial :color="0x222222" :roughness="0.8" flat-shading />
          </TresMesh>
        </template>
      </template>
    </TresGroup>
  </TresCanvas>
</template>
