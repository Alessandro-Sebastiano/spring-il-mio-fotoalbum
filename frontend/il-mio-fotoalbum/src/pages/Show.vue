<template>
    <HeaderComponent></HeaderComponent>
    <div v-if="error == ''">
        <div v-if="!loading" class="show-container p-5">
            <h1 class="text-center mb-5 text-capitalize">{{ photo.title }} details</h1>
            <div class="row justify-content-center">
                <div class="col-lg-6">
                    <img class="img-fluid" :src="photo.url" :alt="photo.title">
                </div>
                <div class="col-lg-4 p-2 text-capitalize fs-5 fw-bold">
                    <p>{{ photo.description }}</p>
                </div>
            </div>
            <div class=" my-5 d-flex align-items-center justify-content-center" v-if="photo.categories.length != 0">
                <h4 class="me-3">Categories:</h4>
                <span class="text-primary fw-bold" v-for="(c, i) in photo.categories">{{ c.type }}<span
                        v-if="i < photo.categories.length - 1">,&#160</span>
                </span>
            </div>
            <div class="text-center my-5">
                <router-link :to="{ name: 'home' }">
                    <button class="btn btn-primary">Home</button>
                </router-link>
            </div>
        </div>
        <div class="show-container" v-else="loading">Loading</div>
    </div>
    <div class="show-container" v-else="error != ''">{{ error }}</div>
</template>

<script>
import HeaderComponent from '../components/HeaderComponent.vue';
import { store } from '../store';
import axios from 'axios';
export default {
    name: 'Show',
    components: { HeaderComponent },

    data() {
        return {
            store,
            photo: null,
            loading: true,
            error: '',
        }
    },

    mounted() {
        this.getPhotoById(this.$route.params.id);
    },

    methods: {
        getPhotoById(id) {
            console.log(id)
            axios.get(this.store.apiUrl + "/" + id)
                .then(response => {
                    console.log(response.data)
                    if (response.data) {
                        this.photo = response.data;
                    }
                }).catch(() => {
                    this.error = "Not Found";
                }).finally(() => {
                    this.loading = false
                });
        }
    }
}


</script>

<style lang="scss" scoped>
.show-container {
    height: calc(100% - 56px);
    overflow-y: auto;
}
</style>