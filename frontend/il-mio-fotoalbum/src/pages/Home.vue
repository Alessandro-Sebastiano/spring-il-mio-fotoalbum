<template>
    <HeaderComponent @call="getAllFiltered" @reset="getAllPhotos"></HeaderComponent>
    <div class="container-fluid home-container p-5">
        <h1 class="text-center mb-5">Photos</h1>
        <div class="row g-3">
            <CardComponent v-for="(photo, i) in photos" :photo="photo"></CardComponent>
        </div>
    </div>
</template>

<script>
import HeaderComponent from '../components/HeaderComponent.vue';
import CardComponent from '../components/CardComponent.vue';
import { store } from '../store';
import axios from 'axios';
export default {
    name: 'Home',
    components: { HeaderComponent, CardComponent },
    data() {
        return {
            store,
            photos: [],
        }
    },

    mounted() {
        this.getAllPhotos();
    },

    methods: {

        getAllPhotos() {
            axios.get(this.store.apiUrl)
                .then(response => {
                    console.log(response.data)
                    this.photos = response.data;
                });
        },

        getAllFiltered() {

            const data = {
                params: {
                    q: store.title,
                }
            }

            axios.get(this.store.apiUrl, data)
                .then(response => {
                    console.log(response.data)
                    this.photos = response.data;
                });
        }

    }
}
</script>

<style lang="scss" scoped>
.home-container {
    height: calc(100% - 56px);
    overflow-y: auto;
}
</style>