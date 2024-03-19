<script setup lang="ts">
import { TresCanvas } from '@tresjs/core';
import { ref, onMounted } from 'vue';
import { Noise } from 'noisejs';

let columnGap = 0.55;
let rowGap = 0.2;
let gridSize = 4; // 50 in production

let time = ref(0);
let noiseSeed = Math.random();
let noise = new Noise(noiseSeed);
let noiseScale = 0.5;
let pillars = ref([]);

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
    let row = [];
    for (let columnIndex = 0; columnIndex < gridSize; columnIndex++) {
      const position = calculatePillarPosition(rowIndex, columnIndex);
      row.push(position);
    }
    pillars.value.push(row);
  }
}

function calculatePillarPosition(rowIndex, columnIndex) {
  let positionX, positionZ;
  if (rowIndex % 2 === 0) {
    positionX = columnIndex * (0.75 + rowGap);
    positionZ = rowIndex * 1.5 * columnGap;
  } else {
    positionX = (columnIndex + 0.5) * (0.75 + rowGap);
    positionZ = rowIndex * 1.5 * columnGap;
  }
  return { x: positionX, y: 0, z: positionZ };
}

function animateWaves() {
  time.value += 0.01;
  noiseSeed += 0.001;

  for (let rowIndex = 0; rowIndex < gridSize; rowIndex++) {
    for (let columnIndex = 0; columnIndex < gridSize; columnIndex++) {
      const noiseValue = noise.simplex2(rowIndex * 0.1, columnIndex * 0.1, time.value);
      const targetHeightOffset = noiseValue * noiseScale;

      const currentHeightOffset = pillars.value[rowIndex][columnIndex].y;
      const heightOffset = lerp(currentHeightOffset, targetHeightOffset, 0.05);

      pillars.value[rowIndex][columnIndex].y = heightOffset;
    }
  }
}

function lerp(a, b, t) {
  return a * (1 - t) + b * t;
}

function animateLoop() {
  animateWaves();
  requestAnimationFrame(animateLoop);
}

onMounted(() => {
  generatePillars();
  animateLoop();
});

let rawObjGridCenter = calculateGroupCenter();
let correctedGridCenter = [0-rawObjGridCenter.x,0-rawObjGridCenter.y,0-rawObjGridCenter.z];
</script>

<template>
  <TresCanvas clear-color="#242424" window-size>
    <TresPerspectiveCamera ref="camera" :position="[10, 10, 5]" :look-at="[0, 0, 0]"/>
    <TresAmbientLight :intensity="0.1" />
    <TresDirectionalLight :position="[-5, 5, 1]"/>

    <TresGroup ref="object-grid" :position="correctedGridCenter">
      <template v-for="(row, rowIndex) in pillars" :key="rowIndex">
        <template v-for="(pillar, columnIndex) in row" :key="columnIndex">
          <TresMesh :position="[pillar.x, pillar.y, pillar.z]">
            <TresCylinderGeometry :args="[0.5, 0.5, 1, 6]" />
            <TresMeshStandardMaterial :color="0x222222" roughness="0.5" flat-shading />
          </TresMesh>
        </template>
      </template>
    </TresGroup>

  </TresCanvas>
</template>