<script setup lang="ts">
import { TresCanvas } from '@tresjs/core';
import { OrbitControls } from '@tresjs/cientos';

let columnGap = 0.55;
let rowGap = 0.2;
let gridSize = 10;

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

let groupCenter = calculateGroupCenter();
</script>

<template>
  <TresCanvas clear-color="#242424" window-size>
    <TresPerspectiveCamera ref="camera" :position="[10, 10, 10]" :look-at="[0, 0, 0]"/>
    <OrbitControls :camera="camera"/>

    <TresGroup ref="object-grid">
      <template v-for="(row, rowIndex) in gridSize" :key="rowIndex">
        <template v-if="rowIndex % 2 === 0">
          <TresMesh v-for="(column, columnIndex) in gridSize" :key="columnIndex"
                    :position="[columnIndex * (0.75 + rowGap), 0, rowIndex * 1.5 * columnGap]">
            <TresCylinderGeometry :args="[0.5, 0.5, 1, 6]" />
            <TresMeshStandardMaterial :color="0x222222" roughness="0.5" flat-shading/>
          </TresMesh>
        </template>
        <template v-else>
          <TresMesh v-for="(column, columnIndex) in gridSize" :key="columnIndex"
                    :position="[(columnIndex + 0.5) * (0.75 + rowGap), 0, rowIndex * 1.5 * columnGap]">
            <TresCylinderGeometry :args="[0.5, 0.5, 1, 6]" />
            <TresMeshStandardMaterial :color="0x222222" roughness="0.5" flat-shading/>
          </TresMesh>
        </template>
      </template>
    </TresGroup>

    <TresMesh ref:="group-center-marker" :position="[groupCenter.x, groupCenter.y, groupCenter.z]" :scale="[0.1, 5, 0.1]">
      <TresCylinderGeometry :args="[0.5, 0.5, 1, 6]" />
      <TresMeshStandardMaterial :color="0xFF0000" roughness="0.5" flat-shading/>
    </TresMesh>

    <TresAmbientLight :intensity="0.1" />
    <TresDirectionalLight :position="[-5, 5, 1]" />
    <TresAxesHelper/>
  </TresCanvas>
</template>