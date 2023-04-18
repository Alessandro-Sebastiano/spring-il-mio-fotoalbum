<script setup>
import { RouterView } from 'vue-router';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
</script>

<template>
  <div class="main-container">
    <router-view></router-view>
  </div>
</template>

<style lang="scss" scoped>
.main-container {
  width: 100%;
  height: 100vh;
  overflow: hidden;
}
</style>
